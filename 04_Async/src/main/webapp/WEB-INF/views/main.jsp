<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
  <h1>fetch() 함수를 이용한 비동기 요청</h1>
  
  <button onclick="getJson3()">게또제이슨</button>
  <br>
  <div id="get-json" ></div>
  
  <script>
   /* 1. fetch() 함수와 then() 메소드 */
  	function getJson() {
  	  fetch("${contextPath}/a/list")
  	  .then(response => response.json())
  	  .then(jsonData => {
  	    console.log(jsonData);
  	    // document.getElementById("get-gson")/textContent = jsonData)
  	  })
  	}
   /* 2. fetch() 함수와 async 함수 */
  	function getJson2() {
  	  getBoards();
  	}
  	async function getBoards() {  // 2. 본문에 await 키워드가 포함되기 위해서는 반드시 함수가 async 함수여야 한다.
 	 const response = await fetch("${contextPath}/b/list");  // 1, fetch() 함수의 반환값은 프로미스이므로, await을 추가하여 프로미스에 저장된 값을 꺼낸다.
 	 const jsonData = await response.json();
 	 console.log(response);
 	 console.log(jsonData);
  	}
  	/* 3. fetch() 함수와 예외 처리 */
  	function getJson3() {
  	  const bid = 1;
  	  fetch("${cocntextPath}/c/detail?bid=" + bid)
  	  	.then(response => {
  	  	  // 404 예외 처리
  	  	  if(response.status === 404) {
  	  	    alert("존재하지 않는 bid");
  	  	    return;
  	  	  }
  	  	  // 404 이외 예외 처리
  	  	  if(!response.ok) {  // status가 200이면 true
  	  	    throw new Error(`HTTP error.status: \${response.status}`);
  	  	  }
  	  	  return response.json();
  	  	})
  	  	.then(jsonData => console.log(jsonData))  // 정상 처리
  	  	.catch(error => console.log(`fetch() error: ${error}`));
  	}
  </script>
  
  <hr>
  
  <button onclick="getXml2()">getXML</button>
  <br>
  <div id="get-xml"></div>
  
  <script type="text/javascript">
  	function getXml() {
  	  fetch("${contextPath}/a/list.xml")
  	  	.then(response => response.text())
  	  	.then(xmlStr => {
  	  	  const parser = new DOMParser();
  	  	  const xmlDoc = parser.parseFromString(xmlStr, "application/xml");
  	  	  console.log(xmlDoc);
  	  	  console.log(xmlDoc.querySelectorAll("item"));
  	  	  document.getElementById("get-xml").textContent = xmlDoc.querySelector("item").textContent;
  	  	})
  	}
  	function getXml2() {
  	  getBoardsXml();
  	}
  	async function getBoardsXml() {
  	  const response = await fetch("${contextPath}/b/list");
  	  const textData = await response.text();
  	  const parser = new DOMParser();
  	  const xmlDoc = parser.parseFromString(textData, "application/xml");
	  console.log(xmlDoc);  	  
  	}
  </script>
</body>
</html>