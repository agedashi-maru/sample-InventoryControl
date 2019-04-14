<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c_" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
<title>出庫確認</title>
</head>
<body>
	<div class="main">
		<div class="conf">
			<h1>下記内容で出庫処理します</h1>
			<table>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>出庫数</th>
				</tr>
				<c_:forEach var="inOutJBItem" items="${inOutJBList}" varStatus="status">
				<tr>
					<td><c_:out value="${inOutJBItem.id}" /></td>
					<td><c_:out value="${inOutJBItem.item}" /></td>
					<td><c_:out value="${historyList.get(status.index).getNum()}" /></td>
				</tr>
				</c_:forEach>
			</table>
				<c_:if test="${strout.size() != 0}">
					<c_:forEach var="msg" items="${strout}" varStatus="status">
						<p><c_:out value="${msg}" /></p>
					</c_:forEach>
				</c_:if>
			<p>
				<a href="/PMtool/ShippingMain"><input class="subm" type="button" value="実行"></a>
			</p>
			<p class="menu">
				<a href="/PMtool/log/returnMenu">メニューへ</a>
			</p>
		</div>
	</div>
</body>
</html>