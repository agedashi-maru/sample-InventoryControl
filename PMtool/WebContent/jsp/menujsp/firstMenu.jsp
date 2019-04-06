
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/firstMenu.css"
	type="text/css">
<title>在庫管理メニュー</title>
</head>
<body>
	<div class="main">
		<div class="busi">
			<h2>業務メニュー</h2>
			<ul>
				<li><a href="/PMtool/log/InOutConf?action=in">入庫</a></li>
				<li><a href="/PMtool/log/InOutConf?action=out">出庫</a></li>
				<li><a href="/PMtool/log/SelectMain">照会</a></li>
			</ul>
		</div>
		<div class="manage">
			<h2>管理メニュー</h2>
			<ul>
				<li><a href="/PMtool/log/InsertConf">新規商品登録</a></li>
				<li><a href="/PMtool/log/UpdateConf">登録内容の変更</a></li>
				<li><a href="/PMtool/log/DeleteConf">在庫データの削除</a></li>
			</ul>
		</div>
		<p>
			<a href="/PMtool/log/Logout">ログアウト</a>
		</p>
	</div>
</body>
</html>