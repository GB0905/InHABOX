package LUH_201945022.panel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.LineBorder;

import LUH_201945022.set.*;
import LUH_201945022.frame.*;

public class SelectSeat extends PanelSet implements ActionListener, ItemListener {

///////////////////////////////////////////////////////////////	
// 변수 선언
	private int btnNum = 3;

	public JButton[] btn = new JButton[10];

	JCheckBox[] seatCheck;

	JButton aup = new JButton();
	JButton adown = new JButton();
	JButton cup = new JButton();
	JButton cdown = new JButton();

	private JLabel acnt = new JLabel("0");
	private JLabel ccnt = new JLabel("0");
	private JLabel childlbl = new JLabel("청소년");
	private JLabel adultlbl = new JLabel("성인");

	private JButton[] updnbtn = { aup, adown, cup, cdown };
	private JLabel[] updnlbl = { acnt, ccnt, childlbl, adultlbl };

	public int cnta, cntc;

	private int checkedNum = 0;

	ArrayList<String> selectedSeat = new ArrayList<String>();

///////////////////////////////////////////////////////////////	
// 생성자
	public SelectSeat(MainPanel main) {
		this.main = main;
		main.ss = this;
		selMovie = main.getSelMovie();

		setBackground(purpledark);
		setLayout(gbl);

		btn = creBtn(btnNum);

		// Pan1
		JPanel Pan1 = new JPanel();
		GridBagLayout gbl1 = new GridBagLayout();
		Pan1.setLayout(gbl1);
		Pan1.setBackground(purplemid);
		GridBagConstraints gbc1 = new GridBagConstraints();
		setGbc(gbc1);

		btn[0].setText("S C R E E N");
		btn[0].setFont(a);
		btn[0].setEnabled(false);

		JPanel seatPanel = new JPanel(new GridLayout(5, 9, 5, 5));
		seatPanel.setBackground(Color.BLACK);
		seatPanel.setBorder(new LineBorder(Color.black, 5));
		ArrayList<String> selSeat = new ArrayList<>();
		
		Database.init();
		String sql = "select seat from ticket where runningid = " + selMovie.getSelRunId();
		ResultSet rs = Database.getResultSet(sql);
		try {
			while (rs.next())
				selSeat.add(rs.getString(1));					// 이미 예약된 좌석
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			Database.conn.close();
			Database.stmt.close();
			System.out.println("DB 연결 종료");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		selSeat.sort(Comparator.naturalOrder());

		String seat = String.join(",", selSeat);
		seat = seat.replaceAll(" ", "");
		String arr[] = seat.split(",");

		String[] seatName = new String[45];
		seatCheck = new JCheckBox[45];

		for (int i = 0; i < 45; i++) {
			seatName[i] = new String();
			if (i / 9 == 0) {
				seatName[i] = "a" + (i % 9 + 1);
			} else if (i / 9 == 1) {
				seatName[i] = "b" + (i % 9 + 1);
			} else if (i / 9 == 2) {
				seatName[i] = "c" + (i % 9 + 1);
			} else if (i / 9 == 3) {
				seatName[i] = "d" + (i % 9 + 1);
			} else {
				seatName[i] = "e" + (i % 9 + 1);
			}
		}

		JLabel[] sold = new JLabel[45];
		for (int i = 0; i < 45; i++) {
			sold[i] = new JLabel(seatName[i]);
			sold[i].setIcon(imageSetSize(new ImageIcon("images/LUH/좌석OFF.png"), 50, 50));
			sold[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			sold[i].setOpaque(true);
			sold[i].setBackground(Color.LIGHT_GRAY);
			sold[i].setEnabled(false);
		}

		for (int i = 0; i < 45; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (seatName[i].equals(arr[j]))
					seatName[i] = "xxx";
			}
		}

		for (int i = 0; i < 45; i++) {
			if (seatName[i].equals("xxx")) {
				seatPanel.add(sold[i]);
			} else {
				seatCheck[i] = new JCheckBox();
				seatCheck[i].setText(seatName[i]);
				seatCheck[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				seatCheck[i].setIcon(imageSetSize(new ImageIcon("images/LUH/좌석OFF.png"), 50, 50));
				seatCheck[i].setSelectedIcon(imageSetSize(new ImageIcon("images/LUH/좌석ON.png"), 50, 50));
				seatCheck[i].setEnabled(false);

				seatCheck[i].addItemListener(this);

				seatPanel.add(seatCheck[i]);
			}
		}
		
		gbc1.weightx = 1;
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbl1.setConstraints(btn[0], gbc1);
		Pan1.add(btn[0]);

		gbc1.fill = GridBagConstraints.NONE;
		gbc1.gridy = 1;
		gbc1.weightx = 0;
		gbc1.weighty = 1;
		gbl1.setConstraints(seatPanel, gbc1);
		Pan1.add(seatPanel);

		// Pan2
		JPanel Pan2 = new JPanel();
		Pan2.setLayout(new FlowLayout(FlowLayout.LEFT));
		Pan2.setBackground(purpledark);
		JPanel acmain = makeAcmain();
		Pan2.add(acmain);
		for (int i = 0; i < updnbtn.length; i++) {
			JButton a = updnbtn[i];
			if (i % 2 == 0) {
				a.setIcon(imageSetSize(new ImageIcon("images/LUH/더하기.png"), 30, 30));
			} else if (i % 2 == 1) {
				a.setIcon(imageSetSize(new ImageIcon("images/LUH/빼기.png"), 30, 30));
			}
			a.setBorderPainted(false);
			a.setContentAreaFilled(false);
			a.setFocusPainted(false);
		}

		// Pan3
		JPanel Pan3 = new JPanel();
		Pan3.setBackground(purpledark);
		Pan3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		for (int i = 1; i < btnNum; i++) {
			JButton a = btn[i];
			a.addActionListener(this);
			Pan3.add(btn[i]);
			if (i == 1) {
				a.setIcon(imageSetSize(new ImageIcon("images/LUH/이전화면1.png"), 100, 30));
				a.setPressedIcon(imageSetSize(new ImageIcon("images/LUH/이전화면2.png"), 100, 30));
			} else {
				a.setIcon(imageSetSize(new ImageIcon("images/LUH/다음화면1.png"), 100, 30));
				a.setPressedIcon(imageSetSize(new ImageIcon("images/LUH/다음화면2.png"), 100, 30));
				a.setDisabledIcon(imageSetSize(new ImageIcon("images/LUH/다음화면0.png"), 100, 30));
			}
			a.setBorderPainted(false);
			a.setContentAreaFilled(false);
			a.setFocusPainted(false);
		}
		btn[2].setEnabled(false);

		// 붙이기
		setGbc();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 100, 10, 0);
		gbl.setConstraints(Pan1, gbc);

		gbc.weighty = 0;
		gbc.gridy = 1;
		gbl.setConstraints(Pan2, gbc);

		gbc.gridy = 2;
		gbl.setConstraints(Pan3, gbc);

		add(Pan1);
		add(Pan2);
		add(Pan3);
	}

///////////////////////////////////////////////////////////////
// 메서드
	public void setGbc(GridBagConstraints gbc) {
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
	}

	public void setBtn(JButton i, String text, JPanel pan, int gx, int gy, double wx, double wy, int fill) {
		i.setText(text);
		setLocation(gx, gy, 1, 0.33, fill);
		pan.add(i, gbc);
	}

	public void setLocation(int x, int y, double wx, double wy, int fill) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.fill = fill;
	}

