<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生长状态回溯图表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
<link rel="stylesheet" type="text/css" href="../charts/highslide.css" />

 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" href="../openlayers/theme/default/style.css" type="text/css">
<link rel="stylesheet" href="../openlayers/style.css" type="text/css">
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<style type="text/css">
${
demo
.css
}
</style>
	
</head>
<body>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="2"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="2"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div>
			<c:choose>
				<c:when test="${!empty resultList}">
					<div id="container" style="min-width: 400px; height: 550px; width:900px;margin: 0 auto"></div>
				</c:when>
				<c:otherwise>暂无作物生长状态记录！</c:otherwise>
			</c:choose>
	    </div>
		<script src="../charts/js/highcharts.js"></script>
		<script src="../charts/js/modules/data.js"></script>
	<!-- <script src="../charts/js/modules/exporting.js"></script> -->	
	   <!-- Additional files for the Highslide popup effect -->
		<script type="text/javascript" src="../charts/highslide-full.min.js"></script>
		<script type="text/javascript" src="../charts/highslide.config.js" charset="utf-8"></script>		
	</div>
	<c:import url="../common/footer.jsp"></c:import>	
	<div id="dialog-showDetail" title="详细描述">
	<div class="content_nr">
        <div class="ny_main">
		  <div id="blockNumber"></div>
          <div id="cropType"></div>	
          <div id="video"></div>
          <div id="gatherTime"></div>		  
		  描述：<br><div id="picture"></div>             
        </div>
    </div>
