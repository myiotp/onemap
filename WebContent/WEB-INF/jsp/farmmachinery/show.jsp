<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>详细</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="../jquery-ui-1.11.1.custom/jquery-ui.min.css">
<link rel="stylesheet" href="../js/jstree/themes/default/style.min.css" />

<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript">
$(function(){
var memuid='4';
var objLink=$("a[href*='memuid="+memuid+"']");
objLink.parent().attr('class',"current");
$('li.group').click(function(){
	if($(this).hasClass('open')){
		$(this).nextAll().hide();
		$(this).removeClass('open');
	}else{
		$(this).nextAll().show();
		$(this).addClass('open');
	}
});
$('li.group').each(function(){
	var iscurrent=false;
	$(this).nextAll().each(function(){
		if($(this).html().indexOf(curl)>0){
			iscurrent=true;
		}
	});
	!iscurrent&&$(this).click();
	
});

});
</script>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			$("#tabs").tabs();
		});
	</script>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="4"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<!-- 左菜单 begin -->
		<div class="sidebar">
			<ul class="column">
				<li class="wzymgl" id=""><a href="../farmmachinery/njyilan"><em></em>农机一览</a></li>
				<li class="wzymgl" id="current"><a href="../farmmachinery/chartsearch"><em></em>农机查询</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/track"><em></em>轨迹回放</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/barrier"><em></em>电子围栏</a></li> 
			</ul>
		</div>
		<!-- 列表内容 begin -->
		<div class="container">

			<!--  主体   start -->

			<input type="hidden" name="id" value="${currentObj.id }" />

			<div id="tabs">
				<ul>
					<li><a href="#container">农机基本规格</a></li>
					<li><a href="#container2">GPS设备信息</a></li>
					<li><a href="#container3">农机所属人信息</a></li>
					<li><a href="#container4">车辆行驶证</a></li>
				</ul>

				<div id="container" style="min-width: 800px;">
					<div class="userinfolist">
						<div class="new_item">
							<div class="tit">
								<em>*</em>合作社：
							</div>
							<div class="bdmain">
								${currentObj.cooperative.cooperativeName }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>车牌号：
							</div>
							<div class="bdmain">${currentObj.licensenumber }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>型号：
							</div>
							<div class="bdmain">${currentObj.xinghao }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>割幅：
							</div>
							<div class="bdmain">${currentObj.gefu }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>外形尺寸：
							</div>
							<div class="bdmain">${currentObj.machinerysize }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>重量：
							</div>
							<div class="bdmain">${currentObj.weight }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>油箱容积：
							</div>
							<div class="bdmain">${currentObj.volume }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>生产厂家：
							</div>
							<div class="bdmain">${currentObj.vendor }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>品牌：
							</div>
							<div class="bdmain">${currentObj.brand }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>农机类型：
							</div>
							<div class="bdmain">${currentObj.machineryType }</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>自动化程度：
							</div>
							<div class="bdmain">${currentObj.automation }</div>
							<div class="new_plus">
								<span id="postcodeTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em>*</em>适用对象：
							</div>
							<div class="bdmain">${currentObj.workfor }</div>
							<div class="new_plus">
								<span id="gpsxTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em>*</em>动力类型：
							</div>
							<div class="bdmain">${currentObj.powertype }</div>
							<div class="new_plus">
								<span id="gpsyTip"></span>
							</div>
						</div>

					</div>
				</div>
				<div id="container2" style="min-width: 800px;">
					<div class="userinfolist">
						<div class="new_item">
							<div class="tit">
								<em></em>GPS序列号：
							</div>
							<div class="bdmain">${currentObj.gpsdevice }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>GPS类型：
							</div>
							<div class="bdmain">${currentObj.gpstype }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>安装人：
							</div>
							<div class="bdmain">${currentObj.gpsinstaller }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>安装日期：
							</div>
							<div class="bdmain">
								<fmt:formatDate var="currentObj_gpsinstalldate"
									value="${currentObj.gpsinstalldate }" pattern="yyyy/MM/dd" />
								${currentObj_gpsinstalldate}

							</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>农机状态：
							</div>
							<div class="bdmain">${currentObj.status }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>

					</div>
				</div>
				<div id="container3" style="min-width: 800px;">
					<div class="userinfolist">
						<div class="new_item">
							<div class="tit">
								<em></em>购买日期：
							</div>
							<div class="bdmain">
								<fmt:formatDate var="currentObj_purchasetime"
									value="${currentObj.purchasetime }" pattern="yyyy/MM/dd" />
								${currentObj_purchasetime}

							</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>生产日期：
							</div>
							<div class="bdmain">
								<fmt:formatDate var="currentObj_producetime"
									value="${currentObj.producetime }" pattern="yyyy/MM/dd" />
								${currentObj_producetime}

							</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>所有人姓名：
							</div>
							<div class="bdmain">${currentObj.ownerName }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>所有人身份证号：
							</div>
							<div class="bdmain">${currentObj.owneridnumber}</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>所有人电话：
							</div>
							<div class="bdmain">${currentObj.ownerTelephone }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>所有人地址：
							</div>
							<div class="bdmain">${currentObj.owneraddress }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>

					</div>
				</div>
				<div id="container4" style="min-width: 800px;">
					<div class="userinfolist">
						<div class="new_item">
							<div class="tit">
								<em></em>车辆行驶证号：
							</div>
							<div class="bdmain">${currentObj.driverlicense }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>发动机号：
							</div>
							<div class="bdmain">${currentObj.enginenumber}</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>机身号码：
							</div>
							<div class="bdmain">${currentObj.bodynumber }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>
						<div class="new_item">
							<div class="tit">
								<em></em>功率：
							</div>
							<div class="bdmain">${currentObj.machinerypower }</div>
							<div class="new_plus">
								<span id="cooperativeNameTip"></span>
							</div>
						</div>

					</div>
				</div>
			</div>







		</div>


	</div>

	<c:import url="../common/footer.jsp"></c:import>

</body>
</html>
