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

<h1>ユーザ情報更新</h1>

<form method="post" action="infoUpdate">

<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>


ログインid	${user.loginId}<br>
<input type="hidden" name="loginId" value ="${user.loginId}">
パスワード	<input type="password" name=password><br>
パスワード(確認)	<input type="password" name=password2> <br>
ユーザ名　<input type="text" name="name" value="${user.name}"><br>
生年月日　<input type="date" name="birth_date" value="${user.birthDate}"><br>
<input type="hidden" name="updateDate" value="${user.updateDate}">

<input type="submit" value="更新"><br>
</form>

<p><a href="users">戻る</a></p>


</body>
</html>