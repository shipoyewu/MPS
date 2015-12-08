<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.zzu.modle.*,com.zzu.dao.*,com.zzu.daoImp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DeleteGroup.jsp' starting page</title>
    
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
    <form action="DeleteGroup" method="post"  align="center">
    <%
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
      %>
        
           <p>
              <label>您共有<%=groups.size() %>个群组</label><br><br>
              <label>请选择你要删除的群组：<br></label><br>
              <%
               for(int i=0;i<groups.size();i++){
               %>
              <label><%=groups.get(i).getGroupname()%>(<%=groups.get(i).getGroupid()%>)</label>
              <input type="checkbox" name="groupid" value='<%=groups.get(i).getGroupid()%>'  /><br><br>
              <%
               }
               %>                 
              
           </p>
         <p>
          <input type="submit" name="delete" value="删除">
         </p>
    </form>
  </body>
</html>
