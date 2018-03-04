var map;
var opts = {
	width : 250, // 信息窗口宽度
	height : 0, // 信息窗口高度
	title : "合作社信息", // 信息窗口标题
	enableMessage : false
// 设置允许信息窗发送短息
};

function initialize() {
	// 百度地图API功能
	map = new BMap.Map("map");
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
	map.enableScrollWheelZoom(); // 启用滚轮放大缩小
	//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
	// map.setMapType(BMAP_SATELLITE_MAP);
	var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
	map.centerAndZoom(point, 14); // 初始化地图,设置中心点坐标和地图级别。
	var marker = new BMap.Marker(point); // 创建标注
	map.addOverlay(marker); // 将标注添加到地图中
	var url = "../site/show?id="+ id;
	addClickHandler(url, marker);
	// var label = new BMap.Label("cooperativeName",{offset:new
	// BMap.Size(20,-10)});
	// marker.setLabel(label);

	// create the layer styleMap by giving the default style a context
	var strokeColors = [ "#2d9908", "#991F7A", "#990000", "#1F7A99", "#5200A3",
			"#B20000", "#16556B", "#E68A00", "#00248F", "#4D4DFF" ];
	var fillColors = [ "#3eb216", "#FF33CC", "#AD3333", "#33CCFF", "#944DDB",
			"#FF9999", "#1F7A99", "#FFA319", "#0033CC", "#B2B2FF" ];

	$
			.getJSON(
					"../landblock/site/" + id + "/trails/record",
					function(responseData) {
						//(responseData.data);
						var len = responseData.data.length;
						for ( var i = 0; i < len; i++) {
							var _responseData = responseData.data[i];
							var paths = _responseData.polygon;
							var points = [];
							for ( var j = 0; j < paths.length; j++) {
								var point = new BMap.Point(paths[j].x,
										paths[j].y);
								points.push(point);
							}
							if (points.length > 0) {
								//(points);
								var polygon = new BMap.Polygon(points, {
									strokeColor : strokeColors[i % 10],
									fillColor : fillColors[i % 10],
									strokeWeight : 3,
									strokeOpacity : 0,
									fillOpacity : 0,
									uid:_responseData.id
								});
								map.addOverlay(polygon);
								// showToolAutoDef();
								
								(function(){
								var index = i;
								var title = "<div><h1>地块信息</h1><div>";
								var _iw = createInfoWindow2(id,title, i, responseData.data,gpsx,gpsy);
								var _polygon = polygon;
								_polygon
										.addEventListener(
												"click",
												function(e) {													
													
													var target = e.target;
													//ShowWinInfo(e.point, title, _responseData,_responseData.id, _responseData.cropTypeId);
													map.openInfoWindow(_iw, e.point);
												});
								})()
							}
						}
					});
}

function createInfoWindow2(siteId,title, i, responseData,gpsx,gpsy){	
	var data = responseData[i];
	
	//(i);
//	var url = encodeURI('../croplifecycle/block/' + data.id + '/crop/' + data.cropTypeId + '/env');
//	
//		// Get the CSV and create the chart
//		$
//			.getJSON(
//					url,
//					function(responsedata) {
//						var jsonData =  eval('(' + responsedata + ')'); 
//						//(jsonData);
//					});
		
	var content = "<div style='font-size:12px'>地块编号: " + data.blockNumber+"<br>地块面积(亩):"+data.area + "<br>地块类型: "
										+ data.blockType.blockType
										+ "<br>土壤类型:"+data.landType.landType+"<br>作物类型:"+data.cropType
										+"<br>所属合作社:"+data.site.cooperativeName+"<br><br>"
										+"<a href='../managementrecord/charts?siteId="+siteId+"&blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "&x="+gpsx+"&y="+gpsy+"' target='_blank'>查看作物生产过程回溯</a><br>"
										/*+"<br><h2>查阅详情:</h2>"
										+"<a href='../croplifecycle/charts?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+"' target='_blank'>测土配方</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>耕地</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>施肥</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>播种</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>移植</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>病害防治</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>虫害防治</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>草害防治</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>灌溉</a><br>"
										+"<a href='../yieldprediction/chart?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>收获</a><br>"*/
										+"</div>";

	var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
	return infoWindow;
}
function addClickHandler(url, marker) {
	marker.addEventListener("click", function(e) {
		//openInfo(content, e);
		window.open(url,"合作社信息"); 
	});
}
function openInfo(content, e) {
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
	map.openInfoWindow(infoWindow, point); // 开启信息窗口
}

// ★★★★弹出信息窗口（需要一个point就是为某个标记弹出信息框）★★★★
function createInfoWindow(epoint, title, data, blockId, cropTypdId) {
	
	var url = encodeURI('../croplifecycle/block/' + data.blockId + '/crop/' + data.cropTypeId + '/env');
	
		// Get the CSV and create the chart
		$
			.getJSON(
					url,
					function(responsedata) {
						var jsonData =  eval('(' + responsedata + ')'); 
						//(jsonData);
					});
		
	var content = "<div style='font-size:.8em'>地块编号: " + data.blockNumber + "<br>地块类型: "
										+ data.blockType.blockType
										+ "<br>土壤类型:"+data.landType.landType+"<br>作物类型:"+data.cropType
										+"<br>所属合作社:"+data.site.id+"<br><br>"
										+"<a href='../managementrecord/charts?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>查看作物生产过程回溯</a><br>"
										+"<a href='../croplifecycle/charts?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+"' target='_blank'>查看作物生长状态图表</a><br>"
										+"<a href='../yieldprediction/charts?blockId="+ data.id+ "&cropTypeId="+data.cropTypeId+ "' target='_blank'>查看作物产量预测图表</a><br>"
										+"</div>";

	var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
	// map.openInfoWindow(infoWindow, map.getCenter()); // 在地图的正中心位置打开信息窗口
	map.openInfoWindow(infoWindow, epoint); // 在地图的特定位置打开信息窗口
	// });
}