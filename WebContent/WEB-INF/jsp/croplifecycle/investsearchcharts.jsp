<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>农作物种植分布图表</title>
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
	var block = '<%=request.getParameter("blockNumber")%>';	
	var crop = '<%=request.getParameter("cropType")%>';
	var url = encodeURI('./block/' + block + '/crop/' + crop + '/charts/crops');
	
	// Get the CSV and create the chart
	$
			.getJSON(
					url,
					function(responsedata) {
						var jsonData =  eval('(' + responsedata + ')'); 
						//(jsonData);
						var dataStrArr=jsonData.time;//分割成字符串数组
						 var dataIntArr=[];//保存转换后的整型字符串
						 $.each( dataStrArr, function( index, data ) {
						 //dataStrArr.forEach(function(data,index,arr){  
							 var innerArr = [];
							 var match = data
								.match(/^([0-9]{1,2})\/([0-9]{1,2})\/([0-9]{4})$/);
							if (match) {
								//(match);
								 innerArr.push(Date.UTC(+(match[3]),match[1] - 1,+match[2]));
								innerArr.push(jsonData.data[index]);
							}							
							dataIntArr.push(innerArr);     
						    });
				//(dataIntArr);		
				$('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 0,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '种子分布图表'
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: [
			                ['Firefox',   45.0],
			                ['IE',       26.8],
			                {
			                    name: 'Chrome',
			                    y: 12.8,
			                    sliced: true,
			                    selected: true
			                },
			                ['Safari',    8.5],
			                ['Opera',     6.2],
			                ['Others',   0.7]
			            ]
			        }]
			    });
				
				$('#container2').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 0,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '化肥分布图表'
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: [
			                ['Firefox',   45.0],
			                ['IE',       26.8],
			                {
			                    name: 'Chrome',
			                    y: 12.8,
			                    sliced: true,
			                    selected: true
			                },
			                ['Safari',    8.5],
			                ['Opera',     6.2],
			                ['Others',   0.7]
			            ]
			        }]
			    });
				
				
				$('#container3').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 0,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '农药分布图表'
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: [
			                ['Firefox',   45.0],
			                ['IE',       26.8],
			                {
			                    name: 'Chrome',
			                    y: 12.8,
			                    sliced: true,
			                    selected: true
			                },
			                ['Safari',    8.5],
			                ['Opera',     6.2],
			                ['Others',   0.7]
			            ]
			        }]
			    });
		    });

		</script>
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
		<div style="width:1000px;">
			<div id="container"	    style="min-width: 310px; height: 400px; margin: 0px 170px"></div>
			<div id="container2"    style="min-width: 310px; height: 400px; margin:  0px 170px"></div>
			<div id="container3"	style="min-width: 310px; height: 400px; margin:  0px 170px"></div>
	    </div>
		<script src="../charts/js/highcharts.js"></script>
		<script src="../charts/js/modules/data.js"></script>
	<!-- <script src="../charts/js/modules/exporting.js"></script> -->	
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
