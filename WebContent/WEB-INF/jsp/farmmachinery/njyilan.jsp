<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Object checkRoleObj = session.getAttribute("admin");
	boolean isAdmin = checkRoleObj != null
			&& Boolean.parseBoolean(checkRoleObj.toString());
	String cooperativeId = request.getParameter("cooperativeId");
	cooperativeId = (cooperativeId == null)?"":cooperativeId;
	String enginenumber = request.getParameter("enginenumber");
	enginenumber = (enginenumber == null)?"":enginenumber;
	String licensenumber = request.getParameter("licensenumber");
	licensenumber = (licensenumber == null)?"":licensenumber;
	//System.out.println(cooperativeId + "," + enginenumber +"," + licensenumber);
%>
<html>
<head>
<title>农机</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="../ng-table/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="../jquery-ui-1.11.1.custom/jquery-ui.min.css">
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script src="../ng-table/js/angular.min.js"></script>
<script src="../ng-table/js/angular-resource.min.js"></script>
<script src="../ng-table/js/angular-mocks.js"></script>
<script src="../ng-table/ng-table.js"></script>
<script src="../js/angular-encode-uri.js"></script>
<link rel="stylesheet" href="../ng-table/ng-table.css" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link href="../js/ngtable/loadingContainer.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<script type="text/javascript" language="javaScript">
$(function(){
	$('#newBtn').click(function(){
		window.location.href='./edit';
	});
});
</script>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="4"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="4"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» 列表
			</div>
			<!--  主体   start -->
			
			<div class="conborder getcode">
				<div class="onlinelist">
					<div ng-app="main">
						<div ng-controller="TableCtrl">

							<h2 class="indextitle">
								<em></em><em class="b2"></em><span></span>
								<p class="tit1">
								<div ng-if="tableParams.total() > 0">
									共计数量：{{tableParams.total()}}</div>
								<div ng-if="tableParams.total() == 0">暂无记录！</div>
								</p>
							</h2>
							<!-- <button ng-click="tableParams.reload()" class="btn pull-right">Reload</button>
					    <button ng-click="tableParams.sorting({})" class="btn pull-right">Clear sorting</button>
					     -->
							<div class="clearfix"></div>

							<div loading-container="tableParams.settings().$loading">
								<table ng-table="tableParams" template-pagination="custom/pager"
									show-filter="false" class="table"
									style="border-collapse: collapse">
									<tr ng-repeat="obj in $data" onmouseout="Kkdown(this);"
										onmouseover="Kkover(this);">
										<td data-title="'农机编号'" 
											sortable="'machineryNumber'">{{obj.machineryNumber}}</td>
										<td data-title="'农机类型'" 
											sortable="'machineryType'">{{obj.machineryType}}</td>
										<td data-title="'车牌号'" filter="{ 'licensenumber': 'text' }"
											sortable="'licensenumber'">{{obj.licensenumber}}</td>	
										<td data-title="'发动机号'" filter="{ 'enginenumber': 'text' }"
											sortable="'enginenumber'">{{obj.enginenumber}}</td>
										<td data-title="'购买日期'" 
											sortable="'purchasetime'">{{obj.purchasetime|
											date:'yyyy-MM-dd'}}</td>
										<td data-title="'农机状态'" 
											sortable="'status'">{{obj.status}}</td>
										<td data-title="'所有人'" 
											sortable="'ownerName'">{{obj.ownerName}}</td>
										<td data-title="'详细'"><a title="详细"
											class="getglobal icoglobal" href="./show?id={{obj.id}}"
											target="_blank"></a></td>
										<td data-title="'定位'"><a title="定位"
											class="getglobal icoglobal" href="./chartsear?licensenumber={{obj.licensenumber|escape}}"
											target="_blank"></a></td>	
										
									</tr>
								</table>
								<script type="text/ng-template" id="custom/pager">
									<div class="ng-cloak ng-table-pager">
									<ul class="pagination ng-table-pagination pull-right">
										<li ng-class="{'disabled': !page.active}" ng-repeat="page in pages" ng-switch="page.type">
											<a ng-switch-when="prev" ng-click="params.page(page.number)" href="">&laquo;</a>
											<a ng-switch-when="first" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a>
											<a ng-switch-when="page" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a>
											<a ng-switch-when="more" ng-click="params.page(page.number)" href="">&#8230;</a>
											<a ng-switch-when="last" ng-click="params.page(page.number)" href=""><span ng-bind="page.number"></span></a>
											<a ng-switch-when="next" ng-click="params.page(page.number)" href="">&raquo;</a>
										</li>
									</ul>
									</div>
								</script>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>


<script type="text/javascript">	
var app = angular.module('main', [ 'ngTable', 'ngResource','rt.encodeuri' ]).controller(
		'TableCtrl',
		function($scope, $http, $filter, $q, $timeout, $resource, ngTableParams) {
			var Api = $resource('./ngtable');

			$scope.tableParams = new ngTableParams({
				page : 1, // show first page
				count : 10, // count per page
				filter : {
					'cooperativeId' :'<%=cooperativeId%>',
					'enginenumber' :'<%=enginenumber%>',
					'licensenumber' :'<%=licensenumber%>'
				},
				sorting : {
					'id' : 'asc' // initial sorting
				}
			}, {
				total : 0, // length of data
				getData : function($defer, params) {
					// ajax request to api
					Api.get(params.url(), function(data) {
						// update table params
						params.total(data.total);
						// set new data
						$defer.resolve(data.result);
					});
				}
			});
			
			var inArray = Array.prototype.indexOf ?
            function (val, arr) {
                return arr.indexOf(val)
            } :
            function (val, arr) {
                var i = arr.length;
                while (i--) {
                    if (arr[i] === val) return i;
                }
                return -1;
            };
            
		    $scope.names = function(column) {
		        var def = $q.defer(),
		            arr = [],
		            names = [];
		        angular.forEach(data, function(item){
		            if (inArray(item.name, arr) === -1) {
		                arr.push(item.name);
		                names.push({
		                    'id': item.name,
		                    'title': item.name
		                });
		            }
		        });
		        def.resolve(names);
		        return def;
		    };
		    
			$scope.showDetailDialog2 = function(id) {	
				//("id:" + id);
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
				
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
			};
		});	
	
app.filter('escape', function() {
  return window.encodeURIComponent;
});
</script>
	
	<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
