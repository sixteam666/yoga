var phoneTest = new RegExp("^[1][3,4,5,7,8][0-9]{9}$");
var mailTest = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
var pwdTest = new RegExp("^[a-z0-9]{8,}$");
var smsTest = new RegExp("^[0-9]{6}$");

// 登录
$("#login_button").click(function(){
	var loginName = $("#fullname").val();
	var g_password = $("#g_password").val();
	// 如果登录账号格式错误，则不执行ajax
	if(!phoneTest.test(loginName) && !mailTest.test(loginName)){
		$("#login_tips").text("邮箱或手机号格式错误");
		return;
	}
	if(!pwdTest.test(g_password) || g_password == ""){
		return;
	}
	$.ajax({
		url:"/gym/login.do",
		type:"post",
		async:true,
		data:"arg="+loginName + "&g_password="+g_password,
		success:function(mes){
			if(mes == 1){
				window.location.href = "/html/gym/msgShow.html";
			}else{
				alert("用户名或密码错误");
			}
		}
	});
});