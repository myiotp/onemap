<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="new_item">
	<div class="tit">
		<em>*</em>所在省：</div>
	<div class="bdmain">					
		<select name="province" id="province">
			<option value=''>------省份------</option>
		</select>
	</div>	
</div>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在市：</div>
	<div class="bdmain">					
		<select name="city1" id="city1">
			<option value=''>------市------</option>
		</select>
	</div>	
	<input type="hidden" name="zipcode" id="zipcode">
</div>
<div class="new_item">
	<div class="tit">
		<em>*</em>所属合作社：</div>
	<div class="bdmain">

		<select name="cooperativeId" id="site">
			
		</select>
		
	</div>
	<div class="new_plus"><span id="cooperativeIdTip"></span></div>
</div>
<!-- 
<div class="new_item">
	<div class="tit">
		<em>*</em>所在县：</div>
	<div class="bdmain">					
		<select name="city2" id="city2">
			<option value=''>------市、县级市、县------</option>
		</select>
		<input type="hidden" name="zipcode" id="zipcode">
	</div>
</div> -->
<script type="text/javascript">
// 	var s=["province","city1","city2"];
// 	var opt0 = ["省份","地级市","市、县级市、县"];
// 	for(i=0;i<s.length-1;i++){
// 		document.getElementById(s[i]).onchange=new Function("change("+(i+1)+")"); 
// 	}
  		
// 	change(0);
 
	$(function() {
		$.ajaxSetup({
			  async: false
			  }); 
		
		$.get("../city/%23/cities", {}, function(data) {	
 				$('#province').empty();
//  				$("<option value=''>------省份------</option>")
// 							.appendTo("#province10");
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].text + "</option>")
							.appendTo("#province"); //把返回的数组添加到另一个下拉框	
				}
				$("#province").val('${param.province}'); 
				$('#province').change();
			}, "json"); 
		
		$('#province').change(function(){
			var province=$('#province').val();
			//$("#province").val($('#province10').find("option:selected").text()); 
			
			$.get("../city/"+province+"/cities", {}, function(data) {	
 				$('#city1').empty();
//  				$("<option value=''>------市------</option>")
// 							.appendTo("#city10");
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].text + "</option>")
							.appendTo("#city1"); //把返回的数组添加到另一个下拉框	
				}
				$("#city1").val('${param.city1}'); 
				$('#city1').change();
			}, "json"); 
		}).change();
		
		$('#city1').change(function(){
			var zipcode=$('#city1').val();
			//(zipcode);			
			$("#zipcode").val(zipcode); 
			$.get("../city/"+zipcode+"/sites", {}, function(data) {

				$('#site').empty();
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].cooperativeName +"("+data[i].cooperativeNumber + ")</option>")
							.appendTo("#site"); //把返回的数组添加到另一个下拉框
	
				}
				$("#site").val('${param.cooperativeId}'); 
							
			}, "json"); //json格式自动解析数组
		}).change();
		
		//alert($("#zipcode"));
		
	});
</script>