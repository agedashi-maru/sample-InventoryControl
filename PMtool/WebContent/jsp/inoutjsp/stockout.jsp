<%@page import="model.ProductJB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<ProductJB> productList = (List<ProductJB>) session.getAttribute("productList");
%>
<%
	String msg = (String) request.getAttribute("msg");
%>
<%
	String dupMsg = (String) request.getAttribute("dupMsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<title>出庫</title>
</head>
<body>
	<div class="main">
		<div class="left">
			<h2>商品IDと出庫数を入力してください</h2>
			<form action="/PMtool/log/InOutConf">
				<table>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid1" size="8"></td>
						<td class="count">出庫数</td>
						<td class="inp"><input type="text" name="count1" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid2" size="8"></td>
						<td class="count">出庫数</td>
						<td class="inp"><input type="text" name="count2" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid3" size="8"></td>
						<td class="count">出庫数</td>
						<td class="inp"><input type="text" name="count3" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid4" size="8"></td>
						<td class="count">出庫数</td>
						<td class="inp"><input type="text" name="count4" size="5"></td>
					</tr>
					<tr>
						<td class="id">商品ID</td>
						<td class="inp"><input type="text" name="stockid5" size="8"></td>
						<td class="count">出庫数</td>
						<td class="inp"><input type="text" name="count5" size="5"></td>
					</tr>
				</table>
				<p>
					<input class="subm" type="submit" value="送信">
				</p>
				<input type="hidden" name="action" value="stockout">
			</form>
			<%
				if (dupMsg != null) {
			%>
			<%=dupMsg%>
			<%
				}
			%>
			<%
				if (msg != null) {
			%>
			<%=msg%>
			<%
				}
			%>

		</div>
		<br>
		<br>
		<div class="right">
			<form action="/PMtool/log/InOutConf">
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
							<th class="type1">商品ID</th>
							<th class="type2">商品名</th>
							<th class="type3">タイプ</th>
							<th class="type4">グループ</th>
							<th class="type5">在庫数</th>
						</tr>
					</thead>
					<tbody class="scrollBody">
						<%
							if (productList != null) {
						%>
						<%
							for (ProductJB jb : productList) {
						%>
						<tr>
							<td class="type1"><%=jb.getId()%></td>
							<td class="type2"><%=jb.getItem()%></td>
							<td class="type3"><%=jb.getKind()%></td>
							<td class="type4"><%=jb.getGroup()%></td>
							<td class="type5"><%=jb.getStock()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
					<%
						}
					%>
				</table>
				<p>
					<input class="subm" type="submit" value="照会">
				</p>
				<input type="hidden" name="action" value="inout2">
			</form>
		</div>
	</div>
	<br clear="all">
	<p class="menu">
		<a href="/PMtool/log/returnMenu">メニューへ</a>
	</p>
</body>
</html>