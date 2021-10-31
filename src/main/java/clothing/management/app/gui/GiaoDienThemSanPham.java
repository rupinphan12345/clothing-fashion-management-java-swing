package gui;


import clothing.management.app.gui.GiaoDienQuanLySanPham;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiaoDienThemSanPham extends JFrame {
    private JButton btnQuayLai;
    private JButton btnThemSanPham;
    private JComboBox<String> cboMaLoai;
    private JComboBox<String> cboMaNCC;
    private JLabel lblChinh;
    private JLabel lblMaNCC;
    private JLabel lblTenSanPham;
    private JLabel lblGia;
    private JLabel lblSoLuong;
    private JLabel lblKichCo;
    private JLabel lblThuongHieu;
    private JLabel lblMauSac;
    private JLabel lblHinhAnh;
    private JLabel lblMaLoai;
    private JPanel pnlChinh;
    private JPanel pnlTop;
    private JPanel pnlBot;
    private JTextField txtTenSanPham;
    private JTextField txtGia;
    private JTextField txtSoLuong;
    private JTextField txtKichCo;
    private JTextField txtThuongHieu;
    private JTextField txtMauSac;
    private JTextField txtHinhAnh;

    public GiaoDienThemSanPham() {

        pnlChinh = new JPanel();
        pnlTop = new JPanel();
        lblChinh = new JLabel();
        btnQuayLai = new JButton();
        pnlBot = new JPanel();
        lblTenSanPham = new JLabel();
        lblGia = new JLabel();
        lblSoLuong = new JLabel();
        lblKichCo = new JLabel();
        lblThuongHieu = new JLabel();
        lblMauSac = new JLabel();
        lblHinhAnh = new JLabel();
        lblMaLoai = new JLabel();
        lblMaNCC = new JLabel();
        txtTenSanPham = new JTextField();
        txtGia = new JTextField();
        txtSoLuong = new JTextField();
        txtKichCo = new JTextField();
        txtThuongHieu = new JTextField();
        txtMauSac = new JTextField();
        txtHinhAnh = new JTextField();
        btnThemSanPham = new JButton();
        cboMaLoai = new JComboBox<>();
        cboMaNCC = new JComboBox<>();


        setTitle("Thêm Sản Phẩm");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        pnlTop.setBorder(BorderFactory.createTitledBorder(""));

        lblChinh.setFont(new Font("Tahoma", 0, 48));
        lblChinh.setText("THÊM SẢN PHẨM");

        btnQuayLai.setFont(new Font("Tahoma", 0, 14));
        btnQuayLai.setText("Quay Lại");
        btnQuayLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        GroupLayout pnlTopLayout = new GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(pnlTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        pnlTopLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(btnQuayLai))
                .addGroup(GroupLayout.Alignment.TRAILING,
                        pnlTopLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblChinh).addGap(573, 573, 573)));
        pnlTopLayout.setVerticalGroup(pnlTopLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        pnlTopLayout.createSequentialGroup().addComponent(btnQuayLai).addGap(18, 18, 18)
                                .addComponent(lblChinh).addContainerGap(45, Short.MAX_VALUE)));

        pnlBot.setBorder(BorderFactory.createTitledBorder(""));

        lblTenSanPham.setFont(new Font("Tahoma", 0, 24));
        lblTenSanPham.setText("Tên Sản Phẩm:");

        lblGia.setFont(new Font("Tahoma", 0, 24));
        lblGia.setText("Giá:");

        lblSoLuong.setFont(new Font("Tahoma", 0, 24));
        lblSoLuong.setText("Số Lượng:");

        lblKichCo.setFont(new Font("Tahoma", 0, 24));
        lblKichCo.setText("Kích Cở:");

        lblThuongHieu.setFont(new Font("Tahoma", 0, 24));
        lblThuongHieu.setText("Thương Hiệu:");

        lblMauSac.setFont(new Font("Tahoma", 0, 24));
        lblMauSac.setText("Màu Sắc:");

        lblHinhAnh.setFont(new Font("Tahoma", 0, 24));
        lblHinhAnh.setText("Hình Ảnh:");

        lblMaLoai.setFont(new Font("Tahoma", 0, 24));
        lblMaLoai.setText("Mã Loại:");

        lblMaNCC.setFont(new Font("Tahoma", 0, 24));
        lblMaNCC.setText("Mã Nhà Cup Cấp:");

        txtTenSanPham.setFont(new Font("Tahoma", 0, 24));

        txtGia.setFont(new Font("Tahoma", 0, 24));

        txtSoLuong.setFont(new Font("Tahoma", 0, 24));

        txtKichCo.setFont(new Font("Tahoma", 0, 24));

        txtThuongHieu.setFont(new Font("Tahoma", 0, 24));

        txtMauSac.setFont(new Font("Tahoma", 0, 24));

        txtHinhAnh.setFont(new Font("Tahoma", 0, 24));

        btnThemSanPham.setFont(new Font("Tahoma", 0, 24));
        btnThemSanPham.setText("Thêm Sản Phẩm");
        btnThemSanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        cboMaLoai.setFont(new Font("Tahoma", 0, 24));
        cboMaLoai.setModel(
                new DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cboMaLoai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cboMaLoaiActionPerformed(evt);
            }
        });
        cboMaNCC.setFont(new Font("Tahoma", 0, 24));
        cboMaNCC.setModel(
                new DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cboMaNCC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cboMaNCCActionPerformed(evt);
            }
        });

        GroupLayout pnlBotLayout = new GroupLayout(pnlBot);
        pnlBot.setLayout(pnlBotLayout);
        pnlBotLayout.setHorizontalGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, pnlBotLayout.createSequentialGroup()
                        .addContainerGap(156, Short.MAX_VALUE)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblThuongHieu).addComponent(lblTenSanPham).addComponent(lblGia)
                                .addComponent(lblSoLuong).addComponent(lblKichCo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtThuongHieu)
                                .addComponent(txtTenSanPham, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGia, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKichCo, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(163, 163, 163)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblHinhAnh).addComponent(lblMaLoai).addComponent(lblMauSac)
                                .addComponent(lblMaNCC))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtHinhAnh).addComponent(txtMauSac)
                                .addComponent(cboMaNCC, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboMaLoai, GroupLayout.PREFERRED_SIZE, 350,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(147, 147, 147))
                .addGroup(pnlBotLayout.createSequentialGroup().addGap(647, 647, 647).addComponent(btnThemSanPham)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlBotLayout.setVerticalGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlBotLayout.createSequentialGroup().addGap(41, 41, 41)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTenSanPham).addComponent(lblMauSac)
                                .addComponent(txtTenSanPham, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMauSac, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGia).addComponent(lblHinhAnh)
                                .addComponent(txtGia, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHinhAnh, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSoLuong).addComponent(lblMaLoai)
                                .addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboMaLoai, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblKichCo).addComponent(lblMaNCC)
                                .addComponent(txtKichCo, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboMaNCC, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(pnlBotLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblThuongHieu).addComponent(txtThuongHieu,
                                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(btnThemSanPham, GroupLayout.PREFERRED_SIZE, 49,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)));

        GroupLayout pnlChinhLayout = new GroupLayout(pnlChinh);
        pnlChinh.setLayout(pnlChinhLayout);
        pnlChinhLayout.setHorizontalGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlChinhLayout.createSequentialGroup().addContainerGap()
                        .addGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pnlBot, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlTop, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        pnlChinhLayout.setVerticalGroup(pnlChinhLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(pnlChinhLayout.createSequentialGroup().addContainerGap()
                        .addComponent(pnlTop, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBot, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                pnlChinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                pnlChinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));


    }

    private void btnQuayLaiActionPerformed(ActionEvent evt) {
        new GiaoDienQuanLySanPham().setVisible(true);
        setVisible(false);
    }

    private void btnThemSanPhamActionPerformed(ActionEvent evt) {

    }

    private void cboMaNCCActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cboMaLoaiActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }


}
