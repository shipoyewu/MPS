/**
 * 
 */

function checkCreate() {
	if(document.getElementById("groupname").value == ""){
		alert("群组名不得为空！");
		return false;
	}
	alert("创建已提交，刷新整个页面可同步刷新结果！");
	return true;
}
function validate_required(field, alerttxt) {
    with(field) {
        if (value == "" || isNaN(value)) {
            alert(alerttxt);
            return false;
        } else {
            return true;
        }
    }
}

function validate_form1(expandgroup) {
    with(expandgroup) {
        if (validate_required(groupdownid, "\u7fa4\u7ec4\u0049\u0044\u5fc5\u987b\u4e3a\u6570\u5b57\u4e32\uff01") == false) {
            groupdownid.focus();
            return false;
        }
    }
}

function validate_required(field, alerttxt) {
    with(field) {
        if (value == "" || isNaN(value)) {
            alert(alerttxt);
            return false;
        } else {
            return true;
        }
    }
}

function validate_form(applygroup) {
    with(applygroup) {
        if (validate_required(groupupid, "\u7fa4\u7ec4\u0049\u0044\u5fc5\u987b\u4e3a\u6570\u5b57\u4e32\uff01") == false) {
            groupupid.focus();
            return false;
        }
    }
}
function CheckAll(groupid, do_check) {
    if (typeof(groupid) == 'undefined') return;
    var cnt = (typeof(groupid.length) != 'undefined') ? groupid.length: 0;
    if (cnt) {
        for (var i = 0; i < cnt; i++) groupid[i].checked = do_check;
    } else groupid.checked = do_check;
}

function deleteYN() {
    //if(YN(this.form.elements['delBox'], this.checked)){if(!confirm('ȷʵҪɾ����?')) return false;}else return false;
    document.deletegroup.action = "DeleteGroup";

    if (typeof(deletegroup.elements['groupid']) == 'undefined') {
        return false;
    } else {
    	var fg = false;
    	for (var i = 0; i < deletegroup.groupid.length; i++) {
			if (deletegroup.groupid[i].checked == true){
            	fg = true;
            	break;
			}
        }
    	if(!fg){
    		alert("请至少选择一个群组！");
    	}
    	else{
    		if (!confirm('\u786e\u5b9e\u8981\u5220\u9664\u5417\u003f')) return false;
            else {
            	alert("页面已提交，刷新整个页面课同步结果！");
                document.deletegroup.submit();
                //return true;
            }
    	}
        return false;
    }
    return false;
}

function Modify() {
    //alert("asdadsasd");
    document.deletegroup.action = "jsp/ModifyGroup.jsp";
    document.deletegroup.submit();
}

var $ = function(id) {
	return "string" == typeof id ? document.getElementById(id) : id;
};
var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	};
};
function AutoComplete(obj, autoObj, arr) {
	this.obj = $(obj); //输入框
	this.autoObj = $(autoObj);//DIV的根节点
	this.value_arr = arr; //不要包含重复值
	this.index = -1; //当前选中的DIV的索引
	this.search_value = ""; //保存当前搜索的字符
};
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
};
