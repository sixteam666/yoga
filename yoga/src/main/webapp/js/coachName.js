				
var welcome = document.getElementById("welcome")

function getUser(){
	$.ajax({
		type:"get",
		url:"/coach/getUser.do",
		asycn:true,
		dataType:"json",
		success:function(re){
			if (re != null) {
				welcome.innerHTML = "welcome,"+re.c_nickname
			}
			
		}
	})
}
getUser()
				
				
