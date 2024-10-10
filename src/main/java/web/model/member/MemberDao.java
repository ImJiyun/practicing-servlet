package web.model.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	
	Connection con;
	
	public MemberDao() throws MyException {
		try {
			//1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. connection 
			String url = "jdbc:mysql://localhost:3306/jesdb";
			String user = "ssafy";
			String pw = "12345678";
			
			con = DriverManager.getConnection(url, user, pw);
		} catch (Exception e) {
			throw new MyException("DB 연결 오류");
		}
		
	}
	
	public Member login(String id, String pw) throws MyException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select name from member where id = ? and pw = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString(1); // "name"으로 받아와도 되나 속도 상으로는 인덱스로 받는 게 좋다 
				Member m = new Member(id, pw, name);
				return m;
			}
			return null;
		} catch (Exception e) {
			throw new MyException("login 오류");
		} finally {
			
			try {
				// 순서 반대로 닫기 
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
