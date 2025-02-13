package KYH_201945023;

/***********************
 * Copyright 2022 GaYoNE
************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Mvedit extends JFrame implements ActionListener {

	private JLabel namelbl;
	private JLabel agelbl;
	private JLabel typelbl;
	private JLabel whenlbl;
	private JLabel imglbl;

	private JButton savebtn;
	private JButton cancelbtn;

	private JTextField tfname;
	private JTextField tfage;
	private JTextField tftype;
	private JDatePickerImpl datePicker;

	private JLabel linklbl;
	private JButton btnpost;
	
	JFileChooser fc = new JFileChooser();
	int aid = 0;
	StaffForm staffFrom;
	stDba db = new stDba();
	String mvname = "";

	public Mvedit(int a, StaffForm staffFomr) {
		aid = a;
		setTitle("영화 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(staffFomr);
		this.staffFrom = staffFomr;

		center();
		below();
		west();

		setVisible(true);
	}
	//이미지 추가 패널
	private void west() {
		JPanel imgpn = new JPanel();
		imgpn.setLayout(new BorderLayout());

		JPanel imgpn1 = new JPanel();
		imgpn1.setBackground(Color.gray);

		imgpn.setPreferredSize(new Dimension(200, 50));
		imglbl = new JLabel("ㅁㄴㅇ");

		btnpost = new JButton("포스터 수정");
		btnpost.addActionListener(this);

		try {
			db.stDBa(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		imgpn1.add(imglbl);
		imgpn.add(imgpn1, BorderLayout.CENTER);
		imgpn.add(btnpost, BorderLayout.SOUTH);
		add(imgpn, BorderLayout.WEST);
	}
	//메인패널
	private void center() {
		JPanel ctpn = new JPanel();
		ctpn.setLayout(new GridLayout(10, 1));
		ctpn.setBackground(new Color(62, 35, 110));
		ctpn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		namelbl = new JLabel("제목");
		namelbl.setForeground(Color.white);
		namelbl.setFont(new Font("맑은 고딕", Font.BOLD,16));
		
		agelbl = new JLabel("연령");
		agelbl.setForeground(Color.white);
		agelbl.setFont(new Font("맑은 고딕", Font.BOLD,16));

		typelbl = new JLabel("장르");
		typelbl.setForeground(Color.white);
		typelbl.setFont(new Font("맑은 고딕", Font.BOLD,16));

		whenlbl = new JLabel("개봉일");
		whenlbl.setForeground(Color.white);
		whenlbl.setFont(new Font("맑은 고딕", Font.BOLD,16));

		linklbl = new JLabel("//");

		tfname = new JTextField(".");
		tfname.setBackground(new Color(188, 160, 220));
		tfname.setBorder(null);
		tfage = new JTextField(".");
		tfage.setBackground(new Color(188, 160, 220));
		tfage.setBorder(null);
		tftype = new JTextField(".");
		tftype.setBackground(new Color(188, 160, 220));
		tftype.setBorder(null);

		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBackground(new Color(188, 160, 220));
		datePicker.getJFormattedTextField().setBackground(new Color(188, 160, 220));


		ctpn.add(namelbl);
		ctpn.add(tfname);

		ctpn.add(agelbl);
		ctpn.add(tfage);

		ctpn.add(typelbl);
		ctpn.add(tftype);

		ctpn.add(whenlbl);
		ctpn.add(datePicker);
		
		ctpn.add(linklbl);
		add(ctpn, BorderLayout.CENTER);
	}
	//하단 버튼 패널
	private void below() {
		JPanel bp = new JPanel();
		bp.setBackground(new Color(62, 35, 110));
		savebtn = new JButton("저장");
		savebtn.setBackground(new Color(169,203,215));
		savebtn.setForeground(Color.white);
		savebtn.addActionListener(this);
		savebtn.setFont(new Font("맑은 고딕", Font.BOLD,16));


		cancelbtn = new JButton("취소");
		cancelbtn.setBackground(new Color(222,165,164));
		cancelbtn.setForeground(Color.white);
		cancelbtn.addActionListener(this);
		cancelbtn.setFont(new Font("맑은 고딕", Font.BOLD,16));

		bp.add(savebtn);
		bp.add(cancelbtn);

		add(bp, BorderLayout.SOUTH);
	}
	//db단
	private class stDba {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		// 드라이버 주소
		static final String DB_URL = "jdbc:mysql://118.46.199.58:3306/movie";
		// DB접속
		// localhost 는 접속하려는 데이터베이스 주소를 입력하시면 됩니다.
		// 3306은 데이터베이스에 접속할때 사용하는 포트 번호입니다. 설치할때설정한 것
		// databasename에는 접속하려는 database의 name을 입력해 줍니다.

		static final String USERNAME = "javapj";
		static final String PASSWORD = "1234";

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rsa = null;
		boolean uprs = false;

		public void stDBa(int stState) throws SQLException {
			System.out.print("Movie DB 접속 : ");
			try {
				Class.forName(JDBC_DRIVER);
				// class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				// url, id, password 를 입력하여 데이터페이스에 접속
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				stmt = conn.createStatement();
				String sql = null;

				if (conn != null) {
					System.out.println("접속 성공");

					switch (stState) {
					case 1:
						// 영화 조회
						System.out.println("영화 조회 : ...");
						sql = "Select mvID, mvTitle, mvAge, mvType, mvWhen from movieinfo where mvid = " + aid + ";";
						rsa = stmt.executeQuery(sql);
						if (rsa.next()) {
							tfname.setText(rsa.getString(2));
							mvname = rsa.getString(2);
							tfage.setText(Integer.toString(rsa.getInt(3)));
							tftype.setText(rsa.getString(4));
							datePicker.getJFormattedTextField().setText(rsa.getString(5));
							ImageIcon ic = new ImageIcon("images/poster/" + rsa.getString(2) + ".jpg");
							Image img = ic.getImage();
							Image newimg = img.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
							ImageIcon newic = new ImageIcon(newimg);
							imglbl = new JLabel(newic);

						}
						break;
					case 2:

						sql = "Update movieinfo set mvTitle = '" + tfname.getText() + "', mvAge = '" + tfage.getText()
								+ "', mvType ='" + tftype.getText() + "', mvWhen ='" + datePicker.getJFormattedTextField().getText()
								+ "' where mvid = '" + aid + "';";

						uprs = stmt.execute(sql);
						if (uprs) {
							JOptionPane.showMessageDialog(null, "수정 오류! 다시한번 확인해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "수정 완료!", "수정", JOptionPane.INFORMATION_MESSAGE);

							if (linklbl.getText().equals("//") || linklbl.getText().equals("nullnull")) {
								System.out.println("포스터 변경없음");
								File dirfile = new File("images/poster/" + mvname + ".jpg");
								System.out.println(mvname);
								File srcfile = new File("images/poster/" + tfname.getText() + ".jpg");
//								Files.move(dirfile.toPath(), srcfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
								if(dirfile.renameTo(srcfile)) {
									System.out.println("변경성공");
								}else {
									System.out.println("변경실패");									
								}

							} else {
								File dirfile = new File(linklbl.getText());
								File srcfile = new File("images/poster/" + tfname.getText() + ".jpg");
								Files.copy(dirfile.toPath(), srcfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							}
							staffFrom.resetmd.setNumRows(0);
							staffFrom.stdba.stDBa(1);
							dispose();
						}
						break;
					}

				} else {
					System.out.println("실패");
				}
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				System.out.println("class not found exection");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("sql exception : " + e.getMessage());
			} finally {
				rsa.close();
				stmt.close();
				conn.close();
			}
		}
	}

	public ImageIcon resize(String imgPath) {
		ImageIcon path = new ImageIcon(imgPath);
		Image img = path.getImage();
		Image newimg = img.getScaledInstance(imglbl.getWidth(), imglbl.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimg);

		return image;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == savebtn) {
			if (tfname.getText().equals("") || tftype.getText().equals("") || tfage.getText().equals("")
					|| datePicker.getJFormattedTextField().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 빈칸을 채워주세요!", "저장 오류", JOptionPane.ERROR_MESSAGE);

			} else {
				// 저장 이벤트처리 및 경로 저장
				try {
					db.stDBa(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (obj == cancelbtn) {
			dispose();
		} else if (obj == btnpost) {
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));
			int rs = fc.showOpenDialog(this);

			if (rs == JFileChooser.APPROVE_OPTION) {

				linklbl.setText(fc.getSelectedFile().getAbsolutePath());
				File selfile = fc.getSelectedFile();
				String path = selfile.getAbsolutePath();
				imglbl.setSize(200, 300);
				imglbl.setIcon(resize(path));
			}

		}
	}
}
