<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils,com.onemap.domain.User,com.onemap.Configuration" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>  
<!-- 顶部 -->
<div class="topwrap navbar-fixed-top">
	<div class="logo1"></div>
	 <div class="logo"></div>
	<div class="topinfo"><span class="loginout">
	</div>
	<div class="menupage">
		<ul class="topmenu">
			<li><a href="../topmemu/list?memuid=0">总览</a></li>	
			<li><a href="../topmemu/list?memuid=9">新闻资讯</a></li>	
			<li><a href="../topmemu/list?memuid=7">货源信息</a></li>	
			<li><a href="../topmemu/list?memuid=3">车源信息</a></li>
			
			
<%Object checkRoleObj = session.getAttribute("admin");
boolean isAdmin = checkRoleObj!=null && Boolean.parseBoolean(checkRoleObj.toString());
Object obj = request.getSession().getAttribute("approverole");
String approverole =(obj==null)?"0":obj.toString();
if(isAdmin){
%>
			<li><a href="../topmemu/list?memuid=2">交易信息</a></li>
			<li><a href="../topmemu/list?memuid=4">车辆年检</a></li>
			<li><a href="../topmemu/list?memuid=5">车辆保险</a></li>
			<li><a href="../topmemu/list?memuid=6">审核管理</a></li>
			<li><a href="../topmemu/list?memuid=1">系统管理</a></li>
						
<%} else { %>   
	<%if(obj!=null) {%>
        <li><a href="../topmemu/list?memuid=2">交易信息</a></li>
        <li><a href="../topmemu/list?memuid=4">车辆年检</a></li>
        <li><a href="../topmemu/list?memuid=5">车辆保险</a></li>
        <li><a href="../topmemu/list?memuid=6">审核管理</a></li>
    <%}%>


<%}  %>	
        <li><a href="../topmemu/list?memuid=8">使用帮助</a></li>
        <%
	    User currentuser = (User)SecurityUtils.getSubject().getPrincipal();
	    if(currentuser != null){
	    %>
	    <li><a href="../user/logout">退出登录</a></li>
	    <!-- <li><a href="#">[<%=currentuser.getUsername()%>]</a></li> -->
	    <%  
	    }else{
	    %>
	    <li><a href="../user/login">登录</a></span></li>
	    <%  
	    }   
	    %>
  
             	
			
		</ul>
	</div>	
</div><!-- 顶部 end -->