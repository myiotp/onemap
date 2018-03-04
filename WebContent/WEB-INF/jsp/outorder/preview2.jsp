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
<form name="form1" id="form1" action="./saveAsOutStore" method="post">
<input type="hidden" name="date" value="${currentObj.date }"/>     
<input type="hidden" name="destination" value="${currentObj.destination }"/>  
<input type="hidden" name="goodsName" value="${currentObj.goods.name }"/>  
<input type="hidden" name="goodsId" value="${currentObj.goods.id }"/>  
<input type="hidden" name="amount" value="${currentObj.amount }"/>  
<input type="hidden" name="i" value="<%=request.getParameter("i")%>"/>  

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
    
    <div class="new_item">
		<div class="tit"><em>*</em>选择月台：</div>
		<div class="bdmain"><label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="platform" name="platform" value="${currentObj.platform }"  />
			</label>
		</div>
    </div>
    
     <c:import url="../common/selectTrucks.jsp">
	   		<c:param name="truckId" value="${currentObj.truckId }"></c:param>
	   </c:import>

<div class="addtoadsub">
	<input type="submit" class="subglobal sub1" value="提交"  id="btnSubmit" />
	<input type="reset" class="escglobal sub2" value="重置" id="btnSubmit" />       
</div>

<div class="conborder getcode">
<div class="onlinelist">
<h2 class="indextitle"><em></em><em class="b2"></em><span></span><p class="tit1">
货区方案
</p></h2>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
   				<th><span>序号</span></th>
   				<th><span>货区</span></th>
   				<th><span>数量</span></th>
  			</tr>
                    <c:forEach items="${outOrderRecommendInfo.outOrderRecommendInfoItem}" var="obj"  varStatus="varStatus">
                      
  			  			<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td >${varStatus.index+1}</td>
    			<td >${obj.area.name}</td>
				<td>${obj.amount}</td>
    			
  			</tr>
  			  		</c:forEach>   
                     
                      </table>
      </div>
	</div>
	</form>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
