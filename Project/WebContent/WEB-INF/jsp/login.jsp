<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ログイン画面</h1>

<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>


<form method="post" action="login">
ログインid	<input type="text" id="id" name="loginId"><br>
パスワード	<input type="password" name="password"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>