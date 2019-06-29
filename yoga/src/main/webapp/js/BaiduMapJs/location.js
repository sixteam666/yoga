var lng = ""; // 地图经度
var lat = ""; // 地图纬度
// 自定义当前位置的图标
// var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(300, 157));
var myIcon = { // 指定Marker的icon属性为Symbol
			icon: new BMap.Symbol(BMap_Symbol_SHAPE_FORWARD_CLOSED_ARROW,{
				scale: 1.5, //图标缩放大小
				fillColor: "red", //填充颜色
				fillOpacity: 0.8 //填充透明度
			})
		}
		
/* 浏览器定位函数 */
var map = new BMap.Map("BaiduMap"); // 地图对象
var point = new BMap.Point(116.331398, 39.897445); // 默认显示的坐标
map.centerAndZoom(point, 12); // 窗口中央位置
var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r) {
	if (this.getStatus() == BMAP_STATUS_SUCCESS) {
		var mk = new BMap.Marker(r.point, myIcon); // 创建标注
		mk.setAnimation(BMAP_ANIMATION_BOUNCE); // 跳动的动画
		map.addOverlay(mk); // 将标注添加到地图中
		map.panTo(r.point);
		lng = r.point.lng;
		lat = r.point.lat;
		// alert('您的位置：'+lng+','+lat);
	} else {
		alert('failed' + this.getStatus());
	}
}, {
	enableHighAccuracy: true
})
//关于状态码
//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)

// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
	// 靠左上角位置
	anchor: BMAP_ANCHOR_TOP_LEFT,
	// LARGE类型
	type: BMAP_NAVIGATION_CONTROL_LARGE,
	// 启用显示定位
	enableGeolocation: true
});
map.addControl(navigationControl);
// 添加定位控件
var geolocationControl = new BMap.GeolocationControl();
geolocationControl.addEventListener("locationSuccess", function(e) {
	// 定位成功事件
	var address = '';
	address += e.addressComponent.province;
	address += e.addressComponent.city;
	address += e.addressComponent.district;
	address += e.addressComponent.street;
	address += e.addressComponent.streetNumber;
	alert("当前定位地址为：" + address);
});
geolocationControl.addEventListener("locationError", function(e) {
	// 定位失败事件
	alert(e.message);
});
map.addControl(geolocationControl);
