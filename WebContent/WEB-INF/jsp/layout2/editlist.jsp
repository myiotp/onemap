<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>  
<!DOCTYPE html>
<html>
<head>
<title>智慧物流平台系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="../ng-table/css/bootstrap.min.css"/>
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script src="../ng-table/js/angular.min.js"></script>
<script src="../ng-table/js/angular-resource.min.js"></script>
<script src="../ng-table/js/angular-mocks.js"></script>
<script src="../ng-table/ng-table.js"></script>
<link rel="stylesheet" href="../ng-table/ng-table.css"/>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link href="../js/ngtable/loadingContainer.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.container {
    max-width: 1570px;
}
.container {
    float: left;
    width: 1310px;
    padding-left: 20px;
    border-left: #e2e2e2 solid 1px;
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
	<c:param name="memuid" value="3"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="3"></c:param>
	<c:param name="a" value="3"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		   
		    	<!--  主体   start -->
<div class="conborder getcode">
<div class="onlinelist">
	<div ng-app="main">
							<div ng-controller="TableCtrl">
								
								<h2 class="indextitle">
									<em></em><em class="b2"></em><span></span>
									<p class="tit1">
										<div ng-if="tableParams.total() > 0">
											共计数量：{{tableParams.total()}}
										</div>
										<div ng-if="tableParams.total() == 0">
											暂无记录！
										</div>
									</p>
								</h2>
							    <!-- <button ng-click="tableParams.reload()" class="btn pull-right">Reload</button>
							    <button ng-click="tableParams.sorting({})" class="btn pull-right">Clear sorting</button>
							     -->
							    <div class="clearfix"></div>
							
							        <div loading-container="tableParams.settings().$loading">
							            <table ng-table="tableParams" template-pagination="custom/pager" show-filter="true" class="table" style="border-collapse: collapse">
							                <tr ng-repeat="obj in $data" onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
							                	<td data-title="'来源城市'" filter="{ 'from_city_name': 'text' }"  sortable="'from_city_name'">
							                        {{obj.fromCityName}}
							                    </td>
							                    <td data-title="'始发地'" filter="{ 'fromname': 'text' }" sortable="'fromname'">
							                        {{obj.fromname}}
							                    </td>
							                    <td data-title="'目的地'" filter="{ 'toname': 'text' }" sortable="'toname'">
                                                    {{obj.toname}}
                                                </td>
                                                <td data-title="'车牌号'"   sortable="'cargo_name'">
                                                    {{obj.licenseplate}}
                                                </td>
                                                <td data-title="'总载重(吨)'"   sortable="'cargo_weight'">
                                                    {{obj.truckWeight}}
                                                </td>
                                                <td data-title="'车型'"   sortable="'car_type'">
                                                    {{obj.carType}}
                                                </td>
                                                <td data-title="'车长'"  sortable="'car_length'">
                                                    {{obj.carLength}}
                                                </td>
                                                <td data-title="'车辆品牌'"  sortable="'price'">
                                                    {{obj.truckBarnd}}
                                                </td>
                                                <td data-title="'发车时间'"   sortable="'ship_timestamp'">
                                                    {{obj.shipTimestamp}}
                                                </td>
                                                <td data-title="'联系方式'"  >
                                                    {{obj.ownerCellphone}}
                                                </td>
                                                <td data-title="'有效天数'"  >
                                                    {{obj.validDays}}
                                                </td>
							                  <td data-title="'审核状态'" >
                                                   <div ng-if="obj.status == 1">
                                                        已批准
                                                    </div>
                                                   <div ng-if="obj.status == 0">
                                                        等待审核
                                                    </div>
                                                    <div ng-if="obj.status == -1">
                                                        已拒绝
                                                    </div>
                                                </td>
							                   <shiro:hasAnyRoles name="admin">
							                    <td data-title="'操作'">
							                        <a title="批准" class="modifyglobal icoglobal" href="#" ng-click="approveDialog(obj.id)"></a> 
                                                    <a title="拒绝" class="deleteglobal icoglobal" href="#" ng-click="rejectDialog(obj.id)"></a>  
                                                  </td>
							                    </shiro:hasAnyRoles>
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
<div id="dialog-approve" title="批准">
    <form id="approveForm" name="approveForm" method="get" action="./approve">
            确定批准该项内容吗?<br/>
            <input type="hidden" id="id" name="id"/>
   
            <!-- <input type="submit" value="导入" /> -->
        </form>
</div>
<div id="dialog-reject" title="拒绝">
    <form id="rejectForm" name="rejectForm" method="get" action="./reject">
            确定拒绝该项内容吗?<br/>
            <input type="hidden" id="id2" name="id2"/>
           
            <!-- <input type="submit" value="导入" /> -->
        </form>
</div>
<script type="text/javascript">
var dialog11 = $("#dialog-approve").dialog({
    autoOpen: false,
    modal: true,
    height: 250,
    width: 480,
    buttons:[{
            text:"确定",
            class: "ui-button ui-button-primary",
            click: function (){
                $('#approveForm').submit();
                $( this ).dialog( "close" );
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
var dialog22 = $("#dialog-reject").dialog({
    autoOpen: false,
    modal: true,
    height: 250,
    width: 480,
    buttons:[{
            text:"确定",
            class: "ui-button ui-button-primary",
            click: function (){
                $('#rejectForm').submit();
                $( this ).dialog( "close" );
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
	var dialog33 = $("#dialog-Delete").dialog({
		autoOpen: false,
		modal: true,
		height: 150,
		width: 480,
		buttons: [{
				text:"确定",
				class: "ui-button ui-button-primary",
				click: function (){
					$('#deleteForm').submit();
					$( this ).dialog( "close" );
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
</script>	
<script src="../js/app/truck/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
