package LUH_201945022.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import LUH_201945022.set.*;
import LUH_201945022.frame.*;

public class PosterLabel extends PanelSet {

///////////////////////////////////////////////////////////////
// 변수 선언	
	private JLabel[] lbl = new JLabel[2];
	public JLabel acCnt, selectedTime, seatNumLbl;
	private Movie selMovie;
	
///////////////////////////////////////////////////////////////
// 생성자	
	public PosterLabel(MainPanel main) {
		this.main = main;
		main.pl = this;
		selMovie = main.getSelMovie();
		
		lbl[0] = new JLabel(imageSetSize(selMovie.getPoster(), screenWidth / 7, screenWidth / 7 * 1.42));
		lbl[1] = new JLabel(selMovie.getName());
		lbl[1].setFont(a);
		lbl[1].setForeground(Color.white);
		lbl[1].setHorizontalAlignment(SwingConstants.CENTER);
		
		setGbc();
		setBackground(purplemid);
		setLayout(gbl);

		setBorder(wl);

		gbc.insets = new Insets(10, 30, 10, 30);
		gbc.weightx = 1;
		gbl.setConstraints(lbl[0], gbc);
		lbl[0].setBorder(new LineBorder(Color.white, 5));

		gbc.gridy = 1;
		lbl[1].setOpaque(true);
		lbl[1].setBackground(Color.white);
		lbl[1].setForeground(Color.black);
		lbl[1].setBorder(new LineBorder(Color.white, 5));
		gbl.setConstraints(lbl[1], gbc);

		gbc.gridy = 7;
		gbc.weighty = 1;
		JLabel empty = new JLabel();
		gbl.setConstraints(empty, gbc);
		
		add(lbl[0]);
		add(lbl[1]);
		add(empty);
	}

///////////////////////////////////////////////////////////////
// 메서드
	public JLabel getAcCnt() {
		return acCnt;
	}
	
	public JLabel getSelectedTime() {
		return selectedTime;
	}
	
	public JLabel getSeatNumLbl() {
		return seatNumLbl;
	}

///////////////////////////////////////////////////////////////
// 리스너

///////////////////////////////////////////////////////////////
}
