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

<script type="text/javascript">
	var gpsx = <%=request.getParameter("x")%>;
	var gpsy = <%=request.getParameter("y")%>;
	var blockId = <%=request.getParameter("blockId")%>;
	var siteId=<%=request.getParameter("siteId")%>;
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
		<c:param name="memuid" value="3"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="3"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="map"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
	<script type="text/javascript">
		function populate(siteId, blockId) {
			var returnvalue;
			var options = {
				type : 'GET',
				url : "../landblock/site/" + siteId + "/block/" + blockId
						+ "/trails",
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
		var opts = {
			width : 250, // 信息窗口宽度
			height : 0, // 信息窗口高度
			title : "合作社信息", // 信息窗口标题
			enableMessage : false
		// 设置允许信息窗发送短息
		};

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
		//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
		// map.setMapType(BMAP_SATELLITE_MAP);
		//var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
		
		
		var url = "../site/show?id=" + siteId;

		// var label = new BMap.Label("cooperativeName",{offset:new
		// BMap.Size(20,-10)});
		// marker.setLabel(label);

		// create the layer styleMap by giving the default style a context
		var strokeColors = [ "#2d9908", "#991F7A", "#990000", "#1F7A99",
				"#5200A3", "#B20000", "#16556B", "#E68A00", "#00248F",
				"#4D4DFF" ];
		var fillColors = [ "#3eb216", "#FF33CC", "#AD3333", "#33CCFF",
				"#944DDB", "#FF9999", "#1F7A99", "#FFA319", "#0033CC",
				"#B2B2FF" ];

		var myP1 = new BMap.Point(gpsx, gpsy); //起点
		var myP2 = new BMap.Point(gpsx, gpsy); //终点

		window.run = function() {

			var responseData = populate(siteId, blockId);
			//console.log(responseData);
			var pts = [];
			if (responseData && responseData.data
					&& responseData.data.length > 0) {
				pts = responseData.data[0].polygon;
			}
			//通过驾车实例，获得一系列点的数组
			var paths = pts.length; //获得有几个点
			var point = new BMap.Point(gpsx, gpsy);
			var marker = new BMap.Marker(point); // 创建标注
			map.addOverlay(marker); // 将标注添加到地图中
			if(paths > 0){
				var centerPoint = new BMap.Point((gpsx+pts[0].x)/2.0, (gpsy+pts[0].y)/2.0);
				map.centerAndZoom(centerPoint, 15); // 初始化地图,设置中心点坐标和地图级别。				
			}else{				
				map.centerAndZoom(point, 14); // 初始化地图,设置中心点坐标和地图级别。
				return;
			}
		
			var points = [];
			for ( var j = 0; j < pts.length; j++) {
				var point = new BMap.Point(pts[j].x, pts[j].y);
				points.push(point);
			}
			//points.push(myP2);
			if (points.length > 0) {
				//(points);
				var polygon = new BMap.Polygon(points, {
					strokeColor : strokeColors[0],
					fillColor : fillColors[0],
					strokeWeight : 3,
					strokeOpacity : 0,
					fillOpacity : 0
				});
				map.addOverlay(polygon);
			}
			//console.log(myP1);
			// console.log( new BMap.Point(pts[0].x, pts[0].y));
			var carMk = new BMap.Marker(myP1, {
				icon : myIcon
			});
			map.addOverlay(carMk);
			//step 1: 合作社->地块
			var aPoints = [];
			
            var size4path = getRelativeDistance(map, myP1, new BMap.Point(pts[0].x, pts[0].y), 40);			
			for ( var j = 0; j < size4path; j++) {
				var x30 =  gpsx + (pts[0].x - gpsx)*j/(size4path + 0.0);
				var y30 =  gpsy + (pts[0].y - gpsy)*j/(size4path + 0.0);
				var point = new BMap.Point(x30, y30);
				aPoints.push(point);
			}
			//step 2: 地块内部
			var bPoints = [];
			var points2 = [];
			for ( var i = 0; i < points.length; i = i+5){
				points2.push(points[i]);
			}
			var pointsLength = points2.length;
			for ( var j = 0; j < pointsLength-1; j++){
			   if(j <= pointsLength/2){
				   bPoints.push(points2[j]);
			       var _endpoint = points2[pointsLength - 1 - j];
				   var _size4path = getRelativeDistance(map, points2[j], _endpoint, 30);
				   for ( var k = 0; k < _size4path; k++) {
						var x30 =  points2[j].lng + (_endpoint.lng - points2[j].lng)*k/(_size4path + 0.0);
						var y30 =  points2[j].lat + (_endpoint.lat - points2[j].lat)*k/(_size4path + 0.0);
						var _point = new BMap.Point(x30, y30);
						bPoints.push(_point);
				   }
				   bPoints.push(_endpoint);	
                   var nextPoint = points2[j+1];
				   var _size4path2 = getRelativeDistance(map, _endpoint, nextPoint, 30);
				   for ( var k = 0; k < _size4path2; k++) {
						var x30 =  _endpoint.lng + (nextPoint.lng - _endpoint.lng)*k/(_size4path2 + 0.0);
						var y30 =  _endpoint.lat + (nextPoint.lat - _endpoint.lat)*k/(_size4path2 + 0.0);
						var _point = new BMap.Point(x30, y30);
						bPoints.push(_point);
				   } 
			   }else{
			       break;
			   }
			}
			//log(bPoints);
			//step 3: 地块->合作社
			var cPoints = [];
            var bPointsLength = bPoints.length;
            size4path = getRelativeDistance(map, bPoints[bPointsLength-1], myP2, 40);	
			for ( var j = 0; j < size4path; j++) {
				var x30 =  bPoints[bPointsLength-1].lng + (gpsx - bPoints[bPointsLength-1].lng)*j/(size4path + 0.0);
				var y30 =  bPoints[bPointsLength-1].lat + (gpsy - bPoints[bPointsLength-1].lat)*j/(size4path + 0.0);
				var point = new BMap.Point(x30, y30);
				cPoints.push(point);
			}
			//console.log(cPoints);
			var fullPoints = aPoints.concat(bPoints);
			fullPoints = fullPoints.concat(cPoints);
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
