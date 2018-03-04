var NavigationBar = function(div, map){
	
	this.fillDropDownList = fillDropDownList;
	
	init();
	var treeData;
	var dropdownLists = [];
	var me=this;
	var remoteMap = null;
	
	var defaultCity = {applyed:false, name:"日照市"};
	function init(){
		$.getJSON("../api/province/cities/tree",function(data){
			treeData = data.data;
			var city = getCityByName(defaultCity.name);
			fillDropDownList(city.parent, city.id, null, true);
		});
	}
	
	function fillDropDownList(province, city, site, zoom){
		var cities = getCityByParent();
		if(cities.length>0){
			bindFirstDropdownList(cities);
			selectProvince(province);
			selectCity(city, zoom, function(sites){
				if(site){
					var siteObj;
					for(var i=0;i<sites.length;i++){
						if(sites[i].id==site){
							siteObj = sites[i];
							break;
						}
					}
					onSelectSite(siteObj,false);
				}
			});
			
		}
	}
	
	function getCityById(id){
		for(var i=0;i<treeData.length;i++){
			if(treeData[i].id==id){
				return treeData[i];
			}
		}
	}
	
	function getCityByName(name){
		for(var i=0;i<treeData.length;i++){
			if(treeData[i].text==name){
				return treeData[i];
			}
		}
	}
		
	function getCityByParent(parentId){
		var result = [];
		parentId = parentId||"#";
		for(var i=0;i<treeData.length;i++){
			if(treeData[i].parent==parentId){
				result.push(treeData[i]);
			}
		}
		return result;
	}
	
	function bindFirstDropdownList(cities){
		//var menu = $("#"+div+" .sel_firstmenu").first();
		var dropDownNode = $("#"+div+" .sel_firstmenu .sel_dropdown").first();
		
		clearDropdown(1);
		for(var i=0;i<cities.length;i++){
			var city = cities[i];
			var item = $("<li><a href='#'>"+city.text+"</a></li>").click(city, function(evt){
				onselectProvince(evt.data,function(cities){
					selectCity(cities[0].id,true);
				});
			});
			dropDownNode.append(item);
		}
	}
	
	function selectProvince(id){
		var province = getCityById(id);
		onselectProvince(province);
	}
	
	function onselectProvince(item, callback){
		var title = $("#"+div+" .sel_firstmenu .sel_title").first();
		title.text(item.text);
		var cities = getCityByParent(item.id);
		bindSecondDropdownList(cities);
		if(callback){
			callback(cities);
		}
	}
	
	function bindSecondDropdownList(cities){
		var dropDownNode = $("#"+div+" .sel_secondmenu .sel_dropdown").first();
		var title = $("#"+div+" .sel_secondmenu .sel_title").first();
		clearDropdown(2);
		for(var i=0;i<cities.length;i++){
			var city = cities[i];
			var item = $("<li><a href='#'>"+city.text+"</a></li>").click(city, function(evt){
				onselectCity(evt.data, true);
			});
			dropDownNode.append(item);
		}
	}
	
	function selectCity(id,zoom,callback){
		var city = getCityById(id);
		onselectCity(city,zoom,function(sites){
			if(callback){
				callback(sites);
			}
		});
	}
	
	function onselectCity(item, zoom, callback){
		var title = $("#"+div+" .sel_secondmenu .sel_title").first();
		title.text(item.text);
		if(zoom){
			map.zoomTo(item.text);
		}
		$.getJSON("../api/province/cities/tree/city/"+item.id.split("_")[1]+"",function(sites){
			bindThirdDropdownList(sites.data);
			if(callback){
				callback(sites.data);
			}
		});
	}
	
	
	function bindThirdDropdownList(sites){
		var dropDownNode = $("#"+div+" .sel_thirdmenu .sel_dropdown").first();
		clearDropdown(3);
		var title = $("#"+div+" .sel_thirdmenu .sel_title").first();
		title.text("全部");
		for(var i=0;i<sites.length;i++){
			var site = sites[i];
			if(site&&site.gpsx&&site.gpsy){
				var item = $("<li><a href='#'>"+site.text+"</a></li>").click(site, function(evt){
					onSelectSite(evt.data,true);
				});
				dropDownNode.append(item);
			}
		}
	}
	//set text and zoom to
	function onSelectSite(site, zoomTo){
		var title = $("#"+div+" .sel_thirdmenu .sel_title").first();
		title.text(site.text);
		if(zoomTo){
			map.zoomToXY(site.gpsx,site.gpsy,14);
		}
	}
	
	function clearDropdown(level){
		if(level==1){
			$("#"+div+" .sel_firstmenu .sel_dropdown").first().empty();
			$("#"+div+" .sel_secondmenu .sel_dropdown").first().empty();
			$("#"+div+" .sel_thirdmenu .sel_dropdown").first().empty();
			
			$("#"+div+" .sel_firstmenu .sel_title").first().empty();
			$("#"+div+" .sel_secondmenu .sel_title").first().empty();
			$("#"+div+" .sel_thirdmenu .sel_title").first().empty();
		}
		if(level==2){
			$("#"+div+" .sel_secondmenu .sel_dropdown").first().empty();
			$("#"+div+" .sel_thirdmenu .sel_dropdown").first().empty();
			
			$("#"+div+" .sel_secondmenu .sel_title").first().empty();
			$("#"+div+" .sel_thirdmenu .sel_title").first().empty();
		}
		if(level==3){
			$("#"+div+" .sel_thirdmenu .sel_dropdown").first().empty();
			
			$("#"+div+" .sel_thirdmenu .sel_title").first().empty();
		}
	}
	
	$(".filterRight").click(function(){
		$(".filterRight").removeClass("filterrightselect");
		$(this).addClass("filterrightselect");
	})
	
	$("#chartsSetBtn").click(function(){
		$("#navigationfilter").show();
		$("#geoinfo").show();
		$("#remotesensediv").hide();
		$("#machinemap").hide();
		
	})	
	$("#dataSetBtn").click(function(){
		$("#navigationfilter").hide();
		$("#geoinfo").hide();
		$("#remotesensediv").show();
		$("#machinemap").hide();
		if(!remoteMap){
			new RemoteMap("remotesense");
		}
	})
	
	
	
	$("#themeSetBtn").click(function(){
		$("#navigationfilter").hide();
		$("#geoinfo").hide();
		$("#remotesensediv").hide();
		$("#machinemap").show();
		me.machineMap.centerZoom();
	})
}