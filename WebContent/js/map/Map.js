var Map = function(mapDiv){
	
	var me = this;
	//本类全局变量
	var map, //baidu Map对象
	currentCity,//当前显示的行政区
	allLandBlocks={}, //{id:{blockInfo}}
	allSites={},//{id:{siteInfo}}
	lastInfoBox;
	
	fixBaiduMap();
	init();
	
	//specify public methods
	this.zoomTo = zoomTo;
	this.zoomToXY = zoomToXY;
	
	var myIcon = new BMap.Icon("../images/car001.jpg",
			new BMap.Size(24, 21), { //小车图片
				//offset: new BMap.Size(0, -5),    //相当于CSS精灵
				imageOffset : new BMap.Size(0, 0)
			//图片的偏移量。为了是图片底部中心对准坐标点。
			});
	
	function init(){
		createMap();
		loadSite();
	}
	
	function createMap(){
		map = new BMap.Map(mapDiv);
		map.addControl(new BMap.NavigationControl({offset:new BMap.Size(0, 50)})); // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小
		//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
		//map.setMapType(BMAP_SATELLITE_MAP);
		zoomToXY(118.83687, 35.57997, 11);
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
				var ply = new BMap.Polygon(rs.boundaries[i], {strokeStyle:"dashed", strokeWeight: 2, strokeColor: "#3181F9",strokeOpacity : 0,
			        fillOpacity : 0, fillColor:""}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				map.setViewport(ply.getPath());    //调整视野     
				overlays.push(ply);
			}
			currentCity = {name:cityName, overlays:overlays};
		});
	}
	
	 function loadSite(){
		$.getJSON(
			"../api/uservehicle/listJson",
			function(responseData) {
				//(responseData);
				for ( var i = 0; i < responseData.length; i++) {
					var licenseplate=responseData[i].licenseplate;
					var id=responseData[i].id;
					var gpsx=responseData[i].fromlng;
					var gpsy=responseData[i].fromlat;
					if(gpsx>0 && gpsy >0){
						var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
						var marker = new BMap.Marker(point, {
							icon : myIcon
						});  // 创建标注
						map.addOverlay(marker);            // 将标注添加到地图中
						//var content = "名称："+ cooperativeName +"<br><a href='./view3?x="+gpsx+"&y="+gpsy+"&id="+id+"'>查看详细</a>";
						siteOnClick(id,marker);
						var label = new BMap.Label(licenseplate,{offset:new BMap.Size(20,-10)});
						label.setStyle({
							"maxWidth": "inherit"
						});
						marker.setLabel(label);
						//loadLandblock(id); //here will only load the site which has gps x and y.
						allSites[id] = responseData[i];
					}
				}
			});	
		
	}
	 
	 function siteOnClick(id,marker){
		 marker.addEventListener("click",function(e){
			var site = allSites[id];
//			if(map.getZoom()<13){
//				zoomToXY(site.fromlng,site.fromlat,14);
//			}
			showSiteInfo(id,marker);
			
//			if(me.navigationBar){
//				me.navigationBar.fillDropDownList("city_"+site.fromProvinceName,"city_"+site.fromCityName,site.fromAreaName);
//			}
		  }
		);
	 }
	 
	 function zoomToXY(x,y,level){
		 map.centerAndZoom(new BMap.Point(x,y), 14); 
	 }
	 
	 function showSiteInfo(id, marker){
		 var site = allSites[id];
		 var width = 255, height=430;
		 $.get("../js/map/templates/SiteInfoBox.html",function(result){
			 var imgsrc=null;
			 try{
				 imgsrc = site.certimage;
			 }catch(e){}
		 	 result=result.replace("{attachimg}",imgsrc||"../images/nopic.jpg");
		  	 result=result.replace(/\{id\}/g,"mappop"+site.id);
		     result=result.replace("{licenseplate}",site.licenseplate);
		     result=result.replace("{enginenumber}",site.enginenumber);
		     result=result.replace("{vehicletype}",site.vehicletype);
		     result=result.replace("{vehiclebrand}",site.vehiclebrand);
		     
		     result=result.replace("{registrationaddress}",site.registrationaddress);
		     result=result.replace("{gps}","("+site.fromlng.toFixed(6)+","+site.fromlat.toFixed(6)+")");
		     result=result.replace("{vehicleweight}",site.vehicleweight);
		     result=result.replace("{cargolength}",site.cargolength);
		     result=result.replace("{checkdeadline}",site.checkdeadline);
		     result=result.replace("{insurancedeadline}",site.insurancedeadline);
		     
//		     $.get("../sitecert/id/"+site.id,function(data){
//		    	 result=result.replace("{certtype}",data.certtype||"");
//		    	 result=result.replace("{certtime}",data.certtime?new Date(data.certtime).format("yyyy-MM-dd"):""); //new Date(132465657676).format("yyyy-MM-dd");
//		    	 result=result.replace("{validtime}",data.validtime||"");
//		    	 result=result.replace("{certorganization}",data.certorganization||"");
//		    	 result=result.replace("{certnumber}",data.certnumber||"");
		    	 if(lastInfoBox){
		    		 lastInfoBox.close();
		     }
			 var infoBox = marker.infoBox;
			 if(!infoBox){
				 infoBox = new BMapLib.InfoBox(map,result,{
						boxStyle:{
							width:width+"px",
							height:height+"px"
						},
						closeIconMargin: "2px 2px 0 0",
						enableAutoPan: true,
						offset:{height:-height*0.65, width:width/2+25},
						closeIconUrl:"../images/close.png"
					}); 
			 }
			 infoBox.open(marker);
			 marker.infoBox = infoBox;
			 lastInfoBox = infoBox;
			 if(!imgsrc){
				 $("#mappop"+site.id+" .name").attr("style","color:black")
			 }
			$("#mappop"+site.id+" .title div").click( function () {
			    
				$("#mappop"+site.id+" .title div").removeClass("select");			
				$(this).addClass("select");
				
				$("#mappop"+site.id+" .content").hide();
				var contentid=$(this).attr("id")+"content";
				$("#"+contentid).show();
				
			 });
			 $(".infoBox").before($(".mastfoot"));
		     //});
		  });
		 
	 }
	
	function loadLandblock(id){
		var strokeColors = [ "#f58220", "#b2d235", "#4e72b8", "#d71345", "#5200A3",
		         			"#B20000", "#16556B", "#E68A00", "#00248F", "#4D4DFF" ];
     	var fillColors = strokeColors;
		$.getJSON(
				"../landblock/site/" + id + "/trails",
				function(responseData) {
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
							var polygon = new BMap.Polygon(points, {
								strokeColor : strokeColors[i % 10],
								fillColor : fillColors[i % 10],
								strokeWeight : 1,
								strokeOpacity : 0,
								fillOpacity : 0,
								uid:_responseData.id
							});
							map.addOverlay(polygon);
							_responseData.geometry = polygon;
							blockOnClick(_responseData);
							allLandBlocks[_responseData.id] = _responseData;
							if(_responseData.site&&_responseData.site.id&&allSites[_responseData.site.id]){
								var blocks = allSites[_responseData.site.id]["landBlocks"];
								if(!blocks){
									blocks = allSites[_responseData.site.id]["landBlocks"] = [];
								}
								blocks.push(_responseData.id);
							}
						}
					}
				});
	}
	
	function blockOnClick(block){
		var geometry = block.geometry;
		geometry.addEventListener("click",function(e){
			showBlockInfo(e.point, block);
		  }
		);
	}
	
	function showBlockInfo(anchor, block){
		 var width = 255, height=430;
		 $.get("../js/map/templates/LandBlockInfoBox.html",function(result){
			 var imgsrc=null;
			 try{
				 imgsrc = $(block.site.description.match(/<img [^>]*src=['"]([^'"]+)[^>]*>/gi)[0]).attr("src");
			 }catch(e){}
			 result=result.replace("{attachimg}",imgsrc||"../images/nopic.jpg");
		  	 result=result.replace(/\{id\}/g,"blockpop"+block.id);
		     result=result.replace("{blockNumber}",block.blockNumber);
		     result=result.replace("{area}",block.area);
		     result=result.replace("{blockType}",block.blockType.blockType);
		     result=result.replace("{landType}",block.landType.landType);
		     result=result.replace("{site}",block.site.cooperativeName);
		     
		     
		     if(lastInfoBox){
		    	 lastInfoBox.close();
		     }
			 var infoBox = block.infoBox;
			 if(!infoBox){
				 infoBox = new BMapLib.InfoBox(map,result,{
						boxStyle:{
							width:width+"px",
							height:height+"px"
						},
						closeIconMargin: "2px 2px 0 0",
						enableAutoPan: true,
						offset:{height:-height*0.65, width:width/2+25},
						closeIconUrl:"../images/close.png"
					}); 
			 }
			 infoBox.addEventListener("close", function(e) {
				    $("#landblockmoreinfo").hide();
				});
			 infoBox.open(anchor);
			 block.infoBox = infoBox;
			 lastInfoBox = infoBox;
			 if(!imgsrc){
				 $("#blockpop"+block.id+" .name").attr("style","color:black");
			 }
			 
			 
			$("#blockpop"+block.id+" .landblockmore .landblockprocess").click(block, function (e) {
			    
				$("#blockpop"+e.data.id+" .landblockmore div").removeClass("select");			
				$(this).addClass("select");
				showLandblockProcess(e.data);
			 });
			 
			 $("#blockpop"+block.id+" .landblockmore .landblockstatus").click(block, function (e) {
			    
				$("#blockpop"+e.data.id+" .landblockmore div").removeClass("select");			
				$(this).addClass("select");
				showLandblockStatus(e.data);
			 });
			 
			 $("#blockpop"+block.id+" .landblockmore .landblockharvest").click(block, function (e) {
			    
				$("#blockpop"+e.data.id+" .landblockmore div").removeClass("select");			
				$(this).addClass("select");
				showLandblockHarvest(e.data);
				
			 });
			 $(".infoBox").before($(".mastfoot"));
		  });
		 
	 }
	 
	 function showLandblockProcess(block){
		 $.get("../managementrecord/process",{
				blockId:block.id,
				cropTypeId:block.cropTypeId_mr
			},function(result){
			 $("#landblockmoreinfo").removeAttr("style");
			 $("#landblockmoreinfo").html(result);
			 $("#landblockmoreinfo").show();
			 
			 $( "#landblockmoreinfo" ).draggable({ handle: ".blockPanelTitle",containment: [ 0, 100 ] });
				 
		 });
	 }
	
	  function showLandblockStatus(block){
		  $.get("../croplifecycle/growStatus",{
				blockId:block.id,
				cropTypeId:block.cropTypeId_lc
				},function(result){
				 $("#landblockmoreinfo").removeAttr("style");
				 $("#landblockmoreinfo").html(result);
				 $("#landblockmoreinfo").show();
				$( "#landblockmoreinfo" ).draggable({ handle: ".blockPanelTitle",containment: [ 0, 100 ] }); 
			 });
	 }
	 
	  function showLandblockHarvest(block){
		  var url = "../yieldprediction/blockyield";
		  $.get(url,{
			  blockId:block.id,cropTypeId:block.cropTypeId_yp,x:block.site.gpsx,y:block.site.gpsy
		  },function(result){
			     $("#landblockmoreinfo").attr("style","width:650px; height:360px;");
				 $("#landblockmoreinfo").html(result);
				 $("#landblockmoreinfo").show();
				 $( "#landblockmoreinfo" ).draggable({ handle: ".blockPanelTitle",containment: [ 0, 100 ] });
			 });
	 }
	
	function fixBaiduMap(){
		BMapLib.InfoBox.prototype._adjustPosition = function (poi){
		var pixel = this._getPointPosition(poi);
		var icon = this._marker && this._marker.getIcon();
		console.log("before: "+this._div.style.left);
		switch(this._opts.align){
			case INFOBOX_AT_TOP:
				if(this._marker){
					this._div.style.bottom = -(pixel.y - this._opts.offset.height - icon.anchor.height + icon.infoWindowAnchor.height) - this._marker.getOffset().height + 2 + "px";
				}else{
					this._div.style.bottom = -(pixel.y - this._opts.offset.height) + "px";
				}
				break;
			case INFOBOX_AT_BOTTOM:
				if(this._marker){
					this._div.style.top = pixel.y + this._opts.offset.height - icon.anchor.height + icon.infoWindowAnchor.height + this._marker.getOffset().height + "px";
				}else{
					this._div.style.top = pixel.y + this._opts.offset.height + "px";
				}
				break;
		}

		if(this._marker){
			this._div.style.left = pixel.x - icon.anchor.width - this._opts.offset.width + this._marker.getOffset().width + icon.infoWindowAnchor.width - this._boxWidth / 2 + "px";
		}else{
			this._div.style.left = pixel.x - this._opts.offset.width- this._boxWidth / 2 + "px";
		}
		console.log("after: "+this._div.style.left);
	};
	
	BMapLib.InfoBox.prototype._panBox = function(){
        if(!this._opts.enableAutoPan){
            return;
        }
        var mapH = parseInt(this._map.getContainer().offsetHeight,10),
            mapW = parseInt(this._map.getContainer().offsetWidth,10),
            boxH = this._boxHeight,
            boxW = this._boxWidth;
        //infobox窗口本身的宽度或者高度超过map container
        if(boxH >= mapH || boxW >= mapW){
            return;
        }
        //如果point不在可视区域内
        if(!this._map.getBounds().containsPoint(this._point)){
            this._map.setCenter(this._point);
        }
        var anchorPos = this._map.pointToPixel(this._point),
            panTop,panBottom,panY,
            //左侧超出
            panLeft = boxW / 2 - anchorPos.x + this._opts.offset.width + 10,
            //右侧超出
            panRight = boxW / 2 + anchorPos.x - mapW - this._opts.offset.width;
        if(this._marker){
            var icon = this._marker.getIcon();
        }
        //基于bottom定位，也就是infoBox在上方的情况
        switch(this._opts.align){
            case INFOBOX_AT_TOP:
                //上侧超出
                var h = this._marker ? icon.anchor.height + this._marker.getOffset().height - icon.infoWindowAnchor.height : 0;
                panTop = boxH - anchorPos.y + this._opts.offset.height + h + 110 ;
                //break;
            case INFOBOX_AT_BOTTOM:
                //下侧超出
                var h = this._marker ? -icon.anchor.height + icon.infoWindowAnchor.height + this._marker.getOffset().height + this._opts.offset.height : 0;
                panBottom = boxH + anchorPos.y - mapH + h + 150 + this._opts.offset.height;
                break;
        }

        panX = panLeft > 0 ? panLeft : (panRight > 0 ? -panRight : 0);
        panY = panTop > 0 ? panTop : (panBottom > 0 ? -panBottom : 0);
        this._map.panBy(panX,panY);
    }
	}
};
