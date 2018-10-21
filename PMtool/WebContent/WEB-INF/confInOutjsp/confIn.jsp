<%@page import="model.History"%>
<%@page import="model.ProductJB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<ProductJB> inOutJBList = (List<ProductJB>)session.getAttribute("inOutJBList");%>
<%List<History> historyList = (List<History>)session.getAttribute("historyList"); %>
<%int counter = 0; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/conf.css" type="text/css">
<title>入庫</title>
</head>
<body>
<div class="main">
<div class="conf">
<h1>下記内容で入庫処理します</h1>
<table>
<tr>
<th>商品ID</th>
<th>商品名</th>
<th>入庫数</th>
</tr>
<%for(ProductJB jb : inOutJBList){ %>
<tr>
<td><%=jb.getId() %></td>
<td><%=jb.getItem() %></td>
<td><%=historyList.get(counter).getNum() %></td>
</tr>
<%counter++; %>
<%} %>
</table>
<p><a href="/PMtool/log/UpdateMain?action=up2"><input class="subm" type="button" value="実行"></a></p>
<p class="menu"><a href="/PMtool/log/returnMenu">メニューへ</a></p>
</div>
</div>
</body>
</html>