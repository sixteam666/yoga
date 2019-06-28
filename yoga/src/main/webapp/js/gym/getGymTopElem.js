// 获取Session中的数据
function getGymTopElem(){
	$.ajax({
		url:"/gym/getGymToSession.do",
		type:"post",
		async:false,
		success:function(mes){
			console.info(mes);
			var topElem = "<div class='col-sm-4 col-xs-12'>"+
								"<ul>"+
									"<li><a href='/html/gym/index.html' class='fon'>首页</a></li>"+
									"<li><a href='showCoach.html' class='fon'>教练</a></li>"+
									"<li><a href='/dynamic/showHot.do' class='fon'>动态</a></li>"+
								"</ul>"+
							"</div>"+
						  "<div class='col-xs-8 text-right'>"+
							    "<ul>";
			if(mes == null){
				topElem+="<li class='has-dropdown' id='gym_top'>" +
							"<span style='color: white'>请登录</span>" +
						 "</li>";
			}else{
				topElem += "<li class='has-dropdown' id='gym_top'>" +
								"<input type='hidden' value='" + mes.g_id +"'>" +
								"<span style='color: white'>欢迎您，"+ mes.g_name +"</span>" +
								"<a href='/logout'>注销</a>" +
						  "</li>";
			}				
			topElem += "<li class='has-dropdown'><a href='#'>场馆中心</a>"+
								"<ul class='dropdown'>"+
									"<li><a href='/gym/showMessage.do'>场馆资料</a></li>"+
									"<li><a href='/html/gym/lesson.html'>课程安排</a></li>"+
									"<li><a href='/html/gym/myCoach.html'>我的签约</a></li>"+
									"<li><a href='/dynamic/gymShowMy.do'>我的动态</a></li>"+
									"<li><a href='/html/gym/SignTheSign.html'>我的通知</a></li>"+
									"<li><a href='/gym/showMoney.do'>我的钱包</a></li>"+
								"</ul>"+
							"</li>"+
						"</ul>"+
					"</div>";
			$(".gtco-nav .gtco-container").children(".row").html(topElem);
		}
	});
}
getGymTopElem();

