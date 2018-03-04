<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<span class="blank12"></span>
<div class="footer">智慧物流平台系统 2018-2020<br/>© 版权所有 通达物流股份有限公司</div>
<script type="text/javascript" language="javaScript">
var curl=window.location.pathname;
curl=curl.replace('/onemap/','');
var objLink=$("a[href*='"+curl+"']");
objLink.parent().attr('id',"current");
</script>