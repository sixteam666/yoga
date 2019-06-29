 	// 数据库中的教练对象的集合
	var gymList;

	// 点击图标进入详情页
	function goMessage(s_id) {
		console.info(s_id);
		window.location.href="/student/hiapage.do?type=1&s_id="+s_id;
	}
	
	// 获取我的签约教练数据
	function getMyCoachData() {
		$.ajax({
			url: "/gym/findAllGym.do",
			type: "post",
			async: false,
			success: function(mes) {
				console.info(mes);
				gymList = mes;
			}
		});
	}
	getMyCoachData();

	/* 将后台数据显示在地图上 */
	// var map1 = new BMap.Map("BaiduMap");
	// 当前位置坐标 (参数：经度，纬度，缩放比列)
	map.centerAndZoom(new BMap.Point(lng, lat), 14);
	map.enableScrollWheelZoom(true);
	var index = 0;
	var myGeo = new BMap.Geocoder();

	function bdGEO() {
		var add = gymList[index].g_address;
		if(add !=null){
			geocodeSearch(add);
		}
		index++;
	}
	bdGEO(); // 批量地址解析
	
	function geocodeSearch(add) {
		if (index < gymList.length) {
			setTimeout(window.bdGEO, 400);
		}
		myGeo.getPoint(add, function(point) {
			if (point) {
				// document.getElementById("result").innerHTML += index + "、" + add + add + ":" + point.lng + "," + point.lat +"</br>";
				var address = new BMap.Point(point.lng, point.lat);
				// 图标描述自定义
				var mapLabel = new BMap.Label(
					"<div class='div_coach' onclick=\"goMessage('" + gymList[index].g_id +"')\">" + 
						"<table>" +
							"<tr><td>"+ gymList[index].g_name +"</td></tr>" +
						"</table>"+
					"</div>",{offset: new BMap.Size(20, -10)});
				addMarker(address, mapLabel);
			}
		}, "成都市");
	}

	// 编写自定义函数,创建标注
	var pointIcon = { // 指定Marker的icon属性为Symbol
		icon: new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
			scale: 1, //图标缩放大小
			fillColor: "blue", //填充颜色
			fillOpacity: 0.8 //填充透明度
		})
	}

	function addMarker(point, label) {
		var marker = new BMap.Marker(point, pointIcon);
		// 给图标对象添加浮动事件
		/* 		marker.onmouseover = function(){
				} */
		// 给图标对象添加点击事件
		/* 		marker.onclick = function(){
					
				} */
		map.addOverlay(marker);
		marker.setLabel(label);
	}
	// 地址联动
	$(document).ready(function() {
		addressInit('cmbProvince', 'cmbCity', 'cmbArea');
	});
	
	// 获取鼠标点击位置的地址信息
	var geoc = new BMap.Geocoder();
	var address = ""; // 点击处地址
	map.addEventListener("click", function(e) {
		var pt = e.point;
		var addComp = null;
		geoc.getLocation(pt, function(rs) {
			addComp = rs.addressComponents;
			address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
			addressInit('cmbProvince', 'cmbCity', 'cmbArea',addComp.province.substring(0,2), addComp.city, addComp.district);
			$("#address_mes").val(addComp.street + addComp.streetNumber);
		});
		// alert("点击的地址：" + address);
	});