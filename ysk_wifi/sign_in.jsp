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
					boolean flag = dao.sign_in(dto);
					if(flag){
						session.setAttribute("id", dto.getId());
						response.sendRedirect("wifi_next.html");
					%>
					<% 
					}else{
					%>
						<script type="text/javascript">
						if(!alert("로그인에 실패하였습니다...!")) document.location = 'wifi_main.html';
						</script>
					<% 
					}
				%>
	</body>
</html>