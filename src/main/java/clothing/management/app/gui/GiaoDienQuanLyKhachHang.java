package clothing.management.app.gui;

import javax.swing.*;
import java.util.List;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import clothing.management.dao.KhachHangDao;
import clothing.management.entity.KhachHang;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class GiaoDienQuanLyKhachHang extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnBack;
	private JPanel pn1;
	private JPanel head;
	private JPanel pn2;
	private DefaultTableModel dtm;
	private JTable table;
	private JComboBox<String> cboFind;
	private JPanel pn3;
	private JPanel pn3BL;
	private JTextField txtFind;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnPrint;
	private MongoClient client = MongoClients.create();
	private KhachHangDao khachHangDao = new KhachHangDao(client);

	public GiaoDienQuanLyKhachHang() throws InterruptedException {
		this.setTitle("Giao Diện Quản Lý Khách Hàng");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//setUndecorated(true);

		JPanel header;

		// Phần North
		pn1 = new JPanel();
		head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.setPreferredSize(new Dimension(150, 50));
		btnBack = new JButton("Quay lại");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnBackActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		head.add(btnBack);
		header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.CENTER));
		header.setPreferredSize(new Dimension(1350, 90));
		JLabel lblHeader = new JLabel("Quản Lý Khách Hàng");
		lblHeader.setFont(new Font("Arial", Font.BOLD, 70));
		header.add(lblHeader);
		pn1.add(header);
		pn1.add(head);

// code  table

		pn2 = new JPanel();
		pn2.setBorder(BorderFactory.createTitledBorder("Danh sách Nhân Viên"));

//get table  fullScreen
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.PAGE_AXIS));

		String[] cols = { "Mã nhân viên", "Họ tên", "Số điện thoại", "Giới tính", "Email", "Ngày sinh" };
		dtm = new DefaultTableModel(cols, 0);
		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);

		List<KhachHang> DSKhachHang = khachHangDao.layDanhSachKhachHang();
		System.out.println(DSKhachHang);
		for (KhachHang khachHang : DSKhachHang) {
			dtm.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
					khachHang.isGioiTinh(), khachHang.getEmail(), khachHang.getNgaySinh()

			});
		}
//setsize for table
		scroll.setPreferredSize(new DimensionUIResource(1400, 600));
		pn2.add(scroll);

// code function
		pn3 = new JPanel();
		pn3.setPreferredSize(new Dimension(1500, 100));
		pn3.setBorder(BorderFactory.createTitledBorder("các chức năng"));
		pn3BL = new JPanel();
		pn3BL.setPreferredSize(new DimensionUIResource(1400, 50));
		pn3BL.setBorder(BorderFactory.createLoweredBevelBorder());
		cboFind = new JComboBox<String>();
		cboFind.setEditable(false);
		cboFind.addItem("Chọn tiêu chí cần tìm");
		cboFind.addItem("Tìm theo mã khách hàng");
		cboFind.addItem("Tìm theo tên khách hàng");
		cboFind.addItem("Tìm theo số điện thoại");
		txtFind = new JTextField(20);
		JLabel lbFind = new JLabel("Tìm Kiếm theo:");
		btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnSearchActionPerformed(evt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdate = new JButton("Cập Nhật");

		btnDelete = new JButton("Xoá");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
		});

		btnPrint = new JButton("Kết Xuất");
		
		pn3BL.add(txtFind);
		pn3BL.add(lbFind);
		pn3BL.add(cboFind);

		lbFind.setPreferredSize(new DimensionUIResource(100, 40));
		txtFind.setPreferredSize(new DimensionUIResource(100, 40));
		cboFind.setPreferredSize(new DimensionUIResource(150, 40));
		btnSearch.setPreferredSize(new DimensionUIResource(100, 40));
		btnUpdate.setPreferredSize(new DimensionUIResource(150, 40));
		btnDelete.setPreferredSize(new DimensionUIResource(100, 40));
		btnPrint.setPreferredSize(new DimensionUIResource(100, 40));
		pn3BL.add(btnSearch);
		pn3BL.add(btnUpdate);
		pn3BL.add(btnDelete);
		pn3BL.add(btnPrint);

		pn3.add(pn3BL);

		this.add(pn1, BorderLayout.NORTH);
		this.add(pn2, BorderLayout.CENTER);
		this.add(pn3, BorderLayout.SOUTH);
	}

	private void btnBackActionPerformed(ActionEvent evt) throws InterruptedException {
		new GiaoDienDieuKhien().setVisible(true);
		setVisible(false);
	}

	private void btnDeleteActionPerformed(ActionEvent evt) {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá");
		} else {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá khách hàng này không!", "Cảnh Báo",
					JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
				try {
					khachHangDao.xoaKhachHang(table.getValueAt(row, 0).toString());
					dtm.removeRow(row);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	private void btnSearchActionPerformed(ActionEvent evt) throws InterruptedException {
		int select = cboFind.getSelectedIndex();
		switch (select) {

		case 0: {
			List<KhachHang> DSKhachHang = khachHangDao.layDanhSachKhachHang();
			dtm.setRowCount(0);
			for (KhachHang khachHang : DSKhachHang) {
				dtm.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
						khachHang.isGioiTinh(), khachHang.getEmail(), khachHang.getNgaySinh()

				});
			}

			break;
		}

		case 1: {
			String maKhachHang = txtFind.getText();

			List<KhachHang> DSKhachHang = khachHangDao.timKhachHangTheoMa(maKhachHang);
			dtm.setRowCount(0);

			for (KhachHang khachHang : DSKhachHang) {

				dtm.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
						khachHang.isGioiTinh(), khachHang.getEmail(), khachHang.getNgaySinh() });
			}

			break;

		}

		case 2: {
			String tenKhachHang = txtFind.getText();

			List<KhachHang> DSKhachHang = khachHangDao.timKhachHangTheoTen(tenKhachHang);
			dtm.setRowCount(0);

			for (KhachHang khachHang : DSKhachHang) {

				dtm.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
						khachHang.isGioiTinh(), khachHang.getEmail(), khachHang.getNgaySinh() });
			}
			break;
		}

		case 3: {

			String SDTKhachHang = txtFind.getText();

			List<KhachHang> DSKhachHang = khachHangDao.timKhachHangTheoSDT(SDTKhachHang);
			dtm.setRowCount(0);

			for (KhachHang khachHang : DSKhachHang) {

				dtm.addRow(new Object[] { khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getSoDienThoai(),
						khachHang.isGioiTinh(), khachHang.getEmail(), khachHang.getNgaySinh() });

			}

			break;
		}
		}
	}

}