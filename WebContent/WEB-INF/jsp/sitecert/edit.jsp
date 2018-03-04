<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>认证信息管理</title>
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
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>	
	<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
</head>
<body>
<link rel="stylesheet" href="../richeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../richeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../richeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="../richeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../richeditor/plugins/code/prettify.js"></script>
<script>

var options = {
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
		};
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[id=certdescription]', options);
			prettyPrint();
		});
	
		 $(function() {
			    $( "#certtime" ).datepicker({
			      showOtherMonths: true,
			      selectOtherMonths: true,
			      dateFormat: 'yy/mm/dd'
			    });
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
			       		» <a href="./editlist">认证信息管理</a> &gt; <c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>认证信息    	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>认证信息信息</span></h3>
  
     <div class="new_item">
		<div class="tit"><em>*</em>认证类别：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="certtype" name="certtype" value="${currentObj.certtype }">
			</label>
			
		</div><div class="new_plus"><span id="certtypeTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>认证时间：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="certtime" name="certtime" value="<fmt:formatDate value="${currentObj.certtime }" type="date" pattern="yyyy/MM/dd"/>">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>有效期限：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="validtime" name="validtime" value="${currentObj.validtime }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>认证机构：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="certorganization" name="certorganization" value="${currentObj.certorganization }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>证书编号：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="certnumber" name="certnumber" value="${currentObj.certnumber }">
			</label>
			
		</div>
    </div>
    
     <div class="new_item">
		<div class="tit"><em></em>证书图片资料：</div>
		<div class="bdmain">
		
				<textarea id="certdescription" name="certdescription" style="width:800px;height:200px;">${currentObj.certdescription }</textarea>
			
		</div>
    </div>
   
</div>
<div class="addtoadsub">
	<input type="hidden" value="${currentObj.cooperativeId }" name="cooperativeId" id="cooperativeId">
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

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
