<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String logMsg = (String)request.getParameter("logMsg"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css"/>
<title>ユーザー認証</title>
</head>
<body class="all">
<div>
<form action="/PMtool/Login" method="post">
<table>
<tr>
<td class="user">ユーザー名</td>
<td class="usertext"><input type="text" name="userid" style="border: none; font-size:25px;" size=12></td>
</tr>
<tr>
<td class="pass">パスワード</td>
<td class="passtext"><input type="password" name="pass" style="border: none; font-size:25px;" size=12></td>
</tr>
</table>
<p><input class="subm" type="submit" value="送信"></p>
</form>
</div>
</body>
</html>