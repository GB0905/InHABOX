package LUH_201945022.panel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;

import GGB_201945025.TicketPrint;
import LUH_201945022.set.*;
import LUH_201945022.frame.*;
import LUH_201945022.frame.MainFrame;

public class SelectPay extends PanelSet implements ActionListener {

///////////////////////////////////////////////////////////////
// 변수 선언
	private final int btnNum = 1;
	private final int lblNum = 1;

	DecimalFormat decFormat = new DecimalFormat("###,###");

	int aPrice = 10000, cPrice = 7000;
	int totalPrice;

	private MainFrame fs;

///////////////////////////////////////////////////////////////
// 생성자
	public SelectPay(MainPanel main) {
		this.main = main;
		this.selMovie = main.getSelMovie();

		setBackground(purpledark);
		setLayout(gbl);

		gbc = new GridBagConstraints();
		setGbc();

		btn = creBtn(btnNum);
		setBtn(btn[0], "결제하기", 7 * 100, 7 * 63);
		btn[0].setPreferredSize(new Dimension(7 * 100, 7 * 63));
		btn[0].addActionListener(this);

		JPanel Pan1 = new JPanel();
		GridBagLayout gbl1 = new GridBagLayout();
		Pan1.setLayout(gbl1);
		Pan1.setBackground(purplemid);
		GridBagConstraints gbc1 = new GridBagConstraints();
		setGbc(gbc1);

		gbc1.fill = GridBagConstraints.BOTH;
		gbl1.setConstraints(btn[0], gbc1);
		Pan1.add(btn[0]);

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 100, 10, 0);
		gbl.setConstraints(Pan1, gbc);
		add(Pan1);

		totalPrice = main.ss.cnta * aPrice + main.ss.cntc * cPrice;
		lbl = creLbl(lblNum);
		lbl[0].setText("결제 금액 : " + decFormat.format(totalPrice) + " 원");
		lbl[0].setForeground(Color.white);
		lbl[0].setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lbl[0].setHorizontalAlignment(JLabel.RIGHT);
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbl.setConstraints(lbl[0], gbc);
		add(lbl[0]);
	}

///////////////////////////////////////////////////////////////
// 메서드

///////////////////////////////////////////////////////////////
// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn[0]) {
			Database.init();
			ResultSet rs = null;
			
			ArrayList<String> ticket = new ArrayList<String>();
			int ticketNum = 0;
			
			ArrayList<String> seatList = selMovie.getSelseat();
			seatList.sort(Comparator.naturalOrder());
			String seat = String.join(", ", seatList);
			
			try {
				String sql = "select TicketId from ticket";
				rs = Database.getResultSet(sql);
				while(rs.next())
					ticket.add(rs.getString(1));
				ticket.sort(Comparator.reverseOrder());
				ticketNum = Integer.parseInt(ticket.get(0)) + 1;
//				// 티켓 정보 DB에 업로드
				sql = "INSERT INTO ticket (RunningId, MvWhen, Seat, mvPrice, payMethod, `member`, bookwho, ticketid)"
						+ "VALUES (" 
						+ selMovie.getSelRunId() + ", " 
						+ "'" + selMovie.getSelDate() + "', " 
						+ "'" + seat + "', "
						+ totalPrice + ", " 
						+ "'카드', " + "4, "
						+ "'"+ selMovie.getSelACcount() +"', " 
						+ ticketNum
						+ ")";
				Database.stmt.execute(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					Database.conn.close();
					Database.stmt.close();
					System.out.println("DB 연결 종료");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			JOptionPane.showConfirmDialog(this.getRootPane(), "예매가 완료되었습니다.", "완료", JOptionPane.DEFAULT_OPTION);

			int qut_data = JOptionPane.showConfirmDialog(this.getRootPane(), "티켓을 출력하시겠습니까?", "결제 완료",
					JOptionPane.YES_NO_OPTION);

			if (qut_data == 0) {
				JOptionPane.showConfirmDialog(this.getRootPane(), "티켓 출력 화면으로 이동합니다.", "완료",
						JOptionPane.DEFAULT_OPTION);
				ArrayList<String> i = new ArrayList<String>();

				Database.init();
				try {
					i.add(Integer.toString(ticketNum)); // i 1번 티켓 번호
					i.add(seat); // i 2번 좌석
					i.add(selMovie.getSelACcount()); // i 3번 성인 청소년 구성

					String sql = "select * from mvtime where RunningId = '" + selMovie.getSelRunId() + "'";
					rs = Database.getResultSet(sql);
					if (rs.next()) {
						i.add(rs.getString(2)); // i 4번 날짜
						i.add(rs.getString(3)); // i 5번 시간
						i.add(rs.getString(5)); // i 6번 상영관
					}

					sql = "select * from movieinfo where mvid ='" + selMovie.getMovNum() + "'";
					rs = Database.getResultSet(sql);
					if (rs.next()) {
						i.add(rs.getString(2)); // i 7번 제목
						i.add(rs.getString(3)); // i 8번 관람가
						i.add(rs.getString(4)); // i 9번 장르
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						rs.close();
						Database.conn.close();
						Database.stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("DB 연결 종료");
				}
				main.pl.setVisible(false);
				main.sp.setVisible(false);

				TicketPrint a = new TicketPrint(i, main);
				a.setPreferredSize(fullsize);
				main.tp = a;
				main.add(main.tp);

				main.revalidate();
			} else {
				JOptionPane.showConfirmDialog(this.getRootPane(), "첫 화면으로 이동합니다.", "완료", JOptionPane.DEFAULT_OPTION);
				fs = main.getFrameSet();

				fs.c.remove(fs.mainPanel);

				MainPanel a = new MainPanel(fs);
				fs.mainPanel = a;
				fs.c.add(fs.mainPanel);

				fs.setVisible(true);

				fs.revalidate();
			}
		}
	}

///////////////////////////////////////////////////////////////
}
