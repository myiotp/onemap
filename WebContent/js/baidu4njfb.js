var MachineMap=function(divname,licnumber){
	var allSites={};
	var 	lastInfoBox;
	var map,
		infoBox;
	var opts = {
			width : 250,     // 信息窗口宽度
			height: 80,     // 信息窗口高度
			title : "信息" , // 信息窗口标题
			enableMessage:false//设置允许信息窗发送短息
	    };
	var myIcon = new BMap.Icon("../images/car001.jpg",
					new BMap.Size(24, 21), { //小车图片
						//offset: new BMap.Size(0, -5),    //相当于CSS精灵
						imageOffset : new BMap.Size(0, 0)
					//图片的偏移量。为了是图片底部中心对准坐标点。
					});
	var onceCenter = false;
	this.centerZoom=function(){
		if(!onceCenter){
			map.centerAndZoom("莒县",8);
			onceCenter = true;
		}
	}

	this.initializemachinemap=function () {
		// 百度地图API功能
		map = new BMap.Map(divname);
		map.addControl(new BMap.NavigationControl({offset:new BMap.Size(0, 50)})); // 添加平移缩放控件
		map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
		map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小
		//map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件
		//map.setMapType(BMAP_SATELLITE_MAP);
		//var point = new BMap.Point(113.680055, 33.549301); // 创建点坐标
		//map.centerAndZoom("郑州",8); // 初始化地图,设置中心点坐标和地图级别。
		
		
		var bdary = new BMap.Boundary();
		bdary.get("日照市", function(rs){       //获取行政区域
			//map.clearOverlays();        //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			for(var i = 0; i < count; i++){
				var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 4, strokeColor: "#4e72b8",strokeOpacity : 0,
					fillOpacity : 0, fillColor:""}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				//map.setViewport(ply.getPath());    //调整视野         
			}
		});	
		
		$.getJSON("../api/uservehicle/listJson"+licnumber,
						function(responseData) {
							//(responseData);
							//var responseData = responseData2.data;
							var llen=responseData.length;
							for ( var i = 0; i < llen; i=i+300) {
								if(i > 1000){
									break;
								}
								(function(_i) {
									setTimeout(function() {									
										for(j=_i; j<_i+300 && j<llen; j++){
											//console.log(_i+","+i+","+j)
											var licensenumber=responseData[j].licenseplate;
											var id=responseData[j].id;
											var gpsx=responseData[j].fromlng;
											var gpsy=responseData[j].fromlat;
											if(gpsx>0 && gpsy >0){
												var point = new BMap.Point(gpsx, gpsy); // 创建点坐标
												var marker = new BMap.Marker(point, {
													icon : myIcon
												});  // 创建标注
												//console.log(j);
												map.addOverlay(marker);            // 将标注添加到地图中
												//addClickHandler(id, licensenumber,gpsx,gpsy,marker);	
												siteOnClick(id,marker);
												var label = new BMap.Label(licensenumber,{offset:new BMap.Size(20,-10)});
												label.setStyle({
													"maxWidth": "inherit"
												});
												marker.setLabel(label);
												//loadLandblock(id); //here will only load the site which has gps x and y.
												allSites[id] = responseData[j];								
											}	
										}									
									}, 1000+_i * 3);
								})(i);
													
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
//		     });
		  });
		 
	 }
	
	function addClickHandler(id, licensenumber,gpsx,gpsy,marker){
		
			marker.addEventListener("click",function(e){
				
				var url="../farmmachinery/showMachineinfo?x="+gpsx+"&y="+gpsy+"&licensenumber="+licensenumber;
				var width = 255, height=430;
				
				 $.get(url,function(result){
					 
					 result=result.replace(/\{id\}/g,"mappop"+id);
					 if(infoBox){
						 infoBox.close();
					 }
					 infoBox = marker.infoBox;
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
						 marker.infoBox = infoBox;
						 infoBox.open(marker);
						 $("#mappop"+id+" .title div").click( function () {
							    
								$("#mappop"+id+" .title div").removeClass("select");			
								$(this).addClass("select");
								
								$("#mappop"+id+" .content").hide();
								var contentid=$(this).attr("id")+"content";
								$("#"+contentid).show();
								
						 });
					 }else{
						 infoBox.open(marker);
					 }
				 });
				
				
				 
			  }
			);
	}
	
	
			
	 


	
	
}

