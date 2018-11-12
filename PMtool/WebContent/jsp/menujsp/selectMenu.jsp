<%@page import="model.ProductJB"%>
<%@page import="model.History"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c_" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/scrollbar.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/leftRight2.css"
	type="text/css">
<title>在庫照会メニュー</title>
</head>
<body>
	<div class="main">
		<div class="left">

			<form action="/PMtool/log/SelectMain" method="get">
				<input type="radio" name="select" value="all">在庫一覧 <input
					type="radio" name="select" value="select1">完全一致 <input
					type="radio" name="select" value="select2">部分一致
				<hr>
				<table>
					<tr>
						<td>商品ID<input type="text" name="selectid" size="5"></td>
						<td>商品名<input type="text" name="selectitem"></td>
						<td>タイプ<input type="text" name="selectkind" size="10"></td>
						<td>グループ<input type="text" name="selectgroup" size="5"></td>
						<td>在庫数<input type="text" name="selectstock" size="5"></td>
					</tr>
				</table>

				<table class="scroll">
					<thead class="scrollHead">
						<tr>
							<th class="type1">商品ID</th>
							<th class="type2">商品名</th>
							<th class="type3">タイプ</th>
							<th class="type4">グループ</th>
							<th class="type5">在庫数</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
						<c_:if test="${!empty proList}">
							<c_:forEach var="product" items="${proList}">
								<tr>
									<td class="type1"><c_:out value="${product.id}" /></td>
									<td class="type2"><c_:out value="${product.item}" /></td>
									<td class="type3"><c_:out value="${product.kind}" /></td>
									<td class="type4"><c_:out value="${product.group}" /></td>
									<td class="type5"><c_:out value="${product.stock}" /></td>
								</tr>
							</c_:forEach>
						</c_:if>
					</tbody>
				</table>
				<p>
					<input class="subm" type="submit" value="照会">
				</p>
				<input type="hidden" name="hidden" value="done">
			</form>
			<c_:if test="${!empty noMsg}">
				<c_:out value="${noMsg}" />
			</c_:if>
			<c_:if test="${!empty errorMsg}">
				<c_:out value="${errorMsg}" />
			</c_:if>

		</div>
		<div class="right">

			<form action="/PMtool/log/HistoryMain" method="get">
				<input type="radio" name="history" value="his1">入庫履歴 <input
					type="radio" name="history" value="his2">出庫履歴 <input
					type="radio" name="history" value="his3">入出庫履歴
				<hr>
				<br>
				<table class="scroll">
					<thead class="scrollHead">
						<tr>
							<th class="typeA">入出庫ID</th>
							<th class="typeB">実行日時</th>
							<th class="typeC">カテゴリ</th>
							<th class="typeD">数量</th>
							<th class="typeE">商品ID</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
						<c_:if test="${!empty hisList}">
							<c_:forEach var="history" items="${hisList}">
								<tr>
									<td class="typeA"><c_:out value="${history.historyid}" /></td>
									<td class="typeB"><c_:out value="${history.historytime}" /></td>
									<td class="typeC"><c_:out value="${history.category}" /></td>
									<td class="typeD"><c_:out value="${history.num}" /></td>
									<td class="typeE"><c_:out value="${history.id}" /></td>
								</tr>
							</c_:forEach>
						</c_:if>
					</tbody>
				</table>
				<p>
					<input class="subm" type="submit" value="照会">
				</p>
			</form>
			<c_:if test="${!empty noHistory}">
				<c_:out value="${noHistory}" />
			</c_:if>

		</div>
	</div>
	<br clear="all">
	<div class="menu">
		<a href="/PMtool/log/returnMenu">メニューへ</a>
	</div>
</body>
</html>