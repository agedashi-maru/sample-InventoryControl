<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c_" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
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
				<c_:forEach var="insertjbItem" items="${insertjbList}" varStatus="status">
					<tr>
						<td><c_:out value="${insertjbItem.item}" /></td>
						<td><c_:out value="${insertjbItem.kind}" /></td>
						<td><c_:out value="${insertjbItem.group}" /></td>
						<td><c_:out value="${insertjbItem.stock}" /></td>
					</tr>
				</c_:forEach>
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