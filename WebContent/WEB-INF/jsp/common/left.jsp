<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
Object obj = request.getSession().getAttribute("approverole");
System.out.println("approverole:" + obj);
String approverole =(obj==null)?"0":obj.toString();
%>
<!-- 左菜单 begin -->
<div class="sidebar">
	<ul class="column">
		<c:choose>
			<c:when test="${param.memuid==0}">
				<li class="wzymgl" id=""><a href="../welcomePage/view2"><em></em>地理信息图</a></li>
				<li class="wzymgl" id=""><a href="../welcomePage/zuowufenbu"><em></em>遥感图</a></li>
				<li class="wzymgl" id=""><a href="../welcomePage/nongjifenbu"><em></em>农机分布图</a></li>
				<!-- <li class="wzymgl" id=""><a href="../welcomePage/zuowufenbu"><em></em>作物分布图</a></li> -->											
			</c:when>
			<c:when test="${param.memuid==1}">
				
				<ul class="column">
				<li class="group open" id=""><a href="javascript:void(0);"><em></em>基本信息管理</a></li>	
				<!-- 
				<li class="wzymgl" id=""><a href="../site/editlist"><em></em>合作社信息</a></li>			
				<li class="wzymgl" id=""><a href="../landblock/editlist"><em></em>地块管理</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/editlist"><em></em>农机管理</a></li>
				            
                <li class="wzymgl" id=""><a href="../fertilizertype/editlist"><em></em>肥料类型管理</a></li>
                <li class="wzymgl" id=""><a href="../pesticidetype/editlist"><em></em>农药类型管理</a></li>   
				 -->
				<li class="wzymgl" id=""><a href="../landtype/editlist"><em></em>车辆类型管理</a></li>
				<li class="wzymgl" id=""><a href="../croptype/editlist"><em></em>货箱长度种类管理</a></li>
				<li class="wzymgl" id=""><a href="../seedtype/editlist"><em></em>付款方式管理</a></li>
	            <shiro:hasAnyRoles name="admin">  
                    <li class="wzymgl" id=""><a href="../user/editlist"><em></em>用户管理</a></li>  
                </shiro:hasAnyRoles>
                <li class="wzymgl" id=""><a href="../publication/editlist"><em></em>新闻资讯管理</a></li>
                        
				</ul>
				
			</c:when>
			
			<c:when test="${param.memuid==2}">	
			 <%if(approverole.equals("0")) {%>
                 <li class="wzymgl" id=""><a href="../managementrecord/editlist?approvelevel=0"><em></em>待审批交易列表</a></li>
             <%}%>	
			 <%if(approverole.equals("1")) {%>
			     <li class="wzymgl" id=""><a href="../managementrecord/editlist?approvelevel=4"><em></em>副经理审批交易列表</a></li>
			 <%}%>	
			 <%if(approverole.equals("2")) {%>
                 <li class="wzymgl" id=""><a href="../managementrecord/editlist?approvelevel=5"><em></em>经理审批交易列表</a></li> 
             <%}%>
			 
			 <li class="wzymgl" id=""><a href="../managementrecord/allconfirmed?approvelevel=-1"><em></em>已确认交易列表</a></li>   	
			 <li class="wzymgl" id=""><a href="../managementrecord/alleditlist"><em></em>全部交易列表</a></li> 
			</c:when>
			
			<c:when test="${param.memuid==3}">
				<!-- 
				<li class="wzymgl" id=""><a href="../managementrecord/chartsearch"><em></em>农作物生产过程回溯</a></li>				
				<li class="wzymgl" id=""><a href="../farmmachinerygps/editlist"><em></em>农机GPS信息管理</a></li> -->
				<!--<li class="wzymgl" id=""><a href="../managementrecord/cropsearch"><em></em>农作物种植分布</a></li>-->
				
			</c:when>
			
			<c:when test="${param.memuid==4}">
			<!-- 
				<li class="wzymgl" id=""><a href="../farmmachinery/njyilan"><em></em>农机一览</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/chartsearch"><em></em>农机查询</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/track"><em></em>轨迹回放</a></li>
				<li class="wzymgl" id=""><a href="../farmmachinery/barrier"><em></em>电子围栏</a></li> 
		-->
			</c:when>
			
			<c:when test="${param.memuid==5}">		
			<!-- 
				<li class="wzymgl" id=""><a href="../yieldprediction/charts"><em></em>产量预测一览</a></li>				
				<li class="wzymgl" id=""><a href="../yieldprediction/chartsearch"><em></em>产量预测图表</a></li>		
			 -->     			
			</c:when>
			<c:when test="${param.memuid==6}">
			    <li class="wzymgl" id=""><a href="../purchasepesticidetype/editlist"><em></em>实名认证审核</a></li>
                <li class="wzymgl" id=""><a href="../purchasefertilizertype/editlist"><em></em>驾驶证认证审核</a></li>
                <li class="wzymgl" id=""><a href="../purchaseseedtype/editlist"><em></em>行驶证认证审核</a></li>
            </c:when>    
			<c:when test="${param.memuid==7}">
									
			</c:when>
			<c:when test="${param.memuid==8}">
							
			</c:when>
			<c:when test="${param.memuid==9}">
								
			</c:when>
		</c:choose>
	</ul>
</div>
<!-- 左菜单 end -->
<script type="text/javascript">
$(function(){
var memuid=${param.memuid};
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