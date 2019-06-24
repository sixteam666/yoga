$("#login_button").click(function(){
	var loginName = $("#fullname").val();
	var g_password = $("#g_password").val();
	$.ajax({
		url:"/gym/login.do",
		type:"post",
		async:true,
		data:"arg="+loginName + "&g_password="+g_password,
		success:function(mes){
			if(mes == 1){
				window.location.href = "/html/gym/gymShow.html";
			}else{
				alert("用户名或密码错误");
			}
		}
	});
});