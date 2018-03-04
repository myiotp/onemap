<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>合作社管理</title>
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
	
</head>
<body><script type="text/javascript">
	$(function() {
		$( "#tabs" ).tabs();
	  });
	
	</script>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	
<!-- 列表内容 begin -->
	<div class="container">
		    	
		    	<!--  主体   start -->
		    	
<input type="hidden" name="id" value="${currentObj.id }"/>

  	<div id="tabs">
				<ul>
					<li><a href="#container">合作社基本信息</a></li>
					<li><a href="#container5">合作社农机一览</a></li>
					<li><a href="#container2">文字图片描述</a></li>
					<li><a href="#container3">企业资质</a></li>
					<li><a href="#container4">认证信息</a></li>
				</ul>
				
					<div id="container"	    style="min-width: 800px;">
					<div class="userinfolist"> <div class="new_item">
		<div class="tit"><em>*</em>名称：</div>
		<div class="bdmain">
		
			${currentObj.cooperativeName }
			
		</div><div class="new_plus"><span id="cooperativeNameTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>负责人姓名：</div>
		<div class="bdmain">
		
			${currentObj.owner }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>性质：</div>
		<div class="bdmain">
		${currentObj.character }
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>规模：</div>
		<div class="bdmain">
		
		${currentObj.size }
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>地址：</div>
		<div class="bdmain">
		
		${currentObj.address }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>网址：</div>
		<div class="bdmain">
		
			${currentObj.website }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>法定代表人：</div>
		<div class="bdmain">
		
			${currentObj.legalperson }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>联系电话：</div>
		<div class="bdmain">
		
			${currentObj.telephone }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>电子邮箱：</div>
		<div class="bdmain">
		
			${currentObj.email }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>QQ：</div>
		<div class="bdmain">
		
		${currentObj.qq }
			
		</div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>微信：</div>
		<div class="bdmain">
		
			${currentObj.wechat }
			
		</div><div class="new_plus"><span id="postcodeTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>X坐标：</div>
		<div class="bdmain">
		
			${currentObj.gpsx }
			
		</div><div class="new_plus"><span id="gpsxTip"></span></div>
    </div>
    <div class="new_item">
		<div class="tit"><em>*</em>Y坐标：</div>
		<div class="bdmain">
		
			${currentObj.gpsy }
			
		</div><div class="new_plus"><span id="gpsyTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>备注：</div>
		<div class="bdmain">
		
			${currentObj.memo }
			
		</div><div class="new_plus"><span id="contactTip"></span></div>
    </div>
        <div class="new_item">
		<div class="tit"><em></em>采集人：</div>
		<div class="bdmain">
		
			${currentObj.collector }
			
		</div>
    </div>
	  <div class="new_item">
		<div class="tit"><em></em>采集时间：</div>
		<div class="bdmain">
		
		<fmt:formatDate value="${currentObj.collecttime }" type="date" pattern="yyyy/MM/dd"/>
			
		</div>
    </div>
    </div>
					</div>
					<div id="container2"    style="min-width: 800px;">${currentObj.description }</div>
					<div id="container3"	style="min-width: 800px;">${currentObj.qualification }</div>
				    <div id="container4"	style="min-width: 800px;">
						<div class="conborder getcode">
							<div class="onlinelist">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
							<tr>
								<!-- <th><span>ID</span></th> -->
								<th><span>认证类别</span></th>
								<th><span>认证时间</span></th>				
								<th><span>有效期限</span></th>
								<th><span>认证机构</span></th>
								<th><span>证书编号</span></th>
								<th><span>证书图片资料</span></th>								
							</tr>
									  
									  <c:forEach items="${resultList}" var="obj"  varStatus="varStatus">
									  
										<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
								<!-- <td >${site.id}</td> -->
								<td >${obj.certtype}</td>
								<td ><fmt:formatDate value="${obj.certtime}" pattern="yyyy年MM月dd日" /></td>
								<td >${obj.validtime}</td>
								<td >${obj.certorganization}</td>
								<td >${obj.certnumber}</td>
								<td ><a title="详细" class="getglobal icoglobal" href ="#" onclick="showDialog(${obj.id })"></a></td>
								
							</tr>
							</c:forEach>
						</table></div></div>
					</div>
					
				<div id="container5" style="min-width: 800px;">
				<div class="conborder getcode">
					<div class="onlinelist">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="border-collapse: collapse">
							<tr>
								<!-- <th><span>ID</span></th> -->
								<th><span>农机编号</span></th>
								<th><span>农机类型</span></th>
								<th><span>购买日期</span></th>
								<th><span>农机状态</span></th>
								<th><span>所有人</span></th>
								<th><span>详细</span></th>
								<th><span>定位</span></th>
							</tr>

							<c:forEach items="${resultList2}" var="obj" varStatus="varStatus">

								<tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
									<!-- <td >${site.id}</td> -->
									<td>${obj.machineryNumber}</td>									
									<td>${obj.machineryType}</td>
									<td><fmt:formatDate value="${obj.purchasetime}"
											pattern="yyyy年MM月dd日" /></td>
									<td>${obj.status}</td>
									<td>${obj.ownerName}</td>
									<td data-title="'详细'"><a title="详细"
											class="getglobal icoglobal" href="../farmmachinery/show?id=${obj.id}"
											target="_blank"></a></td>
									<td data-title="'定位'"><a title="定位"
										class="getglobal icoglobal" href="../farmmachinery/show2?id=${obj.id}"
										target="_blank"></a></td>	

								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			</div>	
			
			
    
     
   
	
    
</div>


</div>

<c:import url="../common/footer.jsp"></c:import>
<div id="dialog-showTrail" title="认证信息">
	<div class="content_nr">
        <div class="ny_main">
          <div id="certtype"></div>
          <div id="certtime"></div>
		  <div id="validtime"></div>
		  <div id="certorganization"></div>
          <div id="certnumber"></div>
		  证书图片资料：<br><div id="certdescription"></div>
             
        </div>
    </div>
</div>
<script type="text/javascript">
	function showDialog(id) {
		var url = encodeURI('../sitecert/id/' + id);
	
	// Get the CSV and create the chart
	$
			.getJSON(
					url,
					function(responsedata) {
					//(responsedata);
		$('#certtype').html("认证类别："+responsedata.certtype);
		$('#certtime').html("认证时间："+responsedata.certtime);
		$('#validtime').html("有效期限："+responsedata.validtime);
		$('#certorganization').html("认证机构："+responsedata.certorganization);
		$('#certnumber').html("证书编号："+responsedata.certnumber);
		$('#certdescription').html(responsedata.certdescription);
		$('#dialog-showTrail').dialog('open');
		return false;
					
	});
		
	}
	
</script>	
<script type="text/javascript">	
	var showTrailDialog = $("#dialog-showTrail").dialog({
		autoOpen: false,
		modal: true,
		height: 600,
		width: 900,
		buttons: {
			"关闭": function () {
				$(this).dialog("close");
			}
		}
	});	
</script>
</body>
</html>
