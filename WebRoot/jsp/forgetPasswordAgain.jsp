<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="footer.jsp"></jsp:include>
<!DOCTYPE html>
<!--通过注册邮箱找回密码(输入的邮箱不存在时,链接到此页面) -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
</head>
<body>
	<form action="FindPasswordServlet" method="post">
		<table align="center" style="margin-top: 150px;">
			<tr>
			<td></td>
			<td style="margin-left:30px;"><mark>该用户不存在,请重新输入:</mark></td></tr>
			<tr>
				<td><b>请输入注册邮箱:</b></td>
				<td><input type="email" name="findPassword"
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