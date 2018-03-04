<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>农作物生产过程回溯图表</title>
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
	var url = encodeURI('./block/' + block + '/crop/' + crop + '/charts/csv');
	
	// Get the CSV and create the chart
	$
			.getJSON(
					url,
					function(responsedata) {
						var jsonData =  eval('(' + responsedata + ')'); 
						//(jsonData);
						var dataStrArr=jsonData.time;//分割成字符串数组
						 var dataIntArr=[];//保存转换后的整型字符串
						 dataStrArr.forEach(function(data,index,arr){  
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
		                type: 'spline'
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
		                }
		            },
		            yAxis: {
		                title: {
		                    text: ''
		                },
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
		                        text: '播种',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Light breeze
		                    from: 5,
		                    to: 10,
		                    color: 'rgba(0, 0, 0, 0)',
		                    label: {
		                        text: '施肥',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }, { // Gentle breeze
		                    from: 10,
		                    to: 15,
		                    color: 'rgba(68, 170, 213, 0.1)',
		                    label: {
		                        text: '喷药',
		                        style: {
		                            color: '#606060'
		                        }
		                    }
		                }]
		            },
		            tooltip: {
		                valueSuffix: ''
		            },
		            plotOptions: {
		                spline: {
		                    lineWidth: 4,
		                    states: {
		                        hover: {
		                            lineWidth: 5
		                        }
		                    },
		                    marker: {
		                        radius: 4,
		                        lineColor: '#666666',
		                        lineWidth: 1
		                    },
		                    pointInterval: 3600000 * 24, // one day
		                    pointStart: Date.UTC(2014, 9, 6, 0, 0, 0)
		                }
		            },
		            series: [{
		                name: '<%=request.getParameter("cropType")%>',
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
			<div id="container"
			style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	    </div>
		<script src="../charts/js/highcharts.js"></script>
		<script src="../charts/js/modules/data.js"></script>
	<!-- <script src="../charts/js/modules/exporting.js"></script> -->	
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
