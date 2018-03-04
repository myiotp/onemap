<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%Object checkRoleObj = session.getAttribute("admin");
boolean isAdmin = checkRoleObj!=null && Boolean.parseBoolean(checkRoleObj.toString());
%>
<html>
<head>
<title>农机管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<!--<link rel="stylesheet" href="../css/redmond/jquery-ui.min.css">-->
<!-- <link rel="stylesheet" href="../bootstrap-3.2.0/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="../bootstrap-3.2.0/css/jquery-ui-1.10.0.custom.css"> -->

<link rel="stylesheet" href="../openlayers/theme/default/style.css" type="text/css">
<link rel="stylesheet" href="../openlayers/style.css" type="text/css">
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../openlayers/OpenLayers.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="../js/mapconfig.js"></script>
<script type="text/javascript" src="../js/map2.js"></script>
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
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">农机GPS信息管理</a> &gt; 列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="?" id="form1" name="form1"><p  class="domain">
			<%if(isAdmin){%>
					<input
						type="button" value="新建" id="newBtn" class="subglobal sub1" />
				<%} %>
	</form>
</div><div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计数量：${totalCount}</c:when>
<c:otherwise>暂无记录！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>ID</span></th>
				<th><span>农机编号</span></th>
				<th><span>农机类型</span></th>
				<!-- <th><span>所属合作社</span></th> -->
				<th><span>操作时间</span></th>
				<th><span>农机状态</span></th>
				<th><span>农机手姓名</span></th>				
    			<th><span>农机手电话</span></th>
				<th><span>轨迹</span></th>
				<%if(isAdmin){%>
    			<th><span>操作</span></th>
    			<%} %>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="result"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${result.id}</td>
    			<td >${result.machineryNumber}</td>
    			<td >${result.machineryType}</td>
				<!-- <td >${result.cooperative}</td> -->
				<td><fmt:formatDate value="${result.operationTime}" pattern="yyyy年MM月dd日" /></td>
    			<td >${result.status}</td>    			
    			<td >${result.ownerName}</td>
				<td >${result.ownerTelephone}</td>				
    			<td ><a href="#" onclick="showDialogByOperationId(${result.id})" id="showTrail_link">查看轨迹</a>&nbsp;<%if(isAdmin){%>|&nbsp;<a href="#" onclick="openDialog(${result.id })" id="manageTrail_link">导入轨迹</a><%} %>
    			</td>    
    			<%if(isAdmin){%>			
    			<td width=100>
				<a title="修改" class="modifyglobal icoglobal" href ="./edit?id=${result.id }"></a>
    			<a title="删除" class="deleteglobal icoglobal" href="#" onclick="deleteDialog(${result.id })"></a> 
    			</td>
    			<%} %>
  			</tr>
  			  		</c:forEach>
                      </table>
                      <c:import url="../common/pagebar.jsp"></c:import>  
      </div>
	</div>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>

<div id="dialog-manageTrail" title="导入轨迹文件">
	<form id="uploadForm" name="uploadForm" method="post" action="../uploadFile2" enctype="multipart/form-data">
			选择要导入的轨迹文件: 
			<input type="file" name="file"/>
			<input type="hidden" name="page" value='<%=request.getParameter("page")%>'/> 
			<input type="hidden" id="cooperativeId" name="cooperativeId"/>
			<input type="hidden" id="operationTime" name="operationTime"/>
			<input type="hidden" id="machineryOperationId" name="machineryOperationId"/>
			
			<!-- <input type="submit" value="导入" /> -->
		</form>
</div>
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
	function showDialog(cooperativeId,operationTime) {
	    var d = new Date(operationTime);
		//(d.getTime());
		$('#cooperativeId').val(cooperativeId);
		$('#operationTime').val(operationTime);
		openMap("./cooperative/"+cooperativeId+"/operation/"+d.getTime()+"/trails");
		$('#dialog-showTrail').dialog('open');
		return false;
	}
	function showDialogByOperationId(machineryOperationId) {
		$('#machineryOperationId').val(machineryOperationId);
		openMap("./machineryOperation/"+machineryOperationId+"/trails");
		$('#dialog-showTrail').dialog('open');
		return false;
	}
	
	
	function openDialog(id) {
		$('#machineryOperationId').val(id);
		$('#dialog-manageTrail').dialog('open');
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
	var dialog = $("#dialog-manageTrail").dialog({
		autoOpen: false,
		modal: true,
		height: 150,
		width: 480,
		buttons: {
			"导入轨迹": function (){
				$('#uploadForm').submit();
				$( this ).dialog( "close" );
			},
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
	// addTab form: calls addTab function on submit and closes the dialog
	var form = $('#uploadForm').submit(function( event ) {	  
		//addTrail();	    
	    dialog.dialog( "close" );
	    //("form submit");
	    //($('#blockId').val());
	    //event.preventDefault();
	});
	function addTrail() {
		var valid = true;
		//("test");
		//($('#blockId').val());
		//form.submit();
		
		return valid;
	}
</script>
</body>
</html>
