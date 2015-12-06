/**
 * 
 */

function checkLoginInfo() {
	var flag = false;
	$.ajax({
		type: "POST",
		url: "LoginCheck",
		data: {"info": $("#info").attr("value"), "pword":$("#pword").attr("value")},
		async:false,
		success: function(data) {
			var obj = eval("("+data+")");
			flag = obj.msg;
		}
	});
	if(!flag)
		alert("用户或者密码错误!请确认后重新输入");
	return flag; 
}