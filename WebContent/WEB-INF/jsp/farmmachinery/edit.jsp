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
<link rel="stylesheet"
	href="../jquery-ui-1.11.1.custom/jquery-ui.min.css">
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="../js/formValidator-4.0.1.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/formValidatorRegex.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>
<script>
	$(function() {
		$("#gpsinstalldate").datepicker({
			showOtherMonths : true,
			selectOtherMonths : true,
			dateFormat : 'yy/mm/dd',
			defaultDate: +0,
			onSelect:function(date){  
			  this.focus();  
			  this.blur();  
			}  
		});
		$("#purchasetime").datepicker({
			showOtherMonths : true,
			selectOtherMonths : true,
			dateFormat : 'yy/mm/dd',
			defaultDate: +0,
			onSelect:function(date){  
			  this.focus();  
			  this.blur();  
			}  
		});
		$("#producetime").datepicker({
			showOtherMonths : true,
			selectOtherMonths : true,
			dateFormat : 'yy/mm/dd',
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
				» <a href="./editlist">农机管理</a>
			</div>
			<!--  主体   start -->


			<form name="form1" id="form1" action="./save" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					<h3 class="navbig">
						<span class="navleft"><em class="ico"></em>
						<c:choose>
								<c:when test="${currentObj ==null }">添加</c:when>
								<c:otherwise>修改</c:otherwise>
							</c:choose>农机信息</span>
					</h3>

					 <c:import url="../common/selectsite.jsp">
					   <c:param name="cooperativeId" value="${currentObj.cooperativeId}"></c:param>
					   </c:import>
					<div class="new_item">
						<div class="tit">
							<em>*</em>车牌号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="licensenumber"
								name="licensenumber" value="${currentObj.licensenumber }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="licensenumberTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>型号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="xinghao"
								name="xinghao" value="${currentObj.xinghao}" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>割幅：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="gefu"
								name="gefu" value="${currentObj.gefu }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>外形尺寸：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="machinerysize"
								name="machinerysize" value="${currentObj.machinerysize }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>重量：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="weight"
								name="weight" value="${currentObj.weight }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>油箱容积：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="volume"
								name="volume" value="${currentObj.volume }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>生产厂家：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="vendor"
								name="vendor" value="${currentObj.vendor }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>品牌：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="brand"
								name="brand" value="${currentObj.brand }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>农机类型：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="machineryType"
								name="machineryType" value="${currentObj.machineryType }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>自动化程度：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="automation"
								name="automation" value="${currentObj.automation }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>适用对象：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="workfor"
								name="workfor" value="${currentObj.workfor }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>动力类型：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="powertype"
								name="powertype" value="${currentObj.powertype }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="machineryTypeTip"></span>
						</div>
					</div>

					<div class="new_item">
						<div class="tit">
							<em>*</em>GPS序列号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="gpsdevice"
								name="gpsdevice" value="${currentObj.gpsdevice }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="gpsdeviceTip"></span>
						</div>
					</div>

					<div class="new_item">
						<div class="tit">
							<em></em>GPS类型：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="gpstype"
								name="gpstype" value="${currentObj.gpstype }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>安装人：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text" id="gpsinstaller"
								name="gpsinstaller" value="${currentObj.gpsinstaller }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em>*</em>安装日期：
						</div>
						<div class="bdmain">
						<fmt:formatDate var="currentObj_gpsinstalldate" value="${currentObj.gpsinstalldate }" pattern="yyyy/MM/dd"/>
							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="gpsinstalldate" name="gpsinstalldate"
								value="${currentObj_gpsinstalldate }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="gpsinstalldateTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>农机状态：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="status" name="status"
								value="${currentObj.status }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em>*</em>购买日期：
						</div>
						<div class="bdmain">
						<fmt:formatDate var="currentObj_purchasetime" value="${currentObj.purchasetime }" pattern="yyyy/MM/dd"/>  
							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="purchasetime" name="purchasetime"
								value="${currentObj_purchasetime }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="purchasetimeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em>*</em>生产日期：
						</div>
						<div class="bdmain">
						<fmt:formatDate var="currentObj_producetime" value="${currentObj.producetime }" pattern="yyyy/MM/dd"/>
							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="producetime" name="producetime"
								value="${currentObj_producetime }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="producetimeTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>所有人姓名：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="ownerName" name="ownerName"
								value="${currentObj.ownerName }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>所有人身份证号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="owneridnumber" name="owneridnumber"
								value="${currentObj.owneridnumber }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>所有人电话：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="ownerTelephone" name="ownerTelephone"
								value="${currentObj.ownerTelephone }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>所有人地址：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="owneraddress" name="owneraddress"
								value="${currentObj.owneraddress }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>车辆行驶证号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="driverlicense" name="driverlicense"
								value="${currentObj.driverlicense }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>发动机号：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="enginenumber" name="enginenumber"
								value="${currentObj.enginenumber }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>机身号码：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="bodynumber" name="bodynumber"
								value="${currentObj.bodynumber }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>
					<div class="new_item">
						<div class="tit">
							<em></em>功率：
						</div>
						<div class="bdmain">

							<label onmouseout="this.className='input_out'"
								onmousemove="this.className='input_move'"
								onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
								onfocus="this.className='input_on';this.onmouseout=''"
								class="input_out"> <input type="text"
								id="machinerypower" name="machinerypower"
								value="${currentObj.machinerypower }" />
							</label>

						</div>
						<div class="new_plus">
							<span id="nameTip"></span>
						</div>
					</div>

				</div>
				<div class="addtoadsub">
					<input type="hidden" value="${currentObj.machineryNumber }"
						name="machineryNumber" id="machineryNumber"> <input
						type="submit" class="subglobal sub1" value="提交" name="btnSubmit"
						id="btnSubmit"> <input type="reset" class="escglobal sub2"
						value="重置" name="btnSubmit" id="btnSubmit">
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

							
					$("#licensenumber").formValidator({
						tipID:"licensenumberTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).inputValidator({
						min:1,
						onError: "请填写车牌号!"
					});
					//gpsdevice
					$("#gpsdevice").formValidator({
						tipID:"gpsdeviceTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).inputValidator({
						min:1,
						onError: "请填写序列号"
					});
					$("#gpsx").formValidator({
						tipID:"gpsxTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).inputValidator({
						type:"number",
						min:0,
						onError: "请填写数字!"
					}).regexValidator({ 
						regExp: "num", 
						dataType: "enum", 
						onError: "数字格式不正确" 
					});
					$("#gpsy").formValidator({
						tipID:"gpsyTip",
						onShow:"",
						onFocus:"",
						onCorrect:""
					}).inputValidator({		
						type:"number",
						min:0,
						onError: "请填写数字!"
					}).regexValidator({ 
						regExp: "num", 
						dataType: "enum", 
						onError: "数字格式不正确" 
					});
					//collecttime
					$("#gpsinstalldate").formValidator({
						tipID:"gpsinstalldateTip",
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

					//purchasetime
					$("#purchasetime").formValidator({
						tipID:"purchasetimeTip",
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
					//producetime
					$("#producetime").formValidator({
						tipID:"producetimeTip",
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
