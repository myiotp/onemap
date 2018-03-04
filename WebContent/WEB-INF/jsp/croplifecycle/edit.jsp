<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生长状态管理</title>
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
	    $( "#gatherTime" ).datepicker({
	      showOtherMonths: true,
	      selectOtherMonths: true,
	      dateFormat: 'yy/mm/dd',
	      onSelect:function(date){  
	          this.focus();  
	          this.blur();  
	      }  
	    });
	    
	    if('${currentObj.video}' != ''){
			$("#video").val('${currentObj.video}');
		}
	  });
	</script>
		
<style>
		form {
			margin: 0;
		}
		textarea {
			display: block;
		}
	</style>
	<link rel="stylesheet" href="../richeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../richeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../richeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="../richeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../richeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="picture"]', {
				cssPath : '../richeditor/plugins/code/prettify.css',
				uploadJson : '../richeditor/uploadFile001.jsp',
				fileManagerJson : '../richeditor/fileManager001.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
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
				» <a href="./editlist">作物生长状态管理</a> &gt; 新建记录
			</div>
			<!--  主体   start -->

			<form name="form1" id="form1" action="./save" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					<h3 class="navbig">
						<span class="navleft"><em class="ico"></em> <c:choose>
								<c:when test="${currentObj ==null }">添加</c:when>
								<c:otherwise>修改</c:otherwise>
							</c:choose>记录信息</span>
					</h3>
					<c:import url="../common/selectlandblock.jsp">
						<c:param name="blockId" value="${currentObj.blockId}"></c:param>
					</c:import>
					<!---->
					<c:import url="../common/selectcroptype.jsp">
						<c:param name="cropTypeId" value="${currentObj.cropTypeId}"></c:param>
					</c:import>
					<div class="new_item">
						<div class="tit">
							<em>*</em>生长状态：
						</div>
						<div class="bdmain">
							<select name="video" id="video">
								<option value="播种出苗" selected="selected">播种出苗</option>
								<option value="越冬分蘖" >越冬分蘖</option>
								<option value="返青拔节" >返青拔节</option>
								<option value="抽穗扬花">抽穗扬花</option>																
								<option value="灌浆乳熟" >灌浆乳熟</option>	
								<option value="收获" >收获</option>	
							</select>

						</div>
						<div class="new_plus">
							<span id="videoTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em>*</em>采集时间：
						</div>
						<div class="bdmain">
						<fmt:formatDate var="currentObj_gatherTime" value="${currentObj.gatherTime }" pattern="yyyy/MM/dd"/>

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="gatherTime"
								name="gatherTime" value="${currentObj_gatherTime }"></label>

						</div>
						<div class="new_plus">
							<span id="gatherTimeTip"></span>
						</div>
					</div>
					<!--
					<div class="new_item">
						<div class="tit">
							<em></em>采集坐标(X)：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="positionX"
								name="positionX" value="${currentObj.positionX }"></label>

						</div>
						<div class="new_plus">
							<span id="operationTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>采集坐标(Y)：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="positionY"
								name="positionY" value="${currentObj.positionY }"></label>

						</div>
						<div class="new_plus">
							<span id="operationTypeTip"></span>
						</div>
					</div>
					
					<div class="new_item">
						<div class="tit">
							<em></em>气压：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="pressure"
								name="pressure" value="${currentObj.pressure }"></label>

						</div>
						<div class="new_plus">
							<span id="operationTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>湿度：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="humidity"
								name="humidity" value="${currentObj.humidity }"></label>

						</div>
						<div class="new_plus">
							<span id="operatorTip"></span>
						</div>
					</div>				
					<div class="new_item">
						<div class="tit">
							<em></em>风向：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="windDirection"
								name="windDirection" value="${currentObj.windDirection }"></label>

						</div>
						<div class="new_plus">
							<span id="seedAmountTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>风速：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="windSpeed"
								name="windSpeed" value="${currentObj.windSpeed }"></label>

						</div>
						<div class="new_plus">
							<span id="seedCompanyTip"></span>
						</div>
					</div>
					
					<div class="new_item">
						<div class="tit">
							<em></em>降雨量：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="rainfall"
								name="rainfall" value="${currentObj.rainfall }"></label>

						</div>
						<div class="new_plus">
							<span id="fertilizerAmountTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>总辐射：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="radiation"
								name="radiation" value="${currentObj.radiation }"></label>

						</div>
						<div class="new_plus">
							<span id="fertilizerCompanyTip"></span>
						</div>
					</div>
					
					<div class="new_item">
						<div class="tit">
							<em></em>温度：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="temperature"
								name="temperature" value="${currentObj.temperature }"></label>

						</div>
						<div class="new_plus">
							<span id="pesticideAmountTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>土壤氮：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="soilN"
								name="soilN" value="${currentObj.soilN }"></label>

						</div>
						<div class="new_plus">
							<span id="pesticideCompanyTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>土壤磷：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="soilP"
								name="soilP" value="${currentObj.soilP }"></label>

						</div>
						<div class="new_plus">
							<span id="diseasedTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>土壤钾：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="soilK"
								name="soilK" value="${currentObj.soilK }"></label>

						</div>
						<div class="new_plus">
							<span id="naturalDisasterTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>土壤温度：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="soilTemperature"
								name="soilTemperature" value="${currentObj.soilTemperature }"></label>

						</div>
						<div class="new_plus">
							<span id="naturalDisasterTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>土壤湿度：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="soilHumidity"
								name="soilHumidity" value="${currentObj.soilHumidity }"></label>

						</div>
						<div class="new_plus">
							<span id="naturalDisasterTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>叶面积指数：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="leafArea"
								name="leafArea" value="${currentObj.leafArea }"></label>

						</div>
						<div class="new_plus">
							<span id="naturalDisasterTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>叶绿素：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="leafGreen"
								name="leafGreen" value="${currentObj.leafGreen }"></label>

						</div>
						<div class="new_plus">
							<span id="naturalDisasterTip"></span>
						</div>
					</div>
					-->
					<div class="new_item">
						<div class="tit">
							<em></em>图片文字：
						</div>
						<div class="bdmain">
							<textarea id="picture" name="picture" style="width:800px;height:600px;">${currentObj.picture }</textarea>
						</div>
						<div class="new_plus">
							<span id="pictureTip"></span>
						</div>
					</div>
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
					//gatherTime
					$("#gatherTime").formValidator({
						tipID:"gatherTimeTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).functionValidator({
			            fun: function (val, elem) {
			                if (!/^\d{4}\/\d{2}\/\d{2}$/.test(val)) {
			                    return "请指定正确的时间";
			                }
			                return true;
			            }
			        });

				});
			</script>
		</div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
