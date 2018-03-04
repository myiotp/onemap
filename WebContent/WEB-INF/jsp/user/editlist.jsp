<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
Object obj = request.getSession().getAttribute("admin");
System.out.println(obj == null);
System.out.println(!Boolean.parseBoolean(obj.toString()));
if(obj == null || !Boolean.parseBoolean(obj.toString())){
	response.sendRedirect("./login");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
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
			       		» <a href="./editlist">用户管理</a> &gt; 用户列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	
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
							                     <!-- 
							                 	<td data-title="'序号'" filter="{ 'id': 'text' }"  sortable="'id'">
							                        {{obj.id}}
							                    </td>
							                     -->
							                     <td data-title="'微信头像'" >
                                                    <img src="{{obj.wx_avatarUrl}}" style="width: 60px; height: 60px;"></img>
                                                </td>
							                    <td data-title="'唯一号'" filter="{ 'username': 'text' }" sortable="'username'">
							                        {{obj.username}}
							                    </td>
							                    <td data-title="'真实姓名'" filter="{ 'realname': 'text' }" sortable="'realname'">
                                                    {{obj.realname}}
                                                </td>
                                                <td data-title="'微信昵称'" filter="{ 'wx_nickName': 'text' }" sortable="'wx_nickName'">
                                                    {{obj.wx_nickName}}
                                                </td>
                                                
                                                <td data-title="'联系方式'" filter="{ 'mobilephone': 'text' }" sortable="'mobilephone'">
                                                    {{obj.mobilephone}}
                                                </td>
                                                <td data-title="'省份'" filter="{ 'province': 'text' }" sortable="'province'">
                                                    {{obj.province}}
                                                </td>
							                    <td data-title="'角色'"  sortable="'approverole'">
							                     <div ng-if="obj.approverole == 0">操作员</div>   
                                                  <div ng-if="obj.approverole == 1">副经理</div> 
                                                  <div ng-if="obj.approverole == 2">经理</div> 
                                                </td>
                                                <td data-title="'详细'">
                                                    <a title="详细" class="getglobal icoglobal" href ="#" ng-click="showDetailDialog2(obj.id)"></a>
                                                </td>   
												
							                   <shiro:hasAnyRoles name="admin">
							                    <td data-title="'***操作***     '" >
							                        <a title="修改角色" class="modifyglobal icoglobal" href="./edit?id={{obj.id }}&actiontype=100"></a> 
													<a title="修改密码" class="modifyglobal icoglobal" href="./edit?id={{obj.id }}&actiontype=200"></a>
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
<div id="dialog-showDetail" title="详细描述">
    <div class="content_nr">
        <div class="ny_main">
          <div id="wx_nickName"></div>
          <div id="wx_avatarUrl"></div>
          <div id="username"></div>
          <div id="realname"></div>
          <div id="company"></div>
          <div id="mobilephone"></div>
          <div id="idcard"></div>
          <!-- <div id="city"></div> -->
          <div id="province"></div>
          <div id="address"></div>
          <div id="emergency"></div>
          <div id="emergencyphone"></div>
             
        </div>
    </div>
</div>
<script type="text/javascript">
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
	
	var showDetailDialog = $("#dialog-showDetail").dialog({
		autoOpen: false,
		modal: true,
		height: 400,
		width: 600,
		buttons:[{
				text:"关闭",
				class: "ui-button",
				click: function () {
					$(this).dialog("close");
				}
			}
		]
	});	
</script>	
<script src="../js/app/user/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>
</body>
</html>
