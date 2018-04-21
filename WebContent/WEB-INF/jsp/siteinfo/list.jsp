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
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" href="../js/jstree/themes/default/style.min.css" />
	 
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script src="../js/jstree/jstree.min.js"></script>
<style>
a:link{color:#fff; text-decoration:underline;outline:none;}
a:visited { color:#fff; text-decoration:underline;}
a:hover{color:#fff;text-decoration:none }

.container {
    border-left: 1px solid #e2e2e2;
    float: left;
    padding-left: 20px;
    width: 900px;
}

 .apply_btn_box {
    position: absolute;
    right: 130px;
    bottom: 300px;
    animation: keyApplyBtn 1s 1 linear;
}
 .apply_btn {
    color: #fff;
    border: 1px solid #68697f;
    line-height: 40px;
    display: inline-block;
    padding: 0 30px;
    background-color:#68697f;
}
</style>
</head>
<body>
	

	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="8"></c:param></c:import>
	<span class="blank18"></span>
	<div class="page">
		<!-- 左菜单 begin -->
		
		<!-- 左菜单 end -->
		
		
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="data">
			<img src="https://www.tongdagufen.cn/images/openplatform.png"/>
			<a class="apply_btn" href="https://www.tongdagufen.cn/images/help/userguide.pdf"  target="_blank">下载使用手册</a>
			<div class="apply_btn_box">
            </div>
		</div>
        
	</div>	
	<c:import url="../common/footer.jsp"></c:import>	
<script type="text/javascript">
var memuid=8;
var objLink=$("a[href*='memuid="+memuid+"']");
objLink.parent().attr('class',"current");
		
		</script>
</body>
</html>
