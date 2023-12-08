package KYH_201945023;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.Sides;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.protocol.Message;
import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

public class addSt extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	private JTextField tfID;
	private JPasswordField tfPW;

	private JLabel lblID;
	private JLabel lblPW;
	private JLabel FindID;
	private int sid;

	private JLabel llogo;
	private JButton lgExitbtn;
	private JLabel lblNm;
	private JTextField tfNm;
	private JLabel lbltp;
	private JComboBox dptp;
	private JButton btnOk;
	private JButton btnexit;

	private String sthead[] = { "사번", "이름", "직책", "ID", "PW" };
	private DefaultTableModel stmodel = new DefaultTableModel(sthead, 0);

	private JTable stTb = new JTable(stmodel);
	private JScrollPane stsr = new JScrollPane(stTb);

	stDb db = new stDb();
	private JRadioButton rdsignup;
	private JRadioButton rdedit;
	private JMenuItem stdel;
	private String rewho = "";
	private JPanel sttitlebarpn;
	private int pnx = 0;
	private int pny = 0;

	public addSt(String who, StaffForm staffForm) {
		rewho = who;
		setDefaultCloseOperation(addSt.DISPOSE_ON_CLOSE);
		setSize(780, 280);
		setLocationRelativeTo(staffForm);
//		setExtendedState(MAXIMIZED_BOTH); 창 최대화
		setUndecorated(true);
		setLayout(new BorderLayout());

		sttitlebar();
		pn();
		table();
//		winEvent();

		setVisible(true);
		tfID.requestFocus();
	}

	private void table() {
		// --------팝업메뉴
		JPopupMenu pmenu = new JPopupMenu();
		stdel = new JMenuItem("삭제하기");
		pmenu.add(stdel);
		stdel.addActionListener(this);

		stsr.setBounds(280, 10, 300, 170);
		stTb.setComponentPopupMenu(pmenu);
		stTb.addMouseListener(this);

		try {
			db.DBlg(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 셀 자동 길이조절
		final TableColumnModel columnModel = stTb.getColumnModel();
		for (int col = 0; col < stTb.getColumnCount(); col++) {
			int wd = 50;
			for (int row = 0; row < stTb.getRowCount(); row++) {
				TableCellRenderer redd = stTb.getCellRenderer(row, col);
				Component comp = stTb.prepareRenderer(redd, row, col);
				wd = Math.max(comp.getPreferredSize().width + 1, wd);
			}
			columnModel.getColumn(col).setPreferredWidth(wd);
		}

		// ------row 컬러 바꾸기
		stTb.setDefaultEditor(Object.class, null);
		stTb.setDefaultRenderer(Object.class, new myColorRenderer());

		// 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = stTb.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		// ------row 컬러 바꾸기
//		stTb.setDefaultRenderer(Object.class, new myColorRenderer());
		stTb.setBackground(new Color(171, 3, 228));
		stTb.setForeground(Color.white);

		// ----multiselect false
		stTb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// -------------j테이블 컬러 바꾸기
		stsr.getViewport().setBackground(new Color(76, 1, 101));

		JTableHeader stTbhd = stTb.getTableHeader();
		stTbhd.setBackground(new Color(76, 1, 101));
		stTbhd.setForeground(Color.white);

		add(stsr, BorderLayout.EAST);
	}

	private void sttitlebar() {
		sttitlebarpn = new JPanel();
		sttitlebarpn.setLayout(new BorderLayout());
		sttitlebarpn.setBackground(new Color(31, 17, 55));
		sttitlebarpn.addMouseMotionListener(this);
		sttitlebarpn.addMouseListener(this);
		
		lgExitbtn = new JButton("X");
		JButton lgExitbtn1 = new JButton("X");

		lgExitbtn.setBackground(Color.red);
		lgExitbtn.setForeground(Color.white);
		lgExitbtn.setFocusPainted(false);

		lgExitbtn.addActionListener(this);

		sttitlebarpn.add(lgExitbtn, BorderLayout.EAST);

		add(sttitlebarpn, BorderLayout.NORTH);
	}

	private void pn() {

		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(76, 1, 101));

		ImageIcon logo = new ImageIcon("images/logo/레알로고_최신(로그인폼).png");
		llogo = new JLabel(logo);
		jp.add(llogo);
		llogo.setBounds(5, 0, 270, 60);

		lblNm = new JLabel("이름");
		lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");
		lbltp = new JLabel("직위");

		ButtonGroup gp = new ButtonGroup();

		// 라이도 버튼
		rdsignup = new JRadioButton("신규");
		rdedit = new JRadioButton("수정");

		gp.add(rdsignup);
		gp.add(rdedit);

		rdsignup.addActionListener(this);
		rdedit.addActionListener(this);

		tfNm = new JTextField(15);
		tfID = new JTextField(15);
		tfPW = new JPasswordField(15);
		String sttp[] = { "점장", "부점장", "매니저", "사원" };
		dptp = new JComboBox<>(sttp);
		dptp.setSelectedItem("매니저");
		new Font("HY중고딕", Font.BOLD, 15);

		jp.add(lblNm);
		jp.add(tfNm);
		jp.add(lblID);
		jp.add(tfID);
		jp.add(lblPW);
		jp.add(tfPW);
		jp.add(lbltp);
		jp.add(dptp);

		jp.add(rdsignup);
		jp.add(rdedit);
		rdsignup.setOpaque(false);
		rdedit.setOpaque(false);
		rdsignup.setForeground(Color.white);
		rdedit.setForeground(Color.white);
		rdsignup.setFont(new Font("굴림", Font.BOLD, 12));
		rdedit.setFont(new Font("굴림", Font.BOLD, 12));

		lblNm.setForeground(Color.white);
		lblNm.setBounds(10, 70, 50, 25);
		lblID.setForeground(Color.white);
		lblID.setBounds(10, 102, 50, 25);
		lblPW.setForeground(Color.white);
		lblPW.setBounds(10, 135, 50, 25);
		tfNm.setBounds(70, 70, 180, 25);
		tfID.setBounds(70, 102, 180, 25);
		tfPW.setBounds(70, 135, 180, 25);
		lbltp.setForeground(Color.white);
		lbltp.setBounds(10, 165, 50, 25);

		rdsignup.setBounds(150, 170, 50, 20);
		rdedit.setBounds(200, 170, 50, 20);

		btnOk = new JButton("신규등록하기");
		btnexit = new JButton("닫기");

		btnOk.setBackground(new Color(171, 3, 228));
		btnOk.setForeground(Color.white);
		btnOk.setFont(new Font("굴림", Font.BOLD, 15));
		jp.add(btnOk);

		btnexit.setBackground(new Color(171, 3, 228));
		btnexit.setForeground(Color.white);
		btnexit.setFont(new Font("굴림", Font.BOLD, 15));
		jp.add(btnexit);

		btnOk.addActionListener(this);
		btnOk.setBounds(20, 200, 130, 25);

		btnexit.addActionListener(this);
		btnexit.setBounds(170, 200, 130, 25);

		dptp.setBounds(70, 165, 80, 25);

		rdsignup.setSelected(true);

		add(jp, BorderLayout.CENTER);
	}

	private class stDb {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		// 드라이버 주소
		static final String DB_URL = "jdbc:mysql://118.46.199.58:3306/movie";
		// DB접속
		// localhost 는 접속하려는 데이터베이스 주소를 입력하시면 됩니다.
		// 3306은 데이터베이스에 접속할때 사용하는 포트 번홍비니다. 설치할때설정한 것
		// databasename에는 접속하려는 database의 name을 입력해 줍니다.
		static final String USERNAME = "javapj";
		static final String PASSWORD = "1234";

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		boolean delrs;
		boolean insrs;
		boolean updrs;

		private void DBlg(int a) throws SQLException {

			System.out.print("User Table 접속 : ");

			try {
				Class.forName(JDBC_DRIVER);
				// class클래스의 forName()함수를 이용해서 해당클래스를메모리로로드
				// url, id, password 를 입력하여 데이터페이스에 접속
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				stmt = conn.createStatement();
				String sql = null;

				if (conn != null) {
					System.out.println("성공");
					switch (a) {
					case 1: // 직원 조회
						System.out.println("Select문");
						sql = "Select * from staff;";
						rs = stmt.executeQuery(sql);

						if (rs.next()) {
							sql = "Select * from staff;";
							rs = stmt.executeQuery(sql);
							while (rs.next()) {
								stmodel.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3),
										rs.getString(4), rs.getString(5) });
							}
						} else {
							JOptionPane.showMessageDialog(null, "직원이 없습니다!", "오류", JOptionPane.ERROR_MESSAGE);
						}
						break;

					case 2: // 사원 삭제
						sql = "Select ps from staff where S_index = " + sid + ";";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							System.out.println(rs.getString(1) + "님");
							if (rs.getString(1).equals("점장") || rs.getString(1).equals("부점장")) {
								JOptionPane.showMessageDialog(null, "본인 혹은 점장(부)은 삭제할 수 없습니다!", "삭제 오류!",
										JOptionPane.ERROR_MESSAGE);
							} else {
								System.out.println("delete문");
								sql = "delete from staff where S_index = " + sid + ";";
								delrs = stmt.execute(sql);

								if (delrs) {
									JOptionPane.showMessageDialog(null, "다시 시도해주세요!", "직원삭제 오류!",
											JOptionPane.ERROR_MESSAGE);
								} else {
									System.out.println("성공");
									DefaultTableModel resetmd = (DefaultTableModel) stTb.getModel();
									resetmd.setNumRows(0);
									db.DBlg(1);
									tfID.setText("");
									tfNm.setText("");
									tfPW.setText("");
									dptp.setSelectedItem("매니저");
									sid = 0;
								}
							}
						}
						break;
					case 3: // 신규 등록
						sql = "Select * from staff;";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (rs.getString(4).equals(tfID.getText())) {
								JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다!", "아이디 중복 오류!",
										JOptionPane.ERROR_MESSAGE);
								break;
							} else {
								System.out.println("insert문");
								sql = "INSERT INTO staff (name,ps,sid,spw)VALUES ('" + tfNm.getText() + "','"
										+ dptp.getSelectedItem() + "','" + tfID.getText() + "','" + tfPW.getText()
										+ "')";
								System.out.println(sql);
								insrs = stmt.execute(sql);
								if (delrs) {
									JOptionPane.showMessageDialog(null, "다시 시도해주세요!", "직원등록 오류!",
											JOptionPane.ERROR_MESSAGE);
								} else {
									System.out.println("성공");
									DefaultTableModel resetmd = (DefaultTableModel) stTb.getModel();
									resetmd.setNumRows(0);
									db.DBlg(1);
									tfID.setText("");
									tfNm.setText("");
									tfPW.setText("");
									dptp.setSelectedItem("매니저");
									JOptionPane.showMessageDialog(null, "사원이 등록되었습니다!", "등록 완료!",
											JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						break;
					case 4: // 수정하기
						sql = "UPDATE staff SET ps='" + dptp.getSelectedItem() + "',name='" + tfNm.getText() + "',spw='"
								+ tfPW.getText() + "' WHERE S_Index=" + sid + ";";
						delrs = stmt.execute(sql);
						if (delrs) {
							JOptionPane.showMessageDialog(null, "다시 시도해주세요!", "직원 수정 오류!", JOptionPane.ERROR_MESSAGE);
						} else {
							System.out.println("성공");
							DefaultTableModel resetmd = (DefaultTableModel) stTb.getModel();
							resetmd.setNumRows(0);
							db.DBlg(1);
							tfID.setText("");
							tfNm.setText("");
							tfPW.setText("");
							dptp.setSelectedItem("매니저");
							sid = 0;
							JOptionPane.showMessageDialog(null, "정보가 수정되었습니다.", "직원 수정",
									JOptionPane.INFORMATION_MESSAGE);
						}

						break;
					default:
						System.out.println("4");
						break;
					}
				} else {
					System.out.println("실패");
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

		if (e.getSource() == btnOk) {
			if (btnOk.getText().equals("신규등록하기")) {
				if (tfID.getText().equals("") || tfNm.getText().equals("") || tfPW.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						db.DBlg(3);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else if (btnOk.getText().equals("수정하기")) {
				if (sid == 0) {
					JOptionPane.showMessageDialog(null, "수정하려는 직원을 선택해주세요!", "직원선택", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (tfID.getText().equals("") || tfNm.getText().equals("") || tfPW.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							db.DBlg(4);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		} else if (e.getSource() == btnexit) {
			dispose();
		} else if (e.getSource() == lgExitbtn) {
			dispose();
		} else if (e.getSource() == rdsignup) {
			btnOk.setText("신규등록하기");
			tfID.setText("");
			tfNm.setText("");
			tfPW.setText("");
			dptp.setSelectedItem("매니저");
			tfID.setEditable(true);

		} else if (e.getSource() == rdedit) {
			btnOk.setText("수정하기");
			tfID.setEditable(false);
		} else if (e.getSource() == stdel) {
			int col = 0;
			int row = stTb.getSelectedRow();
			String vu = stTb.getModel().getValueAt(row, col).toString();
			System.out.println(vu);

			int ms = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION);
			if (ms == JOptionPane.OK_OPTION) {
				sid = Integer.parseInt(vu);
				System.out.println(sid);
				try {
					db.DBlg(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				// 아무일도 일어나지 않는다..
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == sttitlebarpn) {
			pnx = e.getX();
			pny = e.getY();
		}else {
			Point point = e.getPoint();
			int currentRow = stTb.rowAtPoint(point);
			stTb.setRowSelectionInterval(currentRow, currentRow);
			int row = stTb.getSelectedRow();
			int col = 0;
			String vu = stTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			System.out.println(vu + " " + sid);

			if (rdsignup.isSelected()) {
				// 아무것도..
			} else {
				tfNm.setText(stTb.getModel().getValueAt(row, 1).toString());
				tfID.setText(stTb.getModel().getValueAt(row, 3).toString());
				tfPW.setText(stTb.getModel().getValueAt(row, 4).toString());
				dptp.setSelectedItem(stTb.getModel().getValueAt(row, 2).toString());
			}
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == sttitlebarpn) {
			pnx = e.getX();
			pny = e.getY();
		}else {
			int col = 0;
			int row = stTb.getSelectedRow();
			String vu = stTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			System.out.println(vu + " " + sid);
			if (rdsignup.isSelected()) {
				// 아무것도..
			} else {
				tfNm.setText(stTb.getModel().getValueAt(row, 1).toString());
				tfID.setText(stTb.getModel().getValueAt(row, 3).toString());
				tfPW.setText(stTb.getModel().getValueAt(row, 4).toString());
				dptp.setSelectedItem(stTb.getModel().getValueAt(row, 2).toString());
			}
		}
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
	public void mouseDragged(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == sttitlebarpn) {
			
			int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
			int y = (int) MouseInfo.getPointerInfo().getLocation().getY();

			this.setLocation((x-pnx), (y-pny));
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
