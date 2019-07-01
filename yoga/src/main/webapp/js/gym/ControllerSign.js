// 处理签约请求
function agreeSigingApplication(state){
<<<<<<< HEAD
	// console.info("测试：" +c_id+","+c_nickname);
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
			}else{
				alert("已拒绝  "+c_nickname+ "  的签约请求");
			}
			location.replace(this.href);
			event.returnValue=false;
/*			var elem=document.getElementById(c_id);
			elem.parentNode.removeChild(elem);
			if($("#MySign").html()== null){
				$("#MySign").html("<tr align='center'><td>暂无通知</td></tr>");
			}*/
		}
	});
}

// 发送签约教练的通知  ajax返回值 0:请求失败 1:请求成功 2：重复请求 3：教练已向你发送请求
function addRequest(){
	$.ajax({
		url:"/gym/submitSigingApplication.do",
		type:"post",
		async:true,
		data:"c_id="+c_id,
		success:function(mes){
			// console.info("处理请求结果：" + mes);
			if(mes == 1){
				alert("已经向   "+c_nickname+ "  发送签约请求");
			}else if(mes == 2){
				alert("已向该教练发送了签约请求，请勿重复发送");
			}else if(mes == 3){
				alert(c_nickname +"已向您发送了签约请求，请查看通知");
			}else{
				alert("发送签约请求失败，请重试！");
			}
		}
	});
}
		
// 解约教练
function updateCoach(){
	$.ajax({
		url:"/gym/updateCoach.do",
		type:"post",
		async:true,
		data:'g_id=0&c_id='+c_id,
		success:function(mes){
			// console.info("解约结果：" + mes);
=======
	console.info("测试：" +c_id+","+c_nickname);
	$.ajax({
		url:"/gym/agreeSigingApplication.do",
		type:"post",
		async:true,
		data:"c_id="+c_id+"&state="+state,
		success:function(mes){
			console.info("处理请求结果：" + mes);
			if(mes == 0){
				alert("失败");return;
			}
			if(state == 1){
				alert("已经和   "+c_nickname+ "  签约");
			}else{
				alert("已拒绝  "+c_nickname+ "  的签约请求");
			}
			location.replace(this.href);
			event.returnValue=false;
/*			var elem=document.getElementById(c_id);
			elem.parentNode.removeChild(elem);
			if($("#MySign").html()== null){
				$("#MySign").html("<tr align='center'><td>暂无通知</td></tr>");
			}*/
		}
	});
}

// 发送签约教练的通知  ajax返回值 0:请求失败 1:请求成功 2：重复请求 3：教练已向你发送请求
function addRequest(){
	$.ajax({
		url:"/gym/submitSigingApplication.do",
		type:"post",
		async:true,
		data:"c_id="+c_id,
		success:function(mes){
			console.info("处理请求结果：" + mes);
			if(mes == 1){
				alert("已经向   "+c_nickname+ "  发送签约请求");
			}else if(mes == 2){
				alert("已向该教练发送了签约请求，请勿重复发送");
			}else if(mes == 3){
				alert(c_nickname +"已向您发送了签约请求，请查看通知");
			}else{
				alert("发送签约请求失败，请重试！");
			}
		}
	});
}
		
// 解约教练
function updateCoach(){
	$.ajax({
		url:"/gym/updateCoach.do",
		type:"post",
		async:true,
		data:'g_id=0&c_id='+c_id,
		success:function(mes){
			console.info("解约结果：" + mes);
>>>>>>> branch 'master' of https://github.com/sixteam666/yoga.git
			if(mes > 0){
				alert("已和   "+c_nickname+ "  解约");
				history.back();
				location.reload();
			}else{
				alert("解约失败");
			}
		}
	});
}