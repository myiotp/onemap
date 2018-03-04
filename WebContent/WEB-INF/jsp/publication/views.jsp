<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>行业报告管理</title>
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

		
<!-- 列表内容 begin -->
	<div class="container">
		    	
		    	<!--  主体   start -->
<div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">共计行业报告数量：${totalCount}</c:when>
<c:otherwise>暂无行业报告！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>ID</span></th>
				<th><span>标题</span></th>
				<th><span>发布时间</span></th>
				<th><span>来源</span></th>
				<th><span>阅读次数</span></th>
				<th><span>内容</span></th>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${obj.id}</td>
    			<td >${obj.title}</td>
    			<td><fmt:formatDate value="${obj.time}" pattern="yyyy年MM月dd日" /></td>
				<td >${obj.source}</td>
				<td >${obj.readamount}</td>
				<td width=100>
    			<a title="详细" class="getglobal icoglobal" href ="./view?id=${obj.id }" target="_blank"></a>    			
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
