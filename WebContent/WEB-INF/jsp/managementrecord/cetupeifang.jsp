<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>测土配方结果</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="../ng-table/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="../jquery-ui-1.11.1.custom/jquery-ui.min.css">

<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>


<script src="../ng-table/js/angular.min.js"></script>
<script src="../ng-table/js/angular-resource.min.js"></script>
<script src="../ng-table/js/angular-mocks.js"></script>
<script src="../ng-table/ng-table.js"></script>
<link rel="stylesheet" href="../ng-table/ng-table.css" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../editablegrid-2.0.1/editablegrid-2.0.1.css">
<style>	
	table.testgrid { border-collapse: collapse; border: 1px solid #CCB; width: 100%; }
	table.testgrid td, table.testgrid th { padding: 5px; border: 1px solid #E0E0E0; }
	table.testgrid th { background: #E5E5E5; text-align: left; }
	input.invalid { background: red; color: #FDFDFD; }
</style>
</head>
<body>
	
	
	<span class="blank18"></span>
	<div class="page">
		
		<!-- 列表内容 begin -->
		<div class="container">
			
			<!--  主体   start -->

			<div class="conborder getcode">
				<div class="onlinelist">
					
							<input type="hidden" name="id" value="${currentObj.id }" />
                            <input type="hidden" name="var1" value="${currentObj.var1 }" />							
							<div id="tablecontent"></div>
								
				</div>
			</div>
		</div>
		<c:import url="../common/footer.jsp"></c:import>

		<script src="../editablegrid-2.0.1/editablegrid-2.0.1.js"></script>
		<script>
		    var editableGrid = new EditableGrid("DemoGridJsData");
			window.onload = function() {

				// this approach is interesting if you need to dynamically create data in Javascript 
				var metadata = [];
				metadata.push({ name: "project", label: "项目", datatype: "string", editable: false});
				metadata.push({ name: "test1", label:" ", datatype: "string", editable: false});
				metadata.push({ name: "test2", label: "土壤测试结果", datatype: "double(, 2, dot, comma, 0, n/a)", editable: true});
				metadata.push({ name: "test3", label: " ", datatype: "string", editable: false});
				metadata.push({ name: "level", label: "养分水平", datatype: "string", editable: true});
				metadata.push({ name: "result1", label: "推荐施肥(单位)", datatype: "string", editable: false});
				metadata.push({ name: "result2", label: "推荐施肥(公斤/亩)", datatype: "double(, 2, dot, comma, 0, n/a)", editable: true});
				
				// a small example of how you can manipulate the object in javascript
				metadata[4].values = {"l":"低","m":"中","h":"高"};
				
				var data = [];
				var testvar1=${currentObj.var1 };
				var testdata = testvar1.test;
				data.push({id: 1, values: {"project":"有机质","test1":"OM","test2": testdata[0].v2,"test3":"%","level":testdata[0].v4,"result1":"","result2":testdata[0].v6}});
				data.push({id: 2, values: {"project":"铵态氮","test1":"NH4-N","test2": testdata[1].v2,"test3":"mg/L","level":testdata[1].v4,"result1":"","result2":testdata[1].v6}});
				data.push({id: 3, values: {"project":"硝态氮","test1":"N03-N","test2": testdata[2].v2,"test3":"mg/L","level":testdata[2].v4,"result1":"氮","result2":testdata[2].v6}});
				data.push({id: 4, values: {"project":"磷","test1":"P","test2": testdata[3].v2,"test3":"mg/L","level":testdata[3].v4,"result1":"磷(P205)","result2":testdata[3].v6}});
				data.push({id: 5, values: {"project":"钾","test1":"K","test2": testdata[4].v2,"test3":"mg/L","level":testdata[4].v4,"result1":"钾(K20)","result2":testdata[4].v6}});
				data.push({id: 6, values: {"project":"钙","test1":"Ca","test2": testdata[5].v2,"test3":"mg/L","level":testdata[5].v4,"result1":"钙(CaC03)","result2":testdata[5].v6}});
				data.push({id: 7, values: {"project":"镁","test1":"Mg","test2": testdata[6].v2,"test3":"mg/L","level":testdata[6].v4,"result1":"镁(MgC03)","result2":testdata[6].v6}});
				data.push({id: 8, values: {"project":"硫","test1":"S","test2": testdata[7].v2,"test3":"mg/L","level":testdata[7].v4,"result1":"硫","result2":testdata[7].v6}});
				data.push({id: 9, values: {"project":"铁","test1":"Fe","test2": testdata[8].v2,"test3":"mg/L","level":testdata[8].v4,"result1":"铁","result2":testdata[8].v6}});
				data.push({id: 10, values: {"project":"铜","test1":"Cu","test2": testdata[9].v2,"test3":"mg/L","level":testdata[9].v4,"result1":"铜","result2":testdata[9].v6}});
				data.push({id: 11, values: {"project":"锰","test1":"Mn","test2": testdata[10].v2,"test3":"mg/L","level":testdata[10].v4,"result1":"锰","result2":testdata[10].v6}});
				data.push({id: 12, values: {"project":"锌","test1":"Zn","test2": testdata[11].v2,"test3":"mg/L","level":testdata[11].v4,"result1":"锌","result2":testdata[11].v6}});
				data.push({id: 13, values: {"project":"硼","test1":"B","test2": testdata[12].v2,"test3":"mg/L","level":testdata[12].v4,"result1":"硼","result2":testdata[12].v6}});
				data.push({id: 14, values: {"project":"酸碱度","test1":"pH","test2": testdata[13].v2,"test3":"","level":testdata[13].v4,"result1":"","result2":testdata[13].v6}});
				data.push({id: 15, values: {"project":"交换性酸","test1":"AA","test2": testdata[14].v2,"test3":"cmol/L","level":testdata[14].v4,"result1":"","result2":testdata[14].v6}});
				data.push({id: 16, values: {"project":"钙镁比","test1":"Ca/Mg","test2": testdata[15].v2,"test3":"","level":testdata[15].v4,"result1":"石灰","result2":testdata[15].v6}});
				data.push({id: 17, values: {"project":"镁钾比","test1":"Mg/K","test2": testdata[16].v2,"test3":"","level":testdata[16].v4,"result1":"","result2":testdata[16].v6}});
					
				editableGrid.load({"metadata": metadata, "data": data});
				editableGrid.renderGrid("tablecontent", "testgrid");
			}

			function submitform()
			{
				var v2 = [];
				var v6 = [];
				var txt = "";
				for (i = 0; i < 17; i++) {
					txt += "{'index':"+i+", 'v2':"+editableGrid.getValueAt(i,2)+",'v6':"+editableGrid.getValueAt(i,6)+"}";
					if(i < 16){
					  txt+=",";
					}
				}				
				var jsondata="{'test':["+txt+"]}";				
				document.forms["form1"].var1.value=jsondata;
				//console.log(document.forms["form1"].var1.value);
				
				document.forms["form1"].submit();
			}	
		</script>
</body>
</html>
