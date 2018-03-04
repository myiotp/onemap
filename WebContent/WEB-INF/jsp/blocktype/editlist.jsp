<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>地块类型管理</title>
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
			       		» <a href="./editlist">地块类型管理</a> &gt; 地块类型列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="?" id="form1" name="form1">
	<input type="button" value="新建" id="newBtn" class="subglobal sub1"/>
	</form>
</div><div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计地块类型数量：${totalCount}</c:when>
<c:otherwise>暂无地块类型！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>ID</span></th>
				<th><span>地块类型</span></th>
				<th><span>说明</span></th>
				<th><span>操作</span></th>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${obj.id}</td>
    			<td >${obj.blockType}</td>
    			<td >${obj.description}</td>
    			<td width=100>
    			<a title="修改" class="modifyglobal icoglobal" href ="./edit?id=${obj.id }"></a>
    			<a title="删除" class="deleteglobal icoglobal" href="#" onclick="deleteDialog(${obj.id })"></a>
    			</td>
  			</tr>
  			</c:forEach>
            </table>
            <c:import url="../common/pagebar.jsp"></c:import>          
      </div>
	</div>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
<div id="dialog-Delete" title="删除">
	<form id="deleteForm" name="deleteForm" method="get" action="./delete">
			确定删除该项内容吗?
			<input type="hidden" id="id" name="id"/>
			
			<!-- <input type="submit" value="导入" /> -->
		</form>
</div>
<script type="text/javascript">
	function deleteDialog(id){
		$('#id').val(id);
		$('#dialog-Delete').dialog('open');
		return false;
	}
	
	var dialog22 = $("#dialog-Delete").dialog({
		autoOpen: false,
		modal: true,
		height: 150,
		width: 480,
		buttons: {
			"确定": function (){
				$('#deleteForm').submit();
				$( this ).dialog( "close" );
			},
			"关闭": function () {
				$(this).dialog("close");
			}
		}
	});
</script>	
<script src="../js/app/blocktype/script.js"></script>
<script src="../js/ngtable/loadingContainer.js"></script>
</body>
</html>
