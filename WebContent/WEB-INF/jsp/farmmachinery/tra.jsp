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
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=15&ak=6dtvdbX5acH7wNZU4yPXGYL0"></script>
<script language="JavaScript">
<%
String para = request.getParameter("licensenumber");
if(para == null || para.length() == 0){
	para = "";
}else{
	para = new String(
			para.getBytes("iso-8859-1"), "UTF-8");
}
System.out.println("license:" + para);
String period =  request.getParameter("t");
if(period == null || period.length() == 0){
	period = "0";
}
%>
var licensenumber = '<%=para%>';
var period = '<%=period%>';
</script>
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#map {
	width: 85%;
	height: 480px;
}
</style>

</head>
<body>

	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="4"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
		<c:param name="memuid" value="4"></c:param>
		<c:param name="a" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="map"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
<script type="text/javascript">
		function populate(licensenumber, period) {
			var returnvalue;
			var options = {
				type : 'GET',
				url : "../farmmachinerygps/licensenumber/" + licensenumber+ "/period/"+period+"/trails",
				async : false,
				success : function(result) {

					returnvalue = result;
				},
				dataType : "json",
				error : function(result) {

				}
			};
			$.ajax(options);
			return returnvalue;
		}
		
		function getRelativeDistance(map, pointA, pointB, ratio){
			var dis = (map.getDistance(pointA,pointB)).toFixed(2);
			//console.log(Math.floor(dis/20));
			
			return Math.floor(dis/ratio);
		}
	</script>
	<script type="text/javascript">
		// 百度地图API功能
		var map;
		var myIcon = new BMap.Icon("../images/car001.jpg",
				new BMap.Size(24, 21), { //小车图片
					//offset: new BMap.Size(0, -5),    //相当于CSS精灵
					imageOffset : new BMap.Size(0, 0)
				//图片的偏移量。为了是图片底部中心对准坐标点。
				});

		map = new BMap.Map("map");
		map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小
		
		window.run = function() {
			var responseData = {"data":[{"y":34.332145,"x":112.423867}, {"y":34.342145,"x":112.426867}]}; //populate(licensenumber, period);
			if(period == "30"){
                            responseData = {"data":[{"y":34.332145,"x":112.423867}, {"y":34.442145,"x":112.526867}]}; 
                        }else if(period == "180"){
                            responseData = {"data":[{"y":34.332145,"x":112.423867}, {"y":34.942145,"x":112.926867}]}; 
                        }

			var pts = [];
			if (responseData && responseData.data
					&& responseData.data.length > 0) {
				pts = responseData.data;
			}
			var paths = pts.length; //获得有几个点
			
			if( paths <=0){
				return;
			}
			//通过驾车实例，获得一系列点的数组
			
			var point = new BMap.Point(pts[0].x, pts[0].y);
			var myP1 = new BMap.Point(pts[0].x, pts[0].y); //起点
		        var myP2 = new BMap.Point(pts[paths -1].x, pts[paths -1].y); //终点
			var centerPoint = new BMap.Point((pts[paths -1].x+pts[0].x)/2.0, (pts[paths -1].y+pts[0].y)/2.0);
			map.centerAndZoom(point, 14); // 初始化地图,设置中心点坐标和地图级别。
			var polyline = new BMap.Polyline([myP1, myP2], {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});   //创建线
			map.addOverlay(polyline);   //增加线

		
			var carMk = new BMap.Marker(myP1, {
				icon : myIcon
			});
			map.addOverlay(carMk);
		   
			var fullPoints = [];
			/*for(var i=0; i< paths;i++){
				var point = new BMap.Point(pts[i].x, pts[i].y);
				fullPoints.push(point);
			}*/
	                var size4path = getRelativeDistance(map, myP1, myP2, 30);			
			for ( var j = 0; j < size4path; j++) {
				var x30 =  pts[0].x + (pts[1].x - pts[0].x)*j/(size4path + 0.0);
				var y30 =  pts[0].y + (pts[1].y - pts[0].y)*j/(size4path + 0.0);
				var point = new BMap.Point(x30, y30);
				fullPoints.push(point);
			}

			var i = 0;
			var fullLength = fullPoints.length;
			function resetMkPoint(i) {
				if (i < fullLength) {
					carMk.setPosition(fullPoints[i]);
					setTimeout(function() {
						i = i + 1;
						resetMkPoint(i);
					}, 100);
				} else {
					carMk.setPosition(myP2);
					//console.log(myP2);
				}
			}
			setTimeout(function() {
				resetMkPoint(0);
			}, 1000);

		}

		setTimeout(function() {
			run();
		}, 1500);
	</script>
</body>
</html>
