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
	href="${pageContext.request.contextPath}/css/insert.css"
	type="text/css">
<title>新規商品登録</title>
</head>
<body>
	<br>
	<h2>登録する商品の情報を入力してください</h2>
	<div class="main">

		<form action="/PMtool/log/InsertConf" method="get">
			<table class="insert">
				<tr>
					<th class="type">商品名</th>
					<th class="type">タイプ</th>
					<th class="type">グループ</th>
					<th class="type">在庫数</th>
				</tr>
				<tr>
					<td><input type="text" name="insertitem1"></td>
					<td><input type="text" name="insertkind1" size="10"></td>
					<td><input type="text" name="insertgroup1" size="5"></td>
					<td><input type="text" name="insertstock1" size="5"
						class="stock"></td>
				</tr>
				<tr>
					<td><input type="text" name="insertitem2"></td>
					<td><input type="text" name="insertkind2" size="10"></td>
					<td><input type="text" name="insertgroup2" size="5"></td>
					<td><input type="text" name="insertstock2" size="5"
						class="stock"></td>
				</tr>
				<tr>
					<td><input type="text" name="insertitem3"></td>
					<td><input type="text" name="insertkind3" size="10"></td>
					<td><input type="text" name="insertgroup3" size="5"></td>
					<td><input type="text" name="insertstock3" size="5"
						class="stock"></td>
				</tr>
				<tr>
					<td><input type="text" name="insertitem4"></td>
					<td><input type="text" name="insertkind4" size="10"></td>
					<td><input type="text" name="insertgroup4" size="5"></td>
					<td><input type="text" name="insertstock4" size="5"
						class="stock"></td>
				</tr>
				<tr>
					<td><input type="text" name="insertitem5"></td>
					<td><input type="text" name="insertkind5" size="10"></td>
					<td><input type="text" name="insertgroup5" size="5"></td>
					<td><input type="text" name="insertstock5" size="5"
						class="stock"></td>
				</tr>
			</table>
			<br>
			<p>
				<input class="subm" type="submit" value="送信">
			</p>
		</form>
		<c_:if test="${!empty dupMsg}">
			<c_:out value="${dupMsg}" />
		</c_:if>
		<c_:if test="${!empty insMsg}">
			<c_:out value="${insMsg}" />
		</c_:if>
	</div>
	<p class="menu">
		<a href="/PMtool/log/returnMenu">メニューへ</a>
	</p>
</body>
</html>