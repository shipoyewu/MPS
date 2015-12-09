<%@page import="com.zzu.daoImp.ChoiceDaoImp"%>
<%@page import="com.zzu.daoImp.VoteDaoImp"%>
<%@page import="com.zzu.modle.Choice"%>
<%@page import="com.zzu.modle.Content"%>
<%@page import="com.zzu.daoImp.ContentDaoImp"%>
<%@page import="com.zzu.daoImp.GroupDaoImp"%>
<%@page import="com.zzu.modle.Receive"%>
<%@page import="com.zzu.modle.Vote"%>
<%@page import="com.zzu.modle.Message"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MessageInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- <link rel="stylesheet" type="text/css" href="css/styles.css"> -->
	<script type="text/javascript" src="js/MessageInfo.js"></script>
	<script type="text/javascript">
	
	</script>
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
	.ptime{
		color: gray; 
		text-align: right;
	}
	</style>
  </head>
  
  <body style="background-color: #ffffff">
    <%
    	GroupDaoImp GD = new GroupDaoImp();
    	VoteDaoImp VD = new VoteDaoImp();
    	ChoiceDaoImp CHD = new ChoiceDaoImp();
    	Message me=(Message)session.getAttribute("message");
    	Vote v = (Vote)session.getAttribute("vote");
    	long  rid= Long.parseLong((String)session.getAttribute("receiveid"));
    	String rname=GD.getGroup(rid).getGroupname();
    	String sname= GD.getUserName(me.getGroupid());
    	long sid = GD.getUserid(me.getGroupid());
    	ContentDaoImp CD = new ContentDaoImp();
    	Content con = CD.getContent(me.getContentid()); 
    	
    	session.removeAttribute("message");
    	session.removeAttribute("receivename");
    	String width = null;
    	
    	if(v != null){
    		width = "width: 70%;";
    	}
    	else{
    		width = "width: 100%;";
    	}
    	if(v != null){
    		session.removeAttribute("vote");
    	}
    %>
    <div>
    	<h1 align="center"><b><big>查看消息</big></b></h1>
    	<div style="<%= width%> float: left; display: inline;">
		        <div class="info">
		             <ul>
		             <li><b>发送者：</b><img src="userdata/<%=sid%>/icon.jpg"><%= sname%></li> 
		             <li><b>接收者 :</b><%=rname%></li>
		             <li><b>消息主题:<%=me.getMessagetitle()%> </b></li> 
		             <li><b>消息内容：</b><%=con.getText()%> </li>
		             
		             <li><b>消息图片：</b>
								<%
								String imgsrc = con.getImage();
								if(!(imgsrc == null || imgsrc.equals(""))){
									String[] sub = imgsrc.split("\\,");
									for(int k = 0;k < sub.length;k++){
										%>
											<img src="<%= sub[k] %>"> <br>
										<%}%>	
								<%}%>		  			
			  		</li>
		  			<li><b>消息文件：</b>
		  				<%
							String filesrc = con.getFile();
							if(!(filesrc == null || filesrc.equals(""))){
								String[] sub = filesrc.split("\\,");
								for(int k = 0;k < sub.length;k++){
								%>
									<a download="" href="<%= sub[k]%>">点击下载附件<%= k+1%></a> <br>
								<%}%>
							<%}%>
		  			</li>
	            	 <li>
	            	 	<%out.println(me.getMessageid()+" "+rid); %>
		  				<a class="ptime" href="javascript: setflag(<%=me.getMessageid()%>,<%=rid%>);">点击将消息标记为已读</a>
		  				<p class="ptime" ><%=me.getCreatetime().toLocaleString()%><p>
		  			</li>
		         	</ul>
		        </div>
		    </div>
		    <%
		    if(v!=null){
		     %>
		    <div style="width:30% right; display: inline;">
		    	<div class="info">
		    		<form action="participateVote" method="post">
		    		<%
		    		String type = null;
		    		ArrayList<Choice> choice = VD.getChoices(v.getVoteid());
		    		boolean ispart = VD.isparticipate(v.getVoteid(), rid);
		    		
		    		if(v.isIsmul()){
		    			type="checkbox";
		    		}
		    		else{
		    			type="radio";
		    		}
		    		 %>
			    		<ul style="margin-left: 20px;">
			    			<li><b>投票内容：</b> <%= v.getVotecontent()%></li>
							<li>投票选项：
								<ul style="margin-left: 40px">
								<%
								for(int i = 0;i < choice.size();i++){
									boolean  choiced = CHD.ifchoice(rid,choice.get(i).getChoiceid());
									String choeck = "";
									if(choiced){
										choeck = "checked=\"true\"";
									}
								%>
									<li> <input type="<%=type%>" name="choice" <%=choeck %> value="<%=choice.get(i).getChoiceid() %>"> <%= choice.get(i).getChocontent()%></li>								
								<%}%>
								</ul>
							</li>
							<%
							if(!ispart){
							%>
								<li>
								<input align="right" type="reset" value="重置">
								<input align="right" type="submit" value="确定提交"> 
								</li>
							<%}%>
			    		</ul>
		    		</form>
		    	</div>
		    </div>
		    <%}%>
	</div>
  </body>
</html>
