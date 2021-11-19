<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
	//파일이 저장되는 경로
	String path = request.getSession().getServletContext().getRealPath("file");

	int size = 1024*1024*10; //저장가능한 파일크기
	String file = "";			//업로드 한 파일의 이름(이름이 변경될 수 있음) 우리는 안할듯?
	String originalFile = "";	//이름이 변경되기 전 실제 파일 이름
	String path2 = "";
	//실제로 파일 업로드하는 과정
	try{
		MultipartRequest multi = new MultipartRequest(request,path,size,"UTF-8",new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	//파일 이름을 받아와 스트링으로 저장
		
		out.println(str);
		
		file = multi.getFilesystemName(str);	//업로드 된 파일 이름 가져옴
		out.println(file);
		originalFile = multi.getOriginalFileName(str);	//원래의 파일이름 가져옴
		out.println(originalFile);
		path2 = path+"/"+file;
		out.println(path2);
	} catch (Exception e){
		e.printStackTrace();
	}	
%>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<br>
	<%=path%>
	<br>
	<img alt="" src="<%=path2%>">	<!-- 시발됐다 -->
	<img alt="" src="<%=path%>/11.png">	<!-- 시발됐다 -->
	<img alt="" src="<%=path %>">
	file upload success
</body>
</html>