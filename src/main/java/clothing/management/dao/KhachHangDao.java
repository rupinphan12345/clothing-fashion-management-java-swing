package clothing.management.dao;

import clothing.management.entity.KhachHang;
import com.google.gson.Gson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class KhachHangDao extends AbstractDao {
    private Gson GSON = new Gson();
    private MongoCollection<Document> khachHangCollection;

    public KhachHangDao(MongoClient client) {
        super(client);
        khachHangCollection = db.getCollection("danhsachkhachhang");
    }

    public boolean themKhachHang(KhachHang khachHang) throws InterruptedException, ParseException {
        CountDownLatch latch = new CountDownLatch(1);

        AtomicBoolean rs = new AtomicBoolean(false);

        String json = GSON.toJson(khachHang);
        Document doc = Document.parse(json);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date admissiondate = dateFormat.parse(khachHang.getNgaySinh().toInstant().toString());
        doc.remove("ngaySinh");
        doc.append("ngaySinh", admissiondate);

        Publisher<InsertOneResult> pub = khachHangCollection.insertOne(doc);
        Subscriber<InsertOneResult> sub = new Subscriber<InsertOneResult>() {


            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(InsertOneResult t) {
                if (t.getInsertedId() != null)
                    rs.set(true);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        };

        pub.subscribe(sub);
        latch.await();

        return rs.get();

    }

    //	db.danhsachkhachhang.aggregate([{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:"$ngaySinh"},thang:{$month:"$ngaySinh"},ngay:{$dayOfMonth:"$ngaySinh"}}}])
    public List<KhachHang> layDanhSachKhachHang() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<KhachHang> dSKhachHang = new ArrayList<>();
        Document doc = Document.parse("{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:\"$ngaySinh\"},thang:{$month:\"$ngaySinh\"},ngay:{$dayOfMonth:\"$ngaySinh\"}}}");

        khachHangCollection
                .aggregate(Arrays.asList(doc)).subscribe(new Subscriber<Document>() {
                    private Subscription s;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        this.s.request(1);
                    }

                    @Override
                    public void onNext(Document t) {
//					Date date = t.getDate("ngaySinh");
//					Date a = t.getDate("ngaySinh");
//					// Conversion
//					SimpleDateFormat sdf;
//					sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//					sdf.setTimeZone(TimeZone.getTimeZone("CET"));
//					String text = sdf.format(a);
                        t.remove("ngaySinh");
                        String json = t.toJson();
                        KhachHang khachHang = GSON.fromJson(json, KhachHang.class);
                        Date date1 = new Date(t.getInteger("nam") - 1900, t.getInteger("thang"), t.getInteger("ngay") + 1, 1 - 10, 40, 30);
                        khachHang.setNgaySinh(date1);
                        dSKhachHang.add(khachHang);
                        this.s.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                        System.out.println("done!!!");
                    }
                });

        latch.await();
        return dSKhachHang;
    }

    private void createIndex() {

        CountDownLatch latch = new CountDownLatch(1);

        Publisher<String> publisher = khachHangCollection.createIndex(Document.parse("{hoTen: \"text\", soDienThoai : \"text\"}"));

        Subscriber<String> subscriber = new Subscriber<String>() {
            public void onSubscribe(Subscription s) {
                s.request(1);

            }

            public void onNext(String t) {
                System.out.println("Index name: " + t);

            }

            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();

            }

            public void onComplete() {
                latch.countDown();
            }
        };

        publisher.subscribe(subscriber);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //	db.danhsachkhachhang.createIndex({hoTen: "text"})
