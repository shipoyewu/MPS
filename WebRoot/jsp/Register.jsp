<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>多级消息发布系统用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/regist.js"></script>
  </head>
  
  <body style="background:#a7d8f5 url(images/registbg.jpg) no-repeat center top;">
  
    <h1 align="center" style="margin-top: 100px;"> <b>欢迎注册</b></h1>
      <form action="register" method="post" onsubmit="return check();"> 
	      <table  border="0" align="center">
	      <tr>
	          <td align=center ><strong><big>用户名*:</big></strong></td>   
	           <td><input type="text" name="username" id="username" style="width: 194px; height: 23px"></td>
	      </tr>
	      <tr>
	          <td align=center> <strong><big>密码*:</big></strong></td>  
	          <td><input type="password" name="password"  id="pw1" style="height: 23px; width: 194px"></td>
	      </tr>
	      <tr>
	          <td align=center> <strong><big>确认密码*:</big></strong></td>  
	          <td><input type="password" name="password"  id="pw2" style="height: 23px; width: 194px"></td>
	      </tr>
	      <tr>
	          <td align=center> <strong><big>生日:</big></strong></td>  
	          <td><input type="date"  name="birthday" style="height: 23px; width: 194px"></td>
	      </tr>
	      <tr>
	          <td align=center> <strong><big>邮箱*:</big></strong></td>  
	          <td><input type="email" name="email" id="email" style="height: 23px; width: 194px"></td>
	      </tr>
	      <tr>
	          <td align=center> <strong><big>手机*:</big></strong></td>  
	          <td><input type="tel" name="tel" id="tel" style="height: 23px; width: 194px"></td>
	      </tr>
	      </table>
	       <p align="center"> 
	              <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;&nbsp;</h>
	              <input type="submit" name="Submit2" value="提交" style="width: 149px; "> 
	       </p> 
      </form>
      <jsp:include page="footer.jsp" ></jsp:include>
  </body>
</html>
