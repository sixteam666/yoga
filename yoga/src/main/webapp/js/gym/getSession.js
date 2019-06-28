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
