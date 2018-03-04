<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>农作物类型：</div>
	<div class="bdmain">

		<select name="cropTypeId" id="cropTypeId">
			
		</select>

	</div>
</div>
<script type="text/javascript">

	$(function() {
		$.ajaxSetup({
			  async: false
			  }); 
		$.post("../croptype/listJson", {}, function(data) {

			$('#cropTypeId').empty();
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].cropType + "</option>")
						.appendTo("#cropTypeId"); //把返回的数组添加到另一个下拉框

			}
			$("#cropTypeId").val('${param.cropTypeId}'); 
			if(data.length > 0){
				//('${param.cropTypeId}' == '');
				if('${param.cropTypeId}' == ''){
					$("#cropTypeId").val(data[0].id); 
				}else{
					$("#cropTypeId").val('${param.cropTypeId}'); 
				}
			}
			
		}, "json"); //json格式自动解析数组
	});
</script>