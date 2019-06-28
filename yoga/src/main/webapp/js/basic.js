

var blackid= "page-black";
var loginboxid ="login";
var registerboxid ="register";

//设置遮幕的高度
function setHeight(){
	//获取文档高度
	var height = $(document).height();
	$("#"+blackid).css("height",height);
}
function showblack(){
	setHeight();
	$("#"+blackid).fadeIn("fast");
}
function closeblack(){
	setHeight();
	$("#"+blackid).fadeOut("fast");
}


/* 开启和关闭登录框 */
function showLoginBox(){
	$("#"+loginboxid).fadeIn("fast");
	showblack();
}
function closeLoginBox(){
	$("#"+loginboxid).fadeOut("fast");
	closeblack();
}

/* 开启和关闭注册框 */

function showRegisterBox(){
	$("#"+registerboxid).fadeIn("fast");
	showblack();
}
function closeRegisterBox(){
	$("#"+registerboxid).fadeOut("fast");
	closeblack();
}

/*  列表项*/
$(".a-item").click(function(){
				$(this).addClass("active").siblings().removeClass("active");
			});