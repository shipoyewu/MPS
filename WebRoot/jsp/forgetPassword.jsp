<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="footer.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<!--通过注册邮箱找回密码 -->
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"> </script>
<script type="text/javascript" src="js/Forget.js"> </script>
<title>找回密码</title>
</head>
<body style="background:#1E5DBA url(images/forgetBG.jpg) no-repeat center top;">
	<form action="FindPasswordServlet" method="post" onsubmit="return checkMail();">
		<table align="center" style="margin-top: 150px;">
			<tr>
				<td><b>请输入注册邮箱:</b></td>
				<td><input type="email" name="findPassword" id="mail"
					oninvalid="setCustomValidity('请输入正确的邮箱格式!');"
					oninput="setCustomValidity('');" placeholder="邮箱"
					style="width: 230px; height: 35px; border: 1px solid #888888;"
					required></td>
				<td><input type="submit" name="提交" value="提交"
					style="width: 90px; height: 40px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>