</div>

	<script type="text/javascript">
		function showDetailDialog2(id) {
		var url = encodeURI('./id/' + id);
	
// 	// Get the CSV and create the chart
 	$
 		.getJSON(
 					url,
 					function(responsedata) {
 					//(responsedata);
		$('#gatherTime').html("操作时间："+getDefaultFormatDateByLong(responsedata.gatherTime));
		$('#video').html("生长状态："+responsedata.video);
		$('#blockNumber').html("地块编号："+responsedata.blockNumber);
		$('#cropType').html("作物类型："+responsedata.cropType);
		$('#picture').html(responsedata.picture);
		$('#dialog-showDetail').dialog('open');
		return false;
					
 	});
		
	}
	
	</script>	
	<script type="text/javascript">	
	var showDetailDialog = $("#dialog-showDetail").dialog({
		autoOpen: false,
		modal: true,
		height: 600,
		width: 900,
		buttons:[{
				text:"关闭",
				class: "ui-button",
				click: function () {
					$(this).dialog("close");
				}
			}
		]
	});	
	</script>
	
	<script type="text/javascript">
	var block = '<%=request.getParameter("blockId")%>';	
	var crop = '<%=request.getParameter("cropTypeId")%>';
	var url = encodeURI('./block/' + block + '/crop/' + crop + '/charts/csv');
	
	// Get the CSV and create the chart
	$
			.getJSON(
					url,
					function(jsonData) {
						//var jsonData =  eval('(' + responsedata + ')'); 
						//(jsonData);
						var cooperativeId = jsonData.cooperativeId;
						var dataStrArr=jsonData.time;//分割成字符串数组
						 var dataIntArr=[];//保存转换后的整型字符串
						  $.each( dataStrArr, function( index, data ) {
						 //dataStrArr.forEach(function(data,index,arr){  
							 var innerArr = '{';
							 var match = data
								.match(/^([0-9]{1,2})\/([0-9]{1,2})\/([0-9]{4})$/);
							if (match) {
								//(match);
								 innerArr+='x:'+(Date.UTC(+(match[3]),match[1] - 1,+match[2]))+',';
								innerArr+='y:'+(jsonData.data[index])+',marker: {symbol: \'url(../images/lifecycle2/a'+jsonData.type[index]+'.png)\',width:50,height:45},';
								innerArr+='id:'+(jsonData.id[index])+',';
								innerArr+='operationType:\'' +jsonData.operationType[index]+'\',';
								//innerArr+='operator:\'' +jsonData.operator[index]+'\',';
								innerArr+='type:\'' +jsonData.type[index]+'\',';
								//innerArr+='amount:\'' +jsonData.amount[index]+'\',';
								//innerArr+='company:\'' +jsonData.company[index]+'\'';
								//innerArr+='diseased:\'' +jsonData.diseased[index]+'\'';
								//innerArr+='naturalDisaster:\'' +jsonData.naturalDisaster[index]+'\'';
								innerArr+='}';
							}							
							dataIntArr.push(eval('(' + innerArr + ')'));     
						    });
						 dataIntArr.push(eval("({x:"+jsonData.newtime+",y:-1})"));
				//(dataIntArr);		
		        $('#container').highcharts({
		            chart: {
		                type: 'scatter',
            			zoomType: 'xy'
		            },
		            title: {
		                text: '农作物生长状态回溯'
		            },
		            subtitle: {
		                text: '展示农作物的生长状态数据'
		            },
		            xAxis: {
		                type: 'datetime',
		                labels: {
		                    overflow: 'justify'
		                },
		                startOnTick: true,
			            endOnTick: true,
			            showLastLabel: true
		            },
		            yAxis: {
		                title: {
		                    text: ''
		                },
		                startOnTick: true,
			            endOnTick: true,
			            showLastLabel: true,
			            min: 30,
			            max: 30,
		                labels: {
		                    formatter: function() {		                    	
		                        return ''; //this.value +'km';
		                    }
		                },
		                min: 0,
		                minorGridLineWidth: 0,
		                gridLineWidth: 0,
		                alternateGridColor: null,
		                plotBands: [{ // Light air
		                    from: 0,
		                    to: 5,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '播种出苗',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Light breeze
		                    from: 5,
		                    to: 10,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '越冬分蘖',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 10,
		                    to: 15,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '返青拔节',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 15,
		                    to: 20,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '抽穗扬花',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 20,
		                    to: 25,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '灌浆乳熟',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 25,
		                    to: 30,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '收获',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }]
		            },
		            tooltip: {
					    shared:true,
						useHTML:true,
						shadow:false,
						borderWidth:0,
						backgroundColor:'transparent',
						positioner: function() {
							return { x: 0, y: 0 }
						},
						headerFormat: 	'',
						pointFormat: 	'',
						footerFormat: 	'',
		                valueSuffix: ''
		            },
		            plotOptions: {
		                
						series: {
							cursor: 'pointer',
							point: {
								events: {
									click: function (e) {
										//openDialog(e,this, cooperativeId, block);
										showDetailDialog2(this.id);
										/* hs.htmlExpand(null, {
											pageOrigin: {
												x: e.pageX,
												y: e.pageY
											},
											headingText: this.series.name,
											maincontentText: '操作时间:'+Highcharts.dateFormat('%Y/%m/%e', this.x) + '<br>操作类型:'+this.operationType +'<br>操作人:' 
											+this.operator + '<br>类型:'+this.type+'<br>数量:'+this.amount+'<br>厂商:'+this.company
											+'<br><a href="#" onclick="hs.close(this);openDialog('+cooperativeId+','+ this.x+')">查看GPS轨迹</a><br/> ',
											width: 200
										}); */
									}
								}
							},
							marker: {
								lineWidth: 1,
								symbol: 'square'
							}
						},
						spline: {
							marker: {
								radius: 4,
								lineColor: '#2d9908',
								lineWidth: 1
							}
						}
		            },
		            series: [{
		                name: jsonData.cooperativeName+'-地块('+jsonData.blockNumber+')',
		                color: 'orange',
		                data: dataIntArr//responsedata.split(',')		    
		            }]
		            ,
		            navigation: {
		                menuItemStyle: {
		                    fontSize: '10px'
		                }
		            }
		        });
		    });

			
		</script>
</body>
</html>
