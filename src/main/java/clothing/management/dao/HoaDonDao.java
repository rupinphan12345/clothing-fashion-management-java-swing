package clothing.management.dao;

import clothing.management.entity.ChiTietHoaDon;
import clothing.management.entity.HoaDon;
import clothing.management.entity.KhachHang;
import clothing.management.entity.NhanVien;
import clothing.management.entity.SanPham;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HoaDonDao extends AbstractDao {
    private Gson gson = new Gson();
    private MongoCollection<Document> hoadonCollection;

    public HoaDonDao(MongoClient client) {
        super(client);
        hoadonCollection = db.getCollection("danhsachhoadon");
    }

//	db.danhsachhoadon.aggregate([{$project:{_id:1,giamGia:1,tongTienHoaDon:1,maHoaDon:1,maKhachHang:1,maNhanVien:1,danhSachChiTietHoaDon:1,nam:{$year:"$ngayTao"},thang:{$month:"$ngayTao"},ngay:{$dayOfMonth:"$ngayTao"},gio:{$hour:"$ngayTao"},phut:{$minute:"$ngayTao"},giay:{$second:"$ngayTao"}}}])
	public List<HoaDon> getAllListBill() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		List<HoaDon> hoaDons = new ArrayList<HoaDon>();
		Document doc = Document.parse(
				"{$project:{_id:1,giamGia:1,tongTienHoaDon:1,maHoaDon:1,maKhachHang:1,maNhanVien:1,danhSachChiTietHoaDon:1,nam:{$year:\"$ngayTao\"},thang:{$month:\"$ngayTao\"},ngay:{$dayOfMonth:\"$ngayTao\"},gio:{$hour:\"$ngayTao\"},phut:{$minute:\"$ngayTao\"},giay:{$second:\"$ngayTao\"}}}");

		hoadonCollection.aggregate(Arrays.asList(doc)).subscribe(new Subscriber<Document>() {

			private Subscription s;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(1);
			}

			@SuppressWarnings({ "deprecation", "unchecked" })
			@Override
			public void onNext(Document t) {
				String json = t.toJson();
				HoaDon hoaDon = gson.fromJson(json, HoaDon.class);
//				System.out.println(json);
				Date date = new Date(t.getInteger("nam") - 1900, t.getInteger("thang") - 1, t.getInteger("ngay"),
						t.getInteger("gio"), t.getInteger("phut"), t.getInteger("giay"));
				hoaDon.setNgayTao(date);
//				hoaDon.setMaHoaDon(t.getString("maHoaDon"));
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(t.getString("maKhachHang"));
				hoaDon.setKhachHang(khachHang);
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(t.getString("maNhanVien"));
				hoaDon.setNhanVien(nhanVien);
				List<Document> a = (List<Document>) t.get("danhSachChiTietHoaDon");
				List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<ChiTietHoaDon>();
				a.forEach(s -> {
					SanPham sanPham = new SanPham();
					sanPham.setMaSanPham(s.getString("maSanPham"));
					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
					chiTietHoaDon.setSanPham(sanPham);
					chiTietHoaDon.setSoLuong(s.getInteger("soLuong"));
					chiTietHoaDon.setDonGia(s.getDouble("donGia"));
					ChiTietHoaDon newChiTietHoaDon = new ChiTietHoaDon(chiTietHoaDon.getSanPham(), chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia());
					chiTietHoaDons.add(newChiTietHoaDon);
				});

				hoaDon.setDanhSachChiTietHoaDon(chiTietHoaDons);
				
				try {
					HoaDon newHoaDon = new HoaDon(hoaDon.getMaHoaDon(), hoaDon.getKhachHang(), hoaDon.getNhanVien(), hoaDon.getNgayTao(), hoaDon.getGiamGia(), hoaDon.getDanhSachChiTietHoaDon());
					hoaDons.add(newHoaDon);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				this.s.request(1);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("done!!");
				latch.countDown();
			}
		});
		latch.await();
		return hoaDons;
	}

    public boolean createBill(HoaDon hoaDon) throws InterruptedException, ParseException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean rs = new AtomicBoolean(false);

        String json = gson.toJson(hoaDon);
        Document doc = Document.parse(json);

        Document docKH = (Document) doc.get("khachHang");
        doc.remove("khachHang");
        doc.append("maKhachHang", docKH.getString("maKhachHang"));

        Document docNV = (Document) doc.get("nhanVien");
        doc.remove("nhanVien");
        doc.append("maNhanVien", docNV.getString("maNhanVien"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7"));
        Date date = dateFormat.parse(hoaDon.getNgayTao().toInstant().toString());
        doc.remove("ngayTao");
        doc.append("ngayTao", date);

        List<Document> docDanhSachChiTietHoaDon = new ArrayList<Document>();
        hoaDon.getDanhSachChiTietHoaDon().forEach(item -> {
            String jsonCTHD = gson.toJson(item);
            Document docCTHD = Document.parse(jsonCTHD);
            Document docSP = (Document) docCTHD.get("sanPham");
            docCTHD.append("maSanPham", docSP.getString("maSanPham"));
            docCTHD.remove("sanPham");
            docDanhSachChiTietHoaDon.add(docCTHD);
        });

        doc.remove("danhSachChiTietHoaDon");
        doc.append("danhSachChiTietHoaDon", docDanhSachChiTietHoaDon);

        hoadonCollection
                .insertOne(doc)
                .subscribe(new Subscriber<InsertOneResult>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(InsertOneResult t) {
                        if (t.getInsertedId() != null) {
                            rs.set(true);
                        }
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

    public boolean suaHoaDon(String maHoaDon, HoaDon hoaDon) throws InterruptedException, ParseException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean bl = new AtomicBoolean(true);
        String json = gson.toJson(hoaDon);
        Document doc = Document.parse(json);
        Document docKH = (Document) doc.get("khachHang");
        doc.remove("khachHang");
        doc.append("maKhachHang", docKH.getString("maKhachHang"));

        Document docNV = (Document) doc.get("nhanVien");
        doc.remove("nhanVien");
        doc.append("maNhanVien", docNV.getString("maNhanVien"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7"));
        Date date = dateFormat.parse(hoaDon.getNgayTao().toInstant().toString());
        doc.remove("ngayTao");
        doc.append("ngayTao", date);

        List<Document> docDanhSachChiTietHoaDon = new ArrayList<Document>();
        hoaDon.getDanhSachChiTietHoaDon().forEach(item -> {
            String jsonCTHD = gson.toJson(item);
            Document docCTHD = Document.parse(jsonCTHD);
            Document docSP = (Document) docCTHD.get("sanPham");
            docCTHD.append("maSanPham", docSP.getString("maSanPham"));
            docCTHD.remove("sanPham");
            docDanhSachChiTietHoaDon.add(docCTHD);
        });

        doc.remove("danhSachChiTietHoaDon");
        doc.append("danhSachChiTietHoaDon", docDanhSachChiTietHoaDon);

        hoadonCollection.replaceOne(Filters.eq("maHoaDon", maHoaDon), doc)
                .subscribe(new Subscriber<UpdateResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(UpdateResult t) {
                        if (t.getModifiedCount() > 0) {
                            bl.set(true);
                        }
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
        return bl.get();
    }

    public boolean xoaHoaDon(String criteria, String maKH) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean rs = new AtomicBoolean(false);
        hoadonCollection
                .deleteOne(Filters.eq(criteria, maKH))
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
    
    public long layTongSoHoaDon() throws InterruptedException {
    	
    	CountDownLatch latch = new CountDownLatch(1);
    	AtomicLong rs = new AtomicLong(0);
	
		hoadonCollection
		.find()
		.subscribe(new Subscriber<Document>() {
			
			private Subscription s;
			private long count = 0;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(1);
			}

			@Override
			public void onNext(Document t) {
				count ++;
				rs.set(count);
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
		return rs.get();
    }
    
    
    
    
//  db.danhsachhoadon.aggregate([{ $match: { "maHoaDon": "HD00002" } },
// { $project: { _id: 1, giamGia: 1, tongTienHoaDon: 1, maHoaDon: 1, maKhachHang: 1, maNhanVien: 1, danhSachChiTietHoaDon: 1, nam: { $year: "$ngayTao" }, thang: { $month: "$ngayTao" }, ngay: { $dayOfMonth: "$ngayTao" }, gio: { $hour: "$ngayTao" }, phut: { $minute: "$ngayTao" }, giay: { $second: "$ngayTao" } } }])
	 public List<HoaDon> timHoaDon(String criteria, String info) throws InterruptedException {
	     CountDownLatch latch = new CountDownLatch(1);
	     List<HoaDon> hoaDons = new ArrayList<HoaDon>();
	     Document doc1 = Document.parse("{ $match: { "+"\""+criteria+"\""+": "+"\""+info+"\""+" } }");
	     Document doc2 = Document.parse("{ $project: { _id: 1, giamGia: 1, tongTienHoaDon: 1, maHoaDon: 1, maKhachHang: 1, maNhanVien: 1, danhSachChiTietHoaDon: 1, nam: { $year: \"$ngayTao\" }, thang: { $month: \"$ngayTao\" }, ngay: { $dayOfMonth: \"$ngayTao\" }, gio: { $hour: \"$ngayTao\" }, phut: { $minute: \"$ngayTao\" }, giay: { $second: \"$ngayTao\" } } }");
	     hoadonCollection.aggregate(Arrays.asList(doc1,doc2)).subscribe(new Subscriber<Document>() {
	
	         private Subscription s;
	
	         @Override
	         public void onSubscribe(Subscription s) {
	             this.s = s;
	             this.s.request(1);
	         }
	
	         @SuppressWarnings({ "deprecation", "unchecked" })
				@Override
	         public void onNext(Document t) {
	 			String json = t.toJson();
					HoaDon hoaDon = gson.fromJson(json, HoaDon.class);
	//				System.out.println(json);
					Date date = new Date(t.getInteger("nam") - 1900, t.getInteger("thang") - 1, t.getInteger("ngay"),
							t.getInteger("gio"), t.getInteger("phut"), t.getInteger("giay"));
					hoaDon.setNgayTao(date);
	//				hoaDon.setMaHoaDon(t.getString("maHoaDon"));
					KhachHang khachHang = new KhachHang();
					khachHang.setMaKhachHang(t.getString("maKhachHang"));
					hoaDon.setKhachHang(khachHang);
					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(t.getString("maNhanVien"));
					hoaDon.setNhanVien(nhanVien);
					List<Document> a = (List<Document>) t.get("danhSachChiTietHoaDon");
					List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<ChiTietHoaDon>();
					a.forEach(s -> {
						SanPham sanPham = new SanPham();
						sanPham.setMaSanPham(s.getString("maSanPham"));
						ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
						chiTietHoaDon.setSanPham(sanPham);
						chiTietHoaDon.setSoLuong(s.getInteger("soLuong"));
						chiTietHoaDon.setDonGia(s.getDouble("donGia"));
						ChiTietHoaDon newChiTietHoaDon = new ChiTietHoaDon(chiTietHoaDon.getSanPham(), chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia());
						chiTietHoaDons.add(newChiTietHoaDon);
					});
	
					hoaDon.setDanhSachChiTietHoaDon(chiTietHoaDons);
					try {
						HoaDon newHoaDon = new HoaDon(hoaDon.getMaHoaDon(), hoaDon.getKhachHang(), hoaDon.getNhanVien(), hoaDon.getNgayTao(), hoaDon.getGiamGia(), hoaDon.getDanhSachChiTietHoaDon());
						hoaDons.add(newHoaDon);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.s.request(1);
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
	     return hoaDons;
	
	 }
	 
//db.danhsachhoadon.aggregate([{$match: {$expr: {$eq: ["$maHoaDon", "HD00001"]}}},
//	 							{$unwind: "$danhSachChiTietHoaDon"}, 
//	 							{$group: {_id: "$maHoaDon", soLuong: {$sum: "$danhSachChiTietHoaDon.soLuong"}}}, 
//	 							{$project: {maHoaDon: "$_id", soLuongSanPham: "$soLuong"}}])
	 public int laySoLuongSanPhamTheoMaHoaDon(String maHoaDon) throws InterruptedException {
		 
		 CountDownLatch latch = new CountDownLatch(1);
		 
		 AtomicInteger rs = new AtomicInteger(0);

		 hoadonCollection.aggregate(Arrays.asList(
				 Document.parse("{$match: {$expr: {$eq: [\"$maHoaDon\", \""+maHoaDon+"\"]}}}"),
				 Document.parse("{$unwind: \"$danhSachChiTietHoaDon\"}"),
				 Document.parse("{$group: {_id: \"$maHoaDon\", soLuong: {$sum: \"$danhSachChiTietHoaDon.soLuong\"}}}"),
				 Document.parse("{$project: {maHoaDon: \"$_id\", soLuongSanPham: \"$soLuong\"}}")))
		 .subscribe(new Subscriber<Document>() {
			 private Subscription s;

			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(1);
			}

			@Override
			public void onNext(Document t) {
				rs.set(t.getInteger("soLuongSanPham"));
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
		 
		 return rs.get();
		 
	 }
}
