<%@page import="model.ProductJB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%List<ProductJB> updateList = (List<ProductJB>)request.getAttribute("updateList"); %>
<%String upMsg = (String)request.getAttribute("upMsg"); %>
<%String dupMsg = (String)request.getAttribute("dupMsg"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/scrollbar.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftRight3.css" type="text/css">
<title>登録内容の変更</title>
</head>
<body>
<div class="main">
<div class="left">
<h2>変更内容を入力してください</h2>

<form action="/PMtool/log/UpdateConf" method="get">
<table>
<tr>
<th class="type">商品ID</th>
<th class="type">商品名</th>
<th class="type">タイプ</th>
<th class="type">グループ</th>
<th class="type">在庫数</th>
</tr>
<tr>
<td><input type="text" name="upid1" size="3" class="id"></td>
<td><input type="text" name="upitem1"></td>
<td><input type="text" name="upkind1" size="10"></td>
<td><input type="text" name="upgroup1" size="5"></td>
<td><input type="text" name="upstock1" size="3" class="stock"></td>
</tr>
<tr>
<td><input type="text" name="upid2" size="3" class="id"></td>
<td><input type="text" name="upitem2"></td>
<td><input type="text" name="upkind2" size="10"></td>
<td><input type="text" name="upgroup2" size="5"></td>
<td><input type="text" name="upstock2" size="3" class="stock"></td>
</tr>

<tr>
<td><input type="text" name="upid3" size="3" class="id"></td>
<td><input type="text" name="upitem3"></td>
<td><input type="text" name="upkind3" size="10"></td>
<td><input type="text" name="upgroup3" size="5"></td>
<td><input type="text" name="upstock3" size="3" class="stock"></td>
</tr>
<tr>
<td><input type="text" name="upid4" size="3" class="id"></td>
<td><input type="text" name="upitem4"></td>
<td><input type="text" name="upkind4" size="10"></td>
<td><input type="text" name="upgroup4" size="5"></td>
<td><input type="text" name="upstock4" size="3" class="stock"></td>
</tr>
<tr>
<td><input type="text" name="upid5" size="3" class="id"></td>
<td><input type="text" name="upitem5"></td>
<td><input type="text" name="upkind5" size="10"></td>
<td><input type="text" name="upgroup5" size="5"></td>
<td><input type="text" name="upstock5" size="3" class="stock"></td>
</tr>
</table>
<p><input class="subm" type="submit" value="送信"></p>
<input type="hidden" name="action" value="done2">
</form>
<%if(dupMsg!=null){ %>
<%=dupMsg %>
<%} %>
<%if(upMsg!=null){ %>
<%=upMsg %>
<%} %>
　
</div>
<br>
<div class="right">
<br>
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
<td class="type1"><input type="text" name="updateid" size="3"></td>
<td class="type2"><input type="text" name="updateitem" size="15"></td>
<td class="type3"><input type="text" name="updatekind" size="10"></td>
<td class="type4"><input type="text" name="updategroup" size="5"></td>
<td class="type5"><input type="text" name="updatestock" size="3"></td>
</tr>
</table>
<br>
<table class="scroll">
<thead class="scrollHead">
    <tr><th class="type1">商品ID</th><th class="type2">商品名</th><th class="type3">タイプ</th><th class="type4">グループ</th><th class="type5">在庫数</th></tr>
</thead>
<tbody class="scrollBody">
<%if(updateList != null ){%>
    <%for(ProductJB jb : updateList){ %>
    <tr>
    <td class="type1"><%= jb.getId()%></td>
    <td class="type2"><%= jb.getItem()%></td>
    <td class="type3"><%= jb.getKind()%></td>
    <td class="type4"><%= jb.getGroup()%></td>
    <td class="type5"><%= jb.getStock()%></td>
    </tr>
    <%} %>
  <%} %>
  </tbody>

</table>
<p><input class="subm" type="submit" value="照会"></p>
<input type="hidden" name="action" value="done1">
</form>
</div>
</div>
<br clear="all"><p class="menu"><a href="/PMtool/log/returnMenu">メニューへ</a></p>
</body>
</html>