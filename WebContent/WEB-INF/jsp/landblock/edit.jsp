<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>地块管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="../js/formValidator-4.0.1.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/formValidatorRegex.js"
	charset="UTF-8"></script>

<style>
		form {
			margin: 0;
		}
		textarea {
			display: block;
		}
	</style>
	<link rel="stylesheet" href="../richeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../richeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../richeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="../richeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../richeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="memo"]', {
				cssPath : '../richeditor/plugins/code/prettify.css',
				uploadJson : '../richeditor/uploadFile001.jsp',
				fileManagerJson : '../richeditor/fileManager001.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>	
</head>
<body>

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
			       		» <a href="./editlist">地块管理</a>    	</div>
		    	<!--  主体   start -->
		    	
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>地块信息</span></h3>
	<div class="new_item">
		<div class="tit"><em>*</em>面积(亩)：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="area" name="area" value="${currentObj.area}">
			</label>
			
		</div><div class="new_plus"><span id="areaTip"></span></div>
    </div>
   <c:import url="../common/selectblocktype.jsp">
   <c:param name="blockTypeId" value="${currentObj.blockTypeId}"></c:param>
   </c:import>
   
      <c:import url="../common/selectlandtype.jsp">
   <c:param name="landTypeId" value="${currentObj.landTypeId}"></c:param>
   </c:import>
   
     <c:import url="../common/selectsite.jsp">
   <c:param name="cooperativeId" value="${currentObj.cooperativeId}"></c:param>
   </c:import>
   
    <div class="new_item">
		<div class="tit"><em></em>地块描述：</div>
		<div class="bdmain">
			<textarea id="memo" name="memo" style="width:800px;height:200px;">${currentObj.memo }</textarea>
		</div><div class="new_plus"><span id="contactTip"></span></div>
    </div>
	
    
</div>
<div class="addtoadsub">
	<input type="hidden" value="${currentObj.blockNumber }" name="blockNumber" id="blockNumber">
	<input type="submit" class="subglobal sub1" value="提交" name="btnSubmit" id="btnSubmit">
	<input type="reset" class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit">       
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
	$("#area").formValidator({
		tipID:"areaTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		type:"number",
		min:0,
		onError: "请填写正确数字!"
	}).regexValidator({ 
		regExp: "num", 
		dataType: "enum", 
		onError: "数字格式不正确" 
	});

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
