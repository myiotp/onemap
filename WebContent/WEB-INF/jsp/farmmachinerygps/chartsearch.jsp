<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>农作物生产过程回溯图表</title>
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
		<c:param name="memuid" value="3"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="3"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» 查询
			</div>
			<!--  主体   start -->

			<form name="form1" id="form1" action="./chartsearchcharts" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					<h3 class="navbig">
						<span class="navleft"><em class="ico"></em>输入查询信息</span>
					</h3>
					<c:import url="../common/selectlandblock.jsp">
						<c:param name="blockNumber" value="${currentObj.blockNumber}"></c:param>
					</c:import>
					<div class="new_item">
						<div class="tit">
							<em>*</em>作物类型：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="cropType"
								name="cropType" value="${currentObj.cropType }"></label>

						</div>
						<div class="new_plus">
							<span id="cropTypeTip"></span>
						</div>
					</div>					


				</div>
				<div class="addtoadsub">
					<input type="submit" class="subglobal sub1" value="查询"
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

					$("#cropType").formValidator({
						tipID : "cropTypeTip",
						onShow : "",
						onFocus : "",
						onCorrect : ""
					}).inputValidator({
						min : 1,
						onError : "请填写作物类型!"
					});

				});
			</script>
		</div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
