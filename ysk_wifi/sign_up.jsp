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
		<!-- 입력받은 값 파라미터로 서버에서 받아주고, DTO객체 만들어서, DTO값 넣어주는 것 까지  -->
		<jsp:useBean id="dto" class="webproject.MemberDTO"></jsp:useBean>
		<jsp:setProperty property="*" name="dto"/>
		<%
			MemberDAO dao = new MemberDAO();
			boolean flag = dao.insert(dto);
		%>
		<%
			if(flag){
				response.sendRedirect("wifi_main.html");
			}else{
				%>
				<script type="text/javascript">
				if(!alert("아이디가 중복 되었습니다.")) document.location = 'wifi_signup.html';
				</script>
				<%
			}
		%>
	</body>
</html>