<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
  <jsp:param value="${board.bid}번 게시글" name="title"/>
</jsp:include>

  <h1 style="text-align: center">상세</h1>
  
  <div>작성자   : ${board.user.nickname}</div>
  <div>작성일자 : ${board.createdAt}</div>
  <div>수정일자 : ${board.modifiedAt == null ? board.createdAt : board.modifiedAt}</div>
  
  <hr>
  
  <h1>${board.title}</h1>
  <pre>${board.content}</pre>
  
  <hr>
  
  <button onclick="onModifyForm()">수정</button>
  <button onclick="onDelete()">삭제</button>
  
  <br>
  <a href="${contextPath}/board/list">목록으로 돌아가기</a>
  
  <script type="text/javascript">
  	function onModifyForm() {
  	  location.href= "${contextPath}/board/modify?bid=${board.bid}";
  	}
  	function onDelete() {
  	  if(confirm("현재 게시글을 삭제 할까요?")) {
  	    location.href = "${contextPath}/board/delete?bid=${board.bid}";
  	  }
  	}
  </script>
</body>
</html>