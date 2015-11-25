<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<jsp:include page="footer.jsp"></jsp:include>
<!DOCTYPE html>
<!--通过邮箱链接指向此重置密码页面 -->
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/resetPassword.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
</head>
<body>
	<%
		//设置session，将邮箱号传给ResetPasswordServlet.java
		String email = request.getParameter("email");
		//System.out.println(email);
		HttpSession se = request.getSession();
		se.setAttribute("email", email);
	%>
	<form onSubmit="return checkForm()" action="ResetPasswordServlet"
		method="post" name="fromResetPassword" id="fromResetPassword">
		<table align="center" style="margin-top: 150px;">
			<tr>
				<td><b>重置密码</b></td>
			</tr>
			<tr>
				<td>新密码:</td>
				<td><input type="password" name="resetPassword1"
					id="resetPassword1" oninvalid="setCustomValidity('请输入密码!');"
					oninput="setCustomValidity('');" placeholder="密码"
					style="width: 230px; height: 35px; border: 1px solid #888888;"
					required></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" name="resetPassword2"
					id="resetPassword2" oninvalid="setCustomValidity('二次输入密码不一致');"
					oninput="setCustomValidity('');" placeholder="确认密码"
					style="width: 230px; height: 35px; border: 1px solid #888888;"
					required></td>
			</tr>
			<tr>
				<td><input type="submit" name="提交" value="提交"
					style="width: 90px; height: 40px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>