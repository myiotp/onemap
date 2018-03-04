<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>出库单管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
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
	</c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="3"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./list">出库单管理</a> &gt; 出库单列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="?" id="form1" name="form1"><p  class="domain">
	 出库单名称：<input type="text" class="textinput" id="name" name="name" style="width:90px" value="${obj.number}"/></p>
	
	
	<input type="submit" value="查询" class="subglobal sub1"/>

	</form>
</div><div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计出库单数量：${totalCount}</c:when>
<c:otherwise>暂无出库单！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>单据号</span></th>
				<th><span>业务日期</span></th>
				<th><span>卷烟名称</span></th>
				<th><span>总数量</span></th>
				<th><span>明细数量</span></th>
				<th><span>货区</span></th>
				<th><span>去向</span></th>
				<th><span>月台</span></th>
				<th><span>运输车辆</span></th>
    			<th><span>操作</span></th>
  			</tr>
                      
                   <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
                      <c:forEach items="${obj.details}" var="obj2"  varStatus="varStatus2">
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
			    			<td >${obj.number}</td>
			    			<td >${obj.date}</td>
			    			<td >${obj.goodsName}</td>
			    			<td >${obj.amount}</td>
			    			<td >${obj2.amount}</td>
			    			<td >${obj2.areaName}</td>
			    			<td >${obj.destination}</td>
			    			<td >${obj.platform}</td>
			    			<td >${obj.truckNumber}</td>
			    			<td width=100>
			    			<!-- <a title="删除" class="deleteglobal icoglobal" href ="./delete?id=${obj2.id }"></a> -->
			    			</td>
			  			</tr>
  					  </c:forEach>
  			  	</c:forEach>
                      </table>
      </div>
	</div>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
