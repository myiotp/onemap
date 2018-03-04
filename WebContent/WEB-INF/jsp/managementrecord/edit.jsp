<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>交易管理</title>
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
	<script>
	  $(function() {
	    $( "#operationTime" ).datepicker({
	      showOtherMonths: true,
	      selectOtherMonths: true,
	      dateFormat: 'yy/mm/dd',
	      defaultDate: +0,
	      onSelect:function(date){  
	          this.focus();  
	          this.blur();  
	      }  
	    });
		if('${currentObj.operationType}' != ''){
			$("#operationType").val('${currentObj.operationType}');
		}
		
// 		$('#operationType').change(function() {   
// 		   var operationType = $(this).val();   
// 		   if(operationType == "测土配方"){
// 			$('#labelvardate1').html("送检时间");
// 			$('#labelvardate2').hide();
// 		   }
// 		  }); 
	  });
	</script>
	
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
			var editor1 = K.create('textarea[name="mgtrecordmemo"]', {
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
		<c:param name="memuid" value="2"></c:param>
	</c:import>
	<span class="blank18"></span>
	<div class="page">
		<c:import url="../common/left.jsp">
			<c:param name="memuid" value="2"></c:param>
		</c:import>
		<!-- 列表内容 begin -->
		<div class="container">
			<div class="connav">
				» <a href="./editlist">交易管理</a> &gt; 补录回程单
			</div>
			<!--  主体   start -->

			<form name="form1" id="form1" action="./updateReturnOrder" method="post">
				<input type="hidden" name="id" value="${currentObj.id }" />
				<div class="userinfolist">
					<h3 class="navbig">
						<span class="navleft"><em class="ico"></em> <c:choose>
								<c:when test="${currentObj ==null }">添加</c:when>
								<c:otherwise>修改</c:otherwise>
							</c:choose>记录信息</span>
					</h3>
					<div class="new_item">
				        <div class="tit"><em>*</em>货物到达日期：</div>
				        <div class="bdmain">
				        
				            <label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				                <input type="text" id="vardate1" name="vardate1" value="${currentObj.vardate1 }" readonly="readonly" />
				            </label>
				            
				        </div><div class="new_plus"><span id="vardate1Tip"></span></div>
				    </div>
				    <div class="new_item">
                        <div class="tit">
                            <em>*</em>配货经办人：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="approver3"
                                name="approver3" class="{required:true,minlength:1}" value="${currentObj.approver3 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="approver3tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit"><em>*</em>配货日期：</div>
                        <div class="bdmain">
                        
                            <label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
                                <input type="text" id="datetime3" name="datetime3" value="${currentObj.datetime3 }" readonly="readonly" />
                            </label>
                            
                        </div><div class="new_plus"><span id="datetime3Tip"></span></div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em>*</em>审核人：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="approver4"
                                name="approver4" class="{required:true,minlength:1}" value="${currentObj.approver4 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="approver4tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit"><em>*</em>审核日期：</div>
                        <div class="bdmain">
                        
                            <label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
                                <input type="text" id="datetime4" name="datetime4" value="${currentObj.datetime4 }" readonly="readonly" />
                            </label>
                            
                        </div><div class="new_plus"><span id="datetime4Tip"></span></div>
                    </div>
                    
					<div class="new_item">
						<div class="tit">
							<em>*</em>款项#1说明：
						</div>
						<div class="bdmain">
						  <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="item1"
                                name="item1" class="{required:true,minlength:1}" value="${currentObj.item1 }"></label>

						</div>
						<div class="new_plus">
							<span id="item1tip"></span>
						</div>
					</div>
					<div class="new_item">
                        <div class="tit">
                            <em>*</em>款项#1金额：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="price1"
                                name="price1" class="{required:true,minlength:1}" value="${currentObj.price1 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="price1tip"></span>
                        </div>
                    </div>
					<div class="new_item">
                        <div class="tit">
                            <em></em>款项#2说明：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="item2"
                                name="item2" class="{required:true,minlength:1}" value="${currentObj.item2 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="item2tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#2金额：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="price2"
                                name="price2" class="{required:true,minlength:1}" value="${currentObj.price2 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="price2tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#3说明：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="item3"
                                name="item3" class="{required:true,minlength:1}" value="${currentObj.item3 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="item3tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#3金额：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="price3"
                                name="price3" class="{required:true,minlength:1}" value="${currentObj.price3 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="price3tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#4说明：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="item4"
                                name="item4" class="{required:true,minlength:1}" value="${currentObj.item4 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="item4tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#4金额：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="price4"
                                name="price4" class="{required:true,minlength:1}" value="${currentObj.price4 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="price4tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#5说明：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="item5"
                                name="item5" class="{required:true,minlength:1}" value="${currentObj.item5 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="item5tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>款项#5金额：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="price5"
                                name="price5" class="{required:true,minlength:1}" value="${currentObj.price5 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="price5tip"></span>
                        </div>
                    </div>
					<div class="new_item">
                        <div class="tit">
                            <em></em>公司电话：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="company1"
                                name="company1" class="{required:true,minlength:1}" value="${currentObj.company1 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="company1tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>传真：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="company2"
                                name="company2" class="{required:true,minlength:1}" value="${currentObj.company2 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="company2tip"></span>
                        </div>
                    </div>
                    <div class="new_item">
                        <div class="tit">
                            <em></em>手机：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="company3"
                                name="company3" class="{required:true,minlength:1}" value="${currentObj.company3 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="company3tip"></span>
                        </div>
                    </div>
					<div class="new_item">
                        <div class="tit">
                            <em></em>公司地址：
                        </div>
                        <div class="bdmain">
                          <label onmouseout="this.className='input_out'"
                                onmousemove="this.className='input_move'"
                                onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
                                onfocus="this.className='input_on';this.onmouseout=''"
                                class="input_out"> <input type="text" id="company4"
                                name="company4" class="{required:true,minlength:1}" value="${currentObj.company4 }"></label>

                        </div>
                        <div class="new_plus">
                            <span id="company4tip"></span>
                        </div>
                    </div>
                    <!-- 
					<div class="new_item">
						<div class="tit">
							<em></em>其他(图片文字)：
						</div>
						<div class="bdmain">
							<textarea id="mgtrecordmemo" name="mgtrecordmemo" style="width:600px;height:300px;">${currentObj.mgtrecordmemo }</textarea>
						</div>
						<div class="new_plus">
							<span id="mgtrecordmemoTip"></span>
						</div>
					</div>
					 -->
					
					
				</div>
				<div class="addtoadsub">
					<input type="submit" class="subglobal sub1" value="提交"
						name="btnSubmit" id="btnSubmit"> <input type="reset"
						class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit">
				</div>
			</form>
			<script type="text/javascript">
				$(document).ready(function() {
					$.formValidator.initConfig({
						formID : "form1",
						debug : false,
						submitOnce : true,
						onError : function(msg, obj, errorlist) {

						},
						submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
					});
					$("#vardate1").formValidator({
                        tipID:"vardate1Tip",
                        onShow:"",
                        onFocus:"",
                        onCorrect:""
                    }).inputValidator({
                        min:1,
                        onError: "请选择日期!"
                    });
                    $( "#vardate1" ).datepicker({
                        showButtonPanel: true,
                        dateFormat: "yy-mm-dd"
                    });
                    
					$("#approver3").formValidator({
                        tipID:"approver3tip",
                        onShow:"",
                        onFocus:"",
                        onCorrect:""
                    }).inputValidator({
                        min:1,
                        onError: "请填写配货经办人!"
                    });
					$("#datetime3").formValidator({
                        tipID:"datetime3Tip",
                        onShow:"",
                        onFocus:"",
                        onCorrect:""
                    }).inputValidator({
                        min:1,
                        onError: "请选择日期!"
                    });
                    $( "#datetime3" ).datepicker({
                        showButtonPanel: true,
                        dateFormat: "yy-mm-dd"
                    });
                    
                    $("#approver4").formValidator({
                        tipID:"approver4tip",
                        onShow:"",
                        onFocus:"",
                        onCorrect:""
                    }).inputValidator({
                        min:1,
                        onError: "请填写审核人!"
                    });
                    $("#datetime4").formValidator({
                        tipID:"datetime4Tip",
                        onShow:"",
                        onFocus:"",
                        onCorrect:""
                    }).inputValidator({
                        min:1,
                        onError: "请选择日期!"
                    });
                    $( "#datetime4" ).datepicker({
                        showButtonPanel: true,
                        dateFormat: "yy-mm-dd"
                    });
                    
					
					$("#item1").formValidator({
				        tipID:"item1tip",
				        onShow:"",
				        onFocus:"",
				        onCorrect:""
				    }).inputValidator({
				        min:1,
				        onError: "请填写款项说明!"
				    });
					$("#price1").formValidator({
                        tipID:"price1tip",
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
