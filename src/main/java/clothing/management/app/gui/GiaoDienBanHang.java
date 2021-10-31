package clothing.management.app.gui;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GiaoDienBanHang extends JFrame {
    private JPanel pnlSouthTieuDe;
    private JPanel pnlCenterLeft;
    private JPanel pnlCenterRight;
    private JPanel pnlR5;
    private JPanel pnlR6;
    private JPanel pnlR2;
    private JPanel pnlCenter;
    private JPanel pnlBang;
    private DefaultTableModel dtm;
    private JTable tblSanPham;
    private JButton btnCheckSDT;
    private JTextField txtMaNVLHD;
    private JTextField txtDiaChiKH;
    private JPanel pnlDangXuat;
    private JButton btnDangXuat;
    private JPanel pnlR3;
    private JPanel pnlR4;
    private JPanel pnlCenterLeftCN;
    private JPanel pn2BottomLeft;
    private JPanel pnlCenterRightCN;
    private JPanel pnlCenterLeftThem;
    private JComboBox<String> cmbTim;
    private JButton btnTim;
    private JPanel pnlBanHang;
    private JPanel pnlR7;
    private JButton btnData;
    private JLabel lblMKH;
    private JLabel lblHT;
    private JTextField txtMKH;
    private JTextField txtHT;
    private JLabel lblSDT;
    private JTextField txtSDT;
    private JButton btnKiemTraSDT;
    private JLabel lblNam;
    private JRadioButton radGTNam;
    private JLabel lblNu;
    private JRadioButton radGTNu;
    private JLabel lblNS;
    private JTextField txtNS;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JPanel pnlR1;
    private JLabel lblTrong;
    private JLabel lblKhoangCach;
    private JPanel pnlSouth;
    private JPanel pnlChucNang;
    private JPanel pnlTungChucNang;
    private JPanel pnlTongTienHD;
    private JLabel lblTongTienHD;
    private JTextField txtTMHD;
    private JButton btnTKMaHD;
    private JButton btnXoaSP;
    private JButton btnQLKH;
    private JButton btnLHD;
    private JLabel lblKetQuaTTHD;

    public GiaoDienBanHang() {
        this.setTitle("BÁN HÀNG");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
// Bắt đầu vùng South bao gồm: Tiêu đề và chức năng đăng xuất.
        pnlSouthTieuDe = new JPanel();
        pnlDangXuat = new JPanel();
        pnlDangXuat.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlDangXuat.setPreferredSize(new Dimension(95, 40));
        btnDangXuat = new JButton("Đăng Xuất");
        btnDangXuat.addActionListener(evt -> btnDangXuatActionPerformed(evt));
        pnlDangXuat.add(btnDangXuat);
        pnlBanHang = new JPanel();
        pnlBanHang.setPreferredSize(new Dimension(1400, 40));
        JLabel lblHeader = new JLabel("BÁN HÀNG");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 25));
        pnlBanHang.add(lblHeader);
        pnlSouthTieuDe.add(pnlBanHang);
        pnlSouthTieuDe.add(pnlDangXuat);

// Bắt đầu vùng center bao gồm: Logo, thông tin khách hàng.
        pnlCenter = new JPanel();
        pnlCenter.setBorder(BorderFactory.createTitledBorder(""));

        pnlCenterLeft = new JPanel();
        //pnlCenterLeft.setBorder(BorderFactory.createTitledBorder("Logo"));
        pnlCenterLeft.setPreferredSize(new Dimension(750, 300));
        pnlCenterLeft.add(new JLabel(new ImageIcon(new ImageIcon("images/LogoAM2.png").getImage().getScaledInstance(400,
                250, java.awt.Image.SCALE_DEFAULT))));

        pnlCenterRight = new JPanel();
        pnlCenterRight.setBorder(BorderFactory.createTitledBorder("Khách Hàng"));
        pnlCenterRight.setPreferredSize(new Dimension(750, 295));
        pnlCenterRight.setLayout(new GridLayout(10, 1));
