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
    
    <title>My JSP 'AddGroupToGroup.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form action="ApplyToGroup" method="post"  onsubmit="return validate_form(this)" align="center">
      <% 
          
          long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          ArrayList<Long>  groupids=new UserDaoImp().findGroup(userid);
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
      %>
     
           <p>
              <label>请选择你的群组(共<%=groups.size() %>个群组):</label>              
              <select name="groupdown" id="groupdown" style="width: 150pt;height: 20pt;">
               <option value="">
                  --请选择--
                </option> 
                <%
                 for(int i=0;i<groups.size();i++){
                 %> 
                 <option value='<%=groups.get(i).getGroupid() %>'>
                  <%=groups.get(i).getGroupid() %>（<%= groups.get(i).getGroupname()%>）
                  </option> 
                <%
                 }
                 %>             
                <c:forEach items="${groups.objectList}" var="group" varStatus="1" >             
                 <option value="${group.groupid }">
                  ${group.groupname }                            
                  </option>
                </c:forEach> 
              </select>
           </p>
            
           
           
           <p>
              <label>申请加入到的群组ID：</label>
              <input type="text" name="groupupid" id="groupupid">
           </p>
           <script>
           function validate_required(field,alerttxt)
           {
             with (field)
             {
              if(value==""||isNaN(value))
               {alert(alerttxt);return false}
              else {return true}
             }
            }

            function validate_form(thisform)
            {
             with (thisform)
             {
              if (validate_required(groupupid,"群组ID必须为数字串！")==false)
              {groupupid.focus();return false}
             }
            }
             
             </script>
              
           <p>
              <label>验证消息：</label>
              <textarea name="applycontent" id="applycontent" >
              </textarea>
           </p>
                                                      
           <p>
           <input type="submit" name="apply" value ="申请" class="button" />            
           </p>
   
   </form>
  </body>
</html>
