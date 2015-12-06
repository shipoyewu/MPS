/**
 * 
 */
function checkMail() {
	var flag = false;
	$.ajax({
		type: "post",
		url: "checkMail",
		data: {"mail":$("#mail").attr("value")},
		async:false,
		success: function(data){
			var obj = eval("("+data+")");
			flag = obj.msg;
		}
	});
	if(!flag) alert("该邮箱不存在！请验证后重新输入！");
	return flag;	
}
