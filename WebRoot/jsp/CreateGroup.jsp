<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zzu.modle.*,com.zzu.dao.*,com.zzu.daoImp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CreateGroup.jsp' starting page</title>
    
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
    <form action="CreateGroup" method="post" align="center">
    <% 
          
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          User  u=new UserDaoImp().getUser(userid);
          String username=u.getUsername();
      %>
      <p>
        <label>群组名：</label>
        <input type="text" name="groupname" id="groupname">
      </p>
      <p>
         <label>创建用户id：</label>
         <input type="text" name="userid" id="userid" value="<%=userid%>">
        
      </p>
       <p>
         <label>创建用户名：</label>
         <input type="text" name="username" id="username" value="<%=username%>">
        
      </p>
     
      <p>
         <label>是否需要申请认证：</label>
                            是：<input type="radio" checked="checked" name="isneedagree" value="true" />
                            否：<input type="radio" name="isneedagree" value="false" />
      </p> 
      
      <p>
      <input type="submit" name="submit" value ="提交" class="button" ></input>
      </p>
    </form>  
  </body>
</html>
