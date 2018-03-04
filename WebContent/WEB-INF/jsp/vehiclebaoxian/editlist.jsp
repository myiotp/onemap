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
<title>车辆保险告警管理</title>
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
	<c:param name="memuid" value="5"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="5"></c:param>
	<c:param name="a" value="5"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">车辆保险告警管理</a> &gt; 列表    	</div>
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
							            <table ng-table="tableParams" template-pagination="custom/pager" show-filter="false" class="table" style="border-collapse: collapse">
							                <tr ng-repeat="obj in $data" onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
							                	 
							                	    <td data-title="'车牌号'" >
							                	         <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.licenseplate}}</font></div>
                                                      <div ng-if="obj.authresult >30">{{obj.licenseplate}}</div>
                                                    </td>
                                                    
                                                    
                                                    <td data-title="'车主'" >
                                                       <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.creatorUserName}}</font></div>
                                                       <div ng-if="obj.authresult >30">{{obj.creatorUserName}}</div>
                                                    </td>
                                                    <td data-title="'联系方式'" >
                                                       <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.comment}}</font></div>
                                                       <div ng-if="obj.authresult >30">{{obj.comment}}</div>
                                                    </td>
                                                    <td data-title="'车辆品牌'" >
                                                       <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.vehiclebrand}}</font></div>
                                                       <div ng-if="obj.authresult >30">{{obj.vehiclebrand}}</div>
                                                    </td>
                                                    <td data-title="'发动机号'" >
                                                        <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.enginenumber}}</font></div>
                                                        <div ng-if="obj.authresult >30">{{obj.enginenumber}}</div>
                                                    </td>
                                                    <td data-title="'保险时间'" >
                                                        <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.insurancedeadline}}</font></div>
                                                        <div ng-if="obj.authresult >30">{{obj.insurancedeadline}}</div>
                                                    </td>
                                                    <td data-title="'到期天数'" >
                                                        <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.authresult}}</font></div>
                                                        <div ng-if="obj.authresult >30">{{obj.authresult}}</div>
                                                    </td>
                                                    <td data-title="'登记地(市区)'">
                                                       <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.fromname}}</font></div>
                                                       <div ng-if="obj.authresult >30">{{obj.fromname}}</div>
                                                    </td>
                                                    <td data-title="'登记地(详细)'">
                                                        <div ng-if="obj.authresult <=30"><font color=red style="font-size:12px">{{obj.fromAddress}}</font></div>
                                                        <div ng-if="obj.authresult >30">{{obj.fromAddress}}</div>
                                                    </td>
                                               </div>    
							                	    
							                    <td data-title="'详细'">
													<a title="详细" class="getglobal icoglobal" href ="#" ng-click="showDetailDialog2(obj.id)"></a>
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
            理由：<input type="text" id="comment2" name="comment2" value="${currentObj.comment }">
            <!-- <input type="submit" value="导入" /> -->
        </form>
</div>
<div id="dialog-showDetail" title="详细描述">
	<div class="content_nr">
        <div class="ny_main">
          <div id="seedType"></div>
          <div id="name"></div>
		  <div id="purchaseTime"></div>
		  <div id="purchaseAmount"></div>
          <div id="purchaser"></div>
          <div id="serialNumber"></div>
          <div id="level"></div>
          <div id="producer"></div>
          <div id="producerTel"></div>
          <div id="seller"></div>
          <div id="sellerTel"></div>
          <div id="brief"></div>
             
        </div>
    </div>
</div>
<script type="text/javascript">
	
	var dialog22 = $("#dialog-approve").dialog({
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
	var dialog33 = $("#dialog-reject").dialog({
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
	var showDetailDialog = $("#dialog-showDetail").dialog({
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
</script>	
<script src="../js/app/vehiclenianjian/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>

</body>
</html>
