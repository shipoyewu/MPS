/**
 * 
 */

function checkmail(input) {
	var flag = false;
	$.ajax({
		type: "post",
		url: "servlet/checkMail",
		data: {"mail":input},
		async:false,
		success: function(data){
			flag = data.msg;
		}
	});
	return flag;	
	
}


function checkFormat() {
	//加入
}
function checkInput() {
	var flag = true;
	var input= $("#").value;//#后面写你的文本框的id
	
	flag = checkFormat();
	if(!flag){
		//显示输入格式不正确的信息	
		return false;
	}
	
	flag = checkmail(input);
	if(!flag){
		//显示mail已经被使用的内容
	}
	return flag;
}