<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Integer count = (Integer) request.getAttribute("count");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
<title>入庫処理</title>
</head>
<body>
	<div class="main">
		<div class="conf">
			<h1><%=count%>件の入庫処理を完了しました
			</h1>
			<p>
				<a href="/PMtool/log/returnMenu" class="subm">メニューへ</a>
			</p>
		</div>
	</div>
</body>
</html>