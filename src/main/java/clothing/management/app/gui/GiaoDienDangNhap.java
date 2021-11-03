package clothing.management.app.gui;

import clothing.management.dao.TaiKhoanDao;
import com.mongodb.reactivestreams.client.MongoClients;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiaoDienDangNhap extends JFrame {

    private JButton btnDangNhap;
    private JButton btnThoat;
    private JLabel lblTaiKhoan;
    private JLabel lblMatKhau;
    private JLabel lblLogo;
    private JPanel pnlChinh;
    private JPanel pnlBot;
    private JPanel pnlTop;
    private JPasswordField txtMatKhau;
    private JTextField txtTaiKhoan;

    private TaiKhoanDao taiKhoanDao;

    public GiaoDienDangNhap() {
        pnlChinh = new JPanel();
        pnlBot = new JPanel();
        btnDangNhap = new JButton();
        lblTaiKhoan = new JLabel();
        lblMatKhau = new JLabel();
        txtTaiKhoan = new JTextField();
        txtMatKhau = new JPasswordField();
        btnThoat = new JButton();
        pnlTop = new JPanel();
        lblLogo = new JLabel();

        setTitle("Đăng Nhập");
        setSize(550, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(false);
        setLocationRelativeTo(null);

        pnlChinh.setAutoscrolls(true);

        lblTaiKhoan.setText("Tài Khoản:");

        lblMatKhau.setText("Mật Khẩu:");

        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });


        //Panel Chính
        GroupLayout jPanel1Layout = new GroupLayout(pnlChinh);
        pnlChinh.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlTop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(pnlBot, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlBot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlChinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlChinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );


        //Panel Botton
        GroupLayout jPanel2Layout = new GroupLayout(pnlBot);
        pnlBot.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(63, 63, 63)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(lblTaiKhoan)
                                                                        .addComponent(lblMatKhau))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtTaiKhoan, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(217, 217, 217)
                                                                .addComponent(btnDangNhap)))
                                                .addGap(0, 137, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnThoat)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(17, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTaiKhoan, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTaiKhoan, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMatKhau))
                                .addGap(18, 18, 18)
                                .addComponent(btnDangNhap, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnThoat))
        );


        //Panel Top
        lblLogo.setIcon(new ImageIcon("images/logo.png"));
        GroupLayout jPanel3Layout = new GroupLayout(pnlTop);
        pnlTop.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        taiKhoanDao = new TaiKhoanDao(MongoClients.create());
    }

    private void btnThoatActionPerformed(ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(pnlChinh, "Bạn có muốn thoát không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == 0) {
            System.exit(0);
        }
    }

    private void btnDangNhapActionPerformed(ActionEvent evt) {
        String tenDangNhap = txtTaiKhoan.getText();
        String matKhau = String.valueOf(txtMatKhau.getPassword());

        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(pnlChinh, "Tên đăng nhập hoặc mật khẩu không hợp lệ");
            txtTaiKhoan.requestFocus();
            return;
        }
        try {
            if (taiKhoanDao.dangNhap(tenDangNhap, matKhau)) {
                setVisible(false);
                new GiaoDienDieuKhien().setVisible(true);
                return;
            }
            JOptionPane.showMessageDialog(pnlChinh, "Sai mật khẩu hoặc tên đăng nhập");
        } catch (InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pnlChinh, "Lỗi");
        }
    }
}
