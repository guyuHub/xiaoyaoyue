//添加轮播
$(window).on("load",function(){
	var height=$("#liebiao").height();
	var width=$("#carouselBooks").css("width");
	$(".img").height(height*0.65);
	$(".img").width(width);
})