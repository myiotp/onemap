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
<title>合作社管理</title>
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
$(function(){
	$('#newBtn').click(function(){
		window.location.href='./edit';
	});
});
</script>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="1"></c:param>
	<c:param name="a" value="1"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">合作社管理</a> &gt; 合作社列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="?" id="form1" name="form1">
		<%if(isAdmin){%>
					<input
						type="button" value="新建" id="newBtn" class="subglobal sub1" />
				<%} %>
	</form>
</div><div class="conborder getcode">
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
							                	<td data-title="'编号'" filter="{ 'cooperativeNumber': 'text' }"  sortable="'cooperativeNumber'">
							                        <a href="./show?id={{obj.id}}" target="_blank">{{obj.cooperativeNumber}}</a>
							                    </td>
							                    <td data-title="'名称'" filter="{ 'cooperativeName': 'text' }" sortable="'cooperativeName'">
							                        {{obj.cooperativeName}}
							                    </td>
												<td data-title="'负责人姓名'" filter="{ 'owner': 'text' }" sortable="'owner'">
							                       {{obj.owner}}
							                    </td>
							                    <td data-title="'负责人电话'" filter="{ 'telephone': 'text' }" sortable="'telephone'">
							                        {{obj.telephone}}
							                    </td>
												<td data-title="'地块列表'">
													<a href="../site/editli?id={{obj.id}}">查看地块</a>
												</td>
												<td data-title="'认证信息'">
													<a title="认证管理" href ="../sitecert/editlistBySite?id={{obj.id}}">认证管理</a>
												</td>												
							                  
							                   <shiro:hasAnyRoles name="admin">
							                    <td data-title="'操作'">
							                        <a title="修改" class="modifyglobal icoglobal" href="./edit?id={{obj.id }}"></a> 
													<a title="删除" class="deleteglobal icoglobal" href="#" ng-click="deleteDialog(obj.id)"></a>  
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

<div id="dialog-showTrail" title="查看轨迹">
	<div id="map" class="smallmap"></div>
</div>
<div id="dialog-Delete" title="删除">
	<form id="deleteForm" name="deleteForm" method="get" action="./delete">
			确定删除该项内容吗?
			<input type="hidden" id="id" name="id"/>
			
			<!-- <input type="submit" value="导入" /> -->
		</form>
</div>
<script type="text/javascript">
	function showDialog(id) {
		//$('#blockId').val(id);
		openMap("../landblock/site/" + id + "/trails");
		$('#dialog-showTrail').dialog('open');
		return false;
	}
	
	function deleteDialog(id){
		$('#id').val(id);
		$('#dialog-Delete').dialog('open');
		return false;
	}
	
</script>	
<script type="text/javascript">	
	var showTrailDialog = $("#dialog-showTrail").dialog({
		autoOpen: false,
		modal: true,
		height: 530,
		width: 800,
		buttons: {
			"关闭": function () {
				$(this).dialog("close");
			}
		}
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
<script src="../js/app/site/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
