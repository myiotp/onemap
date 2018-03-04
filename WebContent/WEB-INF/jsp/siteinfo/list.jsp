<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧物流平台系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
<link rel="stylesheet" href="../js/jstree/themes/default/style.min.css" />
	 
<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script src="../js/jstree/jstree.min.js"></script>
<style>

.container {
    border-left: 1px solid #e2e2e2;
    float: left;
    padding-left: 20px;
    width: 900px;
}
</style>
</head>
<body>
	

	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="8"></c:param></c:import>
	<span class="blank18"></span>
	<div class="page">
		<!-- 左菜单 begin -->
		<div style="width:350px;float:left">
			<div id="sitetree"></div>
		</div>
		<!-- 左菜单 end -->
		
		
		<!-- 列表内容 begin -->
		<!--  主体   start -->
		<div id="data">
			<div class="content code" style="display:none;"><textarea id="code" readonly="readonly"></textarea></div>
			<div class="content folder" style="display:none;"></div>
			<div class="content image" style="display:none; position:relative;"><img src="" alt="" style="display:block; position:absolute; left:50%; top:50%; padding:0; max-height:90%; max-width:90%;" /></div>
			<div class="content default" style="text-align:center;"></div>
		</div>

	</div>	
	<c:import url="../common/footer.jsp"></c:import>	
<script type="text/javascript">
	
	// data format demo
	$('#sitetree')
	    .on("changed.jstree", function (e, data) {
			if(data && data.selected && data.selected.length) {
				var _d = data.selected[0];
				if(_d.indexOf("city_") == 0){
					var _node = data.instance.get_node(data.selected[0]);
					//(_node);
					$('#data .content').hide();
					$('#data .default').html('').show();
				}else if(_d.indexOf("info") == 0){
					var _node = data.instance.get_node(data.selected[0]);
					//(_node);
					$('#data .content').hide();
					$('#data .default').html('').show();
				}else{
					$.get('../siteinfo/show?id=' + data.selected[0], function (d) {
						$('#data .default').html(d).show();
					});
				}
			
			}
			else {
				$('#data .content').hide();
				$('#data .default').html('').show();
			}
		})
		.jstree({
		'core' : {
			'multiple' : false,
			'data' : {
				"url" : "../siteinfo/tree",
				"data" : function (node) {
					return { "id" : node.id };
				}
			}
		},
		'plugins' : ['state','wholerow']
	});
	
		$(function(){
		var memuid=8;
		var objLink=$("a[href*='memuid="+memuid+"']");
		objLink.parent().attr('class',"current");
		$('li.group').click(function(){
			if($(this).hasClass('open')){
				$(this).nextAll().hide();
				$(this).removeClass('open');
			}else{
				$(this).nextAll().show();
				$(this).addClass('open');
			}
		});
		$('li.group').each(function(){
			var iscurrent=false;
			$(this).nextAll().each(function(){
				if($(this).html().indexOf(curl)>0){
					iscurrent=true;
				}
			});
			!iscurrent&&$(this).click();
			
		});
		
		});
		</script>
</body>
</html>
