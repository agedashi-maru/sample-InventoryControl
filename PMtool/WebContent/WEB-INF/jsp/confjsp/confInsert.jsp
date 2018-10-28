<%@page import="model.ProductJB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<ProductJB> insertjbList = (List<ProductJB>) session.getAttribute("insertjbList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
<title>追加確認</title>
</head>
<body>
	<div class="main">
		<div class="conf">
			<h2>以下の項目を追加します</h2>
			<table>
				<tr>
					<th>商品名</th>
					<th>タイプ</th>
					<th>グループ</th>
					<th>在庫数</th>
				</tr>
				<%
					for (ProductJB jb : insertjbList) {
				%>
				<tr>
					<td><%=jb.getItem()%></td>
					<td><%=jb.getKind()%></td>
					<td><%=jb.getGroup()%></td>
					<td><%=jb.getStock()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<p>
				<a class="subm" href="/PMtool/log/InsertMain">実行</a>
			</p>
			<p class="menu">
				<a href="/PMtool/log/returnMenu">メニューへ</a>
			</p>
		</div>
	</div>
</body>
</html>