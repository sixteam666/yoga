var emailRegForm = "<div class='row form-group'><div class='col-md-12'><label for='fullname'><span class='fon'>邮箱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt name_0'></span></label><input type='text' id='reg_email' class='form-control' placeholder='请输入邮箱' onblur='emailVerfication()' onfocus=\"inputFocus('#reg_email')\"></div></div><div class='row form-group'><div class='col-md-12'><label for='date-start'><span class='fon'>密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt pwd_1'></span></label><input type='password' id='password_1' class='form-control' placeholder='请输入密码' onblur='passwordVerfication()' onfocus=\"inputFocus('#password_1')\"></div></div><div class='row form-group'><div class='col-md-12'><label for='date-start'><span class='fon'>确认密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt pwd_2'></span></label><input type='password' id='password_2' class='form-control' placeholder='请输入密码' onblur='pwdVerfication2()' onfocus=\"inputFocus('#password_2')\"></div></div><div class='row form-group'><div class='col-md-12'><input type='button' id='gymReg' class='btn btn-primary btn-block' value='注册' onclick=\"register('#reg_email')\"></div></div>";
var phoneRegForm = "<div class='row form-group'><div class='col-md-12'><label for='fullname'><span class='fon'>手机号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt name_0'></span></label><input type='text' id='reg_phone' class='form-control' placeholder='请输入手机号' onblur='phoneVerfication()' onfocus=\"inputFocus('#reg_phone')\"></div></div><div class='row form-group'><div class='col-md-12'><label for='fullname'><span class='fon'>短信验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt sms'></span></label><input type='text' id='form-sms' class='form-control' placeholder='请输入短信验证码' onblur='smsVerfication()' onfocus=\"inputFocus('#form-sms')\"><button type='button' id='button-sms' onclick='timing()'>获取短信验证码</button><input type='hidden' id='sms_time' value='0'></div></div><div class='row form-group'><div class='col-md-12'><label for='date-start'><span class='fon'>密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt pwd_1'></span></label><input type='password' id='password_1' class='form-control' placeholder='请输入密码' onblur='passwordVerfication()' onfocus=\"inputFocus('#password_1')\"></div></div><div class='row form-group'><div class='col-md-12'><label for='date-start'><span class='fon'>确认密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='regPrompt pwd_2'></span></label><input type='password' id='password_2' class='form-control' placeholder='请输入密码' onblur='pwdVerfication2()' onfocus=\"inputFocus('#password_2')\" ></div></div><div class='row form-group'><div class='col-md-12'><input type='button' id='gymReg' class='btn btn-primary btn-block' value='注册'  onclick=\"register('#reg_phone')\"></div></div>";
var phoneTest = new RegExp("^[1][3,4,5,7,8][0-9]{9}$");
var mailTest = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
var pwdTest = new RegExp("^[a-z0-9]{8,}$");
var smsTest = new RegExp("^[0-9]{6}$");

/* 注册 返回值：1：注册成功，0：注册失败 -1：登录名格式错误 -2:用户名已存在 */
function register(obj){
	var regName = $(obj).val();
	var g_password = $("#password_1").val();
	var passwordBoo = passwordVerfication();
	var password2Boo = pwdVerfication2();
	var regNameBoo = false;
	var smsBoo = true;
	if(obj == "#reg_email"){ // 如果使用邮箱注册，则获取以下验证信息
		regNameBoo = emailVerfication();
	}else{ // 如果使用电话注册，则获取以下验证信息
		regNameBoo = phoneVerfication();
		smsBoo = smsVerfication();
	}
	// 如果注册的信息未通过验证则退出本方法
	if(!(smsBoo && passwordBoo && password2Boo && regNameBoo)){
		return;
	}
	$.ajax({
		url:"/gym/reg.do",
		type:"post",
		async:true,
		data:"regName="+regName + "&g_password=" + g_password,
		success:function(mes){
			// console.info(mes);
			if(mes == 1){
				if(confirm("注册成功，点击确认登录，点击取消返回主页")){
					window.location.href="/html/gym/gymLogin.html";
				}else{
					window.location.href="/html/index.html";
				}
			}
		}
	});
}