//Vùng các textField & label
//	
//***********************************************
        lblMKH = new JLabel("Mã khách hàng");
        lblMKH.setPreferredSize(new Dimension(150, 25));
        txtMKH = new JTextField(50);

        lblHT = new JLabel("Họ tên");
        lblHT.setPreferredSize(new Dimension(150, 25));
        txtHT = new JTextField(50);

        lblTrong = new JLabel("");
        lblTrong.setPreferredSize(new Dimension(150, 25));

        lblKhoangCach = new JLabel("");
        lblKhoangCach.setPreferredSize(new Dimension(50, 25));

        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setPreferredSize(new Dimension(150, 25));
        txtSDT = new JTextField(50);
        Icon icon = new ImageIcon("images/KinhLup.png");
        btnKiemTraSDT = new JButton(icon);

        btnKiemTraSDT.setPreferredSize(new Dimension(30, 20));

        JLabel lblNS = new JLabel("Ngày sinh:");
        lblNS.setPreferredSize(new Dimension(150, 25));
        txtNS = new JTextField(50);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setPreferredSize(new Dimension(150, 25));
        txtEmail = new JTextField(50);
// Tạo các pnl để add từng lbl &txt vào 
        pnlR1 = new JPanel();
//***************************************************************
        pnlR2 = new JPanel();
        pnlR2.setPreferredSize(new Dimension(770, pnlR2.HEIGHT));
        pnlR2.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlR2.add(lblMKH);
        pnlR2.add(txtMKH);
//****************************************************************
        pnlR3 = new JPanel();
        pnlR3.setPreferredSize(new Dimension(770, pnlR3.HEIGHT));
        pnlR3.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlR3.add(lblHT);
        pnlR3.add(txtHT);
//*****************************************************************
        pnlR4 = new JPanel();
        pnlR4.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblNam = new JLabel("Nam");
        radGTNam = new JRadioButton();
        lblNu = new JLabel("Nữ");
        radGTNu = new JRadioButton();
        pnlR4.add(lblTrong);
        pnlR4.add(lblNam);
        pnlR4.add(radGTNam);
        pnlR4.add(lblKhoangCach);
        pnlR4.add(lblNu);
        pnlR4.add(radGTNu);
//****************************************************************
        pnlR5 = new JPanel();
        pnlR5.setPreferredSize(new Dimension(770, pnlR4.HEIGHT));
        pnlR5.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlR5.add(lblSDT);
        pnlR5.add(txtSDT);
        pnlR5.add(btnKiemTraSDT);
//****************************************************************
        pnlR6 = new JPanel();
        pnlR6.setPreferredSize(new Dimension(770, pnlR6.HEIGHT));
        pnlR6.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlR6.add(lblNS);
        pnlR6.add(txtNS);
//****************************************************************
        pnlR7 = new JPanel();
        pnlR7.setPreferredSize(new Dimension(770, pnlR7.HEIGHT));
        pnlR7.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlR7.add(lblEmail);
        pnlR7.add(txtEmail);
//*****************************************************************
        pnlCenterRight.add(pnlR1);
        pnlCenterRight.add(pnlR2);
        pnlCenterRight.add(pnlR3);
        pnlCenterRight.add(pnlR4);
        pnlCenterRight.add(pnlR5);
        pnlCenterRight.add(pnlR6);
        pnlCenterRight.add(pnlR7);
//*****************************************************************
        pnlCenterLeftCN = new JPanel();
        pn2BottomLeft = new JPanel();
        pn2BottomLeft.setPreferredSize(new Dimension(770, 40));
        pnlCenterLeftThem = new JPanel();
        pnlCenterLeftThem.setBorder(BorderFactory.createLoweredBevelBorder());
        cmbTim = new JComboBox<String>();
        cmbTim.setEditable(false);
        cmbTim.addItem("Mã sản phẩm");
        cmbTim.addItem("Nhà cung cấp");
        cmbTim.addItem("Tên sản phẩm");
        btnTim = new JButton("Thêm");
        pnlCenterLeftThem.add(cmbTim);

        pnlCenterLeftThem.add(btnTim);
        pn2BottomLeft.add(pnlCenterLeftThem);
