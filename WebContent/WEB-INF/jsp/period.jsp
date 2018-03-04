<%@page import="java.util.Calendar,com.onemap.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>切换</title>
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
<script type="text/javascript">
<!--
	function AuthIndexCheck() {
		$.formValidator.initConfig({
			formID : "auth-index",
			debug : false,
			submitOnce : true,
			onError : function(msg, obj, errorlist) {
				//alert(msg);
			},
			submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
		});
		$("#user_name").formValidator({
			tipID : "user_nameTip",
			onShow : "",
			onFocus : "请输入用户名",
			onCorrect : "",
			onError : "请输入用户名"
		}).inputValidator({
			min : 1,
			max : 120,
			onError : "请输入用户名"
		});
		$("#user_password").formValidator({
			tipID : "user_passwordTip",
			onShow : "",
			onFocus : "请输入密码",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			max : 120,
			onError : "密码不能为空"
		});
	}
	$(function() {
		AuthIndexCheck();
	});
//-->
</script>
<script type="text/javascript">
	function setupLabel() {
		if ($('.label_check input').length) {
			$('.label_check').each(function() {
				$(this).removeClass('c_on');
			});
			$('.label_check input:checked').each(function() {
				$(this).parent('label').addClass('c_on');
			});
		}
		;
		if ($('.label_radio input').length) {
			$('.label_radio').each(function() {
				$(this).removeClass('r_on');
			});
			$('.label_radio input:checked').each(function() {
				$(this).parent('label').addClass('r_on');
			});
		}
		;
	}

	$(function() {
		$('body').addClass('has-js');
		$('.label_check,.label_radio').click(function() {
			setupLabel();
		});
		setupLabel();
	});
</script>
</head>
<body>
	<!-- 顶部 -->
	<div class="topwrap topwraplogin">
		<div class="page">
			<h1 class="logo">
				<a href="./"><img src="../images/logo.png"
					 /></a>
			</h1>
		</div>
	</div>
	<!-- 顶部 end -->

	<span class="blank18"></span>
	<div class="page">		
		<form id="auth-index" action="./switchPeriod" method="post">
			<div class="loginleft">
				<div class="new_item">
				<c:if test="${result=='failure'}">
					<span class="onError">错误</span>
					</c:if>
					年份：<select id="year" name="year">
						 <%
						 int currentYear = Calendar.getInstance().get(Calendar.YEAR);
						 for(int i=0; i<10; i++){
							 if(currentYear == (2010+i)){
								out.println("<option value=\""+ (2010+i)+"\" selected>"+(2010+i)+"</option>");
							 }else{
							 	out.println("<option value=\""+ (2010+i)+"\">"+(2010+i)+"</option>");
							 }
						 }
						 %>						
						</select>					
				</div>
				<div class="new_item">
				<%
						Configuration cfg = Configuration.getInstance();
						
						%>
						季度：<select id="period" name="period">
						
						 	<option value="0">春季[<%=cfg.getPeriod1Start() %>月 - <%=cfg.getPeriod1End() %>月]</option>
    						<option value="1" selected>秋季[<%=cfg.getPeriod2Start() %>月 - <%=cfg.getPeriod2End() %>月]</option>					
						</select>
					
				</div>
				<div class="loginbtn">
					<input type="submit" value="" class="bigloginput" name="">
				</div>
				
			</div>
			<div class="loginright">
				<img src="../images/nongji001.png" width="740px"></img>
			</div>
			
		</form>
	</div>
<c:import url="./common/footer.jsp"></c:import>
</body>
</html>
