package LUH_201945022.panel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import LUH_201945022.set.*;
import LUH_201945022.frame.*;

public class SelectMovie extends PanelSet implements MouseListener {

///////////////////////////////////////////////////////////////
// 변수 선언
	public int imgSize, panNum, lblNum, btnNum, numm, counter;
	private JPanel[] imgPan;

///////////////////////////////////////////////////////////////
// 생성자
	public SelectMovie(MainPanel main) {
		setDefault(main);
		main.sm = this;

		setBorder(wl);

		setBackground(purplemid);
		setLayout(new BorderLayout());

		imgSize = screenWidth / 7;

		counter = movCnt;
		if (movCnt % 2 != 0)
			counter++;
		System.out.println(counter);
		lblNum = movCnt * 2;
		imgPan = crePane(counter);

		JPanel movieList = new JPanel();
		movieList.setBackground(purplemid);
		movieList.setLayout(gbl);
		setGbc();
		gbc.weightx = 1;

		lbl = creLbl(lblNum);


		for (int i = 0; i < movCnt; i++) {
			gbc.insets = new Insets(0, 0, 0, 0);
			imgPan[i].setBackground(purplemid);
			imgPan[i].setLayout(new GridBagLayout());
			if (i < counter / 2) {
				gbc.gridx = i;
				gbc.gridy = 0;
			} else {
				gbc.gridx = i - counter / 2;
				gbc.gridy = 1;
			}

			gbc.insets = new Insets(5, 30, 5, 30);
			gbl.setConstraints(imgPan[i], gbc);	
			movieList.add(imgPan[i]);
			System.out.println(i);
			lbl[i].setIcon(setImage(i, imgSize));
			lbl[i].setHorizontalAlignment(SwingConstants.CENTER);

			lbl[i + movCnt].setText(movList[i].getName());
			lbl[i + movCnt].setFont(a);
			lbl[i + movCnt].setForeground(Color.white);
			lbl[i + movCnt].setHorizontalAlignment(SwingConstants.CENTER);

			gbc.gridx = 0;
			gbc.gridy = 0;
			imgPan[i].add(lbl[i], gbc);
			gbc.gridy = 1;
			imgPan[i].add(lbl[i + movCnt], gbc);

			imgPan[i].setBorder(new LineBorder(clear, 3));
			imgPan[i].addMouseListener(this);
		}

		JScrollPane sp = new JScrollPane(movieList, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.getHorizontalScrollBar().setUI(null);
		sp.getHorizontalScrollBar().setUnitIncrement(30);
		add(sp);
	}

///////////////////////////////////////////////////////////////
// 메서드
	public JLabel[] getLbl(int i) {
		JLabel[] a = { lbl[i], lbl[i + movCnt] };
		return a;
	}

	public Object[] getmovInfo(int i) {
		Object[] a = { lbl[i], // 영화 포스터 레이블
				lbl[i + movCnt], // 영화 이름 레이블
				movList[i] }; // 선택된 영화 객체
		main.setSelMovie(movList[i]);
		return a;
	}

///////////////////////////////////////////////////////////////
// 리스너
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		for (int i = 0; i < imgPan.length; i++) {
			if (obj == imgPan[i]) {
				ResultSet rs = null;
				Database.init();
				try {
					// 선택 가능한 영화 개수 확인
//					String sql = "SELECT count(*) FROM mvtime WHERE mvtime.mvid = " 
//							+ movList[i].getMovNum()
//							+ " AND Rday >= date(now()) order by RDay asc";
					String sql = "select count(*) from mvtime where mvtime.mvid = " 
							+ movList[i].getMovNum() 
							+ " order by RDay asc";
					rs = Database.getResultSet(sql);
					if (rs.next())
						numm = rs.getInt(1);
					movList[i].setMovieInfo(numm);

					// 영화 세부 정보 설정
//					sql = "SELECT * FROM mvtime WHERE mvtime.mvid = " 
//							+ movList[i].getMovNum()
//							+ " AND Rday >= date(now()) order by RDay asc";
					sql = "select * from mvtime where mvtime.mvid = " 
							+ movList[i].getMovNum() 
							+ " order by RDay asc";
					rs = Database.getResultSet(sql);
					for (int i1 = 0; rs.next(); i1++) {
						movList[i].setRunId(i1, rs.getInt(1));
						movList[i].setDate(i1, rs.getString(2));
						movList[i].setTime(i1, rs.getString(3));
						movList[i].setRoom(i1, rs.getInt(5));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						rs.close();
						Database.conn.close();
						Database.stmt.close();
						System.out.println("DB 연결 종료");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				if (movList[i].getRunId().length == 0) {
					JOptionPane.showMessageDialog(this.getRootPane(), "상영 중이 아닙니다.", "결과 없음", JOptionPane.ERROR_MESSAGE);
				} else {
					setVisible(false);

					Object[] x = getmovInfo(i);

					PosterLabel pl = new PosterLabel(main);
					pl.setPreferredSize(size5);
					main.add(pl);
					
					SelectTime st = new SelectTime(main);
					st.setPreferredSize(size13);
					main.add(main.st);

					revalidate();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		for (int i = 0; i < imgPan.length; i++) {
			if (obj == imgPan[i]) {
				imgPan[i].setBorder(new LineBorder(Color.white, 3));
				imgPan[i].setBackground(purplelight);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();
		for (int i = 0; i < imgPan.length; i++) {
			if (obj == imgPan[i]) {
				imgPan[i].setBorder(new LineBorder(clear, 3));
				imgPan[i].setBackground(purplemid);
			}
		}
	}

///////////////////////////////////////////////////////////////
}
