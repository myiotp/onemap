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


});
</script>
</head>
<body>
	<div id="{id}" class="mappop nongjipop">
	    <div >
	    	<img class="attachimg" src="../images/20141221191010_221.jpg"></img>
	    </div>
	    <div class="name">${currentObj.cooperative.cooperativeName}</div>
	    <div class="triangle"></div>
		<div class="title">
			<div id="{id}baseinfo" class="select small">基本规格</div>
			<div id="{id}qualification" class="small">GPS设备</div>
			<div id="{id}attestation" class="small">农机所属人</div>
			<div id="{id}car" class="small">车辆行驶证</div>
		</div>
		<div id="{id}baseinfocontent" class="content">
		   <div>
		   		<div class="lable"><em>*</em><span>合作社：</span></div>
		   		<div class="value">${currentObj.cooperative.cooperativeName}</div>
		   </div>
		   <div>
		   		<div class="lable">车牌号：</div>
		   		<div class="value">${currentObj.licensenumber}</div>
		   </div>
		   <div>
		   		<div class="lable">型号：</div>
		   		<div class="value">${currentObj.xinghao}</div>
		   </div>
		   <div>
		   		<div class="lable">割幅：</div>
		   		<div class="value">${currentObj.gefu}</div>
		   </div>
		   <div>
		   		<div class="lable">外形尺寸：</div>
		   		<div class="value">${currentObj.machinerysize}</div>
		   </div>
		    <div>
		   		<div class="lable">重量：</div>
		   		<div class="value">${currentObj.weight}</div>
		   </div>
		    <div>
		   		<div class="lable">油箱容积：</div>
		   		<div class="value">${currentObj.volume }</div>
		   </div>
		   <div>
		   		<div class="lable">生产厂家：</div>
		   		<div class="value">${currentObj.vendor }</div>
		   </div>
		   <div>
		   		<div class="lable">品牌：</div>
		   		<div class="value">${currentObj.brand }</div>
		   </div>
		   <div>
		   		<div class="lable">农机类型：</div>
		   		<div class="value">${currentObj.machineryType}</div>
		   </div>
		    <div>
		   		<div class="lable">自动化程度：</div>
		   		<div class="value">${currentObj.automation}</div>
		   </div>
		    <div>
		   		<div class="lable"><em>*</em><span>适用对象：</span></div>
		   		<div class="value">${currentObj.workfor}</div>
		   </div>
		    <div>
		   		<div class="lable"><em>*</em><span>动力类型：</span></div>
		   		<div class="value">${currentObj.powertype}</div>
		   </div>
		</div>
		
		<div id="{id}qualificationcontent"  class="content hiddenit">
		 	<div>
		   		<div class="lable">GPS序列号：</div>
		   		<div class="value">${currentObj.gpsdevice }</div>
		   </div>
		   <div>
		   		<div class="lable">GPS类型：</div>
		   		<div class="value">${currentObj.gpstype }</div>
		   </div>
		   <div>
		   		<div class="lable">安装人：</div>
		   		<div class="value">${currentObj.gpsinstaller }</div>
		   </div>
		   <div>
		   		<div class="lable">安装日期：</div>
		   		<div class="value"><fmt:formatDate var="currentObj_gpsinstalldate"
										value="${currentObj.gpsinstalldate }" pattern="yyyy/MM/dd" />
									${currentObj_gpsinstalldate}</div>
		   </div>
		   <div>
		   		<div class="lable">农机状态：</div>
		   		<div class="value">${currentObj.status }</div>
		   </div>
		</div>
		
		
		<div id="{id}attestationcontent"  class="content hiddenit">
		  <div>
		   		<div class="lable">购买日期：</div>
		   		<div class="value"><fmt:formatDate var="currentObj_purchasetime"
										value="${currentObj.purchasetime }" pattern="yyyy/MM/dd" />
									${currentObj_purchasetime}</div>
		   </div>
		   <div>
		   		<div class="lable">生产日期：</div>
		   		<div class="value"><fmt:formatDate var="currentObj_producetime"
										value="${currentObj.producetime }" pattern="yyyy/MM/dd" />
									${currentObj_producetime}</div>
		   </div>
		   <div>
		   		<div class="lable">所有人姓名：</div>
		   		<div class="value">${currentObj.ownerName }</div>
		   </div>
		   <div>
		   		<div class="lable">所有人身份证号：</div>
		   		<div class="value">${currentObj.owneridnumber}</div>
		   </div>
		   <div>
		   		<div class="lable">所有人电话：</div>
		   		<div class="value">${currentObj.ownerTelephone }</div>
		   </div>	   
		    <div>
		   		<div class="lable">所有人地址：</div>
		   		<div class="value">${currentObj.owneraddress }</div>
		   </div>
		</div>
		
		<div id="{id}carcontent"  class="content hiddenit">
		    <div>
		   		<div class="lable">车辆行驶证号：</div>
		   		<div class="value">${currentObj.driverlicense }</div>
		   </div>
		    <div>
		   		<div class="lable">发动机号：</div>
		   		<div class="value">${currentObj.enginenumber}</div>
		   </div>
		   <div>
		   		<div class="lable">机身号码：</div>
		   		<div class="value">${currentObj.bodynumber }</div>
		   </div>
		   <div>
		   		<div class="lable">功率：</div>
		   		<div class="value">${currentObj.machinerypower }</div>
		   </div>
		   <div>
		   		<div class="lable">发动机号：</div>
		   		<div class="value">${currentObj.enginenumber}</div>
		   </div>
		</div>
	   
</div>

</body>
</html>
