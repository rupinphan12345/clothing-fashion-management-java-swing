package clothing.management.app.gui;

import javax.swing.*;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import clothing.management.dao.HoaDonDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiaoDienDieuKhien extends JFrame {

    private JButton btnDangXuat;
    private JButton btnBanHang;
    private JButton btnQuanLySanPham;
    private JButton btnQuanLyKhachHang;
    private JButton btnQuanLyNCC;
    private JButton btnQuanLyNhanVien;
    private JButton btnQuanLyHoaDon;
    private JComboBox<String> cboThongKe;
    private JLabel lblMaNhanVien;
    private JLabel lblHoTen;
    private JLabel lblCMND;
    private JLabel lblTongSoHoaDon;
    private JLabel lblDoanhThu;
    private JLabel lblSoTongHoaDon;
    private JLabel lblSoDoanhThu;
    private JPanel pnlChinh;
    private JPanel pnlThongTinNhanVien;
    private JPanel pnlThongKe;
    private JPanel pnlChucNang;
    private JTextField txtMaNhanVien;
    private JTextField txtHoTen;
    private JTextField txtCMND;
    
    private MongoClient client =  MongoClients.create();
    private HoaDonDao hoaDonDao = new HoaDonDao(client);

    public GiaoDienDieuKhien() throws InterruptedException {

        pnlChinh = new JPanel();
        pnlThongTinNhanVien = new JPanel();
        lblMaNhanVien = new JLabel();
        lblHoTen = new JLabel();
        lblCMND = new JLabel();
        txtMaNhanVien = new JTextField();
        txtHoTen = new JTextField();
        txtCMND = new JTextField();
        pnlThongKe = new JPanel();
        lblTongSoHoaDon = new JLabel();
        lblDoanhThu = new JLabel();
        lblSoTongHoaDon = new JLabel();
        lblSoDoanhThu = new JLabel();
        cboThongKe = new JComboBox<>();
        pnlChucNang = new JPanel();
        btnBanHang = new JButton();
        btnQuanLySanPham = new JButton();
        btnQuanLyKhachHang = new JButton();
        btnQuanLyNCC = new JButton();
        btnQuanLyNhanVien = new JButton();
        btnQuanLyHoaDon = new JButton();
        btnDangXuat = new JButton();

        setTitle("Màn Hình Điều Khiển");
        setUndecorated(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Panel Thông Tin Nhân Viên
        pnlThongTinNhanVien.setBorder(BorderFactory.createTitledBorder("Thông Tin Nhân Viên"));

        lblMaNhanVien.setFont(new Font("Tahoma", 0, 36));
        lblMaNhanVien.setText("Mã Nhân Viên:");

        lblHoTen.setFont(new Font("Tahoma", 0, 36));
        lblHoTen.setText("Họ và Tên:");

        lblCMND.setFont(new Font("Tahoma", 0, 36));
        lblCMND.setText("CMND:");

        txtMaNhanVien.setFont(new Font("Tahoma", 0, 36));

        txtHoTen.setFont(new Font("Tahoma", 0, 36));

        txtCMND.setFont(new Font("Tahoma", 0, 36));

        GroupLayout pnlThongTinNhanVienLayout = new GroupLayout(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(pnlThongTinNhanVienLayout);
        pnlThongTinNhanVienLayout.setHorizontalGroup(pnlThongTinNhanVienLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup().addGap(22, 22, 22)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblHoTen).addComponent(lblMaNhanVien).addComponent(lblCMND))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaNhanVien, GroupLayout.PREFERRED_SIZE, 650,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCMND, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE)));
        pnlThongTinNhanVienLayout.setVerticalGroup(pnlThongTinNhanVienLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup().addGap(28, 28, 28)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMaNhanVien).addComponent(txtMaNhanVien, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHoTen))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCMND, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCMND))
                        .addGap(61, 61, 61)));

        // Panel Thống Kê
        pnlThongKe.setBorder(BorderFactory.createTitledBorder("Thống Kê"));

        lblTongSoHoaDon.setFont(new Font("Tahoma", 0, 24));
        lblTongSoHoaDon.setText("Tổng Số Hóa Đơn");

        lblDoanhThu.setFont(new Font("Tahoma", 0, 24));
        lblDoanhThu.setText("Doanh Thu");

        lblSoTongHoaDon.setFont(new Font("Tahoma", 0, 24));
        lblSoTongHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoTongHoaDon.setText( Long.toString(hoaDonDao.layTongSoHoaDon()) + " Đơn");

        lblSoDoanhThu.setFont(new Font("Tahoma", 0, 24));
        lblSoDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoDoanhThu.setText("0 VND");

        cboThongKe.setFont(new Font("Tahoma", 0, 24));
        cboThongKe.setModel(new DefaultComboBoxModel<>(new String[]{"Ngày", "Tháng"}));

        GroupLayout pnlThongKeLayout = new GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlThongKeLayout.createSequentialGroup()
                        .addGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlThongKeLayout.createSequentialGroup().addGap(128, 128, 128)
                                        .addComponent(lblTongSoHoaDon))
                                .addGroup(pnlThongKeLayout.createSequentialGroup().addGap(118, 118, 118).addComponent(
                                        lblSoTongHoaDon, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        pnlThongKeLayout.createSequentialGroup().addComponent(lblDoanhThu).addGap(140,
                                                140, 140))
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        pnlThongKeLayout.createSequentialGroup()
                                                .addComponent(lblSoDoanhThu, GroupLayout.PREFERRED_SIZE, 300,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51))))
                .addGroup(pnlThongKeLayout.createSequentialGroup().addGap(397, 397, 397)
                        .addComponent(cboThongKe, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlThongKeLayout
                .setVerticalGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlThongKeLayout.createSequentialGroup()
                                .addComponent(cboThongKe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDoanhThu).addComponent(lblTongSoHoaDon))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(pnlThongKeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSoTongHoaDon).addComponent(lblSoDoanhThu))
                                .addGap(77, 77, 77)));

        // Panel Chức Năng
        pnlChucNang.setBorder(BorderFactory.createTitledBorder("Chức Năng"));

        btnBanHang.setFont(new Font("Tahoma", 0, 24));
        btnBanHang.setText("Bán Hàng");
        btnBanHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnQuanLySanPham.setFont(new Font("Tahoma", 0, 24));
        btnQuanLySanPham.setText("Quản Lý Sản Phẩm");
        btnQuanLySanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					btnQuanLySanPhamActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnQuanLyKhachHang.setFont(new Font("Tahoma", 0, 24));
        btnQuanLyKhachHang.setText("Quản Lý Khách Hàng");
        btnQuanLyKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					btnQuanLyKhachHangActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnQuanLyNCC.setFont(new Font("Tahoma", 0, 24));
        btnQuanLyNCC.setText("Quản Lý Nhà Cung Cấp");
        btnQuanLyNCC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuanLyNCCActionPerformed(evt);
            }
        });

        btnQuanLyNhanVien.setFont(new Font("Tahoma", 0, 24));
        btnQuanLyNhanVien.setText("Quản Lý Nhân Viên");
        btnQuanLyNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuanLyNhanVienActionPerformed(evt);
            }
        });

        btnQuanLyHoaDon.setFont(new Font("Tahoma", 0, 24));
        btnQuanLyHoaDon.setText("Quản Lý Hóa Đơn");
        btnQuanLyHoaDon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					btnQuanLyHoaDonActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        GroupLayout pnlChucNangLayout = new GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(pnlChucNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlChucNangLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnQuanLyNCC, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnQuanLyKhachHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnQuanLySanPham, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnBanHang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnQuanLyNhanVien, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(btnQuanLyHoaDon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addGap(106, 106, 106)));
        pnlChucNangLayout.setVerticalGroup(pnlChucNangLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlChucNangLayout.createSequentialGroup().addGap(31, 31, 31)
                        .addComponent(btnBanHang, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLySanPham, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLyKhachHang, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLyNCC, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLyNhanVien, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuanLyHoaDon, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        // Panel Chính
        btnDangXuat.setFont(new Font("Tahoma", 0, 18));
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        GroupLayout pnlChinhLayout = new GroupLayout(pnlChinh);
        pnlChinh.setLayout(pnlChinhLayout);
        pnlChinhLayout.setHorizontalGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlChinhLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlThongTinNhanVien, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(pnlThongKe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, 311, Short.MAX_VALUE).addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING,
                        pnlChinhLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnDangXuat)
                                .addGap(18, 18, 18)));
        pnlChinhLayout.setVerticalGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                GroupLayout.Alignment.TRAILING,
                pnlChinhLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDangXuat).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlChinhLayout.createSequentialGroup()
                                        .addComponent(pnlThongTinNhanVien, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlThongKe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnlChucNang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addGap(139, 139, 139)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(pnlChinh,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlChinh, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)));

        setSize(new Dimension(1296, 759));
        setLocationRelativeTo(null);
    }

    private void btnDangXuatActionPerformed(ActionEvent evt) {
        new GiaoDienDangNhap().setVisible(true);
        setVisible(false);
    }

    private void btnQuanLyNCCActionPerformed(ActionEvent evt) {
        new GiaoDienQuanLyNhaCungCap().setVisible(true);
        setVisible(false);
    }

    private void btnBanHangActionPerformed(ActionEvent evt) {
        new GiaoDienBanHang().setVisible(true);
        setVisible(false);
    }

    private void btnQuanLySanPhamActionPerformed(ActionEvent evt) throws InterruptedException {
        new GiaoDienQuanLySanPham().setVisible(true);
        setVisible(false);
    }

    private void btnQuanLyHoaDonActionPerformed(ActionEvent evt) throws InterruptedException {
        new GiaoDienQuanLyHoaDon().setVisible(true);
        setVisible(false);
    }

    private void btnQuanLyNhanVienActionPerformed(ActionEvent evt) {
        new GiaoDienQuanLyNhanVien().setVisible(true);
        setVisible(false);
    }

    private void btnQuanLyKhachHangActionPerformed(ActionEvent evt) throws InterruptedException {
        new GiaoDienQuanLyKhachHang().setVisible(true);
        setVisible(false);
    }

}
