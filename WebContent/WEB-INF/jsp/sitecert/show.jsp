<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>合作社管理</title>
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
	<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
	<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
	
</head>
<body><script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	  });
	
	</script>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="1"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">合作社管理</a> &gt; 合作社信息    	</div>
		    	<!--  主体   start -->
		    	
<input type="hidden" name="id" value="${currentObj.id }"/>

  	<div id="tabs">
				<ul>
					<li><a href="#container">合作社基本信息</a></li>
					<li><a href="#container2">文字图片描述</a></li>
					<li><a href="#container3">企业资质</a></li>
				</ul>
				
					<div id="container"	    style="min-width: 800px;">
					<div class="userinfolist"> <div class="new_item">
		<div class="tit"><em>*</em>名称：</div>
		<div class="bdmain">
		
			${currentObj.cooperativeName }
			
		</div><div class="new_plus"><span id="cooperativeNameTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>负责人姓名：</div>
		<div class="bdmain">
		
			${currentObj.owner }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>性质：</div>
		<div class="bdmain">
		${currentObj.character }
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>规模：</div>
		<div class="bdmain">
		
		${currentObj.size }
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>地址：</div>
		<div class="bdmain">
		
		${currentObj.address }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>网址：</div>
		<div class="bdmain">
		
			${currentObj.website }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>法定代表人：</div>
		<div class="bdmain">
		
			${currentObj.legalperson }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>联系电话：</div>
		<div class="bdmain">
		
			${currentObj.telephone }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>电子邮箱：</div>
		<div class="bdmain">
		
			${currentObj.email }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>QQ：</div>
		<div class="bdmain">
		
		${currentObj.qq }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>微信：</div>
		<div class="bdmain">
		
			${currentObj.wechat }
			
		</div><div class="new_plus"><span id="postcodeTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>X坐标：</div>
		<div class="bdmain">
		
			${currentObj.gpsx }
			
		</div><div class="new_plus"><span id="gpsxTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>Y坐标：</div>
		<div class="bdmain">
		
			${currentObj.gpsy }
			
		</div><div class="new_plus"><span id="gpsyTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>备注：</div>
		<div class="bdmain">
		
			${currentObj.memo }
			
		</div><div class="new_plus"><span id="contactTip"></span></div>
    </div>
        <div class="new_item">
		<div class="tit"><em></em>采集人：</div>
		<div class="bdmain">
		
			${currentObj.collector }
			
		</div>
    </div>
	  <div class="new_item">
		<div class="tit"><em></em>采集时间：</div>
		<div class="bdmain">
		
		<fmt:formatDate value="${currentObj.collecttime }" type="date" pattern="yyyy/MM/dd"/>
			
		</div>
    </div>
    </div>
					</div>
					<div id="container2"    style="min-width: 800px;">${currentObj.description }</div>
					<div id="container3"	style="min-width: 800px;">${currentObj.qualification }</div>
				
			</div>	
			
			
    
     
   
	
    
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
	$("#cooperativeName").formValidator({
		tipID:"cooperativeNameTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写合作社名称!"
	});
	$("#gpsinfo").formValidator({
		tipID:"gpsinfoTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写合作社坐标!"
	});
		


	 

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
