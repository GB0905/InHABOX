package LUH_201945022.set;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import LUH_201945022.frame.*;

public class PanelSet extends JPanel {
	
///////////////////////////////////////////////////////////////
//변수 선언	
	protected MainPanel main;
	
	// 패널 상태 테스트용 흰 선
	protected LineBorder wl = new LineBorder(Color.white, 3);
	
	// 자주 쓰는 폰트
	protected Font a = new Font("맑은 고딕", Font.BOLD, 20);
	protected Font b = new Font("맑은 고딕", Font.BOLD, 23);
	protected Font c = new Font("맑은 고딕", Font.BOLD, 25);
	
	// 자주 쓰는 색
	protected Color clear = new Color(0, 0, 0, 0);
	protected Color purplelight = new Color(99, 69, 196);
	protected Color purplemid = new Color(75, 52, 149);
	protected Color purpledark = new Color(60, 42, 120);
	
	// 버튼, 패널, 레이블 객체 배열
	protected JButton[] btn;
	protected JLabel[] lbl;
	protected JPanel[] pan;
	
	// 영화 포스터 설정
	protected String path = "images/poster";
	protected String[] movPath;

	// 그리드 백 레이아웃 설정
	protected GridBagLayout gbl = new GridBagLayout();
	protected GridBagConstraints gbc;
	
	// 규격
	protected int screenWidth = 1600;
	protected int screenHeight = 900;
	protected Dimension fullsize = new Dimension(screenWidth * 18 / 20, screenHeight * 18 / 20);
	protected Dimension size13 = new Dimension(screenWidth * 13 / 20, screenHeight * 18 / 20);
	protected Dimension size5 = new Dimension(screenWidth * 5 / 20, screenHeight * 18 / 20);
	protected Dimension size11 = new Dimension(screenWidth * 11 / 20, screenHeight * 18 / 20);
	protected Dimension size7 = new Dimension(screenWidth * 7 / 20, screenHeight * 18 / 20);
	
	// 영화 객체
	protected Movie[] movList;
	protected int movCnt;
	protected Movie selMovie;

///////////////////////////////////////////////////////////////
// 메서드
	// 이전 패널에서 넘어오는 값 설정
	protected void setDefault(MainPanel main) {
		this.main = main;
		movPath = main.getMovPath();
		movList = main.getMovList();
		movCnt = movList.length;
	}
	
	// GridBagConstraints 생성 및 초기화
	protected void setGbc() {
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
	}

	// 패널 동적 생성
	protected JPanel[] crePane(int num) {
		JPanel[] pn = new JPanel[num];
		for (int i = 0; i < num; i++) {
			pn[i] = new JPanel();
		}
		return pn;
	}
	
	// 버튼 동적 생성
	protected JButton[] creBtn(int num) {
		JButton[] bt = new JButton[num];
		for (int i = 0; i < num; i++) {
			bt[i] = new JButton();
		}
		return bt;
	}
	
	// 레이블 동적 생성
	protected JLabel[] creLbl(int num) {
		JLabel[] lb = new JLabel[num];
		for (int i = 0; i < num; i++) {
			lb[i] = new JLabel();
		}
		return lb;
	}
	
///////////////////////////////////////////////////////////////
// 이미지 설정
	// 이미지 크기 설정
	protected ImageIcon imageSetSize(ImageIcon icon, double i, double j) {
		Image img1st = icon.getImage();
		Image img2nd = img1st.getScaledInstance((int) i, (int) j, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img2nd);
		return icon;
	}
	
	// 버튼 이미지 설정
	protected void setBtn(JButton asd, String s, int i, int u) {
		asd.setIcon(imageSetSize(
				new ImageIcon("images/LUH/"+ s + 1 + ".png"), i, u));
		asd.setRolloverIcon(imageSetSize(
				new ImageIcon("images/LUH/"+ s + 2 + ".png"), i, u));
		asd.setPressedIcon(imageSetSize(
				new ImageIcon("images/LUH/"+ s + 3 + ".png"), i, u));
		asd.setBorderPainted(false);
		asd.setContentAreaFilled(false);
		asd.setFocusPainted(false);
	}
	
	// 영화 포스터 이미지 설정
	protected ImageIcon setImage(int i, double baseSize) {
		ImageIcon a = movList[i].getPoster();
		a = imageSetSize(a, baseSize, baseSize * 1.42);
		return a;
	}
	
///////////////////////////////////////////////////////////////
// 게터 세터
	public GridBagLayout getGbl() {
		return gbl;
	}
	
	public void setGbl(GridBagLayout gbl) {
		this.gbl = gbl;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}
	
	public Movie getSelMovie() {
		return selMovie;
	}
	
	public void setSelMovie(Movie selMovie) {
		this.selMovie = selMovie;
	}


}