<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>车辆状况</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link href="../css/global.css" rel="stylesheet" type="text/css" />
	<link href="../css/manage.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../js/ahover.js"></script>
</head>
<body>
	<script type="text/javascript" language="javaScript">
		$(function() {
			$('#newBtn').click(function() {
				window.location.href = './edit';
			});
		});
	</script>
	<c:import url="../common/top.jsp" />
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="6"></c:param>

		</c:import>
		<!-- 列表内容 begin -->
		<div class="container"></div>

		<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