//******************************************************************
        pnlCenterRightCN = new JPanel();
        pnlCenterRightCN.setPreferredSize(new Dimension(770, 40));
//*************************************************************
        pnlCenterLeftCN.add(pn2BottomLeft, BorderLayout.WEST);
        pnlCenterLeftCN.add(pnlCenterRightCN, BorderLayout.EAST);
        pnlCenter.add(pnlCenterLeft, BorderLayout.WEST);
        pnlCenter.add(pnlCenterRight, BorderLayout.EAST);
        pnlCenter.add(pnlCenterLeftCN, BorderLayout.SOUTH);
//**********************************************************************
        pnlSouth = new JPanel();
        pnlSouth.setPreferredSize(new Dimension(1500, 430));
        pnlBang = new JPanel();
        pnlBang.setPreferredSize(new Dimension(1530, 300));
        pnlBang.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        pnlBang.setLayout(new BoxLayout(pnlBang, BoxLayout.PAGE_AXIS));
        String[] cols = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Nhà cung cấp", "Kích cỡ", "Số lượng",
                "Đơn giá", "Thành tiền"};
        dtm = new DefaultTableModel(cols, 0);
        tblSanPham = new JTable(dtm);

        JScrollPane scroll = new JScrollPane(tblSanPham);
        pnlBang.add(scroll);

//**********************************************************************
        pnlTongTienHD = new JPanel();
        lblTongTienHD = new JLabel("Tổng tiền hoá đơn: ");
        pnlTongTienHD.setPreferredSize(new Dimension(1200, 20));
        pnlTongTienHD.setLayout(new FlowLayout(FlowLayout.RIGHT));
        lblKetQuaTTHD = new JLabel("0.0 VND");
        pnlTongTienHD.add(lblTongTienHD);
        pnlTongTienHD.add(lblKetQuaTTHD);

//*******************************************************************
        pnlChucNang = new JPanel();
        pnlChucNang.setPreferredSize(new DimensionUIResource(1500, 100));
        pnlChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));

        pnlTungChucNang = new JPanel();
        pnlTungChucNang.setPreferredSize(new DimensionUIResource(1500, 50));
        pnlTungChucNang.setBorder(BorderFactory.createLoweredBevelBorder());
        txtTMHD = new JTextField(30);
        btnTKMaHD = new JButton("Tìm kiếm mã hoá đơn");
        btnXoaSP = new JButton("Xoá sản phẩm");
        btnQLKH = new JButton("Quản lý khách hàng");
        btnLHD = new JButton("Lập hoá đơn");

        txtTMHD.setPreferredSize(new DimensionUIResource(100, 30));
        btnTKMaHD.setPreferredSize(new DimensionUIResource(200, 30));
        btnXoaSP.setPreferredSize(new DimensionUIResource(150, 30));
        btnQLKH.setPreferredSize(new DimensionUIResource(200, 30));
        btnLHD.setPreferredSize(new DimensionUIResource(150, 30));
        btnLHD.addActionListener(evt -> btnLHDActionPerformed(evt));

        pnlTungChucNang.add(txtTMHD);
        pnlTungChucNang.add(btnTKMaHD);
        pnlTungChucNang.add(btnXoaSP);
        pnlTungChucNang.add(btnQLKH);
        pnlTungChucNang.add(btnLHD);

        pnlChucNang.add(pnlTungChucNang);

        pnlSouth.add(pnlBang, BorderLayout.NORTH);
        pnlSouth.add(pnlTongTienHD, BorderLayout.CENTER);
        pnlSouth.add(pnlChucNang, BorderLayout.SOUTH);
//***************************************************************
        this.add(pnlSouthTieuDe, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);

    }

    private void btnLHDActionPerformed(ActionEvent evt) {
        new GiaoDienHoaDon().setVisible(true);
        setVisible(false);
    }

    private void btnDangXuatActionPerformed(ActionEvent evt) {
        new GiaoDienDieuKhien().setVisible(true);
        setVisible(false);
    }

}
