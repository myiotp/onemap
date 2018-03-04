<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>入库单管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
<script type="text/javascript" src="../js/formValidator-4.0.1.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/formValidatorRegex.js"
	charset="UTF-8"></script>
	
	<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.23.custom.min.js" charset="UTF-8"></script>
<link href="../css/redmond/jquery-ui-1.8.23.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>

		<c:import url="../common/top.jsp">
	</c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="2"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./list">入库单管理</a> &gt; <c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>入库单    	</div>
		    	<!--  主体   start -->
		    	

<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>入库单信息</span></h3>
<form name="form1" id="form1" action="./save" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
     <div class="new_item">
		<div class="tit"><em></em>入库单号：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="number" name="number" value="${currentObj.number}" readonly="readonly" />
			</label>
			
		</div><div class="new_plus"><span id="numberTip"></span></div>
    </div>
     <div class="new_item">
		<div class="tit"><em></em>日期：</div>
		<div class="bdmain">
		
			<label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
				<input type="text" id="date" name="date" value="${currentObj.date }"  readonly="readonly" />
			</label>
			
		</div><div class="new_plus"><span id="dateTip"></span></div>
    </div>
    <div class="addtoadsub">
	<input type="submit" class="subglobal sub1" value="提交" name="btnSubmit" id="btnSubmit" />
	<input type="reset" class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit" />       
	</div>
    </form>
    <c:if test="${not empty(currentObj.id)}">
    <p><input type="button" id="addBtn" value="增加明细" class="subglobal sub1"/></p>
     <h3 class="navbig"><span class="navleft"><em class="ico"></em>入库单详细信息</span></h3>
   <div class="onlinelist"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse">
        <tr>
    			<th><span>序号</span></th>
				<th><span>烟名</span></th>
				<th><span>车辆</span></th>
				<th><span>数量</span></th>
    			<th><span>管理</span></th>
  			</tr>
  			<c:forEach items="${inOrderDetailList}" var="obj"  varStatus="varStatus">
                 <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
    			<td style="color:red;font-weight:bold;font-size:16px">${varStatus.index+1}</td>
				<td>${obj.goods.name }</td>
				<td>${obj.truck.number }</td>
				<td>${obj.amount }</td>
				
    			
    		
    			<td width=100>
    			<a title="修改" class="modifyglobal icoglobal" href ="./edit?id=${obj.id }"></a>
    			<a title="删除" class="deleteglobal icoglobal" href ="./delete?id=${obj.id }"></a>
    			</td>
  			</tr>
  			</c:forEach>
  			  		                      </table>
	</div>
    </c:if>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
			
		$("#date").formValidator({
		tipID:"dateTip",
		onShow:"",
		onFocus:"",
		onCorrect:""
	}).inputValidator({
		min:1,
		onError: "请选择日期!"
	});
	
		
		$( "#date" ).datepicker({
			showButtonPanel: true,
				dateFormat: "yy-mm-dd"
				});

				if($( "#date" ).val()==''){
					$( "#date" ).datepicker( 'setDate' , new Date()); 
				}
	 $('#addBtn').click(function(){
		 
		 window.location.href='../inorderdetail/edit?inOrderId=${currentObj.id}';
	 });

});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
