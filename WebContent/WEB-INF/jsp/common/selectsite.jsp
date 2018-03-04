<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="new_item">
	<div class="tit">
		<em>*</em>所属合作社：</div>
	<div class="bdmain">

		<select name="cooperativeId" id="site" class=".select2">
			
		</select>
		
	</div>
</div>
<script type="text/javascript">

	$(function() {
		$.ajaxSetup({
			  async: false
			  }); 
		$.post("../site/listJson", {}, function(data) {

			$('#site').empty();
			var temp = '${param.cooperativeId}';
			for ( var i = 0; i < data.length; i++) {
				if(temp == ''){
					temp = data[0].id;
				}
				$("<option value='"+data[i].id+"'>" + data[i].cooperativeName +"("+data[i].cooperativeNumber + ")</option>")
						.appendTo("#site");				
				//把返回的数组添加到另一个下拉框

			}
				
			$("#site").val(temp); 			
						
		}, "json"); //json格式自动解析数组
	});
</script>