#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 기능 : login.jsp에서 정보를 받아서 mysql 데이터베이스에 넣어주는 기능
#최종 완성일자 : 12월 1일
###########################
<%@page import="java.io.PrintWriter"%>
<%@page import="model.UserBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("euc-kr");	

	UserDAO userDAO = new UserDAO();
	
	Vector<UserBean> vec = userDAO.allSelectMember();
	String id = request.getParameter("id");
	String idcheck = null;
	String password = request.getParameter("password");
	
	for(int i=0; i<vec.size(); i++){
			UserBean bean = vec.get(i); //벡터에 담긴 빈클래스를 하나씩 추출
			if(id.equals(bean.getId())){
				idcheck = bean.getId();
			}
	}
	
	if(idcheck == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('해당 아이디가 존재하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;	
	}
	
	if(!(password.equals(userDAO.getPass(idcheck)))){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 일치하지 않습니다');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	//아이디와 패스워드를 받아서 세션 저장해보자
	session.setAttribute("user_id", id);
	//세션의 유지시간 설정
	session.setMaxInactiveInterval(60*60); //60초*2간유지
	
	response.sendRedirect("select.jsp");
%>
<body>

</body>
</html>
