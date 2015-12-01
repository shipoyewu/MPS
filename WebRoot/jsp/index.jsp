<%@page import="com.zzu.daoImp.ContentDaoImp"%>
<%@page import="com.zzu.modle.Content"%>
<%@page import="com.zzu.service.manger"%>
<%@page import="com.zzu.daoImp.GroupDaoImp"%>
<%@page import="com.zzu.modle.Group"%>
<%@page import="com.zzu.daoImp.ReceiveDaoImp"%>
<%@page import="com.zzu.modle.Message"%>
<%@page import="com.zzu.daoImp.MessageDaoImp"%>
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
    
    <title>多级消息发布系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/chat.css" />
	<link rel="stylesheet" type="text/css" href="css/panel.css" />
	
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/chat.js"></script>
	
	<!--[if lt IE 7]>
	<script src="js/IE7.js" type="text/javascript"></script>
	<![endif]-->
	<!--[if IE 6]>
	<script src="js/iepng.js" type="text/javascript"></script>
	<script type="text/javascript">
	EvPNG.fix('body, div, ul, img, li, input, a, span ,label'); 
	</script>
	<![endif]-->
	
  </head>
  
  <body>
    <!-- Centered page --> 
   <%
    		session.setAttribute("userid", "1");
    		//long userid = Long.parseLong((String)session.getAttribute("userid"));
       		long userid=1l;
       		UserDaoImp UD = new UserDaoImp();
       		LetterDaoImp LD = new LetterDaoImp();
       		User u = UD.getUser(1l);
      	 	ArrayList<User> Rela = UD.getHaveRelation(userid);
      	 	String masrc = "userdata/"+userid+"/icon.jpg";
   %>
   	
	<div style="margin-top: 30px; margin-left: 15px;">
		<div>
			<div>
				<img src="<%= masrc %>"> <b> <%= u.getUsername() %></b>
			</div>
			<div style="margin-left: 275px; color: blue;">
				<a href="" style="color: blue;">发布消息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="right-panel-link" href="#right-panel">发送私信</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="" style="color: blue;">个人信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/jsp/login.jsp"
					style="color: blue;">注销</a>
				<%
					for (int i = 0; i < 49; i++) {
				%>
				&nbsp;
				<%
					}
				%>
				<a href="" style="color: blue;">下一页</a>
			</div>
		</div>
	</div>
	
	
   	<div id="showMessage" class="index_div_left" >
			<!-- 消息显示 -->
			<%
				GroupDaoImp GD = new GroupDaoImp();
				ArrayList<Group> glist = GD.findAllGroup(userid);
				ContentDaoImp CD = new ContentDaoImp();
				ReceiveDaoImp RD = new ReceiveDaoImp();
				for(int i = 0 ;i < glist.size();i++){
					String gname = glist.get(i).getGroupname();
					ArrayList<Message> mlist = RD.getAllUnReadMeg(glist.get(i).getGroupid());
					%>
					<b><%= gname %></b> <br>
					<%
					for(int j = 0;j < mlist.size();j++){
						String sendimg="userdata/"+GD.getUserid(mlist.get(j).getGroupid())+"/icon.jpg";
					 	Content content = CD.getContent(mlist.get(j).getContentid());
					 	Date q = mlist.get(j).getCreatetime();
					 	String t = q.toLocaleString();
 					 %>
						<div> <img src="<%= sendimg %>"> <b><%= GD.getGroup(mlist.get(i).getGroupid()).getGroupname() %></b></div>
						<p>
							<label>消息主题： </label><b style="font-size: 16px;"><%= mlist.get(i).getMessagetitle() %></b>
						</p>
						<p>
							<label>消息内容:<%= content.getText()%> </label><br>
							<label>消息图片:</label>
								<%
								String imgsrc = content.getImage();
								if(!(imgsrc == null || imgsrc.equals(""))){
									String[] sub = imgsrc.split("\\,");
									for(int k = 0;k < sub.length;k++){
										%>
											<img src="<%= sub[k] %>"> <br>
										<%}%>
									
								<%}%>
							<label>消息文件:</label> 
								<%
								String filesrc = content.getFile();
								if(!(filesrc == null || filesrc.equals(""))){
									String[] sub = filesrc.split("\\,");
									out.println(sub);
									for(int k = 0;k < sub.length;i++){
									%>
										<a href="http://<%= sub[k]%>"> 点击下载附件<%= k%></a> <br>
									<%
									}
								}
								%>
								<p style="color: gray; text-align: right;">
									<a href="javascript:setflag();">将改消息标记为已读<a>
									<label><%= t %></label>
								</p>
 						</p>
					<%}%>					
				<%}%>
   		</div>
	   	<div id ="showletter" class="index_div_right">
	   			<div style="background: #F3F3F3; height: 560px; width: 290px;">
					<div
						style="text-align: center; background: #BCBCBC; height: 36px; width:auto; color: white;">
					<h1>私信</h1>
					</div>
					<p>张老师：</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href=""><img
							src="<%=request.getContextPath()%>/images/person1.jpg"
							width="50px" height="30px" border="1"></a>
						资助管理系统已经审核完成，通知励志和助学金的同学，今天开始申请励志和助学金，
						填写理由和完善信息，明天晚上前完成,请同学们及时完成。
					</p>
					<p style="color: gray; text-align: right;">
						<a href="" style="color: blue;">回复</a> <br> 辅导员
						发表于2015-12-25 11:30
					</p>
					<hr>
				</div>
	   	</div>
  <!-- Left panel -->
  <div id="left-panel" class="panel">
    <h2>Left panel</h2>
    <p></p>
  </div>

  <!-- Right panel -->
  <div id="right-panel" class="panel">
    <h2>Right panel</h2>
	    <div class="content">
	    	
	        <div>
				<a id="user"><img src="<%= masrc %>" id="master" name="<%= userid %>"> <%=u.getUsername() %> </a>
			</div>
	        <div class="chatBox">
	            <div class="chatLeft">
	                <div class="chat01">
	                    <div class="chat01_title">
	                        <ul class="talkTo">
	                            <li><a href="javascript:;"></a></li></ul>
	                        <a class="close_btn" id="close-panel-bt"></a>
	                    </div>
	                    <div class="chat01_content">
	                    	<%
	                    		if(Rela!=null)
	                    		for(int i = 1;i <= Rela.size();i++){
	                    			String cc = "message_box mes"+i;
	                    	 %>
			                        <div class="<%=cc%>">
			                        </div>
			                 <%}%>       
	                        
	                    </div>
	                </div>
	                <div class="chat02">
	                    <div class="chat02_title">
	                        <a class="chat02_title_btn ctb01" href="javascript:;"></a>
	                            <embed width="15" height="16" flashvars="swfid=2556975203&amp;maxSumSize=50&amp;maxFileSize=50&amp;maxFileNum=1&amp;multiSelect=0&amp;uploadAPI=http%3A%2F%2Fupload.api.weibo.com%2F2%2Fmss%2Fupload.json%3Fsource%3D209678993%26tuid%3D1887188824&amp;initFun=STK.webim.ui.chatWindow.msgToolBar.upload.initFun&amp;sucFun=STK.webim.ui.chatWindow.msgToolBar.upload.sucFun&amp;errFun=STK.webim.ui.chatWindow.msgToolBar.upload.errFun&amp;beginFun=STK.webim.ui.chatWindow.msgToolBar.upload.beginFun&amp;showTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.showTipFun&amp;hiddenTipFun=STK.webim.ui.chatWindow.msgToolBar.upload.hiddenTipFun&amp;areaInfo=0-16|12-16&amp;fExt=*.jpg;*.gif;*.jpeg;*.png|*&amp;fExtDec=选择图片|选择文件"
	                                data="upload.swf" wmode="transparent" bgcolor="" allowscriptaccess="always" allowfullscreen="true"
	                                scale="noScale" menu="false" type="application/x-shockwave-flash" src="http://service.weibo.com/staticjs/tools/upload.swf?v=36c9997f1313d1c4"
	                                id="swf_3140">
	                        </a>
	                        <label class="chat02_title_t">
	                            <a href="chat.htm" target="_blank">聊天记录</a></label>
	                        <div class="wl_faces_box">
	                            <div class="wl_faces_content">
	                                <div class="title">
	                                    <ul>
	                                        <li class="title_name">常用表情</li><li class="wl_faces_close"><span>&nbsp;</span></li></ul>
	                                </div>
	                                <div class="wl_faces_main">
	                                    <ul>
	                                    <%
	                                    	for(int i = 1;i <= 60;i++){
	                                    		String src;
	                                    		if(i <= 9)
	                                    			src= "img/emo_0"+i+".gif";
	                                    		else
	                                    			src= "img/emo_"+i+".gif";
	                                    %>
	                                    	<li><a href="javascript:;"> <img src="<%=src%>" /></a></li>
	                                    <%}%>
	                                    </ul>
	                                </div>
	                            </div>
	                            <div class="wlf_icon">
	                            </div>
	                        </div>
	                    </div>
	                    <div class="chat02_content">
	                        <textarea id="textarea"></textarea>
	                    </div>
	                    <div class="chat02_bar">
	                        <ul>
	                            <li style="right: 5px; top: 5px;"><a href="javascript:;">
	                                <img src="img/send_btn.jpg"></a></li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	            <div class="chatRight">
	                <div class="chat03">
	                    <div class="chat03_title">
	                        <label class="chat03_title_t">
	                            成员列表</label>
	                    </div>
	                    <div class="chat03_content">
	                        <ul>
	                       	<%
	                       
	                       	if(Rela!=null)
	                       	for(int i = 0;i < Rela.size();i++){
	                       		User v = Rela.get(i);
	                       		String cla;
	                       		String src="userdata/"+v.getUserid()+"/icon.jpg";
	                       		if(LD.ifUnRead(v.getUserid(),u.getUserid())){
	                       			cla = "online";
	                       		}
	                       		else{
	                       			cla = "offline";
	                       		}
	                       	%>
	                       		<li id="<%=v.getUserid()%>">
	                                <label class="<%=cla%>"></label>
	                                <a href="javascript:;"> <img src="<%= src %>"></a><a href="javascript:;" class="chat03_name"><%= v.getUsername()%> </a>
	                            </li>
	                       		 
	                       	<%}%>
	                            
	                        </ul>
	                    </div>
	                </div>
	            </div>
	            <div style="clear: both;">
	            </div>
	        </div>
	    </div>
	<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    
  </div>
  <script src="js/jquery.panelslider.js"></script>
  <script>
    $('#left-panel-link').panelslider();
    $('#right-panel-link').panelslider({side: 'right', clickClose: true, duration: 200 });
    $('#close-panel-bt').click(function() {
      $.panelslider.close();
    });
  </script>
  </body>
</html>
