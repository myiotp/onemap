



	// 百度地图API功能
	map = new BMap.Map("map");
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
	map.enableScrollWheelZoom(); // 启用滚轮放大缩小
	//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
	// map.setMapType(BMAP_SATELLITE_MAP);
	var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
	map.centerAndZoom(point, 15); // 初始化地图,设置中心点坐标和地图级别。
	var marker = new BMap.Marker(point); // 创建标注
	map.addOverlay(marker); // 将标注添加到地图中
	var url = "../site/show?id="+ siteId;
	
	// var label = new BMap.Label("cooperativeName",{offset:new
	// BMap.Size(20,-10)});
	// marker.setLabel(label);

	// create the layer styleMap by giving the default style a context
	var strokeColors = [ "#2d9908", "#991F7A", "#990000", "#1F7A99", "#5200A3",
			"#B20000", "#16556B", "#E68A00", "#00248F", "#4D4DFF" ];
	var fillColors = [ "#3eb216", "#FF33CC", "#AD3333", "#33CCFF", "#944DDB",
			"#FF9999", "#1F7A99", "#FFA319", "#0033CC", "#B2B2FF" ];

	var myP1 = point;    //起点
	var myP2 = point;    //终点
	
	
	//var jsonData =  eval('' + responseData + '');
	

//	$
//			.getJSON(
//					"../landblock/site/" + siteId + "/trails/record",
//					function(responseData) {
//						//(responseData.data);
//						
//					});


function populate(siteId, blockId) {  
    var returnvalue;  
    var options = {  
        type: 'GET',  
        url: "../landblock/site/"+siteId+"/block/"+blockId+"/trails",  
        async:false,  
        success: function (result) {  
          
           returnvalue = result;
        },  
        dataType: "json",  
        error: function (result) {  
            
        }  
    };  
    $.ajax(options);   
    return returnvalue;  
} 

window.run = function (){
// 			var driving = new BMap.DrivingRoute(map);    //驾车实例
// 			driving.search(myP1, myP2);
// 			driving.setSearchCompleteCallback(function(){
 				var pts = driving.getResults().getPlan(0).getRoute(0).getPath(); 
 				var responseData = populate(siteId, blockId);
				
				if(responseData && responseData.data && responseData.data.length > 0){
					 pts = responseData.data[0].polygon;
				}
	
 				//通过驾车实例，获得一系列点的数组
 				var paths = pts.length;    //获得有几个点
 			   //console.log(myP1);
 	          //console.log(pts[0]);
 				var carMk = new BMap.Marker(pts[0],{icon:myIcon});
 				map.addOverlay(carMk);
 				i=0;
 				function resetMkPoint(i){
 					carMk.setPosition(pts[i]);
 					if(i < paths){
 						setTimeout(function(){
 							i++;
 							resetMkPoint(i);
 						},100);
 					}
 				}
 				setTimeout(function(){
 					resetMkPoint(5);
 				},100)
	
// 			});
 		}
	
 		setTimeout(function(){
 			run();
 		},1500);
 		
//var len = responseData.data.length;
//						for ( var i = 0; i < len; i++) {
//							var _responseData = responseData.data[i];
//							var paths = _responseData.polygon;
//							var points = [];
//							for ( var j = 0; j < paths.length; j++) {
//								var point = new BMap.Point(paths[j].x,
//										paths[j].y);
//								points.push(point);
//							}
//							if (points.length > 0) {
//								//(points);
//								var polygon = new BMap.Polygon(points, {
//									strokeColor : strokeColors[i % 10],
//									fillColor : fillColors[i % 10],
//									strokeWeight : 3,
//									strokeOpacity : 0,
//									fillOpacity : 0,
//									uid:_responseData.id
//								});
//								map.addOverlay(polygon);
//								// showToolAutoDef();
//								
//								var carMk = new BMap.Marker(myP1,{icon:myIcon});
//								map.addOverlay(carMk);
//								var i=0;
//								function resetMkPoint(i){
//									carMk.setPosition(pts[i]);
//									if(i < points.length){
//										setTimeout(function(){
//											i++;
//											resetMkPoint(i);
//										},100);
//									}
//								}
//								setTimeout(function(){
//									resetMkPoint(5);
//								},100)
//								
//			
//							}
//						}