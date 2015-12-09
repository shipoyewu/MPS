<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="footer.jsp"></jsp:include>
<!DOCTYPE html>
<!--登录首页  -->
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
	.logmain{
		background:#04aeda url(images/home.png) no-repeat;
		margin-bottom: 70px;
	}
	.logframe{
		
	}
	
</style>
<title>欢迎登录</title>
</head>
<body class="logmain">
		<DIV
			style="DISPLAY: block; FONT-SIZE: 30pt; FILTER: glow(color = white, strength = 10); COLOR: white; HEIGHT: 1px; FONT-FAMILY: 华文行楷;margin-top: 35px;margin-left: 15px">
			<B>分级消息发布系统</B>
		</DIV>
	
		<iframe src="jsp/loginFrame.jsp" width="300px" height="350px" align="right" name="loginframe"
			style="border-style: solid; margin-top: 70px; margin-right:90px; border-color: #B4B3FF; background-color: #EAFFFF;">
		</iframe>
		<DIV
			style="DISPLAY: block; FONT-SIZE: 30pt; FILTER: glow(color = white, strength = 10); COLOR: white; HEIGHT: 1px; FONT-FAMILY: 华文行楷;margin-left: 15px;padding-top: 520px;padding-left: 100px">
			<B>——— You cannot escape the responsibility of tomorrow by evading it today.</B>
		</DIV>
</body>

</html>