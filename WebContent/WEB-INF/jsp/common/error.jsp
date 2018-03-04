<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.shiro.SecurityUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% Exception ex = (Exception)request.getAttribute("exception"); %>
<H2>Exception: <%= ex.getMessage()%></H2>
<P/>
<% ex.printStackTrace(new java.io.PrintWriter(out)); %> 