/**
 * 
 */
function check(){
	
	var username=document.getElementById("username").value;
	var tel=document.getElementById("tel").value;
	var password1=document.getElementById("pw1").value;
	var password2=document.getElementById("pw2").value;
	var mail=document.getElementById("email").value;
	//alert(password1 +" "+password2+" ");
    if(username=="" || tel==""||password1==""||password2==""||mail==""){
         alert("选项不得为空！");
   		return false;
  	}
  	
    
	if(password1!=password2){
        alert("密码不一致！");
        return false;
	}
	
	if(!tel.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/)){
		alert("手机格式错误！");
		return false;
	}
	
	var flag1 = false,flag2 = false;
	
	$.ajax({
		type: "post",
		url: "RegistCheck",
		data: {"mail":mail,"tel":tel},
		async:false,
		success: function(data){
			var obj = eval("(" + data+")");
			flag1 = obj.mail;
			flag2 = obj.tel;
		}
	});
	
	if(flag1 == true){
		alert("该邮箱已被注册！");
		return false;
	}
	if(flag2 == true){
		alert("该手机号已被注册!");
		return false;
	}
	return true; 
}