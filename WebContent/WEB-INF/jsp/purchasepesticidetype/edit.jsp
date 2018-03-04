<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>投入农药管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
	<script type="text/javascript" src="../js/formValidator-4.0.1.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="../js/formValidatorRegex.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>	
	<script>
	  $(function() {
	    $( "#purchaseTime" ).datepicker({
	      showOtherMonths: true,
	      selectOtherMonths: true,
	      dateFormat: 'yy/mm/dd',
	      defaultDate: +0,
	      onSelect:function(date){  
	          this.focus();  
	          this.blur();  
	      }  
	    });
		
	  });
	</script>
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
			       		» <a href="./editlist">投入农药管理</a> &gt; 新建   	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>投入农药记录</span></h3>
	 <c:import url="../common/selectpesticidetype.jsp">
	   <c:param name="pesticideTypeId" value="${currentObj.pesticideTypeId}"></c:param>
	   </c:import>
    <c:import url="../common/selectsite.jsp">
		   <c:param name="cooperativeId" value="${currentObj.cooperativeId}"></c:param>
		   </c:import>
    <div class="new_item">
		<div class="tit"><em>*</em>名称：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="name" name="name" value="${currentObj.name }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
	</div>
	<div class="new_item">
		<div class="tit"><em></em>登记证号：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="serialNumber" name="serialNumber" value="${currentObj.serialNumber }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
		
		<div class="tit"><em></em>防治对象：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="level" name="level" value="${currentObj.level }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
	</div>
	<div class="new_item">
		<div class="tit"><em></em>生产单位：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="producer" name="producer" value="${currentObj.producer }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
		
		<div class="tit"><em></em>联系电话：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="producerTel" name="producerTel" value="${currentObj.producerTel }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
	</div>
	<div class="new_item">
		<div class="tit"><em></em>经销商：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="seller" name="seller" value="${currentObj.seller }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
		
		<div class="tit"><em></em>联系电话：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="sellerTel" name="sellerTel" value="${currentObj.sellerTel }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
	</div>
	<div class="new_item">
		<div class="tit"><em></em>采购时间：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="purchaseTime" name="purchaseTime" value="${currentObj.purchaseTime }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
		
		<div class="tit"><em>*</em>采购数量：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="purchaseAmount" name="purchaseAmount" value="${currentObj.purchaseAmount }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
	</div>
	<div class="new_item">
		<div class="tit"><em></em>采购人：</div>
		<div class="bdmain">		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="purchaser" name="purchaser" value="${currentObj.purchaser }">
			</label>			
			</div><div class="new_plus"><span id="seedTypeTip"></span></div>
		
	</div>
	
     <div class="new_item">
		<div class="tit"><em></em>品种简介：</div>
		<div class="bdmain">
			<textarea id="brief" rows="8" cols="50" name="brief">${currentObj.brief }	
			</textarea>
		</div><div class="new_plus"></span></div>
    </div>
    
    
</div>
<div class="addtoadsub">
	<input type="submit" class="subglobal sub1" value="提交" name="btnSubmit" id="btnSubmit">
	<input type="reset" class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit">       
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
	$("#seedType").formValidator({
		tipID:"seedTypeTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写农药类型名称!"
	});
	
		


	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