	public String getTime(JButton bt) {
		String a = bt.getText();
		return a;
	}

	public JPanel makeAcmain() {
		JPanel acmain = new JPanel();
		acmain.setBackground(purplemid);

		for (JLabel a : updnlbl) {
			a.setFont(this.a);
			a.setForeground(Color.white);
			a.setHorizontalAlignment(JTextField.CENTER);
			a.setPreferredSize(new Dimension(30, 30));
		}
		adultlbl.setPreferredSize(new Dimension(50, 30));
		childlbl.setPreferredSize(new Dimension(70, 30));

		cnta = Integer.parseInt(acnt.getText());
		cntc = Integer.parseInt(ccnt.getText());

		for (JButton a : updnbtn) {
			a.addActionListener(this);
			a.setPreferredSize(new Dimension(30, 30));
		}

		acmain.add(adultlbl);
		acmain.add(adown);
		acmain.add(acnt);
		acmain.add(aup);
		acmain.add(childlbl);
		acmain.add(cdown);
		acmain.add(ccnt);
		acmain.add(cup);

		return acmain;
	}

	public int countCheck(int x) {
		x = 0;
		for (int i = 0; i < 45; i++) {
			if (seatCheck[i] != null) {
				if (seatCheck[i].isSelected())
					x++;
			}
		}
		return x;
	}

