<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


${users.name} さん<p><a href="LogoutServlet">ログアウト</a></p><br>

<h1>ユーザ新規登録</h1>
<form method="post" action="newRegis">

<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>

ログインid	<input type="text" id="id" name="loginId"><br>
パスワード	<input type="password" name="password"><br>
パスワード(確認)	<input type="password" name="password2"><br>
ユーザ名	<input type="text" name="name"><br>
生年月日	<input type="date" name="birth_date"><br>

<input type="submit" value="登録"><br>
<p><a href="users">戻る</a></p>
</form>
</body>
</html>