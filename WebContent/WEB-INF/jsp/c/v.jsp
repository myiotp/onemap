<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>测土配方结果</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<style>
body {text-align: center;}
table,th,td{
	border:1px solid #DADADA;
	padding: 6px 4px;
	line-height: 1.6em;
	border-collapse:collapse;
}
th{
	background: #EEE;
}
</style>
</head>
<body>
			<div class="container">
			
			<!--  主体   start -->
			<table class="mainTable" style="width:100%;">
				<tbody align=center>
					<tr>
						<th colspan='2'>项目</th>
						<th colspan='2'>土壤测试结果</th>
						<th colspan='1'>养分水平</th>
						<th colspan='2'>推荐施肥</th>       
					</tr>
					${t}
				</tbody>
			</table>
			
		</div>
		<c:import url="../common/footer.jsp"></c:import>
</body>
</html>