// 邮箱注册验证
function emailVerfication(){
	var value = $("#reg_email").val();
	var bo = true;
	if(value == ""){
		$(".col-md-12 .regPrompt.name_0").text("邮箱不能为空");
		return false;
	}else if(!mailTest.test(value)){
		$(".col-md-12 .regPrompt.name_0").text("邮箱格式错误");
		return false;
	}
	$.ajax({
		url:"/gym/findGym.do",
		type:"post",
		async:true,
		data:"arg="+value,
		success:function(mes){
			// console.info(mes);
			if(mes == true){
				bo = false;
				$(".col-md-12 .regPrompt.name_0").text("此邮箱已注册");
			}
		}
	});
	return bo;
}

//手机号注册验证
function phoneVerfication(){
	var value = $("#reg_phone").val();
	var bo = true;
	if(value == ""){
		$(".col-md-12 .regPrompt.name_0").text("手机号不能为空");
		return false;
	}else if(!phoneTest.test(value)){
		$(".col-md-12 .regPrompt.name_0").text("手机号格式错误");
		return false;
	}
	$.ajax({
		url:"/gym/findGym.do",
		type:"post",
		async:true,
		data:"arg="+value,
		success:function(mes){
			console.info(mes);
			if(mes == true){
				bo = false;
				$(".col-md-12 .regPrompt.name_0").text("此手机号已注册");
			}
		}
	});
	return bo;
}

// 获取短信验证码
function getSms(){
	console.info("正在获取验证码！！");
}

//短信验证码前台验证
function smsVerfication(){
	var sms = $("#form-sms").val();
	if(sms == ""){
		$(".col-md-12 .regPrompt.sms").text("验证码不能为空");
		return false;
	}else if(!smsTest.test(sms)){
		$(".col-md-12 .regPrompt.sms").text("验证码为6位数字");
		return false;
	}
	return true;
}

// 获取短信验证码倒计时
function timing() {
	var obj = "#button-sms";
	var countdown = 60;
	settime(obj);
	function settime(obj) {
		if (countdown == 0) {
			$(obj).attr("disabled", false);
			$(obj).text("获取验证码");
			countdown = 60;
			return;
		} else {
			$(obj).attr("disabled", true);
			$(obj).text("(" + countdown + ") s 重新发送");
			countdown--;
		}
		setTimeout(function() {
			settime(obj)
		}, 1000)
	}
}

// 密码验证
function passwordVerfication(){
	var pwd_1 = $("#password_1").val();
	if(pwd_1 == ""){
		$(".col-md-12 .regPrompt.pwd_1").text("密码不能为空");
		return false;
	}else if(!pwdTest.test(pwd_1)){
		$(".col-md-12 .regPrompt.pwd_1").text("密码必须包含字母和数字且长度不小于8位");
		return false;
	}
	return true;
}

// 密码二次验证
function pwdVerfication2(){
	var pwd_1 = $("#password_1").val();
	var pwd_2 = $("#password_2").val();
	if(pwd_1 != pwd_2){
		$(".col-md-12 .regPrompt.pwd_2").text("两次输入的密码不一致");
		return false;
	}else if(pwd_2 == "" && pwd_1 != ''){
		$(".col-md-12 .regPrompt.pwd_2").text("请再次输入密码");
		return false;
	}else if(pwd_2 == "" && pwd_1 == ''){
		$(".col-md-12 .regPrompt.pwd_2").text("密码不能为空");
		return false;
	}
	return true;
}

// 失去焦点
function inputFocus(obj){
	$(obj).prev().children(".regPrompt").text("");
}
	
// 切换注册方式
$("#change_reg").click(function(){
	var str = $("#change_reg").text();
	if(str === ">>使用手机号注册"){
		$("#form_reg").html(phoneRegForm);
		$("#change_reg").text(">>使用邮箱注册");
	}else{
		$("#form_reg").html(emailRegForm);
		$("#change_reg").text(">>使用手机号注册");
	}
});
