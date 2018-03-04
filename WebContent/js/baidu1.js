var map;
var opts = {
		width : 250,     // 信息窗口宽度
		height: 80,     // 信息窗口高度
		title : "合作社信息" , // 信息窗口标题
		enableMessage:false//设置允许信息窗发送短息
    };

function initialize() {
	// 百度地图API功能
	map = new BMap.Map("map");
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
	map.enableScrollWheelZoom(); // 启用滚轮放大缩小
	//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
	//map.setMapType(BMAP_SATELLITE_MAP);
	var point = new BMap.Point(113.680055, 33.549301); // 创建点坐标
	map.centerAndZoom(point, 11); // 初始化地图,设置中心点坐标和地图级别。
	
	
	var bdary = new BMap.Boundary();
	bdary.get("舞阳", function(rs){       //获取行政区域
		map.clearOverlays();        //清除地图覆盖物       
		var count = rs.boundaries.length; //行政区域的点有多少个
		for(var i = 0; i < count; i++){
			var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000",strokeOpacity : 0,
		        fillOpacity : 0, fillColor:""}); //建立多边形覆盖物
			map.addOverlay(ply);  //添加覆盖物
			map.setViewport(ply.getPath());    //调整视野         
		}
		
		$
			.getJSON(
					"../site/listJson",
					function(responseData) {
						//(responseData);
						for ( var i = 0; i < responseData.length; i++) {
							var cooperativeName=responseData[i].cooperativeName;
							var id=responseData[i].id;
							var gpsx=responseData[i].gpsx;
							var gpsy=responseData[i].gpsy;
							if(gpsx>0 && gpsy >0){
								var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
								var marker = new BMap.Marker(point);  // 创建标注
								map.addOverlay(marker);            // 将标注添加到地图中
								//var content = "名称："+ cooperativeName +"<br><a href='./view3?x="+gpsx+"&y="+gpsy+"&id="+id+"'>查看详细</a>";
								addClickHandler(id,gpsx,gpsy,marker);
								var label = new BMap.Label(cooperativeName,{offset:new BMap.Size(20,-10)});
								marker.setLabel(label);
							}							
						}
					});	
		
		
	});
	
	
	
//	var polygon = new BMap.Polygon([ new BMap.Point(108.853025, 34.298633),
//			new BMap.Point(108.85475, 34.318075),
//			new BMap.Point(108.856475, 34.319744),
//			new BMap.Point(108.857049, 34.331431),
//			new BMap.Point(108.85245, 34.343354),
//			new BMap.Point(108.852881, 34.351223),
//			new BMap.Point(108.888095, 34.355872),
//			new BMap.Point(108.925896, 34.366123),
//			new BMap.Point(108.925896, 34.302808),
//			new BMap.Point(108.901749, 34.300422),
//			new BMap.Point(108.898012, 34.291714),
//			new BMap.Point(108.877603, 34.291833),
//			new BMap.Point(108.861793, 34.296605) ], {
//		strokeColor : "blue",
//		fillColor : "yellow",
//		strokeWeight : 3,
//		strokeOpacity : 0,
//		fillOpacity : 0
//	});
//	map.addOverlay(polygon);
//	// showToolAutoDef();
//
//	polygon
//			.addEventListener(
//					"click",
//					function(e) {
//						var title = "<div><h1>你点击了多边形，弹出信息框</h1><div><input type='hidden' id='hidareaid' value=''>";
//						ShowWinInfo(e.point, title);
//					});
}

function addClickHandler(id,gpsx,gpsy,marker){
		marker.addEventListener("click",function(e){
			//openInfo(content,e)
			location.href="./vie?x="+gpsx+"&y="+gpsy+"&id="+id;
		  }
		);
}
function openInfo(content,e){
	var p = e.target;
	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	map.openInfoWindow(infoWindow,point); //开启信息窗口
}


// ★★★★弹出信息窗口（需要一个point就是为某个标记弹出信息框）★★★★
function ShowWinInfo(epoint, shtml) {
	// marker.addEventListener("click", function(){ //onmouseover或者click
	// alert("您点击了标注");
	var opts = {
		width : 250, // 信息窗口宽度
		height : 100, // 信息窗口高度
		title : shtml
	// 信息窗口标题
	};
	// var myHtml = "<div class='ditu_js'><h1>修改区域名称</h1>";
	// myHtml += "<div class='con_v'>";
	// myHtml += "<input type='hidden' id='hidareaid' value='" + areaId + "'>";
	// myHtml += "<b>名称：</b><input type='text' class='text' style='width: 150px'
	// id='txtName' value='" + pname + "'><br />";

	var infoWindow = new BMap.InfoWindow("World", opts); // 创建信息窗口对象
	// map.openInfoWindow(infoWindow, map.getCenter()); // 在地图的正中心位置打开信息窗口
	map.openInfoWindow(infoWindow, epoint); // 在地图的特定位置打开信息窗口
	// });
}