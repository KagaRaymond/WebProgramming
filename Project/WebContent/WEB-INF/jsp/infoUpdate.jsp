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

<form method="get" action="infoUpdate">

ログインid	${user.loginId}<br>
パスワード	<input type="password" name="password" value =${user.password}><br>
パスワード(確認)	<input type="password" name=${user.password}> <br>
ユーザ名　<input type="text" name="name" value=${user.name}><br>
生年月日　<input type="text" name="birth" value=${user.birthDate}><br>
<input type="hidden" name="createDate" value=${user.createDate}>
<input type="hidden" name="updateDate" value=${user.updateDate}>

</form>
<form method="post" action="infoUpdate">
<input type="submit" value="更新"><br>
</form>

<p><a href="users">戻る</a></p>


</body>
</html>