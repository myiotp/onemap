<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在库区：</div>
	<div class="bdmain">

		<select name="regionId" id="region">
			
		</select>

	</div>
</div>
<script type="text/javascript">

	$(function() {

		$('#floor,#warehouse').change(function(){
			
			var floor=$('#floor').val();
			var warehouseId=$('#warehouse').val();
			$.post("../region/listJson", {floor:floor,warehouseId:warehouseId}, function(data) {

				$('#region').empty();
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].name + "</option>")
							.appendTo("#region"); //把返回的数组添加到另一个下拉框

				}
				$("#region").val('${param.regionId}'); 
			}, "json"); 
		}).change();
	});
	
</script>