<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生产过程回溯图表</title>
<script>
	$(function(){
		$("#blockPanelProcessClose").click(function(){
			$("#blockPanelProcess").parent().hide();
		});
	})
</script>
<style>
	#blockPanelProcess{
		 background-color: #fff;
	}
	
	#blockPanelProcess .blockPanelTitle{
		padding: 10px 10px 10px 30px;
  		border-bottom: solid #b5b5b6 1px;
	}
	
	#blockPanelProcess .blockPanelTitle span{
		color: #898989;
  		font-size: 14px;
	}
	
	#blockPanelProcess .blockPanelTitle img{
		float: right;
	}
	
	#blockPanelProcess .blockPanelTitle img:hover{
		cursor: pointer;
	}
	
	#blockPanelProcess .blockPanelContent{
		 float: left;
	}
	
	#blockPanelProcess .blockbolder{
	     padding: 10px 5px 5px 10px;
	}
	
</style>
	
</head>
<body>
	<div id="blockPanelProcess">
		<div class="blockPanelTitle">
			<span>作物产量预测图表</span>
			<img id="blockPanelProcessClose" alt="关闭" src="../images/close.png">
		</div>
		<div class="blockbolder">
			<div class="blockPanelContent">
				<div>
			<%
			String type = request.getParameter("type");
			String cropTypeId = request.getParameter("cropTypeId");
			if(type != null || cropTypeId == null){
			%>
			区划所提供的结合遥感的产量预测一览图
			<% 	
			}else{
			%>
			<c:choose>
				<c:when test="${!empty resultList}">
					<script type="text/javascript">
						$(function() {
							var block = '<%=request.getParameter("blockId")%>';	
							var crop = '<%=request.getParameter("cropTypeId")%>';
							var url = encodeURI('../yieldprediction/block/' + block + '/crop/' + crop + '/charts/csv');
							
							// Get the CSV and create the chart
							$.getJSON(url,function(csv) {
								setTimeout(function(){
									drawChart(csv);
								},10);
							});
					
						});
						
						function drawChart(csv){
							$('#container')
							.highcharts(
									{

										data : {
											csv : csv,
											// Parse the American date format
											parseDate : function(s) {
												
												if (s) {
													//(s);
													var match = s
															.match(/^([0-9]{1,2})\/([0-9]{1,2})\/([0-9]{4})$/);
													if (match) {
														//(match);
														return Date.UTC(+(match[3]),match[1] - 1,+match[2]);
														//return match[3]+'/'+(match[1] - 1)+'/'+match[2];
													}
												}
											}
										},
										chart: {
							                type: 'spline'
							            },

										title : {
											text : '产量预测表'
										},

										subtitle : {
											text : '数据源: 区划所测产系统提供的数据'
										},

										xAxis : {
											type : 'datetime',
											tickInterval : 24 * 3600 * 1000 * 30, // one month
											//tickWidth : 0,
											gridLineWidth : 1,
											dateTimeLabelFormats: { 
												month: '%e. %b',
        										year: '%b'									                    
							                },
											labels : {
												align : 'left',
												x : 3,
												y : -3														
											}
										},

										yAxis : [ { // left y axis
											title : {
												text : null
											},
											labels : {
												align : 'left',
												x : 3,
												y : 16,
												format : '{value:.,0f}'
											},
											showFirstLabel : false
										}, { // right y axis
											linkedTo : 0,
											gridLineWidth : 0,
											opposite : true,
											title : {
												text : null
											},
											labels : {
												align : 'right',
												x : -3,
												y : 16,
												format : '{value:.,0f}'
											},
											showFirstLabel : false
										} ],

										legend : {
											enabled: true
										},

										tooltip : {
											shared : true,
											crosshairs : true,
											dateTimeLabelFormats: { 
												day: '%Y/%m/%e'								                    
							                }
										},

										plotOptions : {
											series : {
												cursor : 'pointer',
												point : {
													events : {
														click : function(
																e) {
															hs
																	.htmlExpand(
																			null,
																			{
																				pageOrigin : {
																					x : e.pageX,
																					y : e.pageY
																				},
																				headingText : this.series.name,
																				maincontentText : '预测时间:'+Highcharts.dateFormat('%Y/%m/%e', this.x) 
																				        +'<br/>预测产量:'
																						+ this.y
																						+ ' (斤)',
																				width : 200
																			});
														}
													}
												},
												marker : {
													radius: 4,
							                        lineColor: '#666666',
													lineWidth : 1
												}
											},
											line : {
												dataLabels : {
													enabled : true
												},
												enableMouseTracking : false
											}
										},

										series : [ {
											name : 'All visits',
											//name: jsonData.cooperativeName+'-地块('+jsonData.blockNumber+')',
											lineWidth : 4,
											marker : {
												radius : 4
											}
										} ]
									});
						}
					</script>	
					<div id="container" style="height: 300px; width:600px;margin: 0 auto"></div>
				</c:when>
				<c:otherwise>暂无作物产量预测记录！</c:otherwise>
			</c:choose>		
			<%} %>	
	    </div>
			</div>
		</div>
	</div>
	<script src="../charts/js/highcharts.js"></script>
	<script src="../charts/js/modules/data.js"></script>
	 <!-- Additional files for the Highslide popup effect -->
	<script type="text/javascript" src="../charts/highslide-full.min.js"></script>
	<script type="text/javascript" src="../charts/highslide.config.js" charset="utf-8"></script>
</body>
</html>
