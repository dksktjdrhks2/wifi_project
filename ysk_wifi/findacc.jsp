<%@page import="webproject.MemberDAO"%>
<%@page import="webproject.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:useBean id="dto" class="webproject.MemberDTO"></jsp:useBean>
				<jsp:setProperty property="*" name="dto"/>
				<%
					MemberDAO dao = new MemberDAO();
					String acc = dao.find_acc(dto);
					out.println("<script>if(!alert('" + acc + "')) document.location = 'wifi_main.html';</script>");
				%>
	</body>
</html>