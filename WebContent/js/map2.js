/**
 * 
 */
//var mapUrl="http://115.100.62.174:8082/geoserver/henan001/wms";
var mapUrl="http://"+MAP_SERVER_IP+":"+MAP_SERVER_PORT+"/geoserver/henan001/wms";
var _layers="henan001:henan001_county";
var map, drawControls, selectControl, selectedFeature;
OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
var id = '001';

function onPopupClose(evt) {
	selectControl.unselect(selectedFeature);
}
function onFeatureSelect(feature) {
	selectedFeature = feature;
	popup = new OpenLayers.Popup.FramedCloud("chicken", feature.geometry
			.getBounds().getCenterLonLat(), null,
			"<div style='font-size:.8em'>农机编号: " + feature.attributes.machineryNumber + "<br>农机类型: "
					+ feature.attributes.machineryType+"<br>所属合作社:"+feature.attributes.cooperative
					+ "<br>农机状态:"+feature.attributes.status+"<br>农机手姓名:"+feature.attributes.username
					+"<br>农机手电话:"+feature.attributes.tel
					+"</div>", null, true,
			onPopupClose);
	feature.popup = popup;
	map.addPopup(popup);
	
}
function onFeatureUnselect(feature) {
	map.removePopup(feature.popup);
	feature.popup.destroy();
	feature.popup = null;
}
function loadSuccess(request) {
	//(request.responseText);
	document.getElementById("map").innerHTML = "";
	var bounds = new OpenLayers.Bounds(
                    480456.6800478325, 3923919.553107502,
                    1053507.0211759203, 4448392.03913579
                );
    var options = {
        maxExtent: bounds,
        maxResolution: 2238.477895031593,
        projection: "EPSG:2401",
        units: 'm'
    };

	map = new OpenLayers.Map('map', options);
	// Refresh vector layer
	for ( var i = 0; i < map.layers.length; i++) {
		if (map.layers[i].isVector) {
			map.layers[i].removeAllFeatures();
			map.layers[i].refresh({
				force : true
			}); // Vector layer
		}
	}
	

	// create the layer styleMap by giving the default style a context
	var strokeColors = [ "#2d9908", "#991F7A", "#990000","#1F7A99", "#5200A3", "#B20000","#16556B", "#E68A00", "#00248F", "#4D4DFF" ];
	var fillColors =   [ "#3eb216", "#FF33CC", "#AD3333","#33CCFF", "#944DDB", "#FF9999","#1F7A99", "#FFA319", "#0033CC", "#B2B2FF" ];
	

	//
	// map.addLayers([vectors]);
	var jsonData = eval('(' + request.responseText + ')');
	//(jsonData);
	var responseData = jsonData.data;
	var vectors = [];
	for ( var i = 0; i < responseData.length; i++) {
		var feature = new OpenLayers.Feature.Vector(OpenLayers.Geometry
				.fromWKT(responseData[i].item));		
		feature.attributes = { "cooperative" : responseData[i].cooperative, "machineryNumber":responseData[i].machineryNumber,
				"machineryType":responseData[i].machineryType,"status":responseData[i].status,
				"username":responseData[i].username,"tel":responseData[i].tel,
				"uid": responseData[i].id};
		// set style
		var template = {
			// pointRadius: "${getSize}", // using context.getSize(feature)
			strokeColor : strokeColors[i%10],
			strokeOpacity : 1,
			strokeWidth : 1,
			fillOpacity : 0.80,
			fillColor : fillColors[i%10] // using context.getFillColor(feature)
		};
		var style = new OpenLayers.Style(template);
	
		var _vectors = new OpenLayers.Layer.Vector(responseData[i].machineryNumber, {
			isBaseLayer : false,
			styleMap : new OpenLayers.StyleMap({
				"default" : style,
				"temporary" : new OpenLayers.Style({
					strokeColor : 'blue',
					strokeOpacity : 1,
					strokeWidth : 1,
					fillColor : 'blue',
					fillOpacity : 0.60
				})
			}),
			rendererOptions : {
				zIndexing : true
			}
		});
		_vectors.addFeatures([ feature ]);
		map.addLayers([_vectors ]);
		vectors.push(_vectors);
	}

	/*
	 * var feature2 = new OpenLayers.Feature.Vector(
	 * OpenLayers.Geometry.fromWKT( "POLYGON((-120.828125 -50.3515625, -80.1875
	 * -80.0078125, -40.40625 -20.4140625,-60.40625 -10.4140625, -120.828125
	 * -50.3515625))" ) ); vectors.addFeatures([feature2]);
	 */
	var report = function(e) {
		OpenLayers.//(e.type, e.feature.id);
	};

	var highlightCtrl = new OpenLayers.Control.SelectFeature(vectors, {
		hover : true,
		highlightOnly : true,
		renderIntent : "temporary"
	});

	selectControl = new OpenLayers.Control.SelectFeature(vectors, {
		clickout : true,
		onSelect : onFeatureSelect,
		onUnselect : onFeatureUnselect
	});

	map.addControl(highlightCtrl);
	map.addControl(selectControl);

	highlightCtrl.activate();
	selectControl.activate();

	// var wmsLayer = new OpenLayers.Layer.WMS( "OpenLayers WMS",
	// "http://localhost:8082/geoserver/chinamap/wms?service=WMS&version=1.1.0&request=GetMap&layers=chinamap:CHN_adm0&styles=&bbox=73.557702,15.780000000000086,134.77392499999996,53.560861000000045&width=534&height=330&srs=EPSG:2401&format=application/openlayers",
	// {layers: 'chinamap:CHN_adm0'});
	// "http://vmap0.tiles.osgeo.org/wms/vmap0?", {layers: 'basic'});

	// var polygonLayer = new OpenLayers.Layer.Vector("Polygon Layer");

	var wmsLayer = new OpenLayers.Layer.WMS(
			"基础地图",
			mapUrl,
			{
				// srs: 'EPSG:4269',
				layers : _layers,
				styles : '',
				format : 'image/png'
			}, {
				singleTile : true,
				ratio : 1,
				displayInLayerSwitcher : true,
				visibility : true,
				isBaseLayer : true
			});

	map.addLayers([wmsLayer ]);
	map.addControl(new OpenLayers.Control.LayerSwitcher());
	map.addControl(new OpenLayers.Control.MousePosition());
	// build up all controls
	// map.addControl(new OpenLayers.Control.PanZoomBar({
	// position: new OpenLayers.Pixel(2, 15)
	// }));
	map.addControl(new OpenLayers.Control.Navigation());
	map.addControl(new OpenLayers.Control.Scale());
	//map.addControl(new OpenLayers.Control.MousePosition());
	//var bounds = new OpenLayers.Bounds(-109.06, 36.992, -102.041, 41.003);
	//map.zoomToExtent(bounds);
	map.zoomToMaxExtent();
	/*
	 * selectControl = new OpenLayers.Control.SelectFeature(polygonLayer,
	 * {onSelect: onFeatureSelect, onUnselect: onFeatureUnselect}); drawControls = {
	 * polygon: new OpenLayers.Control.DrawFeature(polygonLayer,
	 * OpenLayers.Handler.Polygon), select: selectControl };
	 * 
	 * for(var key in drawControls) { map.addControl(drawControls[key]); }
	 */

	map.setCenter(new OpenLayers.LonLat(0, 0), 1);
}

function loadFailure(request) {
	document.getElementById("map").innerHTML = "";
	//("failed to load");
}
function openMap(url) {
	OpenLayers.Request.GET({
		url : url,
		success : loadSuccess,
		failure : loadFailure
	});
}