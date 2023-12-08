package KYH_201945023;

/***********************
 * Copyright 2022 GaYoNE
************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class addMVTime extends JFrame implements ActionListener {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private java.util.Date date = new Date();
	private Calendar cal = Calendar.getInstance();

	private int mvid;
	private String title;
	private int where;
	private String time;

	private DBa db = new DBa();

	private int year;
	private int month;
	private int day;

	private Date date1 = new Date();
	private Date date2 = new Date();

	private String y1 = Integer.toString(cal.get(Calendar.YEAR));
	private String m1 = Integer.toString(cal.get(Calendar.MONTH) + 1);
	private String d1 = Integer.toString(cal.get(Calendar.DATE));
	private String y;
	private String m;
	private String d;

	private JButton btns;
	private JButton btnc;
	private JRadioButton jrb1;
	private JRadioButton jrb2;
	private JRadioButton jrb3;
	private ButtonGroup bgp = new ButtonGroup();
	private ButtonGroup bgp1 = new ButtonGroup();

	private JLabel lbl1;
	private JLabel titlelbl;

	private JLabel wherelbl;
	private JLabel timelbl;

	private JDatePickerImpl datePicker;
	private JRadioButton jrb4;
	private JRadioButton jrb5;
	private JRadioButton jrb6;
	private JRadioButton jrb7;
	private JRadioButton jrb8;
	StaffForm st;

	public addMVTime(String title, int mvid, StaffForm st) {
		this.title = title;
		this.mvid = mvid;
		setTitle(title);
		this.st = st;
		titlelbl = new JLabel(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 300);
		setLocationRelativeTo(st);
		setUndecorated(true);
		setLayout(new BorderLayout());
		below();
		center();

		setVisible(true);

	}

	// 하단 패널
	private void below() {
		JPanel bp = new JPanel();
		btns = new JButton("저장");
		btnc = new JButton("취소");
		btns.addActionListener(this);
		btnc.addActionListener(this);

		btns.setBackground(new Color(169, 203, 215));
		btnc.setBackground(new Color(222, 165, 164));

		bp.setBackground(new Color(60, 19, 97));

		bp.add(btns);
		bp.add(btnc);

		add(bp, BorderLayout.SOUTH);
	}

	// 중간 패널
	private void center() {

		wherelbl = new JLabel("상영관 선택");
		timelbl = new JLabel("시간 선택");
		wherelbl.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		timelbl.setFont(new Font("돋움", Font.BOLD, 15));
		wherelbl.setForeground(Color.white);
		timelbl.setForeground(Color.white);

		wherelbl.setBounds(100, 25, 100, 30);
		timelbl.setBounds(100, 180, 100, 30);

		titlelbl = new JLabel(title);
		titlelbl.setBounds(10, 5, 200, 30);
		titlelbl.setForeground(Color.white);

		jrb1 = new JRadioButton("1관");
		jrb2 = new JRadioButton("2관");
		jrb3 = new JRadioButton("3관");
		bgp.add(jrb1);
		bgp.add(jrb2);
		bgp.add(jrb3);

		jrb4 = new JRadioButton("9시");
		jrb5 = new JRadioButton("12시");
		jrb6 = new JRadioButton("15시");
		jrb7 = new JRadioButton("18시");
		jrb8 = new JRadioButton("21시");

		bgp1.add(jrb4);
		bgp1.add(jrb5);
		bgp1.add(jrb6);
		bgp1.add(jrb7);
		bgp1.add(jrb8);

		jrb1.setBounds(70, 50, 50, 30);
		jrb2.setBounds(120, 50, 50, 30);
		jrb3.setBounds(170, 50, 50, 30);

		jrb4.setBounds(60, 200, 50, 30);
		jrb5.setBounds(120, 200, 60, 30);
		jrb6.setBounds(180, 200, 60, 30);
		jrb7.setBounds(90, 230, 60, 30);
		jrb8.setBounds(150, 230, 60, 30);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		// dataPicker
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(70, 120, 150, 50);
		datePicker.setBackground(new Color(188, 160, 220));

		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DATE);

		// dataPicker 처음 날짜 지정
		datePicker.getJFormattedTextField().setText(year + "-" + month + "-" + day);

		lbl1 = new JLabel("날짜 선택");
		lbl1.setFont(new Font("돋움", Font.BOLD, 15));
		lbl1.setForeground(Color.white);
		lbl1.setBounds(70, 90, 100, 30);

		JPanel jp = new JPanel();
		jp.setLayout(null);

		jp.add(jrb1);
		jp.add(jrb2);
		jp.add(jrb3);
		jrb1.setSelected(true);

		jp.add(jrb4);
		jp.add(jrb5);
		jp.add(jrb6);
		jp.add(jrb7);
		jp.add(jrb8);
		jrb4.setSelected(true);

		jp.add(lbl1);
		jp.add(datePicker);

		jp.add(timelbl);
		jp.add(wherelbl);

		jp.add(titlelbl);

		jrb1.setBackground(new Color(188, 160, 220));
		jrb2.setBackground(new Color(188, 160, 220));
		jrb3.setBackground(new Color(188, 160, 220));
		jrb4.setBackground(new Color(188, 160, 220));
		jrb5.setBackground(new Color(188, 160, 220));
		jrb6.setBackground(new Color(188, 160, 220));
		jrb7.setBackground(new Color(188, 160, 220));
		jrb8.setBackground(new Color(188, 160, 220));

		jp.setBackground(new Color(188, 160, 220));

		add(jp, BorderLayout.CENTER);
	}

	// db 단
	private class DBa {
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://118.46.199.58:3306/movie";

		static final String USERNAME = "javapj";
		static final String PASSWORD = "1234";

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		boolean rsa = false;

		public void Dba(int a) throws SQLException {

			System.out.print("User Table 접속 : ");

			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				stmt = conn.createStatement();

				if (conn != null) {
					System.out.println("성공");
					switch (a) {
					case 1: // 로그인 db조회
						System.out.println("Select문");
						String sql = "select * from mvtime where rday = '" + y + "-" + m + "-" + d + "' and mvwhere = "
								+ where + " and Rtime = '" + time + "';";
						rs = stmt.executeQuery(sql);

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "해당시간, 해당 상영관에 상영예정 영화가 있습니다!", "오류",
									JOptionPane.ERROR_MESSAGE);
						} else {
							int q = JOptionPane.showConfirmDialog(null, "상영시간 등록하시겠습니까?", "상영등록",
									JOptionPane.OK_CANCEL_OPTION);
							if (q == 0) {
								sql = "INSERT INTO movie.mvtime (RDay,RTime,mvID,MvWhere) VALUES ('" + y + "-" + m + "-"
										+ d + "','" + time + "', " + mvid + ", " + where + ");";
								rsa = stmt.execute(sql);
								if (!rsa) {
									JOptionPane.showMessageDialog(null, "상영을 등록하였습니다!", "상영 등록",
											JOptionPane.INFORMATION_MESSAGE);
									dispose();
									DefaultTableModel resetmd = (DefaultTableModel) st.getTimeTb().getModel();
									resetmd.setNumRows(0);
									st.setTid(0);
									st.stdba.stDBa(7);
								}
							} else {

							}
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
		Object obj = e.getSource();
		if (obj == btnc) {
			this.dispose();
		} else if (obj == btns) {
			if (jrb1.isSelected()) {
				where = 1;
			} else if (jrb2.isSelected()) {
				where = 2;
			} else if (jrb3.isSelected()) {
				where = 3;
			}
			if (jrb4.isSelected()) {
				time = "09:00";
			} else if (jrb5.isSelected()) {
				time = "12:00";
			} else if (jrb6.isSelected()) {
				time = "15:00";
			} else if (jrb7.isSelected()) {
				time = "18:00";
			} else if (jrb8.isSelected()) {
				time = "21:00";
			}
			
			y = Integer.toString(datePicker.getModel().getYear());
			m = Integer.toString(datePicker.getModel().getMonth() + 1);
			d = Integer.toString(datePicker.getModel().getDay());
			System.out.println(y + m + d);
			y1 = Integer.toString(cal.get(Calendar.YEAR));
			m1 = Integer.toString(cal.get(Calendar.MONTH) + 1);
			d1 = Integer.toString(cal.get(Calendar.DATE));
			
			//오늘 날짜와 지정한 날짜는 변수에 지정
			try {
				date1 = format.parse(y1 + "-" + m1 + "-" + d1);
				date2 = format.parse(y + "-" + m + "-" + d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			//날짜 비교
			int compare = date1.compareTo(date2);
			// 현재 날부터 전일을 지정하면 예외
			if (compare > 0) {
				JOptionPane.showMessageDialog(null, "지난날에 상영을 추가할 수 없습니다!", "과거일 지정 오류!", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					db.Dba(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

}
