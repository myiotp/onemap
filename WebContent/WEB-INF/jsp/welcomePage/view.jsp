<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧物流平台系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="refresh" content="1;url=view2">

	 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?s=1&v=2.0&ak=6dtvdbX5acH7wNZU4yPXGYL0"></script> 
</head>
<body>
	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="0"></c:param></c:import>
	<div class="mainpage">		
		<!--  主体   start
<object  classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0"  >
    <param name="movie" value="../images/Flvplayer.swf" />
    <param name="quality" value="high" />
    <param name="allowFullScreen" value="true" />
    <param name="FlashVars" value="vcastr_file=../images/movie.flv&LogoText=&BufferTime=3&IsAutoPlay=1" />
    <embed id="objectflash" style="width:100%; height:inherit;" src="../images/Flvplayer.swf" loop="false" allowfullscreen="true" flashvars="vcastr_file=../images/movie.flv&LogoText=&IsAutoPlay=1" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>
</object>
		<!--  主体   end -->
	</div>	
<%-- 	<c:import url="../common/footer2.jsp"></c:import>	 --%>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var objLink=$("a[href*='memuid=0']");
	objLink.parent().attr('class',"current");
	//alert($("#objectflash"));
	$("object").height($(window).height()-62);
	$("object").width($(window).width());
});
</script>
</html>
