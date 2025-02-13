package LUH_201945022.set;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Movie {
	
///////////////////////////////////////////////////////////////	
// 변수 선언
	ImageIcon poster; 					// 포스터
	String name;						// 제목
	int num;							// 영화 번호
	
	int[] runId, room;					// 상영 번호, 상영관
	String[] date, time;				// 영화 날짜, 영화 시간
	
	String selACcount;
	
	int selRunId, selRoom;				// 선택한 영화 정보
	String selDate, selTime;
	ArrayList<String> selseat = new ArrayList<String>();
	
///////////////////////////////////////////////////////////////	
// 생성자
	// 영화 이름, 번호, 포스터
	public Movie(String movNum, String movName) {
		this.name = movName;
		this.num = Integer.parseInt(movNum);
		this.poster = new ImageIcon("images/poster" + "/" + movName + ".jpg");
	}
	
///////////////////////////////////////////////////////////////	
// 메서드
	public String getName() {
		return name;
	}
	public int getMovNum() {
		return num;
	}
	public ImageIcon getPoster() {
		return poster;
	}
	
	// 선택된 영화 정보 세팅
	public void setMovieInfo(int Num) {
		runId = new int[Num];
		date = new String[Num];
		time = new String[Num];
		room = new int[Num];
	}
	
	// 영화 상영관
	public int[] getRoom() {
		return room;
	}
	public int getRoom(int i) {
		return room[i];
	}
	public void setRoom(int i, int movRoom) {
		this.room[i] = movRoom;
	}
	
	// 상영 번호
	public int[] getRunId() {
		return runId;
	}
	public int getRunId(int i) {
		return runId[i];
	}
	public void setRunId(int i, int runId) {
		this.runId[i] = runId;
	}
	
	// 영화 날짜
	public String[] getDate() {
		return date;
	}
	public String getDate(int i) {
		return date[i];
	}
	public void setDate(int i, String date) {
		this.date[i] = date;
	}
	
	// 영화 시간
	public void setTime(int i, String movTime) {
		this.time[i] = movTime;
	}
	public String[] getTime() {
		return time;
	}
	public String getTime(int i) {
		return time[i];
	}

	// 선택된 상영 번호
	public void setSelRunId(int selRunId) {
		this.selRunId = selRunId;
	}
	public int getSelRunId() {
		return selRunId;
	}
	
	// 선택된 상영관
	public int getSelRoom() {
		return selRoom;
	}
	public void setSelRoom(int selRoom) {
		this.selRoom = selRoom;
	}

	// 선택된 영화 날짜
	public String getSelDate() {
		return selDate;
	}
	public void setSelDate(String selDate) {
		this.selDate = selDate;
	}

	// 선택된 영화 시간
	public String getSelTime() {
		return selTime;
	}
	public void setSelTime(String selTime) {
		this.selTime = selTime;
	}

	// 선택된 청소년 성인 수
	public String getSelACcount() {
		return selACcount;
	}
	public void setSelACcount(String selACcount) {
		this.selACcount = selACcount;
	}

	// 선택된 좌석
	public ArrayList<String> getSelseat() {
		return selseat;
	}
	public void setSelseat(String seat) {
		selseat.add(seat);
	}
	public void setSelseat(ArrayList<String> selseat) {
		this.selseat = selseat;
	}
	
///////////////////////////////////////////////////////////////	
}
