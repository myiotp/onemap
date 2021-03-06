<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%Object checkRoleObj = session.getAttribute("admin");
boolean isAdmin = checkRoleObj!=null && Boolean.parseBoolean(checkRoleObj.toString());
%>
<!DOCTYPE html>
<html>
<head>
<title>交易</title>
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
 
</head>
<body>
	<script type="text/javascript" language="javaScript">
		$(function() {
			$('#newBtn').click(function() {
				window.location.href = './edit';
			});
		});
	</script>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="2"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
	    <c:param name="memuid" value="2"></c:param>
	    <c:param name="a" value="2"></c:param>
	    </c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» <a href="./editlist">区块链交易记录</a> &gt;利用唯一ID和区块链技术，使用车源与货源唯一ID、交易发起时间戳及交易确认时间戳计算唯一哈希Hash值，双方确认后生成电子交易协议并写入区块链平台。
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
					            <table ng-table="tableParams" template-pagination="custom/pager" show-filter="false" class="table" style="border-collapse: collapse">
					                <tr ng-repeat="obj in $data" onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
					                    <td data-title="'流水号'"  sortable="'ordernumber'">
                                           {{obj.ordernumber}}
                                        </td>
					                    <td data-title="'货源信息'"   sortable="'cargoId'">
                                            <a title="详细"  href ="#" ng-click="showDetailDialog0(obj.cargoId)">点击查看</a>
					                    </td>
					                    <td data-title="'车源信息'"  sortable="'truckId'">
                                            <a title="详细"  href ="#" ng-click="showDetailDialog1(obj.truckId)">点击查看</a>
					                    </td>
					                    <td data-title="'交易时间'"  sortable="'operationTime'">
					                       {{obj.operationTime| date:'yyyy-MM-dd'}}
					                    </td>
					                    <td data-title="'交易状态'"  sortable="'status'">
					                       <div ng-if="obj.status == 1"><font color=blue style="font-size:12px">{{obj.item3}}</font></div>
					                       <div ng-if="obj.status == -1"><font color=red style="font-size:12px">{{obj.item3}}</font></div>    
					                       <div ng-if="obj.status == 0">{{obj.item3}}</div>                   
					                    </td>
					                    <td data-title="'运输总价'"  >
                                           {{obj.price1}}                      
                                        </td>
                                        <td data-title="'款项说明'"  width=90px>
                                           {{obj.item1}}                          
                                        </td>
                                        <td data-title="'金额校验'"  >
                                           <div ng-if="obj.price2 == 0"><font color=blue style="font-size:12px">{{obj.item2}}</font></div>
                                           <div ng-if="obj.price2 == -1"><font color=red style="font-size:12px">{{obj.item2}}</font></div>                      
                                        </td>
					                    <td data-title="'操作人'"  sortable="'operator'">
					                        {{obj.operator}}
					                    </td>
					                    
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
	function showDialog(id) {
		$('#blockId').val(id);
		openMap("./block/"+id+"/trails");
		$('#dialog-showTrail').dialog('open');
		return false;
	}
	function openDialog(id) {
		$('#blockId').val(id);
		$('#dialog-manageTrail').dialog('open');
		return false;
	}
// 	function deleteDialog(id){
// 		$('#id').val(id);
// 		$('#dialog-Delete').dialog('open');
// 		return false;
// 	}
// 	function showDetailDialog2(id) {
// 		var url = encodeURI('./id/' + id);
	
// // 	// Get the CSV and create the chart
//  	$
//  		.getJSON(
//  					url,
//  					function(responsedata) {
//  					//(responsedata);
// 		$('#operationTime').html("操作时间："+getDefaultFormatDateByLong(responsedata.operationTime));
// 		$('#operationType').html("操作类型："+responsedata.operationType);
// 		$('#operator').html("操作人："+responsedata.operator);
// 		$('#blockNumber').html("地块编号："+responsedata.blockNumber);
// 		$('#cropType').html("作物类型："+responsedata.cropType);
// 		$('#mgtrecordmemo').html(responsedata.mgtrecordmemo);
// 		$('#dialog-showDetail').dialog('open');
// 		return false;
					
//  	});
		
// 	}
	
	
</script>	

<div id="dialog0-showDetail" title="货源信息">
    <div class="content_nr">
        <div class="ny_main">
          <div id="item1"></div>
          <div id="item2"></div>
          <div id="item3"></div>
          <div id="item4"></div>
          <div id="item5"></div>
          <div id="item6"></div>
          <div id="item7"></div>
          <div id="item8"></div>
          <div id="item9"></div>
          <div id="item10"></div>
          <div id="item11"></div>
          <div id="item12"></div>
          <div id="item13"></div>
          <div id="item14"></div>
          <div id="item15"></div>
          <div id="item16"></div>   
        </div>
    </div>
</div>
<div id="dialog1-showDetail" title="车源信息">
    <div class="content_nr">
        <div class="ny_main">
          <div id="aitem1"></div>
          <div id="aitem2"></div>
          <div id="aitem3"></div>
          <div id="aitem4"></div>
          <div id="aitem41"></div>
          <div id="aitem5"></div>
          <div id="aitem6"></div>
          <div id="aitem7"></div>
          <!-- <div id="aitem8"></div>
          <div id="aitem9"></div> -->
          <div id="aitem10"></div>
          <div id="aitem11"></div>
          <div id="aitem12"></div>
          <div id="aitem13"></div>
          <div id="aitem14"></div>
          <div id="aitem15"></div>
          <div id="aitem16"></div>   
        </div>
    </div>
</div>
<div id="dialog-Delete" title="删除">
	<form id="deleteForm" name="deleteForm" method="get" action="./delete">
			确定删除该项内容吗?
			<input type="hidden" id="id" name="id"/>
			
			<!-- <input type="submit" value="导入" /> -->
		</form>
</div>

<script type="text/javascript">	
var showDetailDialog0 = $("#dialog0-showDetail").dialog({
    autoOpen: false,
    modal: true,
    height: 500,
    width: 800,
    buttons:[{
            text:"关闭",
            class: "ui-button",
            click: function () {
                $(this).dialog("close");
            }
        }
    ]
}); 
var showDetailDialog1 = $("#dialog1-showDetail").dialog({
    autoOpen: false,
    modal: true,
    height: 500,
    width: 800,
    buttons:[{
            text:"关闭",
            class: "ui-button",
            click: function () {
                $(this).dialog("close");
            }
        }
    ]
}); 
	
	var dialog22 = $("#dialog-Delete").dialog({
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

<script src="../js/app/managementrecord/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
