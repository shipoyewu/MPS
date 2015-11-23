<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建投票</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/styles.css">
	<link rel="stylesheet" type="text/css" href="./css/laydate.css">
	<script type="text/javascript" src="./js/code.js" charset="utf-8"></script>
	<script type="text/javascript" src="./js/Vote.js" charset="UTF-8"></script>
  	
  </head>
  
  <body >
	<form action="servlet/addVote" onsubmit="check()" method="post">
		<div align="center">
		<table>
			<tr>
				<td>投票主题:</td>
				<td><textarea class="votetitle" name="votetitle"></textarea></td>
			</tr>

			<tr>
				<td>投票选项:</td>
				<td><input name= "option" class="voteoption" type="text" placeholder="选项1"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div id="waitforadd">
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<input type="button" id="add" value="+添加选项">
			</tr>
			<tr>
				<td>
				选择模式：
				</td>
				<td>
				<input type="radio" name="anon" value="single" checked="checked">单选
				<input type="radio" name="anon" value="multi" >多选
				</td>
			</tr>
			<tr>
				<td>
				截止时间：
				</td>
				<td >
				<input type="text" id="J-xl" name="enddate">
				<script type="text/javascript" src="./js/laydate.dev.js"></script>
				<script type="text/javascript">
			        laydate({
			            elem: '#J-xl'
			        });

   				 </script>
				<select name="hour">
					<%for(int i = 1;i <= 24;i++){ %>
						<option value ="<%=i%>"><%=i%></option>
					<%}%>	
				</select>
					:
				<select>
					<%for(int i = 1;i <= 60;i++){ %>
						<option value ="<%=i%>"><%=i%></option>
					<%}%>
				</select>
				</td>
			</tr>
			<tr>
				<td>
					投票隐私：
				</td>
				<td>
					
				<input type="radio" name="public" value="no" checked="checked">匿名
				<input type="radio" name="public" value="yes">公开<label style="color: #BCBCBC">    发布者可以查看投票人</label>
				
				</td>
			</tr>
		</table>
			<input type="submit" style="" value="创建" align="right"> <input type="reset" align="right" value="取消">
		</div>
	</form>
</body>
</html>
