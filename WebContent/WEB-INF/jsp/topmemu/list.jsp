<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧物流平台系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
</head>
<body>
	<c:import url="../common/top.jsp">
	</c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	</c:import>
	<div class="container">
		
		<c:choose>
			<c:when test="${param.memuid==10}">
				<!--<img src="../images/nongji001.png"></img>-->
				<c:redirect url="../welcomePage/view" />				
			</c:when>
			<c:when test="${param.memuid==0}">
				<!--<img src="../images/nongji001.png"></img>-->
				<c:redirect url="../welcomePage/view2" />				
			</c:when>
			
			<c:when test="${param.memuid==1}">
				<c:redirect url="../landtype/editlist" />				
			</c:when>
			<c:when test="${param.memuid==2}">
				<c:redirect url="../managementrecord/allconfirmed?approvelevel=-1" />
			</c:when>
				
			<c:when test="${param.memuid==3}">
				<c:redirect url="../layout2/view" />
			</c:when>
			<c:when test="${param.memuid==4}">
				<c:redirect url="../vehiclenianjian/editlist" />
			</c:when>			
			<c:when test="${param.memuid==5}">
				<c:redirect url="../vehiclebaoxian/editlist" />
			</c:when>
			<c:when test="${param.memuid==6}">
                <c:redirect url="../purchasepesticidetype/editlist" />
            </c:when>
			<c:when test="${param.memuid==7}">
				<c:redirect url="../layout/view" />
			</c:when>
			<c:when test="${param.memuid==8}">
				<c:redirect url="../siteinfo/list" />
			</c:when>
			<c:when test="${param.memuid==9}">
				<c:redirect url="../user/editlist" />
			</c:when>
		</c:choose>
		
	</div>
<c:import url="../common/footer.jsp"></c:import>
</div>
<script type="text/javascript">
$(function(){
	$("ul li:eq(0)").click();
});
</script>
</body>
</html>
