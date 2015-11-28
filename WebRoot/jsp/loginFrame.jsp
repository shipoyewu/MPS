<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--登录框架，加入到login.jsp中  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="LoginServlet" method="post" id="form1"
		style="text-align: center">
		<div style="text-align: left;">			
				<label for="" style="color: #000000">您好,请登录...</label>
		</div>
		<table align="center">

			<!--设置cookie实现自动登录 -->
			<%
				Cookie[] cs = request.getCookies();
				String name = "";
				String psw = "";
				if (cs != null && cs.length > 0) {
					//遍历cookies，查找name=userInfo和psw=password的cookie
					for (int i = 0; i < cs.length; i++) {
						//System.out.println(" " + cs[i].getName());
						Cookie c = cs[i];
						if (c.getName().equals("userInfo")) {
							name = c.getValue();
							//System.out.println("name=" + name);
						}
						if (c.getName().equals("password")) {
							psw = c.getValue();
							//System.out.println("psw=" + psw);
						}
					}
				}
			%>
			<tr>
				<td><input type="text" name="useridoreamil"
					oninvalid="setCustomValidity('请输入用户号或邮箱!');"
					oninput="setCustomValidity('');" placeholder="用户号/邮箱"
					style="width: 230px; height: 35px; border: 1px solid #888888;"
					value="<%=name%>" required></td>

			</tr>
			<tr>
				<td></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td><input type="password" name="password" placeholder="密码"
					oninvalid="setCustomValidity('请输入密码!');"
					oninput="setCustomValidity('');"
					style="width: 230px; height: 35px; border: 1px solid #888888;"
					value="<%=psw%>" required></td>
			</tr>
		</table>
		<div style="text-align: left; margin-left: 30px;">
			<label for="remember"><font size="-1"><input
					type="checkBox" name="remember" id="remember" value="remember">记住密码</font></label>
		</div>
		<br>
		<p>
			&nbsp;&nbsp;&nbsp;<a href="#">立即注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="forgetPassword.jsp" target="_blank">忘记密码？</a>
		</p>
		<button type="submit" value="submit" form="form1"
			style="width: 230px; height: 45px; background-color: #5858F2; color: white;">登录</button>
	</form>
</body>
</html>