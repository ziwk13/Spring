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
  
  <button onclick="getJson()">게또제이슨</button>
  <br>
  <div id="get-json" ></div>
  
  <script>
  	function getJson() {
  	  fetch("${contextPath}/a/list")
  	  .then(response => response.json())
  	  .then(jsonData => {
  	    console.log(jsonData);
  	    // document.getElementById("get-gson")/textContent = jsonData)
  	  })
  	}
  </script>
  
  <hr>
  
  <button onclick="getXml()">getXML</button>
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
  </script>
</body>
</html>