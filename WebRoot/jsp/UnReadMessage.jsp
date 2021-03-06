<%@page import="com.zzu.modle.Vote"%>
<%@page import="com.zzu.daoImp.VoteDaoImp"%>
<%@page import="com.zzu.modle.Content"%>
<%@page import="com.zzu.modle.Message"%>
<%@page import="com.zzu.modle.Group"%>
<%@page import="com.zzu.daoImp.ReceiveDaoImp"%>
<%@page import="com.zzu.daoImp.GroupDaoImp"%>
<%@page import="com.zzu.daoImp.ContentDaoImp"%>
<%@page import="com.zzu.modle.User"%>
<%@page import="com.zzu.daoImp.LetterDaoImp"%>
<%@page import="com.zzu.daoImp.UserDaoImp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UnReadMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript" src="js/menuswitch.js"> </script>
	
  </head>
  
  <body>
    
    <%
   		long userid = Long.parseLong((String)session.getAttribute("userid"));
   		UserDaoImp UD = new UserDaoImp();
   		LetterDaoImp LD = new LetterDaoImp();
   		User u = UD.getUser(1l);
  	 	ArrayList<User> Rela = UD.getHaveRelation(userid);
  	 	String masrc = "userdata/"+userid+"/icon.jpg";
   	%>
	  	<div class="content_resize">
		  	<div style="margin-left: 20px">
		  	<a href="javascript:;" id="pageChangeTop">下一页</a>
			<a href="javascript:;" id="pageChangeButtom" style="display:none;">上一页</a>
		  	</div>
		  	
	  		<%
			GroupDaoImp GD = new GroupDaoImp();
			ArrayList<Group> glist = GD.findAllGroup(userid);
			ContentDaoImp CD = new ContentDaoImp();
			ReceiveDaoImp RD = new ReceiveDaoImp();
			int wh = 1;
			boolean flag = false;
			for(int i = 0 ;i < glist.size();i++){
				String gname = glist.get(i).getGroupname();
				ArrayList<Message> mlist = RD.getAllUnReadMeg(glist.get(i).getGroupid());
				if(mlist.size()!=0){
					flag = true; 
					String dip = "";
					if((wh-1)/5==0){
						dip = "disply: block";
					}
					else{
						dip = "disply: none";
					}
			%>
			
				
			  	<div class="menuDiv" id="page<%=wh++%>" style="<%=dip %>">
			  		<h3><b><%= gname%> </b></h3>
			  		<ul>
				  		<%
			  		for(int j = 0;j < mlist.size();j++){
						String sendimg="userdata/"+GD.getUserid(mlist.get(j).getGroupid())+"/icon.jpg";
					 	Content content = CD.getContent(mlist.get(j).getContentid());
					 	Date q = mlist.get(j).getCreatetime();
					 	String t = q.toLocaleString();
					 	VoteDaoImp VD = new VoteDaoImp();
					 	Vote vote = VD.getVoteByMessage(mlist.get(j).getMessageid());
				 	%>
			  			<li><img src="<%= sendimg %>" style="width: 58px;height: 58px;"><b><%= GD.getGroup(mlist.get(j).getGroupid()).getGroupname() %></b><li>
			  			<li>消息主题：<b><%= mlist.get(j).getMessagetitle() %> </b></li>
			  			<li>消息内容：<%= content.getText()%></li>
			  			<li>消息图片：
								<%
								String imgsrc = content.getImage();
								if(!(imgsrc == null || imgsrc.equals(""))){
									String[] sub = imgsrc.split("\\,");
									for(int k = 0;k < sub.length;k++){
										%>
											<img src="<%= sub[k] %>" style="width: 68px;height: 68px;"> <br>
										<%}%>	
								<%}%>		  			
			  			</li>
			  			<li>消息文件：
			  				<%
								String filesrc = content.getFile();
								if(!(filesrc == null || filesrc.equals(""))){
									String[] sub = filesrc.split("\\,");
									for(int k = 0;k < sub.length;k++){
									%>
										<a download="" href="<%= sub[k]%>">点击下载附件<%= k+1%></a> <br>
									<%}%>
								<%}%>
			  			</li>
			  			<%
				  			if(vote!=null){
				  				%>
				  				<li> 投票： <%=vote.getVotecontent() %></li>
				  				<%
				  			}
			  			%>
			  			<li>
			  				<div class="me">
			  					<table>
			  						<tr>
			  							<td>
			  								<a href="MessageInfo?messageid=<%=mlist.get(j).getMessageid() %>&receive=<%= glist.get(i).getGroupid()%>">点击查看消息详情</a>
			  							</td>
			  							<td>
			  								<a href="javascript: setflag(<%=mlist.get(j).getMessageid()%>,<%=glist.get(i).getGroupid()%>);">点击将消息标记为已读</a>
			  							</td>
			  						</tr>
			  					</table>
			  				</div>
			  			</li>
			  			<li>
			  				<p class="ptime"><%=t%><p>
			  			</li>
			  		<%}%>
			  		</ul>
			  	</div>
			  	<%}%>
		  <%}%>
		  <%if(!flag){%>
		  		<div class="menuDiv" id="page<%=wh++%>" style="disply: block;">
		  		<h3><b>这个世界终于清静了...</b></h3>
		  		<ul>
		  			<li><img src="images/nomessage.jpg" width="100%" height="80%"></li>
		  		</ul>
		  		</div>
		  <%}%>
	  	</div>
  </body>
  <script type="text/javascript">
	var mSwitch = new MenuSwitch("menuDiv");
	mSwitch.setDefault(0);
	mSwitch.setPrevious(false);
	mSwitch.init();
  </script>
</html>
