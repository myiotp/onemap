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
<script type="text/javascript" src="../js/formValidator-4.0.1.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/formValidatorRegex.js"
	charset="UTF-8"></script>
	
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.23.custom.min.js" charset="UTF-8"></script>
<link href="../css/redmond/jquery-ui-1.8.23.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
			       		» <a href="./list">出库单管理</a> &gt; 新建出库单    	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./preview" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>出库单信息</span></h3>
     
     <div class="new_item">
		<div class="tit"><em>*</em>业务日期：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="date" name="date" value="${currentObj.date }" readonly="readonly" />
			</label>
			
		</div><div class="new_plus"><span id="dateTip"></span></div>
    </div>
    
    <c:import url="../common/selectGoods.jsp">
   <c:param name="goodsId" value="${currentObj.goodsId}"></c:param>
   </c:import>
    
    <div class="new_item">
		<div class="tit"><em>*</em>总数量：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="amount" name="amount" value="${currentObj.amount}" />
			</label>
			
		</div><div class="new_plus"><span id="numberTip"></span></div>
    </div> 
    
    <div class="new_item">
		<div class="tit"><em>*</em>去向：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="destination" name="destination" value="${currentObj.destination}"  />
			</label>
			
		</div><div class="new_plus"><span id="numberTip"></span></div>
    </div> 
</div>
<div class="addtoadsub">
	<input type="submit" class="subglobal sub1" value="提交" name="btnSubmit" id="btnSubmit" />
	<input type="reset" class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit" />       
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
		$("#date").formValidator({
		tipID:"dateTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请选择日期!"
	});
	
		
		$( "#date" ).datepicker({
			showButtonPanel: true,
				dateFormat: "yy-mm-dd"
				});

	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
