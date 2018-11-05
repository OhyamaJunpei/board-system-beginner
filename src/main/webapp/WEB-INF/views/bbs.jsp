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

<form:form modelAttribute="articleForm" action="${pageContext.request.contextPath}/article/insertArticle" method="post">
投稿者名：<form:input path="name"/><br>
投稿内容：<form:textarea path="content"/><br>
<input type="submit" value="記事投稿">
</form:form>

<hr>

<c:forEach var="article" items="${articleList}">
  投稿ID:<c:out value="${article.id}"/><br>
  投稿者名:<c:out value="${article.name}"/> <br>
  投稿内容:<c:out value="${article.content}"/><br>
 
 <form:form modelAttribute="commentForm" action="${pageContext.request.contextPath}/article/delete" method="post">
 <input type="submit" value="コメント削除">
 <input type="hidden" name="articleId" value="${article.id}"> 
 </form:form>
 
 <br>
	 <c:forEach var="comment" items="${article.commentList}">
	 コメントID:<c:out value="${comment.id}"/><br>
	 コメント者名：<c:out value="${comment.name}"/><br>
	 コメント内容：<c:out value="${comment.content}"/><br>
	 </c:forEach>
 
<form:form modelAttribute="commentForm" action="${pageContext.request.contextPath}/article/insertComment" method="post">
 名前：<form:input path="name"/><br>
 コメント：<form:textarea path="content"/><br>
<input type="hidden" name="articleId" value="${article.id}"> 
<input type="submit" value="コメント投稿">
<hr>
 </form:form>
 
</c:forEach>


</body>
</html>