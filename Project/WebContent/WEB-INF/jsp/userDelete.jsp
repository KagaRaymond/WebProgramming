<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ユーザ名 さん<p><a href="login">ログアウト</a></p><br>

<h1>ユーザ削除確認</h1>

<form method="post" action="userDelete">

ログインid : ${user.loginId}<br>
を本当に削除してよろしいでしょうか。<br>

<p><a href="users">キャンセル</a></p>

<input type="hidden" name="loginId" value ="${user.loginId}">
<input type="submit" value="OK">
</form>
</body>
</html>