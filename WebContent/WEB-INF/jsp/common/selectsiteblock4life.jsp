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

		<select name="cooperativeId" id="site">
			
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
			$("<option value='0'>------请选择------</option>")
						.appendTo("#site");
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].cooperativeName +"("+data[i].cooperativeNumber + ")</option>")
						.appendTo("#site"); //把返回的数组添加到另一个下拉框

			}
			$("#site").val('${param.cooperativeId}'); 
			
			
		}, "json"); //json格式自动解析数组
		
		
		$('#site').change(function() {   
		   var site = $(this).val();   
		   $.post("../landblock/json/blocks/site/"+site , {}, function(data) {

			$('#blockId').empty();
			$("<option value='0'>------请选择------</option>")
						.appendTo("#blockId");
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].blockNumber + "</option>")
						.appendTo("#blockId"); //把返回的数组添加到另一个下拉框

			}
			$("#blockId").val('${param.blockId}'); 
			
			
		}, "json"); //json格式自动解析数组
	   });
	    
		$('#blockId').change(function() {   
		   var blockId = $(this).val();  	
	   $.get("../croplifecycle/block/"+blockId+"/crops", {}, function(data) {

			$('#cropTypeId').empty();
			for ( var i = 0; i < data.length; i++) {
				$("<option value='"+data[i].id+"'>" + data[i].typename + "</option>")
						.appendTo("#cropTypeId"); //把返回的数组添加到另一个下拉框

			}
			$("#cropTypeId").val('${param.cropTypeId}'); 
			
			
		}, "json"); //json格式自动解析数组
		}); 	
	});
</script>

<div class="new_item">
	<div class="tit">
		<em>*</em>地块编号：</div>
	<div class="bdmain">

		<select name="blockId" id="blockId">
			
		</select>

	</div>
</div>

<div class="new_item">
	<div class="tit">
		<em>*</em>农作物类型：</div>
	<div class="bdmain">

		<select name="cropTypeId" id="cropTypeId">
			
		</select>

	</div>
</div>

