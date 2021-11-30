#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 내용 : 회원기능관련 메소드를 작성한 클래스
#최종 완성일자 : 12월 1일
###########################
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DatabaseUtil;

public class UserDAO {
	ResultSet rs; //데이터베이스의 테이블 결과를 리턴받아 자바에 저장해주는 객체
	
	//일일이 겟파라미터로 변수들 받아서 저장
	public int join(String userID, String userPassword) {
		String SQL = "INSERT INTO USER VALUES(?,?)";
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//데이터베이스에서 쿼리를 실행시켜주는 객체
			pstmt.setString(1,userID);
			pstmt.setString(2, userPassword);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//자바빈 사용하여 데이터들 한번에 저장
	public void insertMember(UserBean mbean) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			String SQL = "INSERT INTO USER VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//데이터베이스에서 쿼리를 실행시켜주는 객체
			pstmt.setString(1, mbean.id);
			pstmt.setString(2, mbean.password);
			pstmt.setString(3, mbean.name);
			pstmt.setString(4, mbean.email);
			pstmt.setString(5, mbean.tel);
			pstmt.setString(6, mbean.address);
			pstmt.setInt(7, mbean.money);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<UserBean> allSelectMember(){
		//가변길이로 데이터를 저장
		Vector<UserBean> v = new Vector<>();	
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from user";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				UserBean bean = new UserBean();//컬럼으로 나뉘어진 데이터를 빈클래스에 저정하기위함
				bean.setId(rs.getString(1));
				bean.setPassword(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setTel(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setMoney(rs.getInt(7));
				//패키징된 memberbean클래스를 벡터에 저장
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
	
	//한사람에 대한 정보를 리턴하는 메소드
	public UserBean oneSelectMember(String id) {
		//한사람에 대한 정보만 리턴하기에 빈클래스 객체 생성
		UserBean bean = new UserBean();
		
		try {
			//커넥션연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비
			String sql = "select * from user where userId=?"; //user테이블에서 쿼리를 가져오는데 어떤 아이디가 넘어올지 모르기에
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, id);
			//쿼리실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	//레코드가 있다면
				bean.setId(rs.getString(1));
				bean.setPassword(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setTel(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setMoney(rs.getInt(7));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
		
	//한 회원의 패스워드를 리턴하는 메소드 작성
	public String getPass(String id) {
		//스트링으로 리턴을 해야하기에 스트링변수 선언
		String password="";
		try {
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비
			String sql = "select userPassword from user where userID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString(1); //패스워드값이 저장된 컬럼인덱스
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return password;
	}
		
	//한 회원의 정보를 수정하는 메소드
	public void updateMember(UserBean mbean) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비,,,,만약 수정사항을 더 더하고싶다면 콤마콤마로 늘려가면됨
			String sql = "update user set email=?,tel=? where userID=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, mbean.getEmail());
			pstmt.setString(2, mbean.getTel());
			pstmt.setString(3, mbean.getId());
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//한 회원을 삭제하는 메소드
	public void deleteMember(String id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비
			String sql = "delete from user where userID=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, id);
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
