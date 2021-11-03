package clothing.management.dao;

import clothing.management.entity.NhanVien;
import clothing.management.entity.VaiTro;
import com.google.gson.Gson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class NhanVienDao extends AbstractDao {
    public static final Gson gson = new Gson();
    private static final String collectionName = "danhsachnhanvien";
    private MongoCollection<Document> dsNhanVien;

    public NhanVienDao(MongoClient client) {
        super(client);
        dsNhanVien = db.getCollection(collectionName);
    }

    public boolean themNhanVien(NhanVien nhanVien) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean resultSet = new AtomicBoolean(false);

        String jsonString = gson.toJson(nhanVien);
        Document document = Document.parse(jsonString);
        document.put("chucVu", nhanVien.getChucVu().getChucVu());

        if (timNhanVienTheoMa(nhanVien.getMaNhanVien()) != null)
            return false;

        dsNhanVien.insertOne(document).subscribe(new Subscriber<InsertOneResult>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(InsertOneResult insertOneResult) {
                if (insertOneResult.getInsertedId() != null)
                    resultSet.set(true);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        return resultSet.get();
    }

    public boolean xoaNhanVien(String maNhanVien) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean resultSet = new AtomicBoolean(false);

        dsNhanVien.deleteOne(Filters.eq("maNhanVien", maNhanVien)).subscribe(new Subscriber<DeleteResult>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(DeleteResult deleteResult) {
                if (deleteResult.getDeletedCount() > 0)
                    resultSet.set(true);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        return resultSet.get();
    }

    public NhanVien timNhanVienTheoMa(String maNhanVien) throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7"));
        AtomicReference<NhanVien> resultSet = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        dsNhanVien.find(Filters.eq("maNhanVien", maNhanVien)).first().subscribe(new Subscriber<Document>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(Document document) {
                if (document != null) {
                    NhanVien nhanVien = null;
                    nhanVien = new NhanVien(
                            document.getString("maNhanVien"),
                            document.getString("hoTen"),
                            document.getDate("ngaySinh"),
                            document.getBoolean("gioiTinh"),
                            document.getString("email"),
                            document.getString("soDienThoai"),
                            document.getString("CMND"),
                            document.getString("chucVu").equals("Quản Lý") ? VaiTro.QUAN_LY  : VaiTro.NHAN_VIEN
                    );
                    resultSet.set(nhanVien);
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        return resultSet.get();
    }

    public boolean suaNhanVien(String maNhanVien, NhanVien nhanVien) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicBoolean resultSet = new AtomicBoolean(false);

        String jsonString = gson.toJson(nhanVien);
        Document document = Document.parse(jsonString);

        dsNhanVien.replaceOne(Filters.eq("maNhanVien", maNhanVien), document).subscribe(new Subscriber<UpdateResult>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(UpdateResult updateResult) {
                if (updateResult.getModifiedCount() > 0)
                    resultSet.set(true);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                onComplete();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        return resultSet.get();
    }
}
