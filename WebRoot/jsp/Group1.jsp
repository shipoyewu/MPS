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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form action="CreateGroup" method="post" name=creategroup"" onsubmit="return validate_form(creategroup)"  align="center">
     <% 
          
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
          User  u=new UserDaoImp().getUser(userid);
          String username=u.getUsername();
          ArrayList<Long>  groupids=new UserDaoImp().findGroup(userid);
          ArrayList<Group>  groups=new GroupDaoImp().findAllGroup(userid);
      %>
      
        <script>
         function validate_required(field,alerttxt)
           {
             with (field)
             {
              if(value=="")
               {alert(alerttxt);return false}
              else {return true}
             }
            }

            function validate_form(creategroup)
            {
            alter("dasda");
             with (creategroup)
             {
              if (validate_required(groupname,"群组名不能为空")==false)
              {groupname.focus();return false}
             }
            }          
        </script>  
      <h1>创建群组</h1>
      <p>
        <label>群组名：</label>
        <input type="text" name="groupname" id="groupname">
      </p>
     
      <p>
         <label>创建用户id：</label>
         <input type="text" name="userid" id="userid" value="<%=userid%>" readonly="readonly">
        
      </p>
       <p>
         <label>创建用户名：</label>
         <input type="text" name="username" id="username" value="<%=username%>" readonly="readonly">
        
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
     
    <form  method="post" name="deletegroup" align="center">
     
      <script>

            function CheckAll(groupid, do_check){
               if(typeof(groupid)=='undefined') return;
               var cnt = (typeof(groupid.length)!='undefined')? groupid.length : 0;
               if(cnt){
                    for(var i=0;i<cnt; i++)
                        groupid[i].checked = do_check;
               }else
                  groupid.checked = do_check;
              }

             function deleteYN(){
              //if(YN(this.form.elements['delBox'], this.checked)){if(!confirm('确实要删除吗?')) return false;}else return false;
              document.deletegroup.action="DeleteGroup"; 
              
             if(typeof(deletegroup.elements['groupid'])=='undefined'){
             return false;
             }else{
                  if(deletegroup.groupid.checked==true){  
                     if(!confirm('确实要删除吗?')) 
                        return false;
                     else
                       {
                        document.deletegroup.submit(); 
                        //return true;
                        }
                  }else{ 
                       for(var i=0;i<deletegroup.groupid.length; i++){
                           if(deletegroup.groupid[i].checked==true){
                                 if(!confirm('确实要删除吗?')) 
                                 return false;
                                 else
                                 {
                                 document.deletegroup.submit(); 
                                 //return true;
                                 } 
                            }
                       }
                  }
                return false; 
              }
              return false;
            }
            
            function Modify() {
            	//alert("asdadsasd");
				document.deletegroup.action="jsp/ModifyGroup.jsp";
				document.deletegroup.submit();
			}
          </script>
         <h1>删除群组</h1>
        
           <p>
              <label>您共有<%=groups.size() %>个群组</label><br><br>
              <label>请勾选你要删除的群组：<br></label><br>
              <%
               for(int i=0;i<groups.size();i++){
               %>
              <label><%=groups.get(i).getGroupname()%>(<%=groups.get(i).getGroupid()%>)</label>
              <input type="checkbox" name="groupid" value='<%=groups.get(i).getGroupid()%>'  /> 
              <!-- <input type="text" style="display: none;" name="gid" value='<%=groups.get(i).getGroupid()%>'>    -->     
              
             <%--  <input type="button" name="modify" value="修改群组<%=groups.get(i).getGroupid() %>" onClick="Modify()" > --%><br><br>         
              <%
               }
               %>                 
              
           </p>
           
           
          <p>
            <input type="button" name="delete" value="删除" onclick="deleteYN();">
            <input type="checkbox" name="groupid" value="0" title="点击全选或者全不选" onclick="CheckAll(this.form.elements['groupid'], this.checked)" >
          </p>
           
         
    </form>
    
    <form  method="post" action="jsp/ModifyGroup.jsp" name="modifygroup" align="center">
     
    <h1>修改群组</h1>
        <p>
              <label>请选择你的群组(共<%=groups.size() %>个群组):</label>              
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
           <p>
           <input type="submit" name="modify" value ="修改"/>            
           </p>
    </form>
    
    
  <form action="ExpandGroup" method="post"  name="expandgroup" onsubmit="return validate_form(expandgroup)" align="center">
    
      <h1>扩充群组</h1>
    
           <p>
              <label>请选择你的群组(共<%=groups.size() %>个群组):</label>              
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
           </p>
        
           <p align="center">
              <label>加入我的群组的群组id：</label>
              <input type="text" name="groupdownid" id="groupdownid">
              <br>
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

            function validate_form(expandgroup)
            {
             with (expandgroup)
             {
              if (validate_required(groupdownid,"群组ID必须为数字串！")==false)
              {groupdownid.focus();return false}
             }
            }
             
             
        </script>  
           <p align="center">
              <label>验证消息：</label>
              <textarea name="applycontent" id="applycontent" >
              </textarea>
           </p>
                                                      
           <p align="center">
              <input type="submit" name="apply" value ="申请" class="button" />              
           </p>
   
   </form>
   
    <form action="ApplyToGroup" method="post" name="applygroup"  onsubmit="return validate_form(applygroup)" align="center">
      
     <h1>添加到其他群组下</h1>
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

            function validate_form(applygroup)
            {
             with (applygroup)
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
