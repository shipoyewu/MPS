<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="footer.jsp"></jsp:include>
<!DOCTYPE html>
<!--登录首页  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
</head>
<body	
	style="background-image: url(<%=request.getContextPath()%>/images/home.png); background-repeat: repeat-x; margin-bottom: 100px">
	<DIV
		style="DISPLAY: block; FONT-SIZE: 30pt; FILTER: glow(color = white, strength = 10); COLOR: white; HEIGHT: 1px; FONT-FAMILY: 华文行楷">
		<B>分级消息发布系统</B>
	</DIV>

	<iframe src="loginFrame.jsp" width="300px" height="350px" align="right"
		style="border-style: solid; margin-top: 70px; border-color: #B4B3FF; background-color: #EAFFFF;"></iframe>
</body>
</html>