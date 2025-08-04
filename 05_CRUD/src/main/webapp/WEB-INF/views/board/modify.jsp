<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="${board.bid}번 게시글" name="title"/>
</jsp:include>

<h1 style="text-align: center">수정</h1>

<form method="post"
      action="${contextPath}/board/modify">
      
  <div>작성자  : ${board.user.nickname}</div>
  
  <label>제목 <input type="text" name="title" autofocus value="${board.title}"></label>
  <br>
  <textarea name="content">${board.content}</textarea>
  <br>

  <input type="hidden" name="bid" value="${board.bid}">
    
  <button type="submit">수정</button>
  <button type="button" onclick="onDelete()">삭제</button>
  
  <script type="text/javascript">
	function onDelete() {
	  if(confirm("현재 게시글을 삭제 할까요?")) {
	    location.href = "${contextPath}/board/delete?bid=${board.bid}";
	  }
	}
  </script>
  
</form>

</body>
</html>