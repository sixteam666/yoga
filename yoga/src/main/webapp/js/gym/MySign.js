// 前往教练详情页
function getCoachMessage(c_id){
	console.info(c_id);
	window.location.href="/gym/findCoachById.do?type=2&c_id="+c_id;
}

// 处理签约请求
function agreeSigingApplication(state,c_id,c_nickname){
	$.ajax({
		url:"/gym/agreeSigingApplication.do",
		type:"post",
		async:true,
		data:"c_id="+c_id+"&state="+state,
		success:function(mes){
			console.info(mes);
			if(mes > 0){
				alert("已经和   "+c_nickname+ "  签约");
				var elem=document.getElementById(c_id);
				elem.parentNode.removeChild(elem);
			}else{
				alert("失败");
			}
		}
	});
}

// 得到通知
function getMySign(){
	var g_id = "";
	$.ajax({
		url:"/gym/findCoachByMyResponse.do",
		type:"post",
		async:true,
		data:"g_id="+g_id,
		success:function(mes){
			console.info(mes);
			var elm = "";
			for(var i=0;i<mes.length;i++){
				if(mes[i].c_g_id != 0){continue;}
				elm += "<tr id='"+ mes[i].c_id +"'><td>"+
					"<div><img class='coach_headimg' src='/image/headImg/"+ mes[i].c_headimg +"' onclick=\"getCoachMessage('"+ mes[i].c_id +"')\" /></div>"+
					"<div>&nbsp;&nbsp;&nbsp;"+
						"<span class='coach_name'>"+ mes[i].c_nickname +"</span>"+
						"<span class='coach_tips'>&nbsp;&nbsp;&nbsp;（点击头像查看教练个人信息）</span><br><br/>"+
						"<span class='coach_sms'>&nbsp;&nbsp;&nbsp;&nbsp;对方对你的场馆很感兴趣，希望和您签约，赶快回复他</span>"+
					"</div>"+
						"<div id='coach_controller'>"+
						"<input type='button' value='同意' onclick=\"agreeSigingApplication(1,'"+ mes[i].c_id +"','"+ mes[i].c_nickname +"')\" />&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<input type='button' value='拒绝' onclick=\"agreeSigingApplication(2,'"+ mes[i].c_id +"','"+ mes[i].c_nickname +"')\" />"+
					"</div>"+
				"</td></tr>";
			}
			$("#MySign").html(elm);
		} 
	});
}
getMySign();

//通过场馆id查询响应签约的教练（教练向场馆发请求）
/*function findCoachByMyResponse(g_id){
	$.ajax({
		url:"/gym/findCoachByMyResponse.do",
		type:"post",
		async:true,
		data:"g_id="+"g_id",
		success:function(mes){
			console.info(mes);
		}
	});
}*/
//findCoachByMyResponse("0"); 

//通过场馆id查询请求签约的教练（场馆向教练发请求）
/*function findCoachByMyRequest(g_id){
	$.ajax({
		url:"/gym/findCoachByMyRequest.do",
		type:"post",
		async:true,
		data:"g_id="+"g_id",
		success:function(mes){
			console.info(mes);
		}
	});
}*/
//findCoachByMyRequest("0"); 

