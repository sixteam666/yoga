/* 实现轮播图 */
$(function(){
	var $wrap = $("#show");
	var $ul_list = $wrap.find(".slider").eq(0);
	var $items = $ul_list.children("li");
	$ul_list.width(parseInt($wrap.width())*5);
	
	setInterval(slide,2000);
	
	function slide(){
		var count = $items.length-1;
		var left = parseInt($ul_list.css("margin-left"));
		left = Math.abs(left);
		console.log(left);
		if(left <count*$items.width()){
			$ul_list.animate({"margin-left":"-="+$items.width()+"px"},1000);
		}else{
			$ul_list.animate({"margin-left":"0px"},500);
		}
		
	}
});
