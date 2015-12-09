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
	<!-- <link rel="stylesheet" type="text/css" href="css/styles.css">
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
		margin-top:20px;
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
		align="center";
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
	.textwidth{
		width: 180px;
	}
	.info ul li a:hover{	
		color: #FFFFFF;
		background-color: #DDDDDD;	
	}
		
	</style>
	
  </head>
  
  <body style="background-color: #ffffff;">
    <div>
    	<h1 align="center">修改个人信息 点击可以修改</h1>
    	<div style="width: 70%; float: left; display: inline;">
	    <form action="manger" method="post" class="info" align="center"> 
	            <%
	            long  userid =Long.parseLong( (String)session.getAttribute("userid"));
	             User user=new UserDaoImp().getUser(userid);
	             %>
		          <ul >
			          <li>
			              <strong> 名字: </sliong>   
			              <input type="text" class="textwidth" name="username" id="username" value="<%=user.getUsername() %>" >
			          </li>
			          <li>
			              <strong> 生日: </strong>
			              <input type="Date" class="textwidth" name="birthday" id="birthday" value="<%=user.getBirthday() %>" > 
			          </li>
			          <li>
			              <strong> 邮箱: </strong>  
			              <input type="Email" id="email" name="email" class="textwidth" value="<%=user.getEmail() %>">
			                  
			          </li>
			          <li>
			               <strong> 电话: </strong>  
			              <input type="Tel" name="tel" id="tel" class="textwidth"  value="<%=user.getTel() %>"> 
			  
			          </li>
			          <li>
				          <p align="center"> 
			                  <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;&nbsp;</h>
			                  <input type="submit" name="Submit2" value="提交" style="width: 149px; "> 
			           	  </p>
			          </li>
		          </ul>
	            
	    </form>
	    
	    </div>
	    <div style="width:30%; display: inline;">
		    	<form class="info" action="uploadIcon" method="post" enctype="multipart/form-data"  id="uploadIcon" onsubmit="return checkfile();">
			              	<img src="userdata/<%=user.getUserid() %>/icon.jpg" width="25%" hight="70%" style="margin: 10px 10px 10px 20px">
			              	<p align="right"><input type="file" name="icon" id="icon" value="选择头像" >  <input type="submit" name="Submit1" value=更改> </p>
			   	</form>
		 </div>
	    
   </div>
  </body>
</html>
