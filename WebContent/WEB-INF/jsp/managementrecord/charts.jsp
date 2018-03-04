<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生产过程回溯图表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="../ng-table/css/bootstrap.min.css" />	
<link rel="stylesheet" type="text/css" href="../charts/highslide.css" />

 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 

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
		<c:param name="memuid" value="3"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="3"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div>
			<c:choose>
				<c:when test="${!empty resultList}">
					<div id="container" style="min-width: 400px; height: 550px; width:900px;margin: 0 auto"></div>
				</c:when>
				<c:otherwise>暂无作物生产过程回溯记录！</c:otherwise>
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
	          <div id="operationTime"></div>
	          <div id="operationType"></div>
			  <div id="operator"></div>
			  <div id="blockNumber"></div>
	          <div id="cropType"></div>
	          <div id="trail"></div>
			  描述：<br><div id="mgtrecordmemo"></div>	  
			  <input  type="hidden" name="_testresult2" id="_testresult2"/>           
	        </div>
	    </div>
	</div>
	<div id="dialog-sendMessage" title="发送到手机">
		<div class="content_nr">
	        <div class="ny_main">
			<form id="sendMessageForm" name="sendMessageForm" method="post" action="./sendMessage" class="form-inline">
				<div id="sendMessageResponse"></div><div id="sendMessageResponse1"></div>
				输入手机号码(以逗号隔开)：<input type="text" id="phonenumber"
							name="phonenumber" class="span3" placeholder="" size="77px">
				<div id="_testresult"></div>
					<input id="_testresult0" type="hidden"/><input id="_testresult1" type="hidden"/>	
			</form>  
	        </div>
	    </div>
	</div>

	<script type="text/javascript">
	var sendMessage = function() {
		var phonenumber=$('#phonenumber').val();
		var message1 = $('#_testresult0').val();
		var message2 = $('#_testresult1').val();
		$.post("./sendMessage", { "n": phonenumber,"m": message1},
		   function(data){			 
			 //console.log(data); // 
			 $('#sendMessageResponse').html("第一条信息"+data.result);
		   }, "json");
		$.post("./sendMessage", { "n": phonenumber,"m": message2},
		   function(data){			 
			 //console.log(data); // 
			 $('#sendMessageResponse1').html("第二条信息"+data.result);
		   }, "json");   
		//$("#dialog-sendMessage").dialog("close");
	}
	function showDetailDialog2(id, cooperativeId, blockId,gpsx,gpsy) {
		var url = encodeURI('./id/' + id);
		var trailUrl = encodeURI('./chart?blockId=' + blockId +'&siteId='+cooperativeId+'&x='+gpsx+'&y='+gpsy);
	
// 	// Get the CSV and create the chart
	 	$
	 		.getJSON(
	 					url,
	 					function(responsedata) {
	 					//(responsedata);
			$('#operationTime').html("操作时间："+getDefaultFormatDateByLong(responsedata.operationTime));
			$('#operationType').html("操作类型："+responsedata.operationType);
			var content="";
			var message1="";
			var message2="";
			var jsonData = eval('(' + responsedata.var1 + ')');
			if(jsonData != null){				
				var content0 = jsonData.test;
				message1 = "地块["+responsedata.blockNumber+"]检验结果:有机质"+content0[0].v2+"%,铵态氮"+content0[1].v2+"mg/L,硝态氮"+content0[2].v2+"mg/L,磷"+content0[3].v2+"mg/L,钾"+content0[4].v2+"mg/L,钙"+content0[5].v2+"mg/L";
				message2 = "地块["+responsedata.blockNumber+"]施肥建议:硝态氮"+content0[2].v6+"公斤/亩,磷"+content0[3].v6+"公斤/亩,钾"+content0[4].v6+"公斤/亩";
				content = message1 + "\n\r" + message2+"\n\r检验机构:中国农业科学院国家测土施肥中心实验室";
			}
			$('#_testresult').html('消息内容：<pre class="prettyprint linenums">'+content+'</pre>');
			var url="http://115.100.62.174/onemap/c/v?var8=" + responsedata.var8;
			$('#_testresult0').val(message1+"\n\r详情页面链接:"+url+"\n\r检验机构:中国农业科学院国家测土施肥中心实验室");
			$('#_testresult1').val(message2+"\n\r详情页面链接:"+url+"\n\r检验机构:中国农业科学院国家测土施肥中心实验室");
			$('#operator').html("操作人："+responsedata.operator);
			$('#blockNumber').html("地块编号："+responsedata.blockNumber);
			$('#cropType').html("作物类型："+responsedata.cropType);
			if(responsedata.operationType == "测土配方" || responsedata.operationType == "移植"){
			   $('#trail').html("查看轨迹：无");
			}else{
			   $('#trail').html("查看轨迹： <a href='"+trailUrl+"' target='_blank'> <img src='../images/car001.jpg'</a>");
			}
			$('#mgtrecordmemo').html(responsedata.mgtrecordmemo);
			$('#dialog-showDetail').dialog('open');
			
			var buttons = $('.dialog1 .ui-dialog-buttonpane button');
			for ( var i = 0; i < buttons.length; ++i )
			{
			   var jButton = $( buttons[i] );
			   if ( jButton.text() == '发送到手机') {
				  if( responsedata.operationType == '测土配方') {
					   jButton.prop("disabled", false).removeClass("ui-state-disabled");
				   }else{
					   jButton.prop("disabled", true).addClass("ui-state-disabled");
				   } 
				  //disable this control  
				  //jButton.prop("disabled", true).addClass("ui-state-disabled");
			   } 
			}
			return false;
						
	 	});
			
	}
	</script>	
	<script type="text/javascript">	
	var showDetailDialog = $("#dialog-showDetail").dialog({
		dialogClass : 'dialog1',
		autoOpen: false,
		modal: true,
		height: 500,
		width: 800,
		buttons:[
		    {
		    	text:"发送到手机",
		    	class: "ui-button",
				click: function () {
					$('#sendMessageResponse').html("");
					$('#sendMessageResponse1').html("");
					$('#dialog-sendMessage').dialog('open');
				}
		    },
		    {
				text:"关闭",
				class: "ui-button  ui-button-primary",
				click: function () {
					$(this).dialog("close");
				}
			}
		]
		
	});	
    
    var sendMessageDialog = $("#dialog-sendMessage").dialog({
		dialogClass : 'dialog2',
		autoOpen: false,
		modal: true,
		height: 400,
		width: 600,
		buttons:[
		    {
				text:"发送",
				class: "ui-button  ui-button-primary",
				click: sendMessage
			},
			{
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
	var gpsx = <%=request.getParameter("x")%>;
	var gpsy = <%=request.getParameter("y")%>;
	
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
								innerArr+='y:'+(jsonData.data[index])+',marker: {symbol: \'url(../images/lifecycle/a'+jsonData.type[index]+'.png)\',width:50,height:45},';
								innerArr+='id:'+(jsonData.id[index])+',';
								innerArr+='operationType:\'' +jsonData.operationType[index]+'\',';
								innerArr+='operator:\'' +jsonData.operator[index]+'\',';
								innerArr+='type:\'' +jsonData.type[index]+'\',';
								innerArr+='amount:\'' +jsonData.amount[index]+'\',';
								innerArr+='company:\'' +jsonData.company[index]+'\'';
								//innerArr+='diseased:\'' +jsonData.diseased[index]+'\'';
								//innerArr+='naturalDisaster:\'' +jsonData.naturalDisaster[index]+'\'';
								innerArr+='}';
							}		
							 //(innerArr);
							dataIntArr.push(eval('(' + innerArr + ')'));     
						  });
						 dataIntArr.push(eval("({x:"+jsonData.newtime+",y:-1})"));
				//(dataIntArr);	
				//var newchart = new Highcharts.Chart({
		        $('#container').highcharts({
		            chart: {
		                type: 'scatter',
            			zoomType: 'xy'
		            },
		            title: {
		                text: '农作物生产过程回溯'
		            },
		            subtitle: {
		                text: '展示农作物从种植到收割的全过程数据'
		            },
		            xAxis: {
		                type: 'datetime',
		                labels: {
		                    overflow: 'justify'
		                },
		                startOnTick: true,
			            endOnTick: true,
			            showLastLabel: true
			           // If we have a text (category), return it. Else, return empty string.
			           /*  labels: {
			                formatter: function() {
			                    var value = this.value;
			                    //(value);
			                   	return getSimpleFormatDateByLong(value);
			                }
			            } */
		            },
		            yAxis: {
		                title: {
		                    text: ''
		                },
		                startOnTick: true,
			            endOnTick: true,
			            showLastLabel: true,
			            min: 50,
			            max: 50,
			            offset:20,
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
		                        text: '测土配方',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Light breeze
		                    from: 5,
		                    to: 10,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '耕地',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 10,
		                    to: 15,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '施肥',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 15,
		                    to: 20,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '播种',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 20,
		                    to: 25,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '移植',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 25,
		                    to: 30,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '病害防治',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 30,
		                    to: 35,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '虫害防治',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 35,
		                    to: 40,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '草害防治',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 40,
		                    to: 45,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '灌溉',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 45,
		                    to: 50,
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
										showDetailDialog2(this.id, cooperativeId, block,gpsx,gpsy);
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
								lineWidth: 1
							}
						}
		            },
		            series: [{
		                name: jsonData.cooperativeName+'-地块('+jsonData.blockNumber+')',
		                data: dataIntArr//responsedata.split(',')		    
		            }]
		            ,
		            navigation: {
		                menuItemStyle: {
		                    fontSize: '10px'
		                }
		            }
		        });
		        
		        //newchart.xAxis[0].setExtremes(-1);
		    });
			
			
		</script>
</body>
</html>
