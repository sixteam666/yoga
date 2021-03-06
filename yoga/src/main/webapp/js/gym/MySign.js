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
			// console.info("处理请求结果：" + mes);
			if(mes == 0){
				alert("失败");return;
			}
			if(state == 1){
				alert("已经和   "+c_nickname+ "  签约");
			}else if(state == 2){
				alert("已拒绝  "+c_nickname+ "  的签约请求");
			}else if(state == 4){
				alert("已撤回请求");
			}
			var elem=document.getElementById(c_id);
			elem.parentNode.removeChild(elem);
			var tableElem = document.getElementById("MySign");
			if(!$("#MySignResponse").is(":empty")){
				$("#MySignResponse").html("<tr align='center'><td>暂无通知</td></tr>");
			}
		}
	});
}

// 得到通知
function findCoachByMyResponse(){
	var g_id = "";
	$.ajax({
		url:"/gym/findCoachByMyResponse.do",
		type:"post",
		async:true,
		data:"g_id="+g_id,
		success:function(mes){
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
						   "<span id='signTime'>"+ mes[i].c_password +"</span><br></br></br>";
				if(mes[i].c_g_id == 0){
					elm += "<input type='button' value='同意' onclick=\"agreeSigingApplication(1,'"+ mes[i].c_id +"','"+ mes[i].c_nickname +"')\" />&nbsp;&nbsp;&nbsp;&nbsp;"+
						   "<input type='button' value='拒绝' onclick=\"agreeSigingApplication(2,'"+ mes[i].c_id +"','"+ mes[i].c_nickname +"')\" />";
				}else if(mes[i].c_g_id == 1){
					elm += "<span>对方已同意</span>";
				}else if(mes[i].c_g_id == 2){
					elm += "<span>对方已拒绝</span>";
				}
				elm += "</div></td></tr>";
			}
			if(elm == ""){
				$("#MySignResponse").html("<tr align='center' ><td>暂无通知</td></tr>");
			}else{
				$("#MySignResponse").html(elm);
			}
		} 
	});
}
findCoachByMyResponse();

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
function findCoachByMyRequest(){
	$.ajax({
		url:"/gym/findCoachByMyRequest.do",
		type:"post",
		async:true,
		success:function(mes){
			// console.info("场馆向教练发请求:" + mes);
			var elm = "";
			for(var i=0;i<mes.length;i++){
				if(mes[i].c_g_id != 0){continue;}
				elm += "<tr id='"+ mes[i].c_id +"'><td>"+
					"<div><img class='coach_headimg' src='/image/headImg/"+ mes[i].c_headimg +"' /></div>"+
					"<div>&nbsp;&nbsp;&nbsp;"+
						"<span class='coach_name'>"+ mes[i].c_nickname +"</span><br><br/>"+
						"<span class='coach_tips'>&nbsp;&nbsp;流派："+ mes[i].c_style +"</span><br><br/>"+
						"<span class='coach_sms'>&nbsp;&nbsp;&nbsp;&nbsp;</span>"+
					"</div>"+
					"<div id='coach_controller'>"+
						   "<span id='signTime'>"+ mes[i].c_password +"</span><br></br></br>";
				if(mes[i].c_g_id == 0){
					elm += "<span>等待对方回复</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
						   "<input type='button' value='撤回请求' onclick=\"agreeSigingApplication(4,'"+ mes[i].c_id +"','"+ mes[i].c_nickname +"')\" />";
				}else if(mes[i].c_g_id == 1){
					elm += "<span>对方已同意</span>";
				}else if(mes[i].c_g_id == 2){
					elm += "<span>对方已拒绝</span>";
				}
				elm += "</div></td></tr>";
			}
			if(elm == ""){
				$("#MySignRequest").html("<tr align='center' ><td>暂无通知</td></tr>");
			}else{
				$("#MySignRequest").html(elm);
			}
		}
	});
}
findCoachByMyRequest();

// 切换导航栏
function changeTable(state) {
	if (state == 0) {
		$("#span_0").css("color","rgb(9,198,171)");
		$("#span_1").css("color","rgb(169,169,169)");
		$(".gtco-section .coach_message table").attr("id", "MySignResponse");
		findCoachByMyResponse();
	} else if (state == 1) {
		$("#span_0").css("color","rgb(169,169,169)");
		$("#span_1").css("color","rgb(9,198,171)");
		$(".gtco-section .coach_message table").attr("id", "MySignRequest");
		findCoachByMyRequest();
	}
}