//    db.danhsachkhachhang.aggregate([{$match: {$expr: {$eq: ["$maKhachHang", "KH00001"]}}}, 
//    								{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:"$ngaySinh"},thang:{$month:"$ngaySinh"},ngay:{$dayOfMonth:"$ngaySinh"}}}])
    public List<KhachHang> timKhachHangTheoTen(String tuKhoa) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<KhachHang> DSKhachHang = new ArrayList<>();
        createIndex();
        Document doc = Document.parse("{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:\"$ngaySinh\"},thang:{$month:\"$ngaySinh\"},ngay:{$dayOfMonth:\"$ngaySinh\"}}}");

        khachHangCollection
        .aggregate(Arrays.asList(
        		Document.parse("{$match: {$expr: {$eq: [\"$hoTen\", \""+tuKhoa+"\"]}}}"),
        		Document.parse("{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:\"$ngaySinh\"},thang:{$month:\"$ngaySinh\"},ngay:{$dayOfMonth:\"$ngaySinh\"}}}")))
                .subscribe(new Subscriber<Document>() {
                    private Subscription s;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        this.s.request(1);
                    }

                    @Override
                    public void onNext(Document t) {
                    	t.remove("ngaySinh");
                        String json = t.toJson();
                        KhachHang khachHang = GSON.fromJson(json, KhachHang.class);
                        Date date1 = new Date(t.getInteger("nam") - 1900, t.getInteger("thang"), t.getInteger("ngay") + 1, 1 - 10, 40, 30);
                        khachHang.setNgaySinh(date1);
                        DSKhachHang.add(khachHang);
                        this.s.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
        return DSKhachHang;
    }

    //	db.danhsachkhachhang.createIndex({soDienThoai: "text"})
    public List<KhachHang> timKhachHangTheoSDT(String soDienThoai) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<KhachHang> DSKhachHang = new ArrayList<>();
        createIndex();

        khachHangCollection
        .aggregate(Arrays.asList(
        		Document.parse("{$match: {$expr: {$eq: [\"$soDienThoai\", \""+soDienThoai+"\"]}}}"),
        		Document.parse("{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:\"$ngaySinh\"},thang:{$month:\"$ngaySinh\"},ngay:{$dayOfMonth:\"$ngaySinh\"}}}")))
                .subscribe(new Subscriber<Document>() {

                    private Subscription s;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        this.s.request(1);
                    }

                    @Override
                    public void onNext(Document t) {
                    	t.remove("ngaySinh");
                        String json = t.toJson();
                        KhachHang khachHang = GSON.fromJson(json, KhachHang.class);
                        Date date1 = new Date(t.getInteger("nam") - 1900, t.getInteger("thang"), t.getInteger("ngay") + 1, 1 - 10, 40, 30);
                        khachHang.setNgaySinh(date1);
                        DSKhachHang.add(khachHang);
                        this.s.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
        return DSKhachHang;
    }
    
    public List<KhachHang> timKhachHangTheoMa(String maKH) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<KhachHang> DSKhachHang = new ArrayList<>();

        khachHangCollection
        .aggregate(Arrays.asList(
        		Document.parse("{$match: {$expr: {$eq: [\"$maKhachHang\", \""+maKH+"\"]}}}"),
        		Document.parse("{$project:{_id:0,maKhachHang:1,hoTen:1,soDienThoai:1,gioiTinh:1,email:1,nam:{$year:\"$ngaySinh\"},thang:{$month:\"$ngaySinh\"},ngay:{$dayOfMonth:\"$ngaySinh\"}}}")))
                .subscribe(new Subscriber<Document>() {

                    private Subscription s;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        this.s.request(1);
                    }

                    @Override
                    public void onNext(Document t) {
                    	t.remove("ngaySinh");
                        String json = t.toJson();
                        KhachHang khachHang = GSON.fromJson(json, KhachHang.class);
                        Date date1 = new Date(t.getInteger("nam") - 1900, t.getInteger("thang"), t.getInteger("ngay") + 1, 1 - 10, 40, 30);
                        khachHang.setNgaySinh(date1);
                        DSKhachHang.add(khachHang);
                        this.s.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
        return DSKhachHang;
    }

    //dang loi
    public boolean capNhatThongTinKhachHang(String maKH, KhachHang newKhachHang) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        AtomicBoolean rs = new AtomicBoolean(false);

        String json = GSON.toJson(newKhachHang);
        Document doc = Document.parse(json);

        khachHangCollection
                .replaceOne(Filters.eq("maKhachHang", maKH), doc)
                .subscribe(new Subscriber<UpdateResult>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(UpdateResult t) {
                        if (t.getModifiedCount() > 0)
                            rs.set(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
        return rs.get();

    }

    public boolean xoaKhachHang(String maKH) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean rs = new AtomicBoolean(false);
        khachHangCollection
                .deleteOne(Filters.eq("maKhachHang", maKH))
                .subscribe(new Subscriber<DeleteResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);

                    }

                    @Override
                    public void onNext(DeleteResult t) {
                        if (t.getDeletedCount() != 0)
                            rs.set(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        onComplete();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
        return rs.get();
    }
}
