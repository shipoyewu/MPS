<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zzu.modle.*,com.zzu.dao.*,com.zzu.daoImp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
	<style>
	h1{
		text-align: center;
		font-size: 25px;
		font-weight: bold;
		color: #FFFFFF;
		padding-top: 5px;
		padding-right: 5px;
		padding-bottom: 5px;
		padding-left: 15px;
		background-color: #999999;
		margin: 0px;	
	}
	</style>
	<script type="text/javascript">
		function clickToEdit() {
			document.getElementById("username").readOnly="false";
			//document.getElementById("pw").readOnly="false";
			document.getElementById("birthday").readOnly="false";
			document.getElementById("email").readOnly="false";
			document.getElementById("tel").readOnly="false";
		}
	</script>
  </head>
  <title> 修改个人信息</title>
  <body style="background-color: #ffffff;">
    <h1>修改个人信息 点击可以修改</h1>
    <div class="content">
    <div class="content_resize">
    <form action="manger" method="post" style="margin-top: 30px;"> 
            <%long  userid =Long.parseLong( (String)session.getAttribute("userid"));
              if(userid == 0  )  System.out.print("\n未能查找到该用户的个人信息");
              User user=new UserDaoImp().getUser(userid);
             %>
          <table  border="0" align="center">
          <tr>
              <td align=center ><strong><big>名字:</big></strong></td>   
              <td><input type="text" name="username" id="username" readOnly="true" value="<%=user.getUsername() %>" ></td>
          </tr>
          <tr>
              <td align=center> <strong><big>生日:</big></strong></td>
              <td><input type="Date" name="birthday" id="birthday" readOnly="true" value="<%=user.getBirthday() %>" ></td> 
          </tr>
          <tr>
              <td align=center> <strong><big>邮箱:</big></strong></td>  
              <td><input type="Email" id="email"  readOnly="true" value="<%=user.getEmail() %>"></td>
                  
          </tr>
          <tr>
              <td align=center> <strong><big>电话:</big></strong></td>  
              <td><input type="Tel" name="tel" id="tel" readOnly="true" value="<%=user.getTel() %>"></td> 
  
          </tr>
          <tr>
              <td align=center> <strong><big>头像:</big></strong></td>
              <td><input type="Picture" name="picture" id="picture" value="<%=user.getPicture() %>" ></td>   
             
          </tr>
          </table>
           <p align="center"> 
                  <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;&nbsp;</h>
                  <input type="submit" name="Submit2" value="提交" style="width: 149px; "> 
           </p> 
    </form>
    </div>
    </div>
  </body>
</html>
