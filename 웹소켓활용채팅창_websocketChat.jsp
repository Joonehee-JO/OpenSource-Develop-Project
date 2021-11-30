#작성자 : 조준희
#학과 : 소프트웨어
#학번 : 2017038076
#조 : 오징어(5조)
#프로그램 내용 : 웹소켓 채팅창 jsp
#최종 완성일자 : 12월 1일
###########################
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>
<%=id %>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
 <meta charset="UTF-8">
    <title>Testing websockets</title>
</head>
<body>
<div class="page-content page-container" id="page-content">
<div class="publisher bt-1 border-light"><img class="avatar avatar-xs" src="https://img.icons8.com/color/36/000000/administrator-male.png" alt="..."> 

    <fieldset>
      	<textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea><br>
        <input id="inputMessage" type="text"/>
        <input type="submit" value="send" onclick="send()" />
    </fieldset>
</div>
</div>
</body>
  <!-- 자바스크립트를 활용한 Function작성 -->
 <script type="text/javascript">
     var textarea = document.getElementById("messageWindow");
     //로컬호스트서버와 웹소켓을 연결하기 위한 주소
     var webSocket = new WebSocket('ws://localhost:8080/Auction_Site/broadcasting');	//ws://localhost:8080/프로젝트명/broadcating
     var inputMessage = document.getElementById('inputMessage');
     //에러발생시 onError호출
    webSocket.onerror = function(event) {
      onError(event)
    };
     //채팅서버연결시 onOpen호출
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    //채팅입력시 onmessage호출
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
        textarea.value += "상대 : " + event.data + "\n";
    }
    function onOpen(event) {
        textarea.value += "연결 성공\n";
    }
    function onError(event) {
      alert(event.data);
    }
    function send() {
        textarea.value += "나 : " + inputMessage.value + "\n";
        webSocket.send(inputMessage.value);
        inputMessage.value = "";
    }
 </script>
