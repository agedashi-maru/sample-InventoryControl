<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c_" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/scrollbar.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/leftRight1.css"
	type="text/css">
<title>在庫データの削除</title>
</head>
<body>
	<div class="main">
		<div class="left">
			<form action="/PMtool/log/DeleteConf" method="get">
				<h2>削除する商品IDを入力してください</h2>
				<table>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="deleteid1" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="deleteid2" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="deleteid3" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="deleteid4" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="deleteid5" size="5"></td>
					</tr>
				</table>
				<p>
					<input class="subm" type="submit" value="送信">
				</p>
				<input type="hidden" name="action" value="done2">
			</form>
			<c_:if test="${!empty delMsg}">
				<c_:out value="${delMsg}" />
			</c_:if>
		</div>
		<br> <br>
		<div class="right">
			<form>
				<table class="scroll">
					<tr>
						<th class="type1">商品ID</th>
						<th class="type2">商品名</th>
						<th class="type3">タイプ</th>
						<th class="type4">グループ</th>
						<th class="type5">在庫数</th>
					</tr>
					<tr>
						<td class="type1"><input type="text" name="deleteid" size="3"></td>
						<td class="type2"><input type="text" name="deleteitem"
							size="15"></td>
						<td class="type3"><input type="text" name="deletekind"
							size="10"></td>
						<td class="type4"><input type="text" name="deletegroup"
							size="5"></td>
						<td class="type5"><input type="text" name="deletestock"
							size="3"></td>
					</tr>
				</table>
				<br>
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
						<c_:if test="${!empty deleteList}">
							<c_:forEach var="deleteItem" items="${deleteList}">
								<tr>
									<td class="type1"><c_:out value="${deleteItem.id}" /></td>
									<td class="type2"><c_:out value="${deleteItem.item}" /></td>
									<td class="type3"><c_:out value="${deleteItem.kind}" /></td>
									<td class="type4"><c_:out value="${deleteItem.group}" /></td>
									<td class="type5"><c_:out value="${deleteItem.stock}" /></td>
								</tr>
							</c_:forEach>
						</c_:if>
					</tbody>
				</table>
				<p>
					<input class="subm" type="submit" value="照会">
				</p>
				<input type="hidden" name="action" value="done1">
			</form>
		</div>
	</div>
	<br clear="all">
	<p class="menu">
		<a href="/PMtool/log/returnMenu">メニューへ</a>
	</p>
</body>
</html>