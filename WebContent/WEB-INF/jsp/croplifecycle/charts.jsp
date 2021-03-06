<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生长状态回溯图表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
	
<style type="text/css">
${
demo
.css
}
</style>
	
</head>
<body>
	<c:import url="../common/top.jsp">
		<c:param name="memuid" value="2"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="2"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
                <div id="tabs">
			<ul>
				<li><a href="#image1">2015年4月</a></li>
				<li><a href="#image2">2014年12月</a></li>
			</ul>

			<div id="image1" style="min-width: 800px;">
   		             <img src="../images/xiaomaizhangshi042015.jpg" width="80%"/>
                        </div>
                        <div id="image2" style="min-width: 800px;">
   		             <img src="../images/xiaomaizhangshi122014.jpg" width="80%"/>
                        </div> 
                </div>  
                </div> 

	</div>
	<c:import url="../common/footer.jsp"></c:import>
<script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	  });
	
	</script>
</body>
</html>
