<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="../js/city.js"></script>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在省：</div>
	<div class="bdmain">					
		<select name="province10" id="province10">
			<option value=''>------省份------</option>
		</select>
	</div>
	<input type="hidden" name="province" id="province">
	<input type="hidden" name="provinceCode" id="provinceCode">
</div>
<div class="new_item">
	<div class="tit">
		<em>*</em>所在市：</div>
	<div class="bdmain">					
		<select name="city10" id="city10">
			<option value=''>------市------</option>
		</select>
	</div>
	<input type="hidden" name="city1" id="city1">
	<input type="hidden" name="zipcode" id="zipcode">
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
 				$('#province10').empty();
//  				$("<option value=''>------省份------</option>")
// 							.appendTo("#province10");
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].text + "</option>")
							.appendTo("#province10"); //把返回的数组添加到另一个下拉框	
				}
				$("#province10").val('${param.province}'); 
				$('#province10').change();
			}, "json"); 
		
		$('#province10').change(function(){
			var province=$('#province10').val();
			//$("#province").val($('#province10').find("option:selected").text()); 
			
			$.get("../city/"+province+"/cities", {}, function(data) {	
 				$('#city10').empty();
//  				$("<option value=''>------市------</option>")
// 							.appendTo("#city10");
				for ( var i = 0; i < data.length; i++) {
					$("<option value='"+data[i].id+"'>" + data[i].text + "</option>")
							.appendTo("#city10"); //把返回的数组添加到另一个下拉框	
				}
				$("#city10").val('${param.city1}'); 
				$('#city10').change();
			}, "json"); 
		}).change();
		
		$('#city10').change(function(){
			var zipcode=$('#city10').val();
			//(zipcode);
			$("#city1").val($('#city10').find("option:selected").text()); 
			$("#zipcode").val(zipcode); 
		}).change();
		
		//alert($("#zipcode"));
		
	});
</script>