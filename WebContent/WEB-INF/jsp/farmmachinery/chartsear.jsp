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
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=15&ak=6dtvdbX5acH7wNZU4yPXGYL0"></script>
<script language="JavaScript">
<%
String para = request.getParameter("licensenumber");
if(para == null || para.length() == 0){
	para = "";
}else{
	para = "?licensenumber=" +new String(
			para.getBytes("iso-8859-1"), "UTF-8");
}
System.out.println("license:" + para);
%>
var licnumber = '<%=para%>';
</script>
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#map {
	width: 85%;
	height: 480px;
}
</style>

</head>
<body onload='initialize()'>

	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="4"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
		<c:param name="memuid" value="4"></c:param>
		<c:param name="a" value="1"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="map"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
<script type="text/javascript" src="../js/baidu4njfb.js"></script>	
</body>
</html>
