package webproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MemberDAO {
	Connection con = null;
	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://192.168.86.222:3306/web"; // DBeaver view databases -> server/ 占쎄틙 -> URL
			String user = "web"; // DB user_id
			String password = "in0721"; // DB user_password
			con = DriverManager.getConnection(url, user, password); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	// insert
	public boolean insert(MemberDTO dto) {
		boolean flag = check_id(dto);
		try {
			if(!flag) return flag;
			String sql = "insert into member values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql); 
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getBirth());
			ps.setString(4, dto.getGender());
	
			ps.executeUpdate(); 
		}catch (Exception e) {
			System.out.println("오류 >> " + e.getMessage());
		}
		return flag;
	}
	
	public boolean check_id(MemberDTO dto) {
		boolean flag = true;
		try {
			String sql = "select * from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql); 
			ps.setString(1, dto.getId());
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				System.out.println("회원가입 실패!");
				flag = false;
			}else {
				System.out.println("회원가입 성공!");
			}
		}catch (Exception e) {
			System.out.println("오류 >> " + e.getMessage());
		}
		return flag;
	}
	
	public boolean sign_in(MemberDTO dto) {
		boolean flag = false;
		try {
			String sql = "select * from member where id = ? and pw = ?";
			PreparedStatement ps = con.prepareStatement(sql); // sql문으로 변환해준다
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("로그인 OK");
				flag = true;
			}else {
				System.out.println("로그인 NO");
			}
		} catch (Exception e) {
			System.err.println("에러 발생!");
			System.err.println("내용 >> " + e.getMessage());
		}
		return flag;
	}
	
	public String find_acc(MemberDTO dto) {
		String acc = null;
		try {
			String sql = "select * from member where birth = ? and gender = ?";
			PreparedStatement ps = con.prepareStatement(sql); // sql문으로 변환해준다
			ps.setString(1, dto.getBirth());
			ps.setString(2, dto.getGender());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("오류 1");
				acc = "ID : " + rs.getString(1) +  " PW : " + rs.getString(2);
			}else {
				System.out.println("오류 2");
				acc = "회원님의 대한 정보가 없습니다.";
			}
		} catch (Exception e) {
			System.err.println("에러 발생!");
			System.err.println("내용 >> " + e.getMessage());
		}
		System.out.println("계정찾기 종료");
		return acc;
	}
}
