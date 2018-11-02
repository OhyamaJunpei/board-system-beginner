<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>掲示板アプリケーション</h1>

<c:forEach var="article" items="${articleList}">
  投稿ID:<c:out value="${article.id}"/><br>
  投稿者名:<c:out value="${article.name}"/> <br>
  投稿内容:<c:out value="${article.content}"/><br>
</c:forEach>

<hr>

<form action="${pageContext.request.contextPath}/article/index" method="post">
投稿者名：<input type="text" name="name"><br>
投稿内容：<textarea></textarea><br>
<input type="submit" value="記事投稿">
</form>
</body>
</html>