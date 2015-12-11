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
    
    <title>My JSP 'ModifyGroup.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript" charset="UTF-8" src="js/modify.js"></script>
	
 <%
          long userid = Long.parseLong((String) session.getAttribute("userid"));
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
          User  u=new UserDaoImp().getUser(userid);
          
          String username=u.getUsername();
           // Long gid=1l;   
          long groupid=(Long.parseLong((String)request.getParameter("groupid")));          
          //System.out.println(groupid);
          Group g=new GroupDaoImp().getGroup(groupid);          
          String gname=g.getGroupname(); 
          ArrayList<Group> groupdowns=new RelationDaoImp().getDownOne(groupid);        
      %>
  </head>
  
  <body style="background-color: #ffffff">
  
    <form  method="post"  action="ModifyGroup" name="modifygroup"  class="menuDiv" onsubmit="return showCreate();">
      <h2 class="menuTitle">修改群组</h2>
      <ul>
	      <li>
	        <b>旧群组名：</b>
	        <input type="text"  value="<%=gname%>" disabled="disabled">
	      	<input type="hidden" name="groupname1" value="<%=gname%>" disabled="disabled">
	        
	      </li>
	      <li>
	        <b>群组ID：</b>
	        <input type="text"  value="<%=groupid%>" disabled="disabled" style="width: 186px; ">
	        <input type="hidden" name="groupid" value="<%=groupid%>" style="width: 186px; ">
	      </li>
	      <li>
	         <b>创建用户id：</b>
	         <input type="text"  id="userid" value="<%=userid%>" disabled="disabled" style="width: 159px; ">
	         <input type="hidden" name="userid" id="userid" value="<%=userid%>"  style="width: 159px; ">
	      </li>
	       <li>
	        <b>新群组名：</b>
	        <input type="text" name="groupname" id="groupname"  value="<%=gname%>">
	      </li>
	      
	       <li>
	         <b>创建用户名：</b>
	         <input type="text" name="username" id="username" value="<%=username%>"  disabled="disabled"  style="width: 158px; ">
	      </li>
	     
	      <li>
	         <b>是否需要申请认证：</b>
	                            是：<input type="radio" checked="checked" name="isneedagree" value="true" />
	                            否：<input type="radio" name="isneedagree" value="false" />
	      </li> 
	      
	     
	      <li> 
	             <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;</h>
	             <input type="submit" name="Submit" value="提交" style="width: 149px; "> 
	      </li> 
     </ul>
    </form>
    
    <form  method="post" name="deleteg" class="menuDiv" onsubmit="return showCreate();">
      
         <h2 class="menuTitle"><b>删除群成员</b></h2>
           <ul>
           <li><b>群组<%=gname %>(<%=groupid %>)共有<%=groupdowns.size() %>个群成员</b> <input type="text" name="upid" value='<%=groupid%>' style="display: none;"></li>	
	       <li><b>请勾选你要删除的群成员：</b></li>
           <li>
           		<ul style="margin-left: 20px">
	              
	              <%
	               for(int i=0;i<groupdowns.size();i++){
	               %>
		              <li>
		              		<b><%=new GroupDaoImp().getUserName(groupdowns.get(i).getGroupid())%>(<%=groupdowns.get(i).getGroupid()%>)</b>
		              		<input type="checkbox" name="groupdownid" value='<%=groupdowns.get(i).getGroupid()%>'> 
		              </li>
	              <%
	              }%>                 
              </ul>
          </li>
          <li>
          	<div style="margin-left: 35px">	
            <input type="button" name="delete" value="删除" onclick="deleteYN();" >
            <input type="checkbox" name="groupdownid" value="0" title="点击全选或者全不选" onclick="CheckAll(this.form.elements['groupdownid'], this.checked)" >
          	</div>
          </li>
         </ul>
    </form>
  </body>
</html>
