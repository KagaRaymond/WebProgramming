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

<h1>ユーザ情報詳細参照</h1>


<form method="get" action="userInfo">

ログインid	${user.loginId}<br>
ユーザ名	${user.name}<br>
生年月日	${user.birthDate}<br>
登録日時　  ${user.createDate}<br>
更新日時　  ${user.updateDate}<br>

<p><a href="users">戻る</a></p>
</form>
</body>
</html>