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
			       		» <a href="./list">出库单管理</a> &gt; 推荐方案    	</div>
		    	<!--  主体   start -->
<h3 class="navbig"><span class="navleft"><em class="ico"></em>出库单信息</span></h3>
  <form name="form1" id="form1" action="./preview2" method="post">
<input type="hidden" name="d" value="${currentObj.date }"/>  
<input type="hidden" name="g" value="${currentObj.goods.id }"/>  
<input type="hidden" name="a" value="${currentObj.amount }"/>  
<input type="hidden" name="t" value="${currentObj.destination }"/>  
<input type="hidden" name="i" value=""/>  
 
     <div class="new_item">
		<div class="tit"><em></em>业务日期：</div>
		<div class="bdmain">${currentObj.date }</div>
		
		<div class="tit"><em></em>去向：</div>
		<div class="bdmain">${currentObj.destination }</div>
    </div>
    
    <div class="new_item">
		<div class="tit"><em></em>卷烟名称：</div>
		<div class="bdmain">${currentObj.goods.name }</div>
		
		<div class="tit"><em></em>总数量：</div>
		<div class="bdmain">${currentObj.amount }</div>
    </div>
    
    
  

<div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
<c:choose>
<c:when test="${!empty resultList}">可选择货区方案</c:when>
<c:otherwise>暂无出库单！</c:otherwise> </c:choose>
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
   				<th><span>方案</span></th>
   				<th><span>货区/数量</span></th>
				<th><span>操作</span></th>
  			</tr>
                      
                      <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${varStatus.index+1}</td>
    			<td >
					<c:forEach items="${obj.outOrderRecommendInfoItem}" var="outOrderRecommendInfoItem" varStatus="itemVarStatus">
						${outOrderRecommendInfoItem.area.name}/${outOrderRecommendInfoItem.amount}
					</c:forEach>	
				</td>
    			<td><a title="选择该方案出货" class="" href ="javascript:submitForm('${obj.recommendInfo }');">选择该方案出货>></a></td>
  			</tr>
  			  		</c:forEach>
                      </table>
      </div>
	</div>

</form>

<script type="text/javascript">
	function submitForm(info){
		document.form1.i.value = info;
		document.form1.submit();
	}
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
