<%@page import="model.ProductJB"%>
<%@page import="model.History"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%List<History> hisList = (List<History>)session.getAttribute("hisList"); %>
<%List<ProductJB> proList = (List<ProductJB>)session.getAttribute("proList"); %>
<%String noMsg = (String)request.getAttribute("noMsg"); %>
<%String errorMsg = (String)request.getAttribute("errorMsg"); %>
<%String noHistory = (String)request.getAttribute("noHistory"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scrollbar.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftRight2.css" type="text/css">
<title>在庫照会メニュー</title>
</head>
<body>
<div class="main" >
<div class="left">

<form action="/PMtool/log/SelectMain" method="get">
<input type="radio" name="select" value="all">在庫一覧
<input type="radio" name="select" value="select1">完全一致
<input type="radio" name="select" value="select2">部分一致
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
    <tr><th class="type1">商品ID</th><th class="type2">商品名</th><th class="type3">タイプ</th><th class="type4">グループ</th><th class="type5">在庫数</th></tr>
  </thead>
  <tbody class="scrollBody">
  <%if(proList != null ){%>
    <%for(ProductJB product : proList){ %>
    <tr>
    <td class="type1"><%= product.getId()%></td>
    <td class="type2"><%= product.getItem()%></td>
    <td class="type3"><%=product.getKind()%></td>
    <td class="type4"><%= product.getGroup()%></td>
    <td class="type5"><%=product.getStock()%></td>
    </tr>
    <%} %>
  <%} %>
  </tbody>
</table>
<p><input class="subm" type="submit" value="照会"></p>
<input type="hidden" name="hidden" value="done">
</form>
<%if(noMsg!=null){ %>
<%=noMsg %>
<%} %>
<%if(errorMsg!=null){ %>
<%=errorMsg %>
<%} %>
　
</div>
<div class="right">

<form action="/PMtool/log/HistoryMain" method="get">
<input type="radio" name="history" value="his1">入庫履歴
<input type="radio" name="history" value="his2">出庫履歴
<input type="radio" name="history" value="his3">入出庫履歴
<hr>
<br>
<table class="scroll">
  <thead class="scrollHead">
    <tr><th class="typeA">入出庫ID</th><th class="typeB">実行日時</th><th class="typeC">カテゴリ</th><th class="typeD">数量</th><th class="typeE">商品ID</th></tr>
  </thead>
  <tbody class="scrollBody">
  <%if(hisList != null ){%>
    <%for(History history : hisList){ %>
    <tr>
    <td class="typeA"><%= history.getHistoryid()%></td>
    <td class="typeB"><%= history.getHistorytime()%></td>
    <td class="typeC"><%=history.getCategory()%></td>
    <td class="typeD"><%= history.getNum()%></td>
    <td class="typeE"><%=history.getId() %></td>
    </tr>
    <%} %>
  <%} %>
  </tbody>
</table>
<p><input class="subm" type="submit" value="照会"></p>
</form>
<%if(noHistory!=null){ %>
<%=noHistory %>
<%} %>
</div>
</div>
<br clear="all"><div class="menu"><a href="/PMtool/log/returnMenu">メニューへ</a></div>
</body>
</html>