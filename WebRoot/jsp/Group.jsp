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
    
    <title>My JSP 'Group.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/menuswitch.js"></script>
	<script type="text/javascript" charset="UTF-8" src="js/group.js"></script>
  </head>
  
  <body>
  
  <div class="content_resize">
     <form action="CreateGroup" method="post">
     <% 
          
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          User  u=new UserDaoImp().getUser(userid);
          String username=u.getUsername();
          ArrayList<Long>  groupids=new UserDaoImp().findGroup(userid);
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
      %>
      <div class="menuDiv">
      <h3>创建群组</h3>
      <ul style="margin-left: 10px;">
	      <li>
	        <b>群组名：</b>
	        <input type="text" name="groupname" id="groupname" style="width: 150px;">
	      </li>
	      <li>
	         <b>创建者id：</b>
	         <input type="text" name="userid" id="userid" value="<%=userid%>" style="width: 137;" disabled="disabled">
	        
	      </li>
	       <li>
	         <b>创建者:</b>
	         <input type="text" name="username" id="username" value="<%=username%>" style="width: 160px;" disabled="disabled">
	      </li>
	     
	      <li>
	         <b>是否需要申请认证：</b>
	                            是<input type="radio" checked="checked" name="isneedagree" value="true" />
	                            否<input type="radio" name="isneedagree" value="false" />
	      </li> 
	      <li>
	      	<input type="submit" name="submit" value ="创建" class="button" >
	      </li>
      </ul>
      </div>
    </form>  
     
     
     
    <form  method="post" name="deletegroup">
      <div class="menuDiv">
         <h3>删除群组</h3>
        <ul style="margin-left: 10px;">
           <li>
           		<b>您共有<%=groups.size() %>个群组</b>
              	<b>请勾选你要删除的群组：</b>
            </li>
            <li>  	
           	  	<div style="margin-left: 40px;">
              <%
               for(int i=0;i<groups.size();i++){
               %>
              	<div style="margin-top: 5px;">
              		<%=groups.get(i).getGroupname()%>&nbsp;(<%=groups.get(i).getGroupid()%>)
              		<input type="checkbox" name="groupid" value='<%=groups.get(i).getGroupid()%>'  /> 
              	</div>
              <%
               }
               %>  
               <div style="margin-top: 5px;">
            	&nbsp;&nbsp;&nbsp;&nbsp;
            	<input type="button" name="delete" value="删除" onclick="deleteYN();">
            	<input type="checkbox" name="groupid" value="0" title="点击全选或者全不选" onclick="CheckAll(this.form.elements['groupid'], this.checked)" >
          	   </div>                
              </div>
           </li>
         </ul>
     </div>
    </form>
    
    <form  method="post" action="jsp/ModifyGroup.jsp" name="modifygroup">
     <div class="menuDiv">
    <h3>修改群组</h3>
    <ul style="margin-left: 10px;">
        <li>
              <b>请选择你的群组(共<%=groups.size() %>个群组):</b>              
              <select name="groupid" id="groupid" style="width: 150pt;height: 20pt;">
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
        </li>
        <li>
        	<input type="submit" name="modify" value ="修改" style="margin-left: 250px"/>
        </li>
    </ul>  
    </div>           
    </form>
    
    
  <form action="ExpandGroup" method="post" name="expandgroup"onsubmit="return validate_form(expandGroup)" >
      <div class="menuDiv">
      <h3>扩充群组</h3>
      <ul style="margin-left: 10px;">
           <li>
              <b>请选择你的群组(共<%=groups.size() %>个群组):</b>              
              <select name="groupup" id="groupup" style="width: 150pt;height: 20pt;">
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
           </li>
           <li>
              <b>输入加入我的群组的群组id：</b>
              <input type="text" name="groupdownid" id="groupdownid" style="width: 120px; ">
           </li>
         
           <li>
              <b>验证信息：</b><br>
              <textarea name="applycontent" id="applycontent" style="width: 270px; height: 70px">
              </textarea>
           </li>
           <li>
              <input type="submit" name="apply" value ="申请扩充" class="button" style="margin-left: 100px"/>              
           </li>
   		</ul>
   	</div>
   </form>
   
   
   
   
   
    
    <form action="ApplyToGroup" method="post" name="applygroup"  onsubmit="return validate_form(applygroup)">
     <div class="menuDiv">
     <h3><b>加入群组</b></h3>
     <ul style="margin-left: 10px;">
           <li>
              <b>请选择你的群组(共<%=groups.size() %>个群组):</b>              
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
           </li>
            
           
           
           <li>
              <b>输入申请加入到的群组ID：</b>
              <input type="text" name="groupupid" id="groupupid" style="width: 120px; ">
           </li>
          
              
           <li>
              <b>验证消息：</b><br>
              <textarea name="applycontent" id="applycontent" style="width: 270px; height: 70px">
              </textarea>
           </li>
           <li>
           		<input type="submit" name="apply" value ="申请" class="button" style="margin-left: 100px"/>            
           </li>
   	</ul>
   	</div>
   </form> 
   </div> 
  
  <script type="text/javascript">
	var mSwitch = new MenuSwitch("menuDiv");
	mSwitch.setPrevious(false);
	mSwitch.init();
  </script>
  </body>
  
</html>
