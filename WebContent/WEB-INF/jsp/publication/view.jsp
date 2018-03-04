<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>行业报告管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="../js/formValidator-4.0.1.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/formValidatorRegex.js"
	charset="UTF-8"></script>
</head>
<body>
		
<!-- 列表内容 begin -->
	<div class="container">
		    	
		    	<!--  主体   start -->


<h1 class="navbig"><span class="navleft"><em class="ico"></em>行业报告信息</span></h1>

<div class="content_nr">
        <br/><br/><br/>
        <div class="ny_main">
          <div class="new_biaoti">
               <font style="font-size:16px;">${currentObj.title }</font>
          </div>
          <div class="new_time">  
                                           本信息于 ${currentObj.time } 发布 (出处: ${currentObj.source })
                                           共 ${currentObj.readamount }次访问</div>
          <div class="new_wenzi">
            ${currentObj.content }	
                 
          </div>
          <div class="luokuan"> </div>
        </div>
    </div>
	
    



<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
	$("#seedType").formValidator({
		tipID:"seedTypeTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写行业报告名称!"
	});
	
		


	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
