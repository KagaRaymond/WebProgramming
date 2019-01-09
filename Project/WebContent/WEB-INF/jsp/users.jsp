<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
ユーザ名 さん<p><a href="login">ログアウト</a></p>

<h1>ユーザ一覧</h1>
<p><a href="newRegis">新規登録</a></p><br>
ログインid	<input type="text" id="id"><br>
ユーザ名	<input type="text" name="name"><br>
生年月日　<input type="date" name="birth" value="年/月/日">
～　<input type="date" name="birth2" value="年/月/日"><br>
<input type="submit" value="検索">

<table border="1">
<tr>
<th>ログインid</th>
<th>ユーザ名</th>
<th>生年月日</th>
<th></th>
</tr>
<c:forEach var="user" items="${userList}" >
<tr>
<td>${user.loginId}</td>
<td>${user.name}</td>
<td>${user.birthDate}</td>
<td>
<form method="post" action="userInfo" class="btn">
<input type="submit" value="詳細">
</form>
<form method="post" action="infoUpdate" class="btn">
<input type="submit" value="更新">
</form>
<form method="post" action="userDelete" class="btn">
<input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>

</table>


</body>
</html>