<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在仓库：</div>
	<div class="bdmain">

		<select name="warehouseId" id="warehouse">
			
		</select>

	</div>
</div>
<script type="text/javascript">

	$(function() {

		$('#site').change(function(){
			var siteId=$('#site').val();
		$.post("../warehouse/listJson", {siteId:siteId}, function(data) {

			$('#warehouse').empty();
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].name + "</option>")
						.appendTo("#warehouse"); //把返回的数组添加到另一个下拉框

			}
			$("#warehouse").val('${param.warehouseId}'); 
			$('#warehouse').change();
		}, "json"); 
		}).change();
	});
	
</script>