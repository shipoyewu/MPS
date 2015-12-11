<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.zzu.daoImp.GroupDaoImp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js">
</script> --%>
</head>
<style>
.auto_hidden {
	width: 204px;
	position: absolute;
	display: none;
}

.auto_show {
	width: 204px;
	border: 1px solid #333;
	position: absolute;
	z-index: 9999; /* 设置对象的层叠顺序 */
	display: block;
}

.auto_onmouseover {
	font-family:Calibri, Arial, sans-serif;
	font-size:18px;
	background-color: highlight;
	width: 100%;
}

.auto_onmouseout {	
	font-family:Calibri, Arial, sans-serif;
	font-size:18px;
	width: 100%;
	background-color: #ffffff;
}
</style>
<script>
var $ = function(id) {
	return "string" == typeof id ? document.getElementById(id) : id;
}
var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	}
}
function AutoComplete(obj, autoObj, arr) {
	this.obj = $(obj); //输入框
	this.autoObj = $(autoObj);//DIV的根节点
	this.value_arr = arr; //不要包含重复值
	this.index = -1; //当前选中的DIV的索引
	this.search_value = ""; //保存当前搜索的字符
}
AutoComplete.prototype = {
	//初始化DIV的位置
	init : function() {
		this.autoObj.style.left = this.obj.offsetLeft + "px";
		this.autoObj.style.top = this.obj.offsetTop + this.obj.offsetHeight
				+ "px";
		this.autoObj.style.width = this.obj.offsetWidth - 2 + "px";//减去边框的长度2px
	},
	//删除自动完成需要的所有DIV
	deleteDIV : function() {
		while (this.autoObj.hasChildNodes()) {
			this.autoObj.removeChild(this.autoObj.firstChild);
		}
		this.autoObj.className = "auto_hidden";
	},
	//设置值
	setValue : function(_this) {
		return function() {
			_this.obj.value = this.seq.match("[0-9]+");
			_this.autoObj.className = "auto_hidden";
		}
	},
	//模拟鼠标移动至DIV时，DIV高亮
	autoOnmouseover : function(_this, _div_index) {
		return function() {
			_this.index = _div_index;
			var length = _this.autoObj.children.length;
			for (var j = 0; j < length; j++) {
				if (j != _this.index) {
					_this.autoObj.childNodes[j].className = 'auto_onmouseout';
				} else {
					_this.autoObj.childNodes[j].className = 'auto_onmouseover';
				}
			}
		}
	},
	//更改classname
	changeClassname : function(length) {
		for (var i = 0; i < length; i++) {
			if (i != this.index) {
				this.autoObj.childNodes[i].className = 'auto_onmouseout';
			} else {
				this.autoObj.childNodes[i].className = 'auto_onmouseover';
				this.obj.value = this.autoObj.childNodes[i].seq.match("[0-9]+");
			}
		}
	},
	//响应键盘
	pressKey : function(event) {
		var length = this.autoObj.children.length;
		//光标键"↓"
		if (event.keyCode == 40) {
			++this.index;
			if (this.index > length) {
				this.index = 0;
			} else if (this.index == length) {
				this.obj.value = this.search_value;
			}
			this.changeClassname(length);
		}
		//光标键"↑"
		else if (event.keyCode == 38) {
			this.index--;
			if (this.index < -1) {
				this.index = length - 1;
			} else if (this.index == -1) {
				this.obj.value = this.search_value;
			}
			this.changeClassname(length);
		}
		//回车键
		else if (event.keyCode == 13) {
			this.autoObj.className = "auto_hidden";
			this.index = -1;
		} else {
			this.index = -1;
		}
	},
	//程序入口
	start : function(event) {
		if (event.keyCode != 13 && event.keyCode != 38
				&& event.keyCode != 40) {
			this.init();
			this.deleteDIV();
			this.search_value = this.obj.value;
			var valueArr = this.value_arr;
			valueArr.sort();
			if (this.obj.value.replace(/(^\s*)|(\s*$)/g, '') == "") {
				return;
			}//值为空，退出
			try {
				var reg = new RegExp("(" + this.obj.value + ")", "i");
			} catch (e) {
				return;
			}
			var div_index = 0;//记录创建的DIV的索引
			for (var i = 0; i < valueArr.length; i++) {
				if (reg.test(valueArr[i])) {
					var div = document.createElement("div");
					div.className = "auto_onmouseout";
					div.seq = valueArr[i];
					div.onclick = this.setValue(this);
					div.onmouseover = this.autoOnmouseover(this, div_index);
					div.innerHTML = valueArr[i].replace(reg,
							"<strong>$1</strong>");//搜索到的字符粗体显示//改写
					this.autoObj.appendChild(div);
					this.autoObj.className = "auto_show";
					div_index++;
				}
			}
		}
		this.pressKey(event);
		window.onresize = Bind(this, function() {
			this.init();
		});
	}
}
</script>

<body>
	<div align="center" style="padding-top: 50px;">
		<input type="text"
			style="width: 300px; height: 20px; font-size: 14pt;"
			placeholder="请输入群组名" id="search" onkeyup="autoComplete.start(event)">
		<button type="submit" name="submit" id="button"
			style="width: 70px; height: 26px; background-color: #5858FF;">搜索</button>
	</div>
	<div id="buttonClick"></div>
	<div class="auto_hidden" id="auto">
		<!--自动完成 DIV-->
	</div>
	<%
		String groupInfo[]=new GroupDaoImp().searchAll();
	%>
	<script>
		var codes=new Array();
		<%if (groupInfo!= null) {
				for (int i = 0; i < groupInfo.length; i++) {%>
		    codes[<%=i%>]='<%=groupInfo[i]%>';//将java数组转成js数组
		
	<%}
			}%>
		var autoComplete = new AutoComplete('search', 'auto', codes);
	</script>
</body>
</html>