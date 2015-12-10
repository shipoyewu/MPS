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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <%
          Long userid=1l;
          //long userid = (Long) session.getAttribute("userid");
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
  
  <body>
    <form  method="post"  action="ModifyGroup" name="modifygroup" align="center">
      
      <p>
        <label>旧群组名：</label>
        <input type="text" name="groupname1" value="<%=gname%>" readonly="readonly">
      </p>
      <p>
        <label>群组ID：</label>
        <input type="text" name="groupid" value="<%=groupid%>" readonly="readonly">
      </p>
       <p>
        <label>新群组名：</label>
        <input type="text" name="groupname" id="groupname">
      </p>
      <p>
         <label>创建用户id：</label>
         <input type="text" name="userid" id="userid" value="<%=userid%>" readonly="readonly">
        
      </p>
       <p>
         <label>创建用户名：</label>
         <input type="text" name="username" id="username" value="<%=username%>"  readonly="readonly">
        
      </p>
     
      <p>
         <label>是否需要申请认证：</label>
                            是：<input type="radio" checked="checked" name="isneedagree" value="true" />
                            否：<input type="radio" name="isneedagree" value="false" />
      </p> 
      
     
           <p > 
                  <input type="reset" name="Reset" value="重置" style="width: 131px; "> <h>&nbsp;&nbsp;</h>
                  <input type="submit" name="Submit" value="提交" style="width: 149px; "> 
           </p> 
    </form>
    
    <form  method="post" name="deleteg" align="center">
     
      <script>

            function CheckAll(groupdownid, do_check){
               if(typeof(groupdownid)=='undefined') return;
               var cnt = (typeof(groupdownid.length)!='undefined')? groupdownid.length : 0;
               if(cnt){
                    for(var i=0;i<cnt; i++)
                        groupdownid[i].checked = do_check;
               }else
                  groupdownid.checked = do_check;
              }

             function deleteYN(){  
                        
              document.deleteg.action="Delete";   
              alert("asdadsasd");            
             if(typeof(deleteg.elements['groupdownid'])=='undefined'){
             return false;
             }else{
                  if(deleteg.groupdownid.checked==true){  
                     if(!confirm('确实要删除吗?')) 
                        return false;
                     else
                       {
                        document.deleteg.submit(); 
                        //return true;
                        }
                  }else{ 
                       for(var i=0;i<deleteg.groupdownid.length; i++){
                           if(deleteg.groupdownid[i].checked==true){
                                 if(!confirm('确实要删除吗?')) 
                                 return false;
                                 else
                                 {
                                 document.deleteg.submit(); 
                                 //return true;
                                 } 
                            }
                       }
                  }
                return false; 
              }
              return false;
            }
            
           
          </script>
         <h1>删除群成员</h1>
        
           <p>
              <label>群组<%=gname %>(<%=groupid %>)共有<%=groupdowns.size() %>个群成员</label><br><br>
              <input type="text" name="upid" value='<%=groupid%>' style="display: none;">
              <label>请勾选你要删除的群成员：<br></label><br>
              <%
               for(int i=0;i<groupdowns.size();i++){
               %>
              <label><%=groupdowns.get(i).getGroupname()%>(<%=groupdowns.get(i).getGroupid()%>)</label>
              <input type="checkbox" name="groupdownid" value='<%=groupdowns.get(i).getGroupid()%>'  /> 
              <br><br>         
              <%
               }
               %>                 
              
           </p>
           
           
          <p>
            <input type="button" name="delete" value="删除" onclick="deleteYN();">
            <input type="checkbox" name="groupdownid" value="0" title="点击全选或者全不选" onclick="CheckAll(this.form.elements['groupdownid'], this.checked)" >
          </p>
           
         
    </form>
    
  </body>
</html>
