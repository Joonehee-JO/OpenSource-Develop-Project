#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 내용 : Bid_DAO 클래스에서 작성한 메소드의 기능을 활용하는 jsp파일
#최종 완성일자 : 12월 3일
###########################
<%@page import="product.Bean"%>
<%@page import="product.PrDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="bid.bid_bean"%>
<%@page import="bid.BidDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int product_id = Integer.parseInt(request.getParameter("product_id"));
	String Biddingprice = request.getParameter("Biddingprice");
	int price = Integer.parseInt(Biddingprice);
	String id = null;
	
	PrDAO PDAO = new PrDAO();
	Bean bean = PDAO.getProduct(product_id);
	int startprice = bean.getPrice();
	
	if(startprice >= price){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입찰가가 시작금액보다 낮습니다!');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	if(session.getAttribute("user_id") != null){
			id = (String) session.getAttribute("user_id");
	}
	
	BidDAO BDAO = new BidDAO();
	
	bid_bean bbean = BDAO.getHighest2(product_id);
	int Hprice = bbean.getPrice();
	
	if(price <= Hprice){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입찰가가 현재 최고입찰액보다 낮습니다!');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	PDAO.incleaseViews(product_id);
	BDAO.bidding(product_id, price, id);
	
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입찰성공! 행운을빕니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
%>
</body>
</html>
