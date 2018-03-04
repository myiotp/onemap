<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>农机管理</title>
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
	    $( "#operationTime" ).datepicker({
	      showOtherMonths: true,
	      selectOtherMonths: true,
	      dateFormat: 'yy/mm/dd'
	    });
		
	  });
	</script>	
</head>
<body>

	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="3"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="3"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">农机管理</a>    	</div>
		    	<!--  主体   start -->
		    	
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>农机信息</span></h3>
     <div class="new_item">
		<div class="tit"><em>*</em>操作时间：</div>
		<div class="bdmain">
			<fmt:formatDate var="currentObj_operationTime" value="${currentObj.operationTime }" pattern="yyyy/MM/dd"/>
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="operationTime" name="operationTime" value="${currentObj_operationTime }"/>
			</label>
			
			</div><div class="new_plus"><span id="operationTimeTip"></span></div>
	  </div>
     <div class="new_item">
		<div class="tit">
			<em>*</em>农机：</div>
		<div class="bdmain">
	
			<select name="machineryNumber" id="machineryNumber">
				
			</select>
	
		</div>
	</div>
	<script type="text/javascript">
	
		$(function() {
			$.ajaxSetup({
				  async: false
				  }); 
			$.post("../farmmachinery/listJson", {}, function(data) {
	
				$('#machineryNumber').empty();
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].machineryNumber+"'>" + data[i].machineryNumber + "</option>")
							.appendTo("#machineryNumber"); //把返回的数组添加到另一个下拉框
	
				}
				$("#machineryNumber").val('${param.machineryNumber}'); 
				
				
			}, "json"); //json格式自动解析数组
		});
	</script>
     
    
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
			
	$("#operationTime").formValidator({
		tipID:"operationTimeTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写操作时间!"
	});
	
});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
