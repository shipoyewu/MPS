<%@page import="com.zzu.daoImp.LetterDaoImp"%>
<%@page import="com.zzu.modle.User"%>
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
    
    <title>私信</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
        <div class="chatBox">
            <div class="chatLeft">
                <div class="chat01">
                    <div class="chat01_title">
                        <ul class="talkTo">
                            <li><a href="javascript:;">王旭</a></li></ul>
                        <a class="close_btn" href="javascript:;"></a>
                    </div>
                    <div class="chat01_content">
                        <div class="message_box mes1">
                        </div>
                        <div class="message_box mes2">
                        </div>
                        <div class="message_box mes3" style="display: block;">
                        </div>
                        <div class="message_box mes4">
                        </div>
                        <div class="message_box mes5">
                        </div>
                        <div class="message_box mes6">
                        </div>
                        <div class="message_box mes7">
                        </div>
                        <div class="message_box mes8">
                        </div>
                        <div class="message_box mes9">
                        </div>
                        <div class="message_box mes10">
                        </div>
                    </div>
                </div>
                <div class="chat02">
                    <div class="chat02_title">
                        <a class="chat02_title_btn ctb01" href="javascript:;"></a>
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
                                   	String src="img/emo_";
                               		for(int img = 1;img <= 60;img++){
                               			String srcloc= src+img+".gif";
                                     %>
										<li><a href="javascript:;"><img src="<%=srcloc%>" /></a></li>                                     
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
                        UserDaoImp UD = new UserDaoImp();
                        User user = (User)session.getAttribute("user");
                        LetterDaoImp LD = new LetterDaoImp();
                        
                       	ArrayList<User> hr = UD.getHaveRelation(user.getUserid());
                       	for(int i = 0;i < hr.size();i++){
                       		String cla;
                       		if(LD.ifUnRead(user.getUserid(),hr.get(i).getUserid())){
                       			cla = "online";
                       		}
                       		else{
                       			cla = "offline";
                       		}
                       	%>
                       	
                       	
                       	<li>
                       		<label class=#{cla}></label>
                       		
                       	</li>
                       		
                       	
                        }%>
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2013.jpg"></a><a href="javascript:;" class="chat03_name">刘秀</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2014.jpg"></a><a href="javascript:;" class="chat03_name">陈诚</a>
                            </li>
                            <li class="choosed">
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2015.jpg"></a><a href="javascript:;" class="chat03_name">王旭</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2016.jpg"></a><a href="javascript:;" class="chat03_name">张灵</a>
                            </li>
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2017.jpg"></a><a href="javascript:;" class="chat03_name">吴敬</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2018.jpg"></a><a href="javascript:;" class="chat03_name">王海东</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2019.jpg"></a><a href="javascript:;" class="chat03_name">郑小勇</a>
                            </li>
                            <li>
                                <label class="online">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2020.jpg"></a><a href="javascript:;" class="chat03_name">张珊珊</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2021.jpg"></a><a href="javascript:;" class="chat03_name">刘强</a>
                            </li>
                            <li>
                                <label class="offline">
                                </label>
                                <a href="javascript:;">
                                    <img src="img/head/2022.jpg"></a><a href="javascript:;" class="chat03_name">程海斌</a>
                            </li>
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

  </body>
</html>
