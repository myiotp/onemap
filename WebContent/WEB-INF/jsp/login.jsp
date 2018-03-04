<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="../ng-table/css/bootstrap.min.css"/>
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script src="../ng-table/js/angular.min.js"></script>
<script src="../ng-table/js/angular-resource.min.js"></script>
<script src="../ng-table/js/angular-mocks.js"></script>
<script src="../ng-table/ng-table.js"></script>
<link rel="stylesheet" href="../ng-table/ng-table.css"/>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/login.css" rel="stylesheet" type="text/css" />
<link href="../js/ngtable/loadingContainer.css" rel="stylesheet" type="text/css" />
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
<body class="bgimage">
	<!-- 顶部 -->
	<div class="topwrap">
	   <div class="logo1"></div>
	   <div class="logo"></div>
	</div>
	<!-- 顶部 end -->

	<div class="page">		
		<form id="auth-index" action="./loginCheck" method="post" class="loginleft">
		        <div class="title">登入系统</div>
		        <c:if test="${result=='failure'}">
					<div><span class="onError">账号或密码错误</span></div>
				</c:if>
				<div class="new_item">
					<div class="tit">账号：</div>
					<div class="bdmain">
						<input type="text" class="biginput" id="user_name"
							name="username" value="admin"
							onfocus="this.className='input_on';this.onmouseout=''"
							onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
							onmousemove="this.className='input_move'"
							onmouseout="this.className='input_out'">
					</div>
					<div class="new_plus">
						<span id="user_nameTip"></span>
					</div>
				</div>
				<div class="new_item">
					<div class="tit">密码：</div>
					<div class="bdmain">
						<input type="password" class="biginput" id="user_password"
							name="password"
							onfocus="this.className='input_on';this.onmouseout=''"
							onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};"
							onmousemove="this.className='input_move'"
							onmouseout="this.className='input_out'">
					</div>
					<div class="new_plus">
						<span id="user_passwordTip"></span>
					</div>
				</div>
				<div class="loginbtn">
					<input type="submit" value="登录" class="bigloginput" name="">
				</div>
			
		</form>
	</div>
<div class="mastfoot">智慧物流平台系统 2018-2020<br/>© 版权所有</div>
</body>
</html>
