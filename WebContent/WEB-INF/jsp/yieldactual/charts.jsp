<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>产量预测记录图表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link href="../css/global.css" rel="stylesheet" type="text/css" />
	<link href="../css/manage.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../charts/js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/ahover.js"></script>
	<script type="text/javascript" src="../js/formValidator-4.0.1.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="../js/formValidatorRegex.js"
		charset="UTF-8"></script>
	
<style type="text/css">
${
demo
.css
}
</style>
<script type="text/javascript">
	$(function() {
		var block = '<%=request.getParameter("blockNumber")%>';	
		var crop = '<%=request.getParameter("cropType")%>';
		var url = encodeURI('./block/' + block + '/crop/' + crop + '/charts/csv');
		
		// Get the CSV and create the chart
		$
				.getJSON(
						url,
						function(csv) {
							//$.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=analytics.csv&callback=?', function (csv) {	

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
													tickInterval : 24 * 3600 * 1000, // one day
													tickWidth : 0,
													gridLineWidth : 1,
													dateTimeLabelFormats: { 
														day: '%e/%m',
											            week: '%b %e'									                    
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
													align : 'left',
													verticalAlign : 'top',
													y : 20,
													floating : true,
													borderWidth : 0
												},

												tooltip : {
													shared : true,
													crosshairs : true,
													dateTimeLabelFormats: { 
														day: '%e/%m/%Y'								                    
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
																						maincontentText : Highcharts
																								.dateFormat(
																										'%A, %b %e, %Y',
																										this.x)
																								+ ':<br/> '
																								+ this.y
																								+ ' visits',
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
													lineWidth : 4,
													marker : {
														radius : 4
													}
												}, {
													name : 'New visitors'
												} ]
											});
						});

	});
</script>	
</head>
<body>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="5"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="5"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div style="width:1000px;">
			<div id="container"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	    </div>
		<script src="../charts/js/highcharts.js"></script>
		<script src="../charts/js/modules/data.js"></script>
		
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
