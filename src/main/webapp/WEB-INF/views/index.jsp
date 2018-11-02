<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

<form:form modelAttribute="articleForm" action="${pageContext.request.contextPath}/article/index" method="post">
投稿者名：<form:input path="name"/><br>
投稿内容：<form:textarea path="content"/><br>
<input type="submit" value="記事投稿">
</form:form>
</body>
</html>