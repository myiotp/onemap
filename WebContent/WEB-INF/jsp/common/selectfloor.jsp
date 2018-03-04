<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在楼层：</div>
	<div class="bdmain">

		<select name="floor" id="floor">
			
		</select>

	</div>
</div>
<script type="text/javascript">

	$(function() {

		$('#warehouse').change(function(){
			var warehouseId=$('#warehouse').val();
			
		$.post("../warehouse/getJson", {id:warehouseId}, function(data) {

			$('#floor').empty();
			for ( var i = 1; i <= data.floorCount; i++) {
				$("<option value='"+i+"'>" + i + "</option>")
						.appendTo("#floor"); //把返回的数组添加到另一个下拉框

			}
			$("#floor").val('${param.floor}'); 

		}, "json"); 
		}).change();
	});
	
</script>