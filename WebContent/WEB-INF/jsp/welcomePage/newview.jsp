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
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" type="text/css" href="../bootstrap-3.2.0/css/bootstrap.css">
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link href="../css/infoWindow.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?s=1&v=2.0&ak=6dtvdbX5acH7wNZU4yPXGYL0"></script>
<script type="text/javascript" src="../js/map/Map.js"></script>	
<script type="text/javascript" src="../js/map/InfoBox.js"></script>	
<script type="text/javascript" src="../js/map/NavigationBar.js"></script>	
<script type="text/javascript" src="../js/baidu4njfb.js"></script>
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	min-height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}
.mainpage{
	min-height: 100%;
}
.map {
	padding-top:70px;
	width: 100%;
}
.remotesense{
	width: 100%;
	padding-top:100px;
	text-align:center;
	display:none;
}
.machinemap{
	display:none;
}
.landblockmoreinfo{
	position: absolute;
  	bottom: 5px;
  	right: 5px;
  	width:455px;
  	height: 610px;
  	background-color: #fff;
}

</style>
<script type="text/javascript">
<%
String para = request.getParameter("licensenumber");
if(para == null || para.length() == 0){
	para = "";
}else{
	para = "?licensenumber=" +new String(
			para.getBytes("iso-8859-1"), "UTF-8");
}
System.out.println("license:" + para);
%>
var licnumber = '<%=para%>';
var mapObj,navigationBar;
function initialize(){
	var objLink=$("a[href*='memuid=0']");
	objLink.parent().attr('class',"current");
	$(".map").height($(window).height()-70);
	$(".remotesenseimg").height($(window).height()-120);
	
	mapObj = new Map("map");
	navigationBar = new NavigationBar("navigationbar",mapObj);
	mapObj.navigationBar = navigationBar;
	//mapObj.zoomTo("舞阳");
	var machineMap=new MachineMap("machinemap",licnumber);
	machineMap.initializemachinemap();
	
	navigationBar.machineMap=machineMap;
	
	
	
}
</script>
</head>
<body onload='initialize()'>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="0"></c:param>
	</c:import>
	<div class="mainpage">
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="map" class="map"></div>
		<div id="landblockmoreinfo" style="display:none" class="landblockmoreinfo">
		</div>
		<div id="remotesense" class="remotesense">
			<img class="remotesenseimg" src="../images/zuowubuju.jpg">
		</div>
		<div id="machinemap" class="map machinemap"></div>
		<div id="navigationbar" class="navbar-fixed-top filter filterbg">
			<div id="navigationfilter" class="filterform">
				<div class="sel_firstmenu filterlinediv filterLeft">
					<button class="filterbtn" type="button" id="NameSelectbtn"
						data-toggle="dropdown" aria-expanded="flase">
						<span class="sel_title"></span>
						<span class="menudown"></span>
					</button>
					<ul class="sel_dropdown dropdown-menu filter-dropdown">
					</ul>
				</div>
				<div class="sel_secondmenu filterlinediv filterLeft">
				<button class="filterbtn" type="button" id="NameSelectbtn"
					data-toggle="dropdown" aria-expanded="flase">
					<span class="sel_title"></span>
					<span class="menudown"></span>
				</button>
				<ul class="sel_dropdown dropdown-menu filter-dropdown">
				</ul>
			</div>
			<div class="sel_thirdmenu filterlinediv filterLeft">
				<button class="filterbtn" type="button" id="NameSelectbtn"
					data-toggle="dropdown" aria-expanded="flase">
					<span class="sel_title"></span>
					<span class="menudown"></span>
				</button>
				<ul class="sel_dropdown dropdown-menu filter-dropdown">
				</ul>
			</div>
			</div>
			
			<div
				class="lastRight filterlinediv filterRight  filterrighttext"
				id="themeSetBtn">农机分布图</div>
			<div
				class="filterlinediv filterRight  filterrighttext"
				id="dataSetBtn">遥感图</div>
			<div
				class="filterlinediv filterRight  filterrighttext filterrightselect"
				id="chartsSetBtn">地理信息图</div>
			</div>

		</div>
	<c:import url="../common/footer2.jsp"></c:import>	

</body>
</html>
