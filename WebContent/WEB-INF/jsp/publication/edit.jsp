<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
System.out.println("[[[[[[[[]]]]]]]]"+htmlData);
%>
<!DOCTYPE html>
<html>
<head>
<title>行业报告管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
	<script type="text/javascript" src="../js/formValidator-4.0.1.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="../js/formValidatorRegex.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>	
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
			var editor1 = K.create('textarea[name="content"]', {
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
<script>
	  $(function() {
	    $( "#time" ).datepicker({
	      showOtherMonths: true,
	      selectOtherMonths: true,
	      dateFormat: 'yy/mm/dd',
	      onSelect:function(date){  
	          this.focus();  
	          this.blur();  
	      }  
	    });
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
			       		» <a href="./editlist">行业报告管理</a> &gt; 新建行业报告    	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>行业报告信息</span></h3>
     <div class="new_item">
		<div class="tit"><em>*</em>标题：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="title" name="title" value="${currentObj.title }">
			</label>
			
		</div><div class="new_plus"><span id="titleTip"></span></div>
    </div>
	<div class="new_item">
		<div class="tit"><em>*</em>发布时间：</div>
		<div class="bdmain">
		<fmt:formatDate var="currentObj_time" value="${currentObj.time }" pattern="yyyy/MM/dd"/>
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="time" name="time" value="${currentObj_time}">
			</label>
			
		</div><div class="new_plus"><span id="timeTip"></span></div>
    </div>
	<div class="new_item">
		<div class="tit"><em></em>来源：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="source" name="source" value="${currentObj.source }">
			</label>
			
		</div><div class="new_plus"><span id="sourceTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>内容：</div>
		<div class="bdmain">
			<textarea id="content" name="content" style="width:800px;height:200px;">${currentObj.content }</textarea>
		</div><div class="new_plus"></span></div>
    </div>
    
    
</div>
<div class="addtoadsub">
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
			
	$("#title").formValidator({
		tipID:"titleTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写行业报告名称!"
	});
	$("#time").formValidator({
		tipID:"timeTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).functionValidator({
        fun: function (val, elem) {
            if (!/^\d{4}\/\d{2}\/\d{2}$/.test(val)) {
                return "请指定正确的时间";
            }
            return true;
        }
    });
	
});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
