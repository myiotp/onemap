<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
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
</head>
<body>
		<c:import url="../common/top.jsp">
	<c:param name="memuid" value="1"></c:param></c:import>
	<span class="blank18"></span>
<div class="page">
	<c:import url="../common/left.jsp">
	<c:param name="memuid" value="1"></c:param>
	</c:import>
<!-- 列表内容 begin -->
	<div class="container">
		    	<div class="connav">
			       		» <a href="./editlist">用户管理</a> &gt;    	</div>
		    	<!--  主体   start -->
		    	
<form name="form1" id="form1" action="./updateuser" method="post">
<input type="hidden" name="id" value="${currentObj.id }"/>
<input type="hidden" name="actiontype" value="${currentObj.actiontype }"/>
<input type="hidden" id="oldrole" name="oldrole" value="${currentObj.approverole }"/>
<div class="userinfolist">
<h3 class="navbig"><span class="navleft"><em class="ico"></em><c:choose><c:when test="${currentObj ==null }">添加</c:when>
<c:otherwise>修改</c:otherwise></c:choose>用户信息</span></h3>
     <div class="new_item">
		<div class="tit"><em>*</em>用户名：</div>
		<div class="bdmain">${currentObj.username }</div><div class="new_plus"><span id="usernameTip"></span></div>
    </div>
    <c:choose>
        <c:when test="${currentObj.actiontype == '100' }">
            <div class="new_item">
		        <div class="tit"><em>*</em>审批角色：</div>
		        <div class="bdmain">
		             <select name="approverole" id="approverole">
		                <option value="0">操作员</option>
		                <option value="1">副经理</option>
		                <option value="2">经理</option>
		              </select>
		         
		        </div><div class="new_plus"><span id="passwordTip"></span></div>
		    </div>
        </c:when>
        <c:when test="${currentObj.actiontype == '200' }">
            <div class="new_item">
                <div class="tit"><em>*</em>修改密码：</div>
                <div class="bdmain">
                     <label onmouseout="this.className='input_out'" onmousemove="this.className='input_move'" onblur="this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onfocus="this.className='input_on';this.onmouseout=''" class="input_out">
		                <input type="password" id="password" name="password" value="">
		            </label>
                 
                </div><div class="new_plus"><span id="passwordTip"></span></div>
            </div>
        </c:when>
        <c:when test="${currentObj.actiontype == '300' }">
            <div class="new_item">
                <div class="tit"><em>*</em>是否内部员工：</div>
                <div class="bdmain">
                     <select name="isinternal" id="isinternal">
                        <option value="0">否</option>
                        <option value="1">是</option>
                      </select>
                 
                </div><div class="new_plus"><span id="passwordTip"></span></div>
            </div>
        </c:when>
    </c:choose>
    
     
    
    
</div>
<div class="addtoadsub">
	<input type="submit" class="subglobal sub1" value="提交" name="btnSubmit" id="btnSubmit">
	<input type="reset" class="escglobal sub2" value="重置" name="btnSubmit" id="btnSubmit">       
</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	var oldrole=$("#oldrole").val();	
	$("#approverole").val(oldrole); 
});
</script>
</div>
</div>
<c:import url="../common/footer.jsp"></c:import>
</body>
</html>
