<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    
    <title>欢迎注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
          <h1 align="center"> <b>欢迎登录系统</b></h1>
          <script type="text/javascript">
	      function test(){
		       var userid=document.getElementById("id").value;
		       var password1=document.getElementById("pw1").value;
		       var password2=document.getElementById("pw2").value;
		       var email=document.getElementById("email").value;
		       if(userid==""||password1==""||password2==""||email==""){
			         alert("选项不得为空！");
			   return false;
		      }
		    else if(password1!=password2){
			            alert("密码不一致！");
			            return false;
		    }
		    return true;
	      }
          </script>
          <form action="register" method="post"> 
          <table  border="2" align="center" style="background-color: #FFFFCC">
          <tr>
              <td align=center ><strong><big>账号:</big></strong></td>  
              <td><input type="text" name="userid" id="id" style="width: 194px; height: 23px"/></td>
          </tr>
          <tr>
              <td align=center ><strong><big>名字:</big></strong></td>   
               <td><input type="text" name="username" style="width: 194px; height: 23px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>密码:</big></strong></td>  
              <td><input type="password" name="password" value="password1" id="pw1"style="height: 23px; width: 194px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>确认密码:</big></strong></td>  
              <td><input type="password" name="password" value="password2" id="pw2"style="height: 23px; width: 194px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>生日:</big></strong></td>  
              <td><input type="date"  name="birthday" style="height: 23px; width: 194px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>邮箱:</big></strong></td>  
              <td><input type="email" name="email" id="email" style="height: 23px; width: 194px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>电话:</big></strong></td>  
              <td><input type="tel" name="tel" style="height: 23px; width: 194px"></td>
          </tr>
          <tr>
              <td align=center> <strong><big>头像:</big></strong></td>  
              <td> <input type="image" name="picture" style="height: 23px; width: 100px"></td>
          </tr>
          </table>
           <p align="center"> 
                  <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h>
                  <input type="submit" name="Submit2" value="提交" style="width: 149px; "> 
           </p> 
          </form>
  </body>
<jsp:include page="footer.jsp" ></jsp:include>
</html>