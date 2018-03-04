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
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>	
	<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>

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
			var editor1 = K.create('textarea[id=description]', {
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
		KindEditor.ready(function(K) {
			var editor2 = K.create('textarea[id=qualification]', {
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
		 $(function() {
			    $( "#collecttime" ).datepicker({
			      showOtherMonths: true,
			      selectOtherMonths: true,
			      dateFormat: 'yy/mm/dd',
				      defaultDate: +0,
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
			       		» <a href="./editlist">合作社管理</a> &gt; <c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>合作社    	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>合作社信息</span></h3>
	 <c:import url="../common/selectcity.jsp">
		<c:param name="id" value="${currentObj.id}"></c:param>
		<c:param name="province" value="${currentObj.province}"></c:param>
		<c:param name="city1" value="${currentObj.city1}"></c:param>
	 </c:import>	 
     <div class="new_item">
		<div class="tit"><em>*</em>名称：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="cooperativeName" name="cooperativeName" value="${currentObj.cooperativeName }">
			</label>
			
		</div><div class="new_plus"><span id="cooperativeNameTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>负责人姓名：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="owner" name="owner" value="${currentObj.owner }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>性质：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="character" name="character" value="${currentObj.character }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>规模：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="size" name="size" value="${currentObj.size }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>地址：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="address" name="address" value="${currentObj.address }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>网址：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="website" name="website" value="${currentObj.website }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>法定代表人：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="legalperson" name="legalperson" value="${currentObj.legalperson }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>联系电话：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="telephone" name="telephone" value="${currentObj.telephone }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>电子邮箱：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="email" name="email" value="${currentObj.email }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>QQ：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="qq" name="qq" value="${currentObj.qq }">
			</label>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>微信：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="wechat" name="wechat" value="${currentObj.wechat }">
			</label>
			
		</div><div class="new_plus"><span id="postcodeTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>X坐标：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="gpsx" name="gpsx" value="${currentObj.gpsx }">
			</label>
			
		</div><div class="new_plus"><span id="gpsxTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>Y坐标：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="gpsy" name="gpsy" value="${currentObj.gpsy }">
			</label>
			
		</div><div class="new_plus"><span id="gpsyTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>备注：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="memo" name="memo" value="${currentObj.memo }">
			</label>
			
		</div><div class="new_plus"><span id="contactTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>文字图片描述：</div>
		<div class="bdmain">
		
				<textarea id="description" name="description" style="width:800px;height:200px;">${currentObj.description }</textarea>
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>企业资质：</div>
		<div class="bdmain">
		
				<textarea id="qualification" name="qualification" style="width:800px;height:200px;">${currentObj.qualification }</textarea>
			
		</div>
    </div>
     
       <div class="new_item">
		<div class="tit"><em></em>采集人：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="collector" name="collector" value="${currentObj.collector }">
			</label>
			
		</div>
    </div>
	  <div class="new_item">
		<div class="tit"><em>*</em>采集时间：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="collecttime" name="collecttime" value="<fmt:formatDate value="${currentObj.collecttime }" type="date" pattern="yyyy/MM/dd"/>">
			</label>
			
		</div><div class="new_plus"><span id="collecttimeTip"></span></div>
    </div>
	
    
</div>
<div class="addtoadsub">
	<input type="hidden" value="${currentObj.cooperativeNumber }" name="cooperativeNumber" id="cooperativeNumber">
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
			
	$("#cooperativeName").formValidator({
		tipID:"cooperativeNameTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请填写名称!"
	});
	$("#gpsx").formValidator({
		tipID:"gpsxTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		type:"number",
		min:0,
		onError: "请填写数字!"
	}).regexValidator({ 
		regExp: "num", 
		dataType: "enum", 
		onError: "数字格式不正确" 
	});
	$("#gpsy").formValidator({
		tipID:"gpsyTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({		
		type:"number",
		min:0,
		onError: "请填写数字!"
	}).regexValidator({ 
		regExp: "num", 
		dataType: "enum", 
		onError: "数字格式不正确" 
	});
	//collecttime
	$("#collecttime").formValidator({
		tipID:"collecttimeTip",
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