	void setPlLbl(int i, JLabel name) {
		name.setBorder(wl);
		name.setFont(a);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setOpaque(true);
		name.setBackground(Color.white);
		main.pl.getGbc().weighty = 0;
		main.pl.getGbc().gridy = i;
		main.pl.getGbl().setConstraints(name, main.pl.getGbc());
		main.pl.add(name);
	}
	

///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn[1]) {
			setVisible(false);
			main.st.setVisible(true);
			if (main.pl.getSelectedTime() != null) {
				main.pl.getSelectedTime().setVisible(false);
			}
			main.revalidate();
		} else if (obj == btn[2]) {
			setVisible(false);
			main.sp = new SelectPay(main);
			main.sp.setPreferredSize(size13);
			main.add(main.sp);

			JLabel acCnt;
			if (cnta == 0) {
				acCnt = new JLabel("청소년 " + cntc);
			} else if (cntc == 0) {
				acCnt = new JLabel("성인 " + cnta);
			} else {
				acCnt = new JLabel("성인 " + cnta + ", " + "청소년 " + cntc);
			}
			setPlLbl(3, acCnt);
			selMovie.setSelACcount(acCnt.getText());
			main.pl.acCnt = acCnt;

			selectedSeat.sort(Comparator.naturalOrder());
			selMovie.setSelseat(selectedSeat);

			String seatNum = "<html><body>";
			for (int i = 0; i < selectedSeat.size(); i++) {
				if (i == 0) {
					seatNum += selectedSeat.get(i);
				} else if (i != 0 && i % 7 == 0) {
					seatNum += "<br>" + selectedSeat.get(i);
				} else if (i + 1 == selectedSeat.size()) {
					seatNum += ", " + selectedSeat.get(i);
				} else {
					seatNum += ", " + selectedSeat.get(i);
				}
			}
			seatNum += "</body></html>";

			JLabel seatNumLbl = new JLabel(seatNum);
			setPlLbl(4, seatNumLbl);
			main.pl.seatNumLbl = seatNumLbl;

			revalidate();
		} else if ((obj == aup || obj == cup) && cnta + cntc < 45) { // +버튼 눌렀을 때
			if (obj == aup) {
				cnta++;
				acnt.setText(Integer.toString(cnta));
			} else if (obj == cup) {
				cntc++;
				ccnt.setText(Integer.toString(cntc));
			}
			if (checkedNum < cnta + cntc) {
				for (int i = 0; i < seatCheck.length; i++) {
					if (seatCheck[i] != null) {
						seatCheck[i].setEnabled(true);
					}
				}
			}
		} else if (obj == adown || obj == cdown) { // -버튼 눌렀을 때
			if (obj == adown && cnta > 0) {
				cnta--;
				acnt.setText(Integer.toString(cnta));
				if (checkedNum >= cnta + cntc) {
					for (int i = 0; i < seatCheck.length; i++) {
						if(seatCheck[i] != null) {
							seatCheck[i].setSelected(false);
							btn[2].setEnabled(false);
						}
					}
				}
			} else if (obj == cdown && cntc > 0) {
				cntc--;
				ccnt.setText(Integer.toString(cntc));
				if (checkedNum >= cnta + cntc) {
					for (int i = 0; i < seatCheck.length; i++) {
						if(seatCheck[i] != null) {
							seatCheck[i].setSelected(false);
							btn[2].setEnabled(false);
						}
					}
				}
			}
			if (cnta + cntc == 0) {
				for (int i = 0; i < seatCheck.length; i++) {
					if(seatCheck[i] != null) {
						seatCheck[i].setEnabled(false);
						btn[2].setEnabled(false);
					}
				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		btn[2].setEnabled(false);
		for (int i = 0; i < seatCheck.length; i++) {
			if (obj == seatCheck[i]) {
				checkedNum = countCheck(checkedNum);
				int cntAll = cnta + cntc;
				if (checkedNum <= cntAll) {
					if (checkedNum == cntAll) {
						for (int i1 = 0; i1 < seatCheck.length; i1++) {
							btn[2].setEnabled(true);
							if(seatCheck[i1] != null) {
								if (seatCheck[i1].isSelected() == false) {
									seatCheck[i1].setBackground(Color.WHITE);
									seatCheck[i1].setEnabled(false);
								}
							}
						}
					}
					if (seatCheck[i].isEnabled()) {
						if (seatCheck[i].isSelected()) {
							seatCheck[i].setBackground(Color.GRAY);
							selectedSeat.add(seatCheck[i].getText());
						} else if (seatCheck[i].isSelected() == false) {
							seatCheck[i].setBackground(Color.WHITE);
							selectedSeat.remove(seatCheck[i].getText());
							for (int i1 = 0; i1 < seatCheck.length; i1++) {
								if(seatCheck[i1] != null) {
									if (seatCheck[i1].isEnabled() == false && cntAll > 0) {
										seatCheck[i1].setBackground(Color.WHITE);
										seatCheck[i1].setEnabled(true);
									}
								}
							}
						}
					}
				}
			}
		}
	}

///////////////////////////////////////////////////////////////
}
