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
    <div class="content">
    
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
			for(int i = 0 ;i < glist.size();i++){
				
				String gname = glist.get(i).getGroupname();
				ArrayList<Message> mlist = RD.getAllUnReadMeg(glist.get(i).getGroupid());
				if(mlist.size()!=0){
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
			  		<%
			  		for(int j = 0;j < mlist.size();j++){
						String sendimg="userdata/"+GD.getUserid(mlist.get(j).getGroupid())+"/icon.jpg";
					 	Content content = CD.getContent(mlist.get(j).getContentid());
					 	Date q = mlist.get(j).getCreatetime();
					 	String t = q.toLocaleString();
				 	%>
				 	
			  		<ul>
			  			<li><img src="<%= sendimg %>"><b><%= GD.getGroup(mlist.get(i).getGroupid()).getGroupname() %></b><li>
			  			<li>消息主题：<b><%= mlist.get(i).getMessagetitle() %> </b></li>
			  			<li>消息内容：<%= content.getText()%></li>
			  			<li>消息图片：
								<%
								String imgsrc = content.getImage();
								if(!(imgsrc == null || imgsrc.equals(""))){
									String[] sub = imgsrc.split("\\,");
									for(int k = 0;k < sub.length;k++){
										%>
											<img src="<%= sub[k] %>"> <br>
										<%}%>	
								<%}%>		  			
			  			</li>
			  			<li>消息文件：
			  				<%
								String filesrc = content.getFile();
								if(!(filesrc == null || filesrc.equals(""))){
									String[] sub = filesrc.split("\\,");
									out.println(sub.length);
									for(int k = 0;k < sub.length;k++){
									%>
										<a download="" href="<%= sub[k]%>">点击下载附件<%= k%></a> <br>
									<%}%>
								<%}%>
			  			</li>
			  			<li>
			  				<a href="javascript: setflag();" style="text-align: right;">将改消息标记为已读</a>
							<p class="ptime"><%=t%></p>
			  			</li>
			  		</ul>
			  		<%}%>
			  	</div>
			  	<%}%>
		  <%}%>
	  	</div>
  	</div>
  </body>
  <script type="text/javascript">
	var mSwitch = new MenuSwitch("menuDiv");
	mSwitch.setDefault(0);
	mSwitch.setPrevious(false);
	mSwitch.init();
  </script>
</html>
