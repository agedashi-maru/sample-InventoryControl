<%@page import="model.ProductJB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Integer count = (Integer) session.getAttribute("count");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
<title>新規登録結果</title>
</head>
<body>
	<div class="main">
		<div class="conf">
			<h1><%=count%>件のデータを新規登録しました
			</h1>
			<p class="menu">
				<a href="/PMtool/log/returnMenu">メニューへ</a>
			</p>
		</div>
	</div>
</body>
</html>