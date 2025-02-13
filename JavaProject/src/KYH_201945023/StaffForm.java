package KYH_201945023;

/***********************
 * Copyright 2022 GaYoNE
************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class StaffForm extends JFrame implements ActionListener, MouseListener, WindowListener {

	private JMenuItem stfitemLogout;
	private JMenuItem stfitemExit;
	private JMenuItem stfiteminfo;

	private JButton stfbtnSnEdit;
	private JButton stfbtnStEdit;

	private String colName[] = { "영화ID", "영화제목", "등급", "장르", "개봉일" };
	private DefaultTableModel model = new DefaultTableModel(colName, 0);

	private JTable mvTb = new JTable(model);
	private JScrollPane mvsr = new JScrollPane(mvTb);
	DefaultTableModel resetmd = (DefaultTableModel) mvTb.getModel();

	private String colTimeName[] = { "상영ID", "영화제목", "상영일", "상영시간", "상영관" };
	private DefaultTableModel Tmodel = new DefaultTableModel(colTimeName, 0);

	private JTable TimeTb = new JTable(Tmodel);
	private JScrollPane Timesr = new JScrollPane(TimeTb);
	DefaultTableModel resetTimemd = (DefaultTableModel) TimeTb.getModel();

	private JLabel tfstName;
	private JLabel tfsttype;
	private JLabel verlbl;
	private JTextField noth_1;
	private JTextField noth_2;
	private JTextField noth_3;
	private JLabel logolbl;
	private JTextPane howtxa;
	private String rewho;
	private int resid;
	stDba stdba = new stDba();
	private addSt at;
	private editTimeForm ed;
	private MovieAdd ma;
	private SnackManage sm;
	private addMVTime ads;
	private Login lg;
	private Mvedit mi;
	private instruction ictn;

	private JMenuItem addmv;
	private JMenuItem editmv;
	private JMenuItem delmv;

	private int sid;
	private int tid;
	private String sname;
	private String tname;
	private JMenuItem sraddmv;
	private JMenuItem showmvtm;
	private JMenuItem editTime;
	private JMenuItem delTime;
	private JMenuItem addTime;
	private JButton stfbtnreset;

	public JTable getTimeTb() {
		return TimeTb;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public StaffForm(String who, int sid) {
		addWindowListener(this);
		resid = sid;
		rewho = who;
		setTitle("Inhabox");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1200, 900);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		Image titleIcon = Toolkit.getDefaultToolkit().getImage("images/logo/아이콘 로고.png");
		this.setIconImage(titleIcon);

		setMenu();
		west();
		center();

		setVisible(true);

	}

	private void setMenu() {

		JMenuBar stfmenubar = new JMenuBar();
		JMenu stfmenu = new JMenu("계정");
		JMenu stfhelp = new JMenu("도움말");

		stfitemLogout = new JMenuItem("로그아웃");
		stfitemLogout.addActionListener(this);
		stfitemExit = new JMenuItem("시스템 종료");
		stfitemExit.addActionListener(this);
		stfiteminfo = new JMenuItem("프로그램 정보");
		stfiteminfo.addActionListener(this);

		stfmenu.add(stfitemLogout);
		stfmenu.add(stfitemExit);
		stfhelp.add(stfiteminfo);

		stfmenubar.add(stfmenu);
		stfmenubar.add(stfhelp);

		setJMenuBar(stfmenubar);
	}

	private void west() {

		JPanel stfWest = new JPanel();
		stfWest.setLayout(new GridLayout(7, 1, 10, 10));
		stfWest.setBackground(new Color(62, 35, 110));
		// --------------------오른쪽 패널--------------------------

		JPanel rp = new JPanel();
		rp.setLayout(new GridLayout(2, 1));
		rp.setBackground(new Color(62, 35, 110));

		tfstName = new JLabel("이름");
		tfstName.setForeground(Color.white);
		tfstName.setFont(new Font("굴림", Font.BOLD, 30));

		tfsttype = new JLabel("직위");
		tfsttype.setForeground(Color.white);
		tfsttype.setFont(new Font("굴림", Font.BOLD, 30));

		noth_1 = new JTextField("");
		noth_1.enable(false);
		noth_1.setBackground(new Color(62, 35, 110));
		noth_1.setBorder(null);
		noth_2 = new JTextField("");
		noth_2.enable(false);
		noth_2.setBackground(new Color(62, 35, 110));
		noth_2.setBorder(null);
		noth_3 = new JTextField("");
		noth_3.enable(false);
		noth_3.setBackground(new Color(62, 35, 110));
		noth_3.setBorder(null);

		howtxa = new JTextPane();
		howtxa.setText("자세한 사용 방법은\n상단 메뉴에 도움말을\n통해 자세한 안내를\n받을 수 있습니다.");
		howtxa.enable(false);
		howtxa.setForeground(Color.white);
		howtxa.setFont(new Font("굴림", Font.BOLD, 17));
		howtxa.setBackground(new Color(62, 35, 110));

		StyledDocument doc = howtxa.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		verlbl = new JLabel("ⓒCopyright InHABOX");
		verlbl.setForeground(Color.white);
		verlbl.setFont(new Font("굴림", Font.BOLD, 12));
		verlbl.setHorizontalAlignment(SwingConstants.RIGHT);

		ImageIcon westicon = new ImageIcon("images/logo/스태프로고.png");
		logolbl = new JLabel(westicon);

		try {
			stdba.stDBa(9);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rp.add(tfstName);
		rp.add(tfsttype);

		JPanel rp1 = new JPanel();

		rp1.setLayout(new GridLayout(1, 1, 0, 50));
		rp1.setBorder(BorderFactory.createEmptyBorder(30, 60, 5, 50));
		rp1.setBackground(new Color(62, 35, 110));

		rp1.add(rp);

		// -----------------------패널 추가-----------------------

		stfWest.add(logolbl);
		stfWest.add(rp1);
		stfWest.add(noth_1);
		stfWest.add(noth_2);
		stfWest.add(noth_3);
		stfWest.add(howtxa);
		stfWest.add(verlbl);

		add(stfWest, BorderLayout.WEST);

	}

	private void center() {
		JPanel mdpn = new JPanel();
		JPanel downpn = new JPanel();

		mdpn.setLayout(new BorderLayout());
		downpn.setLayout(new BorderLayout());
		// ---------테이블 팝업메뉴
		JPopupMenu pmenu = new JPopupMenu();
		JPopupMenu srmenu = new JPopupMenu();
		addmv = new JMenuItem("영화 추가하기");
		sraddmv = new JMenuItem("영화 추가하기");
		editmv = new JMenuItem("영화 수정하기");
		delmv = new JMenuItem("영화 삭제하기");
		addTime = new JMenuItem("상영 추가하기");
		showmvtm = new JMenuItem("영화 상영 정보");
		pmenu.add(addmv);
		pmenu.add(editmv);
		pmenu.add(delmv);
		pmenu.addSeparator();
		pmenu.add(addTime);
		pmenu.add(showmvtm);

		srmenu.add(sraddmv);
		sraddmv.addActionListener(this);
		addmv.addActionListener(this);
		editmv.addActionListener(this);
		delmv.addActionListener(this);
		showmvtm.addActionListener(this);
		addTime.addActionListener(this);
		mvsr.setComponentPopupMenu(srmenu);
		mvTb.setComponentPopupMenu(pmenu);
		mvTb.addMouseListener(this);

		// 테이블 매서드
		try {
			stdba.stDBa(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		// -------------j테이블( 각 컬럼 길이 자동 조절 )
		final TableColumnModel columnModel = mvTb.getColumnModel();
		for (int col = 0; col < mvTb.getColumnCount(); col++) {
			int wd = 50;
			for (int row = 0; row < mvTb.getRowCount(); row++) {
				TableCellRenderer redd = mvTb.getCellRenderer(row, col);
				Component comp = mvTb.prepareRenderer(redd, row, col);
				wd = Math.max(comp.getPreferredSize().width + 1, wd);
			}
			columnModel.getColumn(col).setPreferredWidth(wd);
		}
		// row 컬러
		mvTb.setDefaultEditor(Object.class, null);
		mvTb.setDefaultRenderer(Object.class, new myColorRenderer());
		mvTb.setBackground(new Color(114, 2, 152));
		mvTb.setForeground(Color.white);
		mvTb.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

		// -------------j테이블( 스크롤 ) 사이즈 정하기
		mvsr.setPreferredSize(new Dimension(500, 500));

		// 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = mvTb.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		// -------------j테이블( 해드 ) 컬러 바꾸기
		JTableHeader mvTbhd = mvTb.getTableHeader();
		mvTbhd.setBackground(Color.black);
		mvTbhd.setForeground(Color.white);

		// multiselec false
		mvTb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// -------------j테이블( 빈공간 ) 컬러 바꾸기
		mvsr.getViewport().setBackground(new Color(52, 29, 92));
		mvsr.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		mvsr.setBackground(new Color(60, 19, 97));

		// ----------------------------------------------------
		// ---------테이블 팝업메뉴
		JPopupMenu pTimemenu = new JPopupMenu();
		JPopupMenu srTimemenu = new JPopupMenu();
		editTime = new JMenuItem("회차 수정하기");
		delTime = new JMenuItem("회차 삭제하기");

		pTimemenu.add(editTime);
		pTimemenu.add(delTime);
		pTimemenu.addSeparator();

		delTime.addActionListener(this);
		editTime.addActionListener(this);

		Timesr.setComponentPopupMenu(srTimemenu);
		TimeTb.setComponentPopupMenu(pTimemenu);
		TimeTb.addMouseListener(this);

		// 테이블 매서드111111111111111111111111
		try {
			stdba.stDBa(5);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		// -------------j테이블( 각 컬럼 길이 자동 조절 )
		final TableColumnModel ttcolumnModel = TimeTb.getColumnModel();
		for (int col = 0; col < TimeTb.getColumnCount(); col++) {
			int wd = 10;
			for (int row = 0; row < TimeTb.getRowCount(); row++) {
				TableCellRenderer redd = TimeTb.getCellRenderer(row, col);
				Component comp = TimeTb.prepareRenderer(redd, row, col);
				wd = Math.max(comp.getPreferredSize().width + 1, wd);
			}
			ttcolumnModel.getColumn(col).setPreferredWidth(wd);
		}
		mvTb.setRowHeight(30);

		// row 컬러
		TimeTb.setDefaultEditor(Object.class, null);
//		mvTb.setDefaultRenderer(Object.class, new myColorRenderer());
		TimeTb.setBackground(new Color(114, 2, 152));
		TimeTb.setForeground(Color.white);
		// -------------j테이블( 스크롤 ) 사이즈 정하기
		Timesr.setPreferredSize(new Dimension(500, 200));

		// 셀 가운데 정렬
		DefaultTableCellRenderer Tdtcr = new DefaultTableCellRenderer();
		Tdtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel Ttcm = TimeTb.getColumnModel();
		for (int i = 0; i < Ttcm.getColumnCount(); i++) {
			Ttcm.getColumn(i).setCellRenderer(Tdtcr);
		}
		TimeTb.setRowHeight(20);

		// -------------j테이블( 해드 ) 컬러 바꾸기
		JTableHeader TimeTbhd = TimeTb.getTableHeader();
		TimeTbhd.setBackground(Color.black);
		TimeTbhd.setForeground(Color.white);

		// multiselec false
		TimeTb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TimeTb.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		// -------------j테이블( 빈공간 ) 컬러 바꾸기
		Timesr.getViewport().setBackground(new Color(52, 29, 92));
		Timesr.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		Timesr.setBackground(new Color(60, 19, 97));

		downpn.add(Timesr, BorderLayout.CENTER);
		downpn.setBackground(Color.red);

		// --------하단 오른쪽 패널
		JPanel eastpn = new JPanel();
		eastpn.setLayout(new GridLayout(3, 1, 5, 5));
		eastpn.setPreferredSize(new Dimension(300, 200));
		eastpn.setBackground(new Color(60, 19, 97));
		eastpn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		stfbtnSnEdit = new JButton("매점 관리");
		stfbtnSnEdit.setBackground(new Color(93, 52, 165));
		stfbtnSnEdit.setForeground(Color.white);
		stfbtnSnEdit.addActionListener(this);
		stfbtnSnEdit.setFont(new Font("굴림", Font.BOLD, 30));
		stfbtnSnEdit.setBorder(new RoundedBorder(10));

		stfbtnreset = new JButton("상영정보 초기화");
		stfbtnreset.setBackground(new Color(93, 52, 165));
		stfbtnreset.setForeground(Color.white);
		stfbtnreset.addActionListener(this);
		stfbtnreset.setFont(new Font("굴림", Font.BOLD, 30));
		stfbtnreset.setBorder(new RoundedBorder(10));

		stfbtnStEdit = new JButton("직원 관리");
		stfbtnStEdit.setBackground(new Color(93, 52, 165));
		stfbtnStEdit.setForeground(Color.white);
		stfbtnStEdit.addActionListener(this);
		stfbtnStEdit.setFont(new Font("굴림", Font.BOLD, 30));
		stfbtnStEdit.setBorder(new RoundedBorder(10));

		eastpn.add(stfbtnSnEdit);
		eastpn.add(stfbtnreset);
		eastpn.add(stfbtnStEdit);

		if (rewho.equals("점장") || rewho.equals("부점장")) {
			stfbtnStEdit.setVisible(true);
		} else {
			stfbtnStEdit.setVisible(false);
		}

		downpn.add(eastpn, BorderLayout.EAST);
		mdpn.add(mvsr, BorderLayout.CENTER);
		// mdpn.add(Timesr,BorderLayout.SOUTH);
		mdpn.add(downpn, BorderLayout.SOUTH);

		add(mdpn, BorderLayout.CENTER);
	}

	// db처리

	public class stDba {
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
		boolean delrs;
		boolean insrs;
		boolean updrs;

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
						sql = "Select mvID, mvTitle, mvAge, mvType, mvWhen from movieinfo order by mvwhen desc;";
						rsa = stmt.executeQuery(sql);
						if (rsa.next()) {
							sql = "Select mvID, mvTitle, mvAge, mvType, mvWhen from movieinfo order by mvwhen desc;";
							rsa = stmt.executeQuery(sql);
							while (rsa.next()) {
								if (rsa.getString(3).equals("전체")) {
									model.addRow(new Object[] { rsa.getInt(1), rsa.getString(2), rsa.getString(3),
											rsa.getString(4), rsa.getString(5) });

								} else {
									model.addRow(new Object[] { rsa.getInt(1), rsa.getString(2), rsa.getString(3) + "세",
											rsa.getString(4), rsa.getString(5) });

								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "영화관에 등록된 영화가 없습니다!", "등록 영화 없음",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 4:
						// 영화 삭제
						System.out.println("영화 삭제 : ...");
						try {
							sql = "Select mvID from movieinfo where mvID = " + sid + ";";
							rsa = stmt.executeQuery(sql);
							if (rsa.next()) {
								// 우선 상영정보 삭제

								// 자녀 테이블 데이터 우선 삭제
								sql = "delete from mvtime where mvID =" + sid + ";";
								delrs = stmt.execute(sql);

								// 부모 테이블 데이터 차후 삭제
								sql = "delete from movieinfo where mvID =" + sid + ";";
								delrs = stmt.execute(sql);

								if (delrs) {
									JOptionPane.showMessageDialog(null, "다시 시도해주세요!", "영화삭제 오류!",
											JOptionPane.ERROR_MESSAGE);
								} else {
									System.out.println("성공");

									DefaultTableModel resetmd = (DefaultTableModel) mvTb.getModel();
									resetmd.setNumRows(0);
									stdba.stDBa(1);
									sid = 0;
									resetmd = (DefaultTableModel) TimeTb.getModel();
									resetmd.setNumRows(0);
									stdba.stDBa(5);
									tid = 0;

									System.out.println("종료");
								}

							} else {
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "이미 예약한 고객이있습니다!", "삭제 불가!", JOptionPane.ERROR_MESSAGE);
						}

						break;
					case 5:
						// 상영 조회
						System.out.println("상영 조회 : ...");
						sql = "select movieinfo.mvtitle, mvtime.* from movieinfo, mvtime where movieinfo.mvid = mvtime.mvid and Rday >= date(now()) order by Rday asc;";
						rsa = stmt.executeQuery(sql);

						if (rsa.next()) {
							sql = "select movieinfo.mvtitle, mvtime.* from movieinfo, mvtime where movieinfo.mvid = mvtime.mvid and Rday >= date(now()) order by Rday asc;";
							rsa = stmt.executeQuery(sql);
							while (rsa.next()) {
								System.out.println(rsa.getString(1));
								Tmodel.addRow(new Object[] { rsa.getInt(2), rsa.getString(1), rsa.getString(3),
										rsa.getString(4), rsa.getString(6) + "관" });
							}
						} else {
							JOptionPane.showMessageDialog(null, "금일로부터 예정된 영화가 없습니다!", "상영 예정 없음",
									JOptionPane.ERROR_MESSAGE);
						}

						break;

					case 6:
						// 상영 삭제
						System.out.println("상영 삭제 : ...");

						// 우선 상영정보 조회
						sql = "Select * from mvtime where RunningID = " + tid + ";";
						try {
							rsa = stmt.executeQuery(sql);
							if (rsa.next()) {
								// 상영 삭제
								sql = "delete from mvtime where RunningID = " + tid + ";";
								System.out.println(sql);
								delrs = stmt.execute(sql);

								if (delrs) {
									JOptionPane.showMessageDialog(null, "다시 시도해주세요!", "상영삭제 오류!",
											JOptionPane.ERROR_MESSAGE);
									System.out.println("실패");
								} else {
									System.out.println("성공");

									DefaultTableModel resetmd = (DefaultTableModel) mvTb.getModel();
									resetmd.setNumRows(0);
									stdba.stDBa(1);
									sid = 0;
									resetmd = (DefaultTableModel) TimeTb.getModel();
									resetmd.setNumRows(0);
									stdba.stDBa(5);
									tid = 0;

									System.out.println("종료");
								}

							} else {
								System.out.println("없음");
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "이미 예약한 고객이있습니다!", "삭제 불가!", JOptionPane.ERROR_MESSAGE);
						}

						break;
					case 7:
						// 상영 상세 조회
						System.out.println("상영 상세 조회 : ...");
						sql = "select movieinfo.mvtitle, mvtime.* from movieinfo, mvtime where movieinfo.mvid = mvtime.mvid and mvtime.mvid = "
								+ sid + "  order by Rday asc;";
						rsa = stmt.executeQuery(sql);
						System.out.println(sql);
						tid = 0;
						if (rsa.next()) {
							DefaultTableModel resetmd = (DefaultTableModel) TimeTb.getModel();
							resetmd.setNumRows(0);
							sql = "select movieinfo.mvtitle, mvtime.* from movieinfo, mvtime where movieinfo.mvid = mvtime.mvid and mvtime.mvid = "
									+ sid + "  order by Rday asc;";
							rsa = stmt.executeQuery(sql);
							while (rsa.next()) {
								Tmodel.addRow(new Object[] { rsa.getInt(2), rsa.getString(1), rsa.getString(3),
										rsa.getString(4), rsa.getString(6) + "관" });
							}

							System.out.println("있음");

						} else {
							JOptionPane.showMessageDialog(null, "상영정보가 없습니다!", "상영 없음!", JOptionPane.ERROR_MESSAGE);
						}

						break;

					case 9:
						// 사원 조회
						System.out.println("사원 조회");

						sql = "Select sid, name, ps from staff where s_Index = " + resid + ";";
						rsa = stmt.executeQuery(sql);
						if (rsa.next()) {
							tfstName.setText(rsa.getString(2));
							tfsttype.setText(rsa.getString(3));

						} else {
							System.out.println("사원정보 오류");
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

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == stfitemLogout) {
			int loginfo = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
			if (loginfo == JOptionPane.NO_OPTION) {

			} else {
				dispose();
				lg = new Login("로그인");
			}
		} else if (obj == stfbtnSnEdit) {
			if (sm == null) {
				sm = new SnackManage(this);
			} else {
				sm.dispose();
				sm = new SnackManage(this);
			}
		} else if (obj == stfbtnStEdit) {
			if (at == null) {
				at = new addSt(rewho, this);
			} else {
				at.dispose();
				at = new addSt(rewho, this);
			}
		} else if (obj == addmv || obj == sraddmv) {
			if (ma == null) {
				ma = new MovieAdd("영화추가", this);
			} else {
				ma.dispose();
				ma = new MovieAdd("영화추가", this);
			}
		} else if (obj == editmv) {
			// 영화 수정
			int col = 0;
			int row = mvTb.getSelectedRow();
			String mvid = mvTb.getModel().getValueAt(row, col).toString();
			System.out.println(mvid);
			
			if(mi == null) {
				mi = new Mvedit(Integer.parseInt(mvid), this);				
			}else {
				mi.dispose();
				mi = new Mvedit(Integer.parseInt(mvid), this);								
			}

		} else if (obj == delmv) {
			int col = 0;
			int row = mvTb.getSelectedRow();
			String mvid = mvTb.getModel().getValueAt(row, col).toString();
			System.out.println(mvid);

			int ms = JOptionPane.showConfirmDialog(null, "해당영화를 삭제시 상영정보도 삭제됩니다. \n 정말로 삭제하시겠습니까?", "영화 삭제",
					JOptionPane.OK_CANCEL_OPTION);
			if (ms == JOptionPane.OK_OPTION) {
				sid = Integer.parseInt(mvid);
				System.out.println(sid);
				try {
					stdba.stDBa(4);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (obj == showmvtm) {
			try {
				stdba.stDBa(7);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj == delTime) {
			int deltime = JOptionPane.showConfirmDialog(null, "정말 상영정보를 삭제하시겠습니까?", "상영 삭제",
					JOptionPane.OK_CANCEL_OPTION);
			if (deltime == JOptionPane.OK_OPTION) {

				try {
					stdba.stDBa(6);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (obj == addTime) {
			if(ads == null) {
				ads = new addMVTime(sname, sid, this);				
			}else {
				ads.dispose();
				ads = new addMVTime(sname, sid, this);				
			}
		} else if (obj == stfbtnreset) {
			try {
				DefaultTableModel resetmd = (DefaultTableModel) TimeTb.getModel();
				resetmd.setNumRows(0);
				tid = 0;
				stdba.stDBa(5);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (obj == editTime) {
			if (ed == null) {
				ed = new editTimeForm(tname, tid, this);
			} else {
				ed.dispose();
				ed = new editTimeForm(tname, tid, this);
			}
		} else if (obj == stfitemExit) {
			int loginfo = JOptionPane.showConfirmDialog(null, "시스템을 종료하시겠습니까??", "시스템 종료", JOptionPane.YES_NO_OPTION);
			if (loginfo != JOptionPane.NO_OPTION) {
				this.dispose();
				try {
					if (ma != null) {
						ma.dispose(); // 영화 추가
					}
					if (mi != null) {
						mi.dispose(); // 영화 수정
					}
					if (at != null) {
						at.dispose(); // 상영 시간 추가
					}
					if (ed != null) {
						ed.dispose(); // 상영시간 수정
					}
					if (ads != null) {
						ads.dispose(); // 직원 관리
					}
					if (sm != null) {
						sm.dispose(); // 간식 관리
					}
					if (ictn != null) {
						ictn.dispose(); // 설명서
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		} else if (obj == stfiteminfo) {
			if (ictn == null) {
				ictn = new instruction("설명서", this);
			} else {
				ictn.dispose();
				ictn = new instruction("설명서", this);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == mvTb) {
			Point point = e.getPoint();
			int currentRow = mvTb.rowAtPoint(point);
			mvTb.setRowSelectionInterval(currentRow, currentRow);
			int row = mvTb.getSelectedRow();
			int col = 0;
			String vu = mvTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			vu = mvTb.getModel().getValueAt(row, 1).toString();
			sname = vu;
			System.out.println(vu + " " + sid + sname);

		} else if (obj == mvsr) {
			Point point = e.getPoint();
			int currentRow = mvTb.rowAtPoint(point);
			mvTb.setRowSelectionInterval(currentRow, currentRow);
			int row = mvTb.getSelectedRow();
			int col = 0;
			String vu = mvTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			vu = mvTb.getModel().getValueAt(row, 1).toString();
			sname = vu;
			System.out.println(vu + " " + sid + sname);
		} else if (obj == TimeTb) {
			Point point = e.getPoint();
			int currentRow = TimeTb.rowAtPoint(point);
			TimeTb.setRowSelectionInterval(currentRow, currentRow);
			int row = TimeTb.getSelectedRow();
			int col = 0;
			String vu = TimeTb.getModel().getValueAt(row, col).toString();
			tid = Integer.parseInt(vu);
			vu = TimeTb.getModel().getValueAt(row, 1).toString();
			tname = vu;
			System.out.println(vu + " " + tid + tname);

		} else if (obj == Timesr) {
			Point point = e.getPoint();
			int currentRow = TimeTb.rowAtPoint(point);
			TimeTb.setRowSelectionInterval(currentRow, currentRow);
			int row = TimeTb.getSelectedRow();
			int col = 0;
			String vu = TimeTb.getModel().getValueAt(row, col).toString();
			tid = Integer.parseInt(vu);
			vu = TimeTb.getModel().getValueAt(row, 1).toString();
			tname = vu;
			System.out.println(vu + " " + tid + tname);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == mvTb) {
			int col = 0;
			int row = mvTb.getSelectedRow();
			String vu = mvTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			vu = mvTb.getModel().getValueAt(row, 1).toString();
			sname = vu;
			System.out.println(vu + " " + sid + sname);

		} else if (obj == mvsr) {
			int col = 0;
			int row = mvTb.getSelectedRow();
			String vu = mvTb.getModel().getValueAt(row, col).toString();
			sid = Integer.parseInt(vu);
			vu = mvTb.getModel().getValueAt(row, 1).toString();
			sname = vu;
			System.out.println(vu + " " + sid + sname);
		} else if (obj == TimeTb) {
			int col = 0;
			int row = TimeTb.getSelectedRow();
			String vu = TimeTb.getModel().getValueAt(row, col).toString();
			tid = Integer.parseInt(vu);
			vu = TimeTb.getModel().getValueAt(row, 1).toString();
			tname = vu;
			System.out.println(vu + " " + tid + tname);

		} else if (obj == Timesr) {
			int col = 0;
			int row = TimeTb.getSelectedRow();
			String vu = TimeTb.getModel().getValueAt(row, col).toString();
			tid = Integer.parseInt(vu);
			vu = TimeTb.getModel().getValueAt(row, 1).toString();
			tname = vu;
			System.out.println(vu + " " + tid + tname);
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if (ma != null) {
				ma.dispose(); // 영화 추가
			}
			if (mi != null) {
				mi.dispose(); // 영화 수정
			}
			if (at != null) {
				at.dispose(); // 상영 시간 추가
			}
			if (ed != null) {
				ed.dispose(); // 상영시간 수정
			}
			if (ads != null) {
				ads.dispose(); // 직원 관리
			}
			if (sm != null) {
				sm.dispose(); // 간식 관리
			}
			if (ictn != null) {
				ictn.dispose(); // 설명서
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
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
