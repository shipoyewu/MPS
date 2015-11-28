<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.zzu.modle.*,com.zzu.dao.*,com.zzu.daoImp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html style="color: Green; background-color: Silver">
  <head>
    <base href="<%=basePath%>">
    
   
    <title>查看个人信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 
  
  <body > 
        <h1 align="center"> <b><big>个人信息</big></b></h1>
        <div>
             <% 
              long  userid = (Long) session.getAttribute("userid");
              if(userid == '0'  )  System.out.print("\n未能查找到该用户的个人信息");
              User user=new UserDaoImp().getUser(userid);
              
             // sess.setAttribute("userid", new Usr())
             %>
             <h1>账号：<%=user.getUserid() %> </h1> 
             <h1>名字：<%=user.getUsername() %></h1>
             <h1>生日：<%=user.getBirthday() %></h1> 
             <h1>邮箱：<%=user.getEmail() %></h1>
             <h1>电话：<%=user.getTel()%></h1>
             <h1>头像：<%=user.getPicture() %></h1>
             <h1>注册时间：<%=user.getRegistertime() %></h1>
         
        </div>
        <h1 align="right"><a href="">修改个人信息</a>  </h1>
                             
        
        
  </body>
</html>
