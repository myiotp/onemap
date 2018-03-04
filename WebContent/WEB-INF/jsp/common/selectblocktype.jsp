<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>地块类型：</div>
	<div class="bdmain">

		<select name="blockTypeId" id="blockTypeId">
			
		</select>

	</div>
</div>
<script type="text/javascript">

	$(function() {
		$.ajaxSetup({
			  async: false
			  }); 
		$.post("../blocktype/listJson", {}, function(data) {

			$('#blockTypeId').empty();
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].blockType + "</option>")
						.appendTo("#blockTypeId"); //把返回的数组添加到另一个下拉框

			}
			$("#blockTypeId").val('${param.blockTypeId}'); 
			
			
		}, "json"); //json格式自动解析数组
	});
</script>