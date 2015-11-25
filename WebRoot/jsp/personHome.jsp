<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,com.zzu.modle.User,com.zzu.daoImp.UserDaoImp,com.zzu.modle.Receive,com.zzu.daoImp.ReceiveDaoImp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页</title>
<%-- <jsp:include page="perHomeLeft.jsp"></jsp:include>  --%>
<%
    String userid=(String)request.getAttribute("userid");
	User user=new User();
	user=new UserDaoImp().getUser(userid);//得到用户的信息    
	
	//得到用的接受的所有信息
	ArrayList<Receive> recList=new ReceiveDaoImp().getAllReceiveMeg(Long.parseLong(userid));
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/perhome.css" type="text/css" />
</head>
<body style="font-size: 12px;">
	<div style="margin-top: 30px; margin-left: 15px;">
		<div>
			您好!&nbsp;&nbsp;362842353@qq.com
			<div style="margin-left: 275px; color: blue;">
				<a href="" style="color: blue;">发布消息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="" style="color: blue;">发送私信</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="" style="color: blue;">个人信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=request.getContextPath()%>/jsp/login.jsp"
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
		<hr noshade>
	</div>

	<div id="wrap">
		<table id="table_layout" border="1">
			<tr>
				<td width="230px" valign="top" bordercolor="white">

					<div style="bottom: 0px; width: 90%; text-align: left;">
						<div style="margin-left: 80px; margin-right: 12px;">
							<div style="background: #F3F3F3; height: 568px; width: 230px;">
								<div style="background: white;">
									<a href=""><img
										src="<%=request.getContextPath()%>/images/person1.jpg"
										width="130px" height="100px" border="1"></a>
								</div>
								<div
									style="text-align: center; background: #BCBCBC; height: 36px; width: 230px; color: white;">
									<h1>成员列表</h1>
								</div>
							</div>
						</div>
					</div>
				</td>
				<!-- 消息显示 -->
				<td id="left_b" bordercolor="white" align="left" valign="top">
					<%
						for (int i = 0; i < 10; i++) {
					%>
					<div>
						<p>
							<b style="font-size: 16px;">开始申请助学金和励志奖学金</b>
						</p>
						<p>张老师：</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp; <img
								src="<%=request.getContextPath()%>/images/person1.jpg"
								width="50px" height="30px" border="1">
							资助管理系统已经审核完成，通知励志和助学金的同学，今天开始申请励志和助学金，
							填写理由和完善信息，明天晚上前完成,请同学们及时完成。<a href="<%=request.getContextPath()%>/css/perhome.css" style="color: blue;">(文件名)附件</a>
						</p>
						<p style="color: gray; text-align: right;">
							<input type="button" name="consume" value="收到确认"
								onkeydown="javascript:this.display='none';"
								style="width: 55px; height: 23px; text-align: right;"> <br>
							辅导员 发表于2015-12-25 11:30
						</p>
						<hr>
					</div> 
					<%}%>
				</td>
				<!-- 私信显示 -->
				<td id="right_b" align="left" valign="top" bordercolor="white">
					<div style="background: #F3F3F3; height: 560px; width: 290px;">
						<div
							style="text-align: center; background: #BCBCBC; height: 36px; width: 290px; color: white;">
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
				</td>
			</tr>
		</table>
	</div>
</body>
</html>