<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>库区管理</title>
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
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="1"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./list">库区管理</a> &gt; 库区列表    	</div>
		    	<!--  主体   start -->
<div class="topselect">
	<form method="get" action="?" id="form1" name="form1"><p  class="domain">
	 库区名称：<input type="text" class="textinput" id="name" name="name" style="width:90px" value="${warehouse.name}"/></p>
	
	
	<input type="submit" value="查询" class="subglobal sub1"/>
	<input type="button" value="新建" id="newBtn" class="subglobal sub1"/>
	</form>
</div><div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计库区数量：${totalCount}</c:when>
<c:otherwise>暂无库区！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>ID</span></th>
				<th><span>名称</span></th>
				<th><span>站点</span></th>
				<th><span>仓库</span></th>
				<th><span>楼层</span></th>
				<th colspan="3"><span>容量</span></th>
    			<th><span>操作</span></th>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="result"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${result.id}</td>
    			<td >${result.name}</td>
    			<td >${result.warehouse.site.name}</td>
    			<td >${result.warehouse.name}</td>
    			<td >${result.floor}</td>
    			<td >${result.coreCapacity}(核心)</td>
    			<td >${result.relatedCapacity}(关联)</td>
    			<td >${result.commonCapacity}(一般)</td>
    			<td width=100>
				<a title="修改" class="modifyglobal icoglobal" href ="./edit?id=${result.id }"></a>
    			<a title="删除" class="deleteglobal icoglobal" href ="./delete?id=${result.id }"></a>
    			</td>
  			</tr>
  			  		</c:forEach>
                      </table>
      </div>
	</div>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
