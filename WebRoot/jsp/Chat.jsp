<%@page import="com.zzu.modle.User"%>
<%@page import="com.zzu.daoImp.LetterDaoImp"%>
<%@page import="com.zzu.daoImp.UserDaoImp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>私信</title>
	<link rel="stylesheet" type="text/css" href="css/chat.css" />
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
	
    <div class="content">
    	<%
    		long userid = (Long)session.getAttribute("userid");
       		UserDaoImp UD = new UserDaoImp();
       		LetterDaoImp LD = new LetterDaoImp();
       		User u = UD.getUser(userid);
      	 	ArrayList<User> Rela = UD.getHaveRelation(userid);
      	 	String masrc = "userdata/"+userid+"/icon.jpg";
       	%>
        <div>
			<a id="user"><img src="<%=masrc%>" id="master" name="<%= userid%>"/> <%=u.getUsername() %> </a>
		</div>
        <div class="chatBox">
            <div class="chatLeft">
                <div class="chat01">
                    <div class="chat01_title">
                        <ul class="talkTo">
                            <li><a href="javascript:;"></a></li></ul>
                        <a class="close_btn" href="javascript:;"></a>
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
                                <label class="<%=cla%>">
                                </label>
                                <a href="javascript:;">
                                    <img src="<%=src%>"></a><a href="javascript:;" class="chat03_name"><%=v.getUsername()%></a>
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
<p></p>
<p></p>
</div>
    <br><br>

</body>
</html>