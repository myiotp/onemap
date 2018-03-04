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
	String enginenumber = request.getParameter("enginenumber");
	String licensenumber = request.getParameter("licensenumber");
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
<link rel="stylesheet" href="../ng-table/ng-table.css" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link href="../js/ngtable/loadingContainer.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.new_item {
    display: block;
    height: 10%;
    overflow: hidden;
    padding: 4px 0;
    width: 100%;
}
</style>
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
										<td data-title="'车牌号'" 
											sortable="'machineryNumber'">{{obj.licensenumber}}</td>
										<td data-title="'农机类型'" 
											sortable="'machineryType'">{{obj.machineryType}}</td>
										<td data-title="'农机状态'" 
											sortable="'status'">{{obj.status}}</td>
										<td data-title="'所有人'" 
											sortable="'ownerName'">{{obj.ownerName}}</td>
										<td data-title="'电子围栏报警检测'"><a title="电子围栏报警检测"
											class="getglobal icoglobal" href ="#" ng-click="showDetailDialog2(obj.id,obj.licensenumber)"></a></td>
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
<div id="dialog-showDetail" title="电子围栏报警检测">
	<div class="content_nr">
        <div class="ny_main">
		  <form name="form1" id="form1" action="./chartsearc" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					按照行政区域来设置围栏
					 <div id="njid"></div><div id="licensenumber"></div>		
					<c:import url="../common/selectcity.jsp">
						<c:param name="id" value="${currentObj.id}"></c:param>
					</c:import>
					<div class="addtoadsub">
						<input type="button" class="subglobal sub1" value="检测"
							name="btnCheck" id="btnCheck" onclick="check()"> 
					  </div>				 		 
				  <div id="checkresult"></div>
				  
            </div>			
		</form> 
        </div>
    </div>
</div>
<div id="dialog-sendMessage" title="发送到手机">
		<div class="content_nr">
	        <div class="ny_main">
			<form id="sendMessageForm" name="sendMessageForm" method="post" action="./sendMessage" class="form-inline">
				<div id="sendMessageResponse"></div>
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
	
		$.post("../managementrecord/sendMessage", { "n": phonenumber,"m": message1},
		   function(data){			 
			 //console.log(data); // 
			 $('#sendMessageResponse').html(data.result);
		   }, "json");
		
		//$("#dialog-sendMessage").dialog("close");
	}
	
function check() {
		var njid = $('#njid').val();
		var licensenumber=$('#licensenumber').val();
		var city1=$('#city1 option:selected').text();
		var province=$('#province option:selected').text();
		console.log("njid:"+njid+",licensenumber:"+licensenumber+",city1:"+city1+",province:"+province);
		//call backend api
		$.post("./check", { "licensenumber": licensenumber,"city1": city1,"province":province, "district":null},
		   function(data){			 
			 //console.log(data); // 
			 var result = data.result;
			 var c = "";
			 if( result.length > 0){
				 for(var i = 0; i < result.length; i++){
					c += "["+(i+1)+"]时间："+result[i].operationTime +",地点：" + result[i].formatted_address +"\n\r";
				 }
			 }else{
				 c="无";
			 }
			 var content = '车辆['+licensenumber+']触发围栏的状态检测结果：<pre class="prettyprint linenums">'+ c +'</pre>';
				$('#_testresult').html(content);
				$('#checkresult').html(content);
				return false;
		   }, "json");
		
	}
	
	var showDetailDialog = $("#dialog-showDetail").dialog({
		dialogClass : 'dialog1',
		autoOpen: false,
		modal: true,
		height: 400,
		width: 700,
		buttons:[{
		    	text:"发送到手机",
		    	class: "ui-button",
				click: function () {
					$('#sendMessageResponse').html("");
					$('#dialog-sendMessage').dialog('open');
				}
		    },{
				text:"关闭",
				class: "ui-button",
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
		width: 700,
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
var app = angular.module('main', [ 'ngTable', 'ngResource' ]).controller(
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
		    
			$scope.showDetailDialog2 = function(id,licensenumber) {	
				//("id:" + id);
				$('#njid').val(id);
				$('#licensenumber').val(licensenumber);
				$('#checkresult').html("");
				$('#dialog-showDetail').dialog('open');
				return false;
				
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
			};
		});	
	
	
</script>
	
	<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
