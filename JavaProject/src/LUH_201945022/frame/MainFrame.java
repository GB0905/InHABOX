package LUH_201945022.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
///////////////////////////////////////////////////////////////	
// 변수 선언
	private Dimension screenSize = new Dimension(1600, 936);
	public Container c;
	public MainPanel mainPanel;
	private TopPanel topPanel;
	
///////////////////////////////////////////////////////////////	
// 생성자
	public MainFrame() {
		setUndecorated(true);
		setTitle("INHA MOVIE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 끄기옵션
		setSize(screenSize);
		setLocationRelativeTo(null);
		setResizable(false);
		
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		topPanel = new TopPanel(this);
		c.add(topPanel, BorderLayout.NORTH);
		
		mainPanel = new MainPanel(this);
		c.add(mainPanel);
		
		setVisible(true);
	}
	
///////////////////////////////////////////////////////////////	
// 메인
	public static void main(String[] args) {
		MainFrame a = new MainFrame();
	}
	
///////////////////////////////////////////////////////////////	
}
