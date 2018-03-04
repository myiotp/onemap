<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>农作物投入品分布图表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" href="../js/jstree/themes/default/style.min.css" />
	 
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
	<script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	  });
  
	var block = '<%=request.getParameter("blockId")%>';	
	var crop = '<%=request.getParameter("cropTypeId")%>';
	//var url = encodeURI('./block/' + block + '/crop/' + crop + '/charts/crops');
	var url = encodeURI('../purchaseseedtype/tongji');
	var url2 = encodeURI('../purchasefertilizertype/tongji');
	var url3 = encodeURI('../purchasepesticidetype/tongji');
	
	// Get the data and create the chart
	$
			.getJSON(
					url,
					function(responsedata) {
					//(responsedata);
					var dataIntArr=[];//保存转换后的整型字符串
					$.each( responsedata, function( index, data ) {
					//responsedata.forEach(function(data,index,arr){  
							 var innerArr = [];
							 innerArr.push(responsedata[index].typename);
							 innerArr.push(responsedata[index].totalamount);							 
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
			            text: '种子投入品分布图表'
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
			            name: '投入品分布',
			            data: dataIntArr
			        }]
			    });				
		    });

		// Get the data and create the chart
	$
			.getJSON(
					url2,
					function(responsedata) {
					//(responsedata);
					var dataIntArr=[];//保存转换后的整型字符串
					$.each( responsedata, function( index, data ) {
					//responsedata.forEach(function(data,index,arr){  
							 var innerArr = [];
							 innerArr.push(responsedata[index].typename);
							 innerArr.push(responsedata[index].totalamount);							 
							 dataIntArr.push(innerArr);     
						    });
				//(dataIntArr);									
				$('#container2').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 0,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '肥料投入品分布图表'
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
			            data: dataIntArr
			        }]
			    });				
		    });	
			
		// Get the data and create the chart
	$
			.getJSON(
					url3,
					function(responsedata) {
					//(responsedata);
					var dataIntArr=[];//保存转换后的整型字符串
					$.each( responsedata, function( index, data ) {
					//responsedata.forEach(function(data,index,arr){  
							 var innerArr = [];
							 innerArr.push(responsedata[index].typename);
							 innerArr.push(responsedata[index].totalamount);							 
							 dataIntArr.push(innerArr);     
						    });
				//(dataIntArr);									
				$('#container3').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 0,//null,
			            plotShadow: false
			        },
			        title: {
			            text: '农药投入品分布图表'
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
			            data: dataIntArr
			        }]
			    });				
		    });	
		</script>
</head>
<body>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="1"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div style="width:1000px;" class="container">
			<div id="tabs">
				<ul>
					<li><a href="#container">种子投入品分布图表</a></li>
					<li><a href="#container2">肥料投入品分布图表</a></li>
					<li><a href="#container3">农药投入品分布图表</a></li>
				</ul>
				
					<div id="container"	    style="min-width: 800px; height: 400px; margin: 0px 70px"></div>
					<div id="container2"    style="min-width: 800px; height: 400px; margin: 0px 70px"></div>
					<div id="container3"	style="min-width: 800px; height: 400px; margin: 0px 70px"></div>
				
			</div>	
		</div>
		<script src="../charts/js/highcharts.js"></script>
		<script src="../charts/js/modules/data.js"></script>
	<!-- <script src="../charts/js/modules/exporting.js"></script> -->	
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
