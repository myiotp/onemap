<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>产量预测表管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
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
		<c:param name="memuid" value="5"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="5"></c:param>
			<c:param name="a" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» <a href="./list">产量预测表管理</a> &gt; 产量预测表列表
			</div>
			<!--  主体   start -->
			<div class="topselect">
				<form method="get" action="?" id="form1" name="form1">
					<input
						type="button" value="新建" id="newBtn" class="subglobal sub1" />
				</form>
			</div>
			<div class="conborder getcode">
				<div class="onlinelist">
					<h2 class="indextitle">
						<em></em><em class="b2"></em><span></span>
						<p class="tit1">
							<c:choose>
								<c:when test="${!empty resultList}">共计产量预测数量：${totalCount}</c:when>
								<c:otherwise>暂无产量预测表！</c:otherwise>
							</c:choose>
						</p>
					</h2>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="border-collapse: collapse">
						<tr>
							<th><span>ID</span></th>
							<th><span>地块编号</span></th>
							<th><span>作物类型</span></th>
							<th><span>预测时间</span></th>
							<th><span>预测产量</span></th>
							<!--<th><span>实际产量</span></th>-->
							<th><span>操作</span></th>
						</tr>

						<c:forEach items="${resultList}" var="obj" varStatus="varStatus">

							<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
								<td>${obj.id}</td>
								<td>${obj.blockNumber}</td>
								<td>${obj.cropType}</td>
								<td><fmt:formatDate value="${obj.predictTime}" pattern="yyyy年MM月dd日" /></td>
								<td>${obj.predictYield}</td>
								<!--<td>${obj.actualYield}</td>-->
								<td width=100><a title="修改" class="modifyglobal icoglobal"
									href="./edit?id=${obj.id }"></a> <a title="删除"
									class="deleteglobal icoglobal" href="./delete?id=${obj.id }"></a>
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
</body>
</html>