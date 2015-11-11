<%@ page contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

  </head>
  
  <body>
    <!-- <img src="C:\MPSDOWNLOADS\icon.jpg"> -->
  
    <p align="center"> 请您选择需要上传的文件</p>
	<form id="form1" name="form1" method="post" action="/com/zzu/service/ShowIcon" enctype="multipart/form-data">
		<table border="1" align="center">
	<tr>		
		<td>上传文件：</td>
		<td><input name="file" type="file" size="20" ></td>	
	</tr>
	<tr>
		<td><input type="submit" name="submit" value="提交" ><td/>
		<td><input type="reset" name="reset" value="重置" ><td/>
	</tr>
		</table>
</form>
  </body>
</html>
