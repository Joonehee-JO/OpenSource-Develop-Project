<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%
	//������ ����Ǵ� ���
	String path = request.getSession().getServletContext().getRealPath("file");

	int size = 1024*1024*10; //���尡���� ����ũ��
	String file = "";			//���ε� �� ������ �̸�(�̸��� ����� �� ����) �츮�� ���ҵ�?
	String originalFile = "";	//�̸��� ����Ǳ� �� ���� ���� �̸�
	String path2 = "";
	//������ ���� ���ε��ϴ� ����
	try{
		MultipartRequest multi = new MultipartRequest(request,path,size,"UTF-8",new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();	//���� �̸��� �޾ƿ� ��Ʈ������ ����
		
		out.println(str);
		
		file = multi.getFilesystemName(str);	//���ε� �� ���� �̸� ������
		out.println(file);
		originalFile = multi.getOriginalFileName(str);	//������ �����̸� ������
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
	<img alt="" src="<%=path2%>">	<!-- �ùߵƴ� -->
	<img alt="" src="<%=path%>/11.png">	<!-- �ùߵƴ� -->
	<img alt="" src="<%=path %>">
	file upload success
</body>
</html>