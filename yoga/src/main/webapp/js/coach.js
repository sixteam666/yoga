var inpUserName = document.getElementById("fullname")
				var inpPassWord = document.getElementsByClassName("password")
				
				var inpRePassWord = document.getElementsByClassName("repassword")
				var username_warning = document.getElementsByClassName("username_warning")[0]
				var password_warning = document.getElementsByClassName("password_warning")
				var repassword_warning = document.getElementsByClassName("repassword_warning")
				var get_code = document.getElementsByClassName("get_code")[0]
				var name_pwd = document.getElementsByClassName("name_pwd")[0]
				var phone = document.getElementsByClassName("phone")[0]
				var fullname = document.getElementById("fullname")
				var inPhone = document.getElementById("phone_reg")
				var phone_warning = document.getElementsByClassName("phone_warning")[0]
				var phone_reg = document.getElementById("phone_reg")
				var inCode = document.getElementById("code_reg")
				var code_warning = document.getElementsByClassName("code_warning")[0]
				//切换手机号注册
				function phoneReg() {
					name_pwd.setAttribute("class", "notshow")
					phone.removeAttribute("class", "notshow")
					inpPassWord[1].setAttribute("name","c_password")
					inpPassWord[0].setAttribute("name","")
					fullname.setAttribute("name","")
					phone_reg.setAttribute("name","c_name")
				}

				function name_pwdReg() {
					phone.setAttribute("class", "notshow")
					name_pwd.removeAttribute("class", "notshow")
					inpPassWord[0].setAttribute("name","c_password")
					inpPassWord[1].setAttribute("name","")
					fullname.setAttribute("name","c_name")
					phone_reg.setAttribute("name","")
				}
				
				inpUserName.onfocus = function() {
					username_warning.style.visibility = "hidden"
				}
				inpPassWord[0].onfocus = function() {
					password_warning[0].style.visibility = "hidden"
				}
				inpPassWord[1].onfocus = function() {
					password_warning[1].style.visibility = "hidden"
				}
				inpRePassWord[0].onfocus = function() {
					repassword_warning[0].style.visibility = "hidden"
				}
				inpRePassWord[1].onfocus = function() {
					repassword_warning[1].style.visibility = "hidden"
				}
				
				inPhone.onfocus = function() {
					phone_warning.style.visibility = "hidden"
				}
				inCode.onfocus = function() {
					code_warning.style.visibility = "hidden"
				}

				inCode.onblur = function() {
					var regx1 = /^[0-9]{4}$/;
					cod = inCode.value;
					if (regx1.test(cod)) {
						code_warning.style.visibility = "hidden"
						return true;
					} else {
						code_warning.style.visibility = "visible"
						return false;
					}
				}
				inPhone.onblur = function() {
					var regx1 = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
					pho = inPhone.value;
					if (regx1.test(pho)) {
						phone_warning.style.visibility = "hidden"
						return true;
					} else {
						phone_warning.style.visibility = "visible"
						return false;
					}
				}
				
				
				/*         ==============      */
				inpUserName.onblur = function() {
					var regx1 = /^[0-9a-zA-Z]{6,16}$/;
					name = inpUserName.value;
					if (regx1.test(name)) {
						username_warning.style.visibility = "hidden"
						return true;
					} else {
						username_warning.style.visibility = "visible"
						return false;
					}
				}
				
				inpPassWord[0].onblur = function(){
					var regx1 = /^[1-9a-zA-Z]{6,16}$/;
					pwd = inpPassWord[0].value;
					if (regx1.test(pwd)) {
						password_warning[0].style.visibility = "hidden"
						return true;
					} else {
						password_warning[0].style.visibility = "visible"
						return false;
					}
				}
				inpPassWord[1].onblur = function(){
					var regx1 = /^[1-9a-zA-Z]{6,16}$/;
					pwd = inpPassWord[1].value;
					if (regx1.test(pwd)) {
						password_warning[1].style.visibility = "hidden"
						return true;
					} else {
						password_warning[1].style.visibility = "visible"
						return false;
					}
				}
				
				inpRePassWord[0].onblur = function(){
					var pwd = inpPassWord[0].value
					var repwd = inpRePassWord[0].value
					if (repwd != pwd) {
						repassword_warning[0].style.visibility = "visible"
							return false;
					}
					return true;
				}
				inpRePassWord[1].onblur = function(){
					var pwd = inpPassWord[1].value
					var repwd = inpRePassWord[1].value
					if (repwd != pwd) {
						repassword_warning[1].style.visibility = "visible"
							return false;
					}
					return true;
				}
				
				var clock = '';
				var nums = 60;

				function sendCode(thisBtn) {
					btn = thisBtn;
					btn.disabled = true; //将按钮置为不可点击
					btn.value = nums + '秒后可重新获取';
					clock = setInterval(doLoop, 1000); //一秒执行一次
				}

				function doLoop() {
					nums--;
					if (nums > 0) {
						btn.value = nums + '秒后可重新获取';
					} else {
						clearInterval(clock); //清除js定时器
						btn.disabled = false;
						btn.value = '点击获取验证码';
						nums = 60; //重置时间
					}
				}
				get_code.onclick = function() {
					get_code.setAttribute("class", "code_point");
					sendCode(get_code);
				}
				var sumbitbtn = document.getElementsByTagName("form")[0]
				sumbitbtn.onsubmit = function(){
					var boo1 = inpUserName.onblur();
					var boo2 = inpPassWord[0].onblur();
					var boo3 = inpRePassWord[0].onblur();
					
					var boo4 = inPhone.onblur();
					var boo5 = inCode.onblur();
					var boo6 = inpPassWord[1].onblur();
					var boo7 = inpRePassWord[1].onblur();
					if ((boo1 && boo2 && boo3)||(boo4 && boo5 && boo6 && boo7)) {
						return true;
					} else{
						return false;
					}
				}
				
