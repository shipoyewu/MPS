<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Vote.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../js/Vote.js"></script>
  </head>
  
  <body>
    <form>
  		<table>
  			<tr>
  				<td>
  					投票主题：
  				</td>
  				<td>
  					<textarea rows="3" cols="20"> </textarea>
  				</td> 
  			</tr>
  			
  			<tr>
  				<td>
  					投票选项：
  				</td>
  				<td>
  					<input type="text" name="choice" id="1"><br>
  					
  				</td>
  			</tr>
  			<tr>
  				<td>
  				</td>
  				<td id="waitforadd">
  				</td>
  			</tr>
  			<tr>
  				<td>
  				</td>
  				<td>
  					<button name="add" id="add" onclick="addchoice();">+添加选项</button>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
