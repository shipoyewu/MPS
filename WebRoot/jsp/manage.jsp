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
    
    <title>My JSP 'manage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function edit() {
			document.getElementById("email").disabled=null;
		}
	</script>
  </head>
  <title> 修改个人信息</title>
  <body>
    <h1 align="center">修改个人信息 点击可以修改</h1>
    <form action="manger" method="post"> 
            <%long  userid = (Long) session.getAttribute("userid");
              if(userid == '0'  )  System.out.print("\n未能查找到该用户的个人信息");
              User user=new UserDaoImp().getUser(userid);
             %>
          <table  border="2" align="center">
          <tr>
              <td align=center ><strong><big>名字:</big></strong></td>   
              <td><input type="text" name=username id=username ></td>
              <td> <%=user.getUsername() %></td>
          </tr>
          <tr>
              <td align=center> <strong><big>密码:</big></strong></td>  
              <td><input type="password" name="password" value="password1" id="pw1"style="height: 23px; width: 194px"></td>
              <td><%=user.getPassword() %></td>
          </tr>
          <tr>
              <td align=center> <strong><big>生日:</big></strong></td>
              <td><input type="Date" name=birthday id=birthday ></td> 
              <td><%=user.getBirthday() %></td>
          </tr>
          <tr>
              <td align=center> <strong><big>邮箱:</big></strong></td>  
              <td><input type="Email" id="email" value="asasdasda" disabled="" onclick="edit()"></td>
              <td><%=user.getEmail() %></td>         
          </tr>
          <tr>
              <td align=center> <strong><big>电话:</big></strong></td>  
              <td><input type="Tel" name=tel id=tel ></td> 
              <td><%=user.getTel() %></td>
          </tr>
          <tr>
              <td align=center> <strong><big>头像:</big></strong></td>
              <td><input type="Picture" name=picture id=picture ></td>   
              <td><%=user.getPicture() %></td>
          </tr>
          </table>
           <p align="center"> 
                  <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h>
                  <input type="submit" name="Submit2" value="提交" style="width: 149px; "> 
           </p> 
          </form>
  </body>
</html>
