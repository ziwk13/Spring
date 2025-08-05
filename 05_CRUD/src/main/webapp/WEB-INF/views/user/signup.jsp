<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<style>
  form {
  display: block;
  margin: 0 auto;
  }
  .signup {
  border: 1px solid black;
  text-align: center;
  }
</style>
<body>

   <!-- 정규식 / 이메일인증 / 비밀번호 확인 포함 필요 -->

  <h1 style="text-align: center">회원 가입 페이지</h1>
  
  <form method="post" action="${contextPath}/user/signup">
  
  <div class="signUp">
    <input type="hidden" name="url" value="${url}"> 
    
    <label>이메일: <input type="text" id="email" name="email" placeholder="이메일을 입력하세요"></label>
    <span id="email-msg" style="font-size: 12px; color: red;"></span> 
    <br>
    
    <label>비밀번호: <input type="password" name="password" placeholder="비밀번호를 입력하세요"></label>
    <br>
    
    <label>닉네임: <input type="text" name="nickname" placeholder="닉네임을 입력하세요"></label>
    <span id="nick-msg" style="font-size: 12px; color: red;"></span> 
    <br>
    
    <button type="submit" >회원가입</button>
  </div>
  </form>
  
  <c:if test="${not empty error}">
    <div style="font-size: 12px; color: red;">${error}</div>
  </c:if>
  <script>
  	// 이메일 입력 완료 후 이메일 중복 체크
  	function emailDuplicateCheck() {
  	  const email = document.getElementById("email");
  	  email.addEventListener("blur", function(e){ // 입력 완료 후 포커스를 해제 하면
  	    fetch("${contextPath}/user/emailDuplicateCheck?email=" + email.value)
  	       .then(response => response.json())
  	       .then(jsonData => {
  	         // console.log(jsonData);
  	         if(!(jsonData.result)) {
  	           document.getElementById("email-msg").textContent = jsonData.msg;
  	         } else {
  	           document.getElementById("email-msg").textContent = "";
  	         }
  	       })
  	  })
  	}
  	emailDuplicateCheck();
  </script>


</body>
</html>