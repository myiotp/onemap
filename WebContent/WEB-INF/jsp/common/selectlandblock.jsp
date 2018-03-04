<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>地块编号：</div>
	<div class="bdmain">

		<select name="blockId" id="blockId">
			
		</select>

	</div>
	<div class="new_plus">
		<span id="blockIdTip"></span>
	</div>
</div>
<script type="text/javascript">

	$(function() {
		$.ajaxSetup({
			  async: false
			  }); 
		$.post("../landblock/listJson", {}, function(data) {

			$('#blockId').empty();
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].blockNumber + "</option>")
						.appendTo("#blockId"); //把返回的数组添加到另一个下拉框

			}
			if(data.length > 0){
				//('${param.blockId}' == '');
				if('${param.blockId}' == ''){
					$("#blockId").val(data[0].id); 
				}else{
					$("#blockId").val('${param.blockId}'); 
				}
			}
		}, "json"); //json格式自动解析数组
		
		
	});
</script>