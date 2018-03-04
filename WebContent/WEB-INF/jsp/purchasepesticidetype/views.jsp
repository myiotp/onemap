<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
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
</head>
<body>


	<span class="blank18"></span>
	<div class="page">

		<!-- 列表内容 begin -->
		<div class="container">
			<!--  主体   start -->
			<div class="conborder getcode">
				<div class="onlinelist">
					<h2 class="indextitle">
						<em></em><em class="b2"></em><span></span>
						<p class="tit1"></p>
					</h2>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="border-collapse: collapse">
						<tr>
							<th colspan=2><span>资料信息</span></th>
						</tr>

						<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
							<td>姓名</td>
							<td>${currentObj.realname}</td>
						</tr>
						<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>身份证号</td>
                            <td>${currentObj.idcard}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>联系方式</td>
                            <td>${currentObj.mobilephone}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>公司名称</td>
                            <td>${currentObj.company}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>类型</td>
                            <td>${currentObj.password}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>所在地区</td>
                            <td>${currentObj.province}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>详细地址</td>
                            <td>${currentObj.address}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>应急联系人</td>
                            <td>${currentObj.emergency}</td>
                        </tr>
                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                            <td>应急联系电话</td>
                            <td>${currentObj.emergencyphone}</td>
                        </tr>
					</table>

				</div>
			</div>
		</div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
