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
    
    <title>My JSP 'CreateSucceed.jsp' starting page</title>
    
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
     <%
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
      %>
     <p  align="center">
                        创建成功<br><br></b><a align="center" href="jsp/index.jsp">返回个人主页</a>
          <br><br></b><a align="center" href="jsp/Group.jsp">返回管理页面</a>
      </p>
  </body>
</html>
