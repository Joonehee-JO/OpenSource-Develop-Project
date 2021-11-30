#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 내용 : 회원기능관련 메소드를 작성한 클래스
#최종 완성일자 : 12월 1일
###########################
package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import model.UserBean;
import util.DatabaseUtil;

public class PrDAO {
	private ResultSet rs;
	
	//상품이미지 수정
	public void updateProductImg(String user_id, String path) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비,,,,만약 수정사항을 더 더하고싶다면 콤마콤마로 늘려가면됨
			String sql = "update product set address=? where user_id=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, path);
			pstmt.setString(2, user_id);
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  //상품을 최신순으로 정렬하여 Bean형태로 Vector에 담아서 저장하는 메소드
	public Vector<Bean> sortLatest(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product where category = ? order by id desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
  //상품을 카테고리별로 분류하는 
	public Vector<Bean> categorySelectProduct(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from product where category=?";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	//상품 검색 메소드
	public Vector<Bean> search(String name){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product WHERE name LIKE ?";
			//쿼리를 실행시켜주는 객체 선언
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, "%" + name + "%");
			//pstmt.setString(1, name);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
}
