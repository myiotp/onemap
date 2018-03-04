var RemoteMap = function(mapDiv){
	
	var me = this;
	//本类全局变量
	var map; //baidu Map对象
	var currentCity;
	var bounds;
	init();
	
	function init(){
		createMap();
		zoomTo("漯河市");
	}
	
	function createMap(){
		map = new BMap.Map(mapDiv,{
			maxZoom:14
		});
		map.addControl(new BMap.NavigationControl({offset:new BMap.Size(0, 50)})); // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小
		//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
		//map.setMapType(BMAP_SATELLITE_MAP);
		//zoomToXY(113.680055, 33.549301, 11);
		map.addEventListener("moving",function(args){
			moveImage()
		});
		map.addEventListener("moveend",function(args){
			moveImage()
		});
		map.addEventListener("zoomend",function(args){
			moveImage()
		});
		map.addEventListener("dragend",function(args){
			moveImage()
		});
	}
	
	function moveImage(){
		if(bounds){
			var lb = map.pointToPixel(bounds.getSouthWest());
			var rt = map.pointToPixel(bounds.getNorthEast());
			//console.log("style","lb.x: "+lb.x+"px; lb.y: "+lb.y+"px; rt.x:"+rt.x+"px; rt.y: "+rt.y+"px;");
			$("#remotesenseimg").attr("style","top: "+rt.y+"px; left: "+lb.x+"px; width:"+(0.9*(rt.x-lb.x))+"px; height: "+((lb.y-rt.y))+"px;z-index:10")
		}
	}
	
	function zoomTo(cityName){
		var bdary = new BMap.Boundary();
		bdary.get(cityName, function(rs){       //获取行政区域
			//清除地图覆盖物   
			if(currentCity&&currentCity.overlays){
				for(var j=0;j<currentCity.overlays.length;j++){
					map.removeOverlay(currentCity.overlays[j]);
				}
			}
			
			var overlays = [];
			var count = rs.boundaries.length; //行政区域的点有多少个
			
			for(var i = 0; i < count; i++){
				var points = rs.boundaries[i];
				var ply = new BMap.Polygon(rs.boundaries[i], {strokeStyle:"dashed", strokeWeight: 2, strokeColor: "#3181F9",strokeOpacity : 0,
			        fillOpacity : 0, fillColor:""}); //建立多边形覆盖物
				//map.addOverlay(ply);  //添加覆盖物
				map.setViewport(ply.getPath());    //调整视野     
				overlays.push(ply);
				bounds = ply.getBounds();
			}
			$("#"+mapDiv+" .BMap_mask").before($('<img  id="remotesenseimg" src="../images/zuowubuju1.png" class="remotesenseimg" ></img>'));
			moveImage();
			currentCity = {name:cityName, overlays:overlays};
		});
	}
};
