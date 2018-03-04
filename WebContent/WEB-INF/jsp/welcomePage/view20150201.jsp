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
<meta http-equiv="refresh" content="10;url=view2">

	 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6dtvdbX5acH7wNZU4yPXGYL0"></script> 
	<style type="text/css">
#map {width: 85%;height: 480px;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
</head>
<body>
	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="0"></c:param></c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
		<c:param name="memuid" value="0"></c:param>
		<c:param name="a" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<!--  主体   start -->
			<div id="map"></div>
			<script type="text/javascript">
			
			$(function(){
                	var map = new BMap.Map("map");
                	map.centerAndZoom("郑州",5);  // 初始化地图,设置中心点坐标和地图级别
                	//map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
				map.enableScrollWheelZoom(true);
				function getBoundary(area){       
					var bdary = new BMap.Boundary();
					bdary.get(area, function(rs){       //获取行政区域
						map.clearOverlays();        //清除地图覆盖物       
						var count = rs.boundaries.length; //行政区域的点有多少个
						for(var i = 0; i < count; i++){
							var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
							map.addOverlay(ply);  //添加覆盖物
							//map.setViewport(ply.getPath());    //调整视野         
						}    
					});  
					
				}
				setTimeout(function(){
					getBoundary('河南');
				}, 2000);
				 
			setTimeout(function(){
				map.setCenter('郑州');
				getBoundary('河南');
				map.setZoom(7);
				
			},6000);
			});
	
				
				
				
			</script>  
	</div>	
	<c:import url="../common/footer.jsp"></c:import>	

</body>
</html>
