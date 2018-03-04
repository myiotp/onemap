<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>车辆管理</title>
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
</head>
<body>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="6"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="6"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./list">车辆管理</a> &gt; 新建车辆   	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>车辆信息</span></h3>
     <div class="new_item">
		<div class="tit"><em>*</em>车牌号：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="number" name="number" value="${currentObj.number }">
			</label>
			
		</div><div class="new_plus"><span id="numberTip"></span></div>
    </div>
   
     <c:import url="../common/selectsite.jsp">
   <c:param name="siteId" value="${currentObj.siteId}"></c:param>
   </c:import>
    <div class="new_item">
		<div class="tit"><em>*</em>是否在用：</div>
		<div class="bdmain">
	
		<input value="0" name="isActive" id="isActive0"  type="radio" <c:if test="${currentObj.isActive==0 }"> checked="checked"</c:if>>
		<label for="isActive0" style="font-weight:bold"><span style="color:green">否</span></label> 
	 	<input value="1" name="isActive" id="isActive1"<c:if test="${currentObj.isActive==1 or empty(currentObj) }"> checked="checked"</c:if> type="radio"> 
	 	<label for="isActive1" style="font-weight:bold"><span style="color:red">是</span></label>
	 
	 	
		</div><div class="new_plus"><span id="prolimittimeTip"></span></div>
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
			
			$("#number").formValidator({
		tipID:"numberTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写车牌号!"
	});
	
		


	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
