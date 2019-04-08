<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c_" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scrollbar.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftRight1.css" type="text/css">
<title>入庫</title>
</head>
<body>
	<div class="main">
		<div class="left">
			<h2>商品IDと入庫数を入力してください</h2>
			<form action="/PMtool/ReceiveConf">
				<table>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid1" size="8"></td>
						<td class="count">入庫数</td>
						<td class="inp"><input type="text" name="count1" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid2" size="8"></td>
						<td class="count">入庫数</td>
						<td class="inp"><input type="text" name="count2" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid3" size="8"></td>
						<td class="count">入庫数</td>
						<td class="inp"><input type="text" name="count3" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid4" size="8"></td>
						<td class="count">入庫数</td>
						<td class="inp"><input type="text" name="count4" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid5" size="8"></td>
						<td class="count">入庫数</td>
						<td class="inp"><input type="text" name="count5" size="5"></td>
					</tr>
				</table>
				<p>
					<input class="subm" type="submit" value="送信">
				</p>
			</form>
			<c_:if test="${!empty dupMsg}">
				<c_:out value="${dupMsg}" />
			</c_:if>
			<c_:if test="${!empty msg}">
				<c_:out value="${msg}" />
			</c_:if>
		</div>
		<br>
		<br>
		<div class="right">
			<form action="/PMtool/StockReference">
				<table class="scroll">
					<tr>
						<th class="type1">商品ID</th>
						<th class="type2">商品名</th>
						<th class="type3">タイプ</th>
						<th class="type4">グループ</th>
						<th class="type5">在庫数</th>
					</tr>
					<tr>
						<td class="type1"><input type="text" name="inoutid" size="3"></td>
						<td class="type2"><input type="text" name="inoutitem"
							size="15"></td>
						<td class="type3"><input type="text" name="inoutkind"
							size="10"></td>
						<td class="type4"><input type="text" name="inoutgroup"
							size="5"></td>
						<td class="type5"><input type="text" name="inoutstock"
							size="3"></td>
					</tr>
				</table>
				<br>
				<table class="scroll">
					<thead class="scrollHead">
						<tr>
							<th class="type1" style="width:;">商品ID</th>
							<th class="type2" style="width:;">商品名</th>
							<th class="type3" style="width:;">タイプ</th>
							<th class="type4" style="width:;">グループ</th>
							<th class="type5">在庫数</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
					<c_:if test="${!empty productList }">
						<c_:forEach var="productItem" items="${productList}" varStatus="status">
							<tr>
								<td class="type1"><c_:out value="${productItem.id}" /></td>
								<td class="type2"><c_:out value="${productItem.item}" /></td>
								<td class="type3"><c_:out value="${productItem.kind}" /></td>
								<td class="type4"><c_:out value="${productItem.group}" /></td>
								<td class="type5"><c_:out value="${productItem.stock}" /></td>
							</tr>
						</c_:forEach>
					</c_:if>
					</tbody>
				</table>
				<p>
					<input class="subm" type="submit" value="照会">
				</p>
				<input type="hidden" name="action" value="inout1">
			</form>
		</div>
	</div>
	<br clear="all">
	<p class="menu">
		<a href="/PMtool/log/returnMenu">メニューへ</a>
	</p>
</body>
</html>