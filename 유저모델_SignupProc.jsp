#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 기능 : 회원가입 관련 UserDAO 클래스에 작성한 메소드를 활용하는 jsp파일
#최종 완성일자 : 12월 1일
###########################
<%@page import="model.UserBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="model.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
	request.setCharacterEncoding("utf-8"); //한글처리
%>
<!DOCTYPE html>
<html>

<body>
<%
	request.setCharacterEncoding("utf-8"); //한글처리
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String password2 = request.getParameter("password2");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String tel = request.getParameter("tel");
	String address = request.getParameter("address");
	
	UserDAO userDAO = new UserDAO();
	
	Vector<UserBean> vec = userDAO.allSelectMember();
	
	for(int i=0; i<vec.size(); i++){
		UserBean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
		if(id.equals(bean.getId())){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('같은 아이디가 존재합니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}
	
	//하나라도 입력이 제대로 안되어있다면
	if(id == "" || password == "" ||  name == "" || email == "" || tel == "" || address == ""){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있음');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//비밀번호의 길이가 짧을 때
	if(password.length()<6){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호의 길이가 너무 짧습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//비밀번호가 일치하지 않을 때
	if(!(password.equals(password2))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 일치하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	//response.sendRedirect("signup.jsp");
%>
<jsp:useBean id="mbean" class="model.UserBean">
	<jsp:setProperty name="mbean" property="*"/>
</jsp:useBean>
<!-- 자바빈에 데이터 담고 보내주기 -->
<%
	userDAO.insertMember(mbean);
	
	//회원가입이 되었다면
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('회원가입성공! 환영합니다');");
	script.println("history.go(-2);");
	script.println("</script>");
	script.close();
	response.sendRedirect("login.jsp");
%>
</body>
</html>
