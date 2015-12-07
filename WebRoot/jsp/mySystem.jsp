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
    
   
    <title>查看个人信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	.info{
		border: 1px solid #CCCCCC;
		background-color: #FFFFFF;
		padding: 1px;
		margin-top: 5px;
		margin-right: 5px;
		margin-left: 5px;
		margin-bottom: 0px;
	}
	.info h3{
		font-size: 14px;
		font-weight: bold;
		color: #FFFFFF;
		padding-top: 5px;
		padding-right: 5px;
		padding-bottom: 5px;
		padding-left: 15px;
		background-color: #999999;
		margin: 0px;
	}
	.info ul{
		margin: 0px;
		padding: 0px;
		list-style-type: none;
	}
	h1{
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
	.info ul li{
		color: #666666;
		background-color: #EEEEEE;
		display: block;
		padding: 5px 5px 5px 15px;
		font-size: 14px;
		margin-top: 1px;
		margin-right: 0px;
		margin-bottom: 0px;
		margin-left: 0px;
		height: auto;
	}
	.info ul li a{
		color: #666666;
		background-color: #F4F4F4;
		display: block;
		padding: 5px 5px 5px 15px;
		font-size: 14px;
		margin-top: -5px;
		margin-right: -5px;
		margin-bottom: -5px;
		margin-left: -15px;
		text-decoration: none;
		height: 16px;
	}
	.info ul li a:hover{	
		color: #FFFFFF;
		background-color: #DDDDDD;	
	}
		
	</style>
  </head>
 
  
  <body style="background-color: #ffffff"> 
        <h1 align="center"> <b><big>个人信息</big></b></h1>
        <div>
	        <div style="width: 70%; float: left; display: inline;">
		        <div class="info">
		             <% 
		              long  userid = Long.parseLong((String)session.getAttribute("userid"));
		              if(userid == '0'  )  System.out.print("\n未能查找到该用户的个人信息");
		              User user=new UserDaoImp().getUser(userid);
		              
		             // sess.setAttribute("userid", new Usr())
		             %>
		             <ul >
		    
		             <li><b>账号：</b><%=user.getUserid() %></li> 
		             <li><b>名字： </b><%=user.getUsername() %></li>
		             <li><b>生日：</b><%=user.getBirthday() %> </li> 
		             <li><b>邮箱：</b><%=user.getEmail() %> </li>
		             <li><b>电话：</b><%=user.getTel()%> </li>
		             <li><b>注册时间：</b><%=user.getRegistertime() %> </li>
		         	 <li><p align="center"><a href="jsp/manage.jsp">修改个人信息</a></p></li>	
		         	</ul>
		        </div>
		    </div>
		    <div style="width:30% right; display: inline;">
		    	<div class="info">
		    		<img src="userdata/<%=user.getUserid() %>/icon.jpg" width="25%" hight="100%" style="margin: 10px 10px 10px 20px">
		    	</div>
		    </div>
		</div>
	        
                             
        
        
  </body>
</html>
