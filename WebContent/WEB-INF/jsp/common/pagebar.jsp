<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String id = request.getParameter("id");
if(id != null){
	id = "&id="+id;
}else{
	id = "";
}
%>
<div style="text-align:right">
共计${totalPage }页,${totalCount }项.
<a href='?page=1<%=id%>'>首页</a> 
<c:forEach var="currentPage" begin="1" end="${totalPage }" step="1">
      <a href="?page=${currentPage}<%=id%>">${ currentPage}</a>
    </c:forEach>

<a href="?page=${totalPage}<%=id%>">末页</a></div>