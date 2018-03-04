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
<title>认证信息管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" href="../openlayers/theme/default/style.css" type="text/css">
<link rel="stylesheet" href="../openlayers/style.css" type="text/css">
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../openlayers/OpenLayers.debug.js"></script>
<script type="text/javascript" src="../js/mapconfig.js"></script>
<script type="text/javascript" src="../js/map3.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
</head>
<body>
<script type="text/javascript" language="javaScript">
$(function(){
	$('#newBtn').click(function(){
		window.location.href='./editBySite?cooperativeId=<%=request.getParameter("id") %>';
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
			       		» <a href="./editlist">认证信息管理</a> &gt; 认证信息列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="./edit" id="form1" name="form1">
		<%if(isAdmin){%>
					<input
						type="button" value="新建" id="newBtn" class="subglobal sub1" />					
				<%} %>
	</form>
</div><div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计认证信息数量：${totalCount}</c:when>
<c:otherwise>暂无认证信息！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<!-- <th><span>ID</span></th> -->
    			<th><span>认证类别</span></th>
				<th><span>认证时间</span></th>				
				<th><span>有效期限</span></th>
				<th><span>认证机构</span></th>
				<th><span>证书编号</span></th>
				<th><span>证书图片资料</span></th>
				<%if(isAdmin){%>
    			<th><span>操作</span></th>
    			<%} %>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<!-- <td >${site.id}</td> -->
    			<td >${obj.certtype}</td>
    			<td ><fmt:formatDate value="${obj.certtime}" pattern="yyyy年MM月dd日" /></td>
    			<td >${obj.validtime}</td>
    			<td >${obj.certorganization}</td>
    			<td >${obj.certnumber}</td>
    			<td ><a title="详细" class="getglobal icoglobal" href ="#" onclick="showDialog(${obj.id })"></a></td>
    			<%if(isAdmin){%>
    			<td width=100>    			
    			<a title="修改" class="modifyglobal icoglobal" href ="./edit?id=${obj.id }"></a>
    			<a title="删除" class="deleteglobal icoglobal" href="#" onclick="deleteDialog(${obj.id },${obj.cooperativeId })"></a>
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

<div id="dialog-Delete" title="删除">
	<form id="deleteForm" name="deleteForm" method="get" action="./delete">
			确定删除该项内容吗?
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="cooperativeId" name="cooperativeId"/>
			
			<!-- <input type="submit" value="导入" /> -->
		</form>
</div>
<script type="text/javascript">
	function deleteDialog(id,cooperativeId){
		$('#id').val(id);
		$('#cooperativeId').val(cooperativeId);
		$('#dialog-Delete').dialog('open');
		return false;
	}
	
</script>	
<script type="text/javascript">	
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
<div id="dialog-showTrail" title="认证信息">
	<div class="content_nr">
        <div class="ny_main">
          <div id="certtype"></div>
          <div id="certtime"></div>
		  <div id="validtime"></div>
		  <div id="certorganization"></div>
          <div id="certnumber"></div>
		  证书图片资料：<br><div id="certdescription"></div>
             
        </div>
    </div>
</div>
<script type="text/javascript">
	function showDialog(id) {
		var url = encodeURI('./id/' + id);
	
	// Get the CSV and create the chart
	$
			.getJSON(
					url,
					function(responsedata) {
					//(responsedata);
		$('#certtype').html("认证类别："+responsedata.certtype);
		$('#certtime').html("认证时间："+responsedata.certtime);
		$('#validtime').html("有效期限："+responsedata.validtime);
		$('#certorganization').html("认证机构："+responsedata.certorganization);
		$('#certnumber').html("证书编号："+responsedata.certnumber);
		$('#certdescription').html(responsedata.certdescription);
		$('#dialog-showTrail').dialog('open');
		return false;
					
	});
		
	}
	
</script>	
<script type="text/javascript">	
	var showTrailDialog = $("#dialog-showTrail").dialog({
		autoOpen: false,
		modal: true,
		height: 600,
		width: 900,
		buttons: {
			"关闭": function () {
				$(this).dialog("close");
			}
		}
	});	
</script>
</body>
</html>
