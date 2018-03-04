<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>产量预测记录管理</title>
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
	    $( "#predictTime" ).datepicker({
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
		<c:param name="memuid" value="1"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» <a href="./list">产量预测记录管理</a> &gt; 新建产量预测记录
			</div>
			<!--  主体   start -->

			<form name="form1" id="form1" action="./save" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					<h3 class="navbig">
						<span class="navleft"><em class="ico"></em> <c:choose>
								<c:when test="${currentObj ==null }">添加</c:when>
								<c:otherwise>修改</c:otherwise>
							</c:choose>产量预测记录信息</span>
					</h3>
					<c:import url="../common/selectlandblock.jsp">
						<c:param name="blockId" value="${currentObj.blockId}"></c:param>
					</c:import>
					<c:import url="../common/selectcroptype.jsp">
						<c:param name="cropTypeId" value="${currentObj.cropTypeId}"></c:param>
					</c:import>
					<div class="new_item">
						<div class="tit">
							<em>*</em>预测时间：
						</div>
						<div class="bdmain">
							<fmt:formatDate var="currentObj_predictTime" value="${currentObj.predictTime }" pattern="yyyy/MM/dd"/>
							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out validate[required]"> <input type="text" id="predictTime"
								name="predictTime" value="${currentObj_predictTime }"></label>

						</div>
						<div class="new_plus">
							<span id="predictTimeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em>*</em>预测产量：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="predictYield"
								name="predictYield" class="{required:true,minlength:1}" value="${currentObj.predictYield }"></label>

						</div>
						<div class="new_plus">
							<span id="predictYieldTip"></span>
						</div>
					</div>
					<!--
					<div class="new_item">
						<div class="tit">
							<em></em>实际产量：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="actualYield"
								name="actualYield" value="${currentObj.actualYield }"></label>

						</div>
						<div class="new_plus">
							<span id="actualYieldTip"></span>
						</div>
					</div>-->


				</div>
				<div class="addtoadsub">
					<input type="submit" class="subglobal sub1" value="提交"
						name="btnSubmit" id="btnSubmit"> <input type="reset"
						class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit">
				</div>
			</form>
			<script type="text/javascript">
				$(document).ready(function() {
					$.formValidator.initConfig({
						formID : "form1",
						debug : false,
						submitOnce : true,
						onError : function(msg, obj, errorlist) {

						},
						submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
					});

					$("#predictTime").formValidator({
						tipID : "predictTimeTip",
						onShow : "",
						onFocus : "",
						onCorrect : ""
					
					}).functionValidator({
			            fun: function (val, elem) {
			                if (!/^\d{4}\/\d{2}\/\d{2}$/.test(val)) {
			                    return "请指定正确的时间";
			                }
			                return true;
			            }
			        });
					$("#predictYield").formValidator({
						tipID:"predictYieldTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).inputValidator({
						type:"number",
						min:0,
						onError: "请填写正确数字!"
					}).regexValidator({ 
						regExp: "num", 
						dataType: "enum", 
						onError: "数字格式不正确" 
					});

				});
			</script>
		</div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
