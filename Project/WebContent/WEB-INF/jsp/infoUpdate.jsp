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

<h1>ユーザ情報更新</h1>

ログインid	id0001<br>
パスワード	<input type="password" name="password"><br>
パスワード(確認)	<input type="password" name="pass"> <br>
ユーザ名　<input type="text" name="name" value="田中太郎"><br>
生年月日　<input type="text" name="birth" value="1989/04/26"><br>
<input type="submit" value="更新"><br>

<p><a href="users">戻る</a></p>


</body>
</html>