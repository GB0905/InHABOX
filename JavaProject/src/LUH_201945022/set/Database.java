package LUH_201945022.set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
///////////////////////////////////////////////////////////////	
// 변수 선언
	// 드라이버 주소
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// DB 주소
	static final String DB_URL = "jdbc:mysql://118.46.199.58:3306/movie";
	// DB 아이디
	static final String USERNAME = "javapj";
	// DB 비밀번호
	static final String PASSWORD = "1234";
	
	public static Connection conn;
	public static Statement stmt;
	
///////////////////////////////////////////////////////////////	
// 생성자
	public static void init() {
		try {
			// class클래스의 forName()함수를 이용해서 해당 클래스를 메모리로 로드
			Class.forName(JDBC_DRIVER);
			// url, id, password 를 입력하여 데이터페이스에 접속
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("DB 연결 오류 또는 쿼리 오류 입니다.");
			e.printStackTrace();
		}
	}
	
///////////////////////////////////////////////////////////////	
// 메서드
	public static ResultSet getResultSet(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
///////////////////////////////////////////////////////////////	
}
