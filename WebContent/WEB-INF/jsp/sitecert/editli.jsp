<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>地块管理</title>
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
<script type="text/javascript" src="../js/map001.js"></script>
</head>
<body>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="1"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» 地块列表    	</div>
		    	<!--  主体   start -->
<div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计地块数量：${totalCount}</c:when>
<c:otherwise>暂无地块！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>ID</span></th>
				<th><span>地块编号</span></th>
				<th><span>地块类型</span></th>
				<th><span>土壤类型</span></th>
				<th><span>作物类型</span></th>
				<th><span>所属合作社</span></th>
				<th><span>备注</span></th>				
    			<th><span>轨迹</span></th>    
  			</tr>
                      
                      <c:forEach items="${resultList}" var="result"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${result.id}</td>
    			<td >${result.blockNumber}</td>
    			<td >${result.blockType.blockType}</td>
    			<td >${result.landType.landType}</td>
    			<td >${result.cropType.cropType}</td>
    			<td >${result.cooperative.cooperativeNumber}</td>
    			<td >${result.memo}</td>
    			<td ><a href="#" onclick="showDialog(${result.id })" id="showTrail_link">查看轨迹</a>
    			</td>    			
    			
  			</tr>
  			  		</c:forEach>
                      </table>
                      <c:import url='<%="../common/pagebar.jsp?id="+request.getParameter("id")%>'></c:import>  
      </div>
	</div>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>

<div id="dialog-showTrail" title="查看轨迹">
	<div id="map" class="smallmap"></div>
</div>

<script type="text/javascript">
	function showDialog(id) {
		//$('#blockId').val(id);
		openMap("../landblock/block/"+id+"/trails");
		$('#dialog-showTrail').dialog('open');
		return false;
	}
	
</script>	
<script type="text/javascript">	
	var showTrailDialog = $("#dialog-showTrail").dialog({
		autoOpen: false,
		modal: true,
		height: 500,
		width: 800,
		buttons: {
			"关闭": function () {
				$(this).dialog("close");
			}
		}
	});
	
</script>
</body>
</html>
