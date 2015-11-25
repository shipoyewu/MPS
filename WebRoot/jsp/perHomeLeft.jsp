<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div style="margin-top: 30px; margin-left: 15px;">
		<div>
			您好!
			<%-- <%=user.getEmail() %> --%>
			<div style="margin-left: 275px;">
				<a href="">发布消息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="">发送私信</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="">个人信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=request.getContextPath()%>/jsp/login.jsp">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="">下一页</a>
			</div>
		</div>
		<hr noshade>
	</div>
	<div
		style="float: left; position: fixed; bottom: 0px; width: 90%; text-align: left;">
		<div style="margin-left: 80px; margin-right: 12px;">
			<div style="background: #F3F3F3; height: 568px; width: 230px;">
				<div style="background: white;">
					<a href=""><img
						src="<%=request.getContextPath()%>/images/person1.jpg"
						width="150px" height="120px" border="1"></a>
				</div>
				<div
					style="text-align: center; background: #BCBCBC; height: 36px; width: 230px; color: white;">
					<h1>我的组员</h1>
				</div>
			</div>
		</div>
	</div>
</body>
</html>