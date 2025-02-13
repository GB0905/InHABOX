package LUH_201945022.frame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GGB_201945025.Inquiry;
import GGB_201945025.TicketPrint;
import GYH_202145024.SnackMenu;
import LUH_201945022.set.*;
import LUH_201945022.panel.*;

public class MainPanel extends PanelSet {

///////////////////////////////////////////////////////////////
// 변수 선언
	private MainFrame fs;

	public FirstFrame_left fl;
	public FirstFrame_right fr;
	public SelectMovie sm;
	public PosterLabel pl;
	public SelectTime st;
	public SelectSeat ss;
	public Inquiry iq;
	public TicketPrint tp;
	public SelectPay sp;
	public SnackMenu s;
	
///////////////////////////////////////////////////////////////
// 생성자
	public MainPanel(MainFrame fs) {
		this.fs = fs;
		setBackground(purpledark);
		setBorder(wl);
		setGbc();
		setLayout(gbl);

		Database.init();
		ResultSet rs = null;
		try {
			rs = Database.getResultSet("select count(*) from movieinfo");
			if (rs.next())
				movCnt = rs.getInt(1);
			System.out.println(movCnt);
			movList = new Movie[movCnt];
			
			rs = Database.getResultSet("select * from movieinfo");
			for (int i = 0; rs.next(); i++)
				movList[i] = new Movie(rs.getString(1), rs.getString(2));
			setPath();
			
			rs = Database.getResultSet("select TicketId from ticket");
			ArrayList<String> ticket = new ArrayList<String>();
			while(rs.next())
				ticket.add(rs.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				Database.conn.close();
				Database.stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("DB 연결 종료");
		}

		fl = new FirstFrame_left(this);
		fl.setPreferredSize(size7);
		gbc.gridx = 0;
		gbl.setConstraints(fl, gbc);

		fr = new FirstFrame_right(this);
		fr.setPreferredSize(size11);
		gbc.gridx = 1;
		gbl.setConstraints(fr, gbc);

		add(fr);
		add(fl);
	}

///////////////////////////////////////////////////////////////
// 메서드
	public void setPath() {
		movPath = new String[movCnt];
		for(int i = 0; i < movCnt; i++) {
			movPath[i] = path + "/" + movList[i].getName() + ".jpg";
		}
	}

	public String[] getMovPath() {
		return movPath;
	}

	public Movie[] getMovList() {
		return this.movList;
	}

	public MainFrame getFrameSet() {
		return this.fs;
	}

	public MainFrame getFs() {
		// TODO Auto-generated method stub
		return fs;
	}
	
///////////////////////////////////////////////////////////////
// 리스너

///////////////////////////////////////////////////////////////
}
