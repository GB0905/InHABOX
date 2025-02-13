package KYH_201945023;

/***********************
 * Copyright 2022 GaYoNE
************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener, MouseListener, MouseMotionListener, WindowListener {
	private JTextField tfID;
	private JPasswordField tfPW;
	private JButton btnLg;
	private JLabel lblID;
	private JLabel lblPW;
	private String Sid = "";
	private int sid = 0;
	private String who = "";
	private String spw;
	private JLabel llogo;
	private JButton lgExitbtn;
	private int pnx;
	private int pny;
	private StaffForm st;

	public Login(String title) {
		addWindowListener(this);
		setDefaultCloseOperation(Login.DISPOSE_ON_CLOSE);
		setSize(300, 220);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setTitle(title);

		LGtitlebar();
		pn();

		setVisible(true);
		tfID.requestFocus();
	}

	// 상단 바 패널
	private void LGtitlebar() {
		JPanel lgtitlebar = new JPanel();
		lgtitlebar.setLayout(new BorderLayout());
		lgtitlebar.setBackground(new Color(31, 17, 55));
		lgtitlebar.addMouseListener(this);
		lgtitlebar.addMouseMotionListener(this);

		lgExitbtn = new JButton(" X ");
		lgExitbtn.setBackground(Color.red);
		lgExitbtn.setForeground(Color.white);
		lgExitbtn.setFocusPainted(false);
		lgExitbtn.setBorder(new RoundedBorder(5));

		lgExitbtn.addActionListener(this);

		lgtitlebar.add(lgExitbtn, BorderLayout.EAST);

		add(lgtitlebar, BorderLayout.NORTH);
	}

	// 로그인 매인 패널
	public void pn() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(75, 14, 116));

		ImageIcon logo = new ImageIcon("images/logo/레알로고_최신(로그인폼).png");
		ImageIcon iconBtn = new ImageIcon("images/logo/로그인버튼_찐찐막.png");

		llogo = new JLabel(logo);
		jp.add(llogo);
		llogo.setBounds(5, 0, 270, 60);
		lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");
		tfID = new JTextField(15);
		tfPW = new JPasswordField(15);

		new Font("HY중고딕", Font.BOLD, 15);

		jp.add(lblID);
		jp.add(tfID);
		jp.add(lblPW);
		jp.add(tfPW);

		lblID.setForeground(Color.white);
		lblID.setBounds(20, 70, 50, 25);

		lblPW.setBounds(15, 105, 50, 25);
		lblPW.setForeground(Color.white);

		tfID.setBounds(70, 70, 180, 25);
		tfID.addActionListener(this);

		tfPW.setBounds(70, 105, 180, 25);
		tfPW.addActionListener(this);

		btnLg = new JButton(iconBtn);
		btnLg.addActionListener(this);
		jp.add(btnLg);

		btnLg.setBounds(30, 140, 220, 25);
		btnLg.setBorder(new RoundedBorder(10));

		add(jp, BorderLayout.CENTER);

	}
	//로그인 DB
	private class DbLG {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		// 드라이버 주소
		static final String DB_URL = "jdbc:mysql://118.46.199.58:3306/movie";
		static final String USERNAME = "javapj";
		static final String PASSWORD = "1234";

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;

		public void DBlg(int a) throws SQLException {

			System.out.print("User Table 접속 : ");

			try {
				Class.forName(JDBC_DRIVER);
				// class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				// url, id, password 를 입력하여 데이터페이스에 접속
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				stmt = conn.createStatement();

				if (conn != null) {
					System.out.println("성공");
					switch (a) {
					case 1: // 로그인 db조회
						System.out.println("Select문");
						String sql = "Select S_Index, name, Sid, Spw, ps from staff where Sid = '" + Sid
								+ "' and Spw = '" + spw + "'";
						rs = stmt.executeQuery(sql);

						if (rs.next()) {
							sid = rs.getInt(1);
							who = rs.getString(5);
							String name = rs.getString(2);
							JOptionPane.showConfirmDialog(null, name + "님 환영합니다.", "로그인", JOptionPane.DEFAULT_OPTION,
									JOptionPane.INFORMATION_MESSAGE, null);

							StaffForm st = new StaffForm(who, sid);

							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 올바르지 않습니다!", "오류",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					default:

						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "데이터 베이스 접속 오류!\n 관리자에게 문의하세요", "DB 오류",
							JOptionPane.ERROR_MESSAGE);					
				}
			} catch (ClassNotFoundException e) {
				System.out.println("class not found exection");
			} catch (Exception e) {
				System.out.println("sql exception : " + e.getMessage());
			} finally {
				rs.close();
				stmt.close();
				conn.close();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnLg) {
			doLogin();
		} else if (obj == lgExitbtn) {
			dispose();
		} else if (obj == tfID) {
			doLogin();
		} else if (obj == tfPW) {
			doLogin();
		}
	}

	private void doLogin() {
		System.out.println("서버 접속시도...");
		Sid = tfID.getText();
		spw = tfPW.getText();
		if (Sid.equals("") || spw.equals("")) {
			JOptionPane.showMessageDialog(null, "빈칸을 모두 입력해주세요!", "오류", JOptionPane.ERROR_MESSAGE);
		} else {
			DbLG dblg = new DbLG();
			try {
				dblg.DBlg(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(who + sid);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		int y = (int) MouseInfo.getPointerInfo().getLocation().getY();

		this.setLocation((x - pnx), (y - pny));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pnx = e.getX();
		pny = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		if(st != null) {
			dispose();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
