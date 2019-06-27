// 获取Session中的数据
function getSesison(){
	$.ajax({
		url:"/gym/getGymToSession.do",
		type:"post",
		async:true,
		success:function(mes){
			console.info(mes);
			var str = "";
			if(mes != null){
				str = "<input type='hidden' value='" + mes.g_id +"'><span style='color: white'>欢迎您，"+ mes.g_name +"</span><a href='/logout'>注销</a>";
			}
			$("#gym_top").html(str);
		}
	});
}
getSesison();

// 通过场馆id查询响应签约的教练（教练向场馆发请求）
function findCoachByMyResponse(g_id){
	$.ajax({
		url:"/gym/findCoachByMyResponse.do",
		type:"post",
		async:true,
		data:"g_id="+"g_id",
		success:function(mes){
			console.info(mes);
		}
	});
}
// findCoachByMyResponse("0"); 

// 通过场馆id查询请求签约的教练（场馆向教练发请求）
function findCoachByMyRequest(g_id){
	$.ajax({
		url:"/gym/findCoachByMyRequest.do",
		type:"post",
		async:true,
		data:"g_id="+"g_id",
		success:function(mes){
			console.info(mes);
		}
	});
}
// findCoachByMyRequest("0"); 
