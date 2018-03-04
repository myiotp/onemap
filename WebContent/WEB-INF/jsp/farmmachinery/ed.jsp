<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>地块轨迹管理</title>
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
	<c:param name="memuid" value="8"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="8"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./list">地块轨迹管理</a> &gt; 新建地块    	</div>
		    	<!--  主体   start -->
		    	
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>地块信息</span></h3>
     <div class="new_item">
		<div class="tit"><em>*</em>地块编号：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="blockNumber" name="blockNumber" value="${currentObj.blockNumber }"/>
			</label>
			
		</div><div class="new_plus"><span id="nameTip"></span></div>
    </div>
         <c:import url="../common/selectblocktype.jsp">
   <c:param name="blockType" value="${currentObj.blockType}"></c:param>
   </c:import>
   
      <c:import url="../common/selectlandtype.jsp">
   <c:param name="blockType" value="${currentObj.landType}"></c:param>
   </c:import>
   
     <c:import url="../common/selectsite.jsp">
   <c:param name="cooperativeNumber" value="${currentObj.cooperativeNumber}"></c:param>
   </c:import>
   
    <div class="new_item">
		<div class="tit"><em></em>备注：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="memo" name="memo" value="${currentObj.memo }">
			</label>
			
		</div><div class="new_plus"><span id="contactTip"></span></div>
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
			
			$("#name").formValidator({
		tipID:"nameTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写地块名称!"
	});
	
			$("#capacityOfCore").formValidator({
		tipID:"capacityOfCoreTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写核心品规容量!"
	});
	
			$("#capacityOfRelated").formValidator({
		tipID:"capacityOfRelatedTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写关联品规容量!"
	});
			$("#capacityOfCommon").formValidator({
		tipID:"capacityOfCommonTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写一般品规容量!"
	});
	


	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
