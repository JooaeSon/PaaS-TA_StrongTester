<%@page import="org.aspectj.weaver.patterns.IScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/testInit.css" />
<link rel="icon" type="image/png" href="img/favicon.png" />
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
   rel="stylesheet" />
<script src="./js/testCam.js" defer> </script>
<script src="./js/test.js"></script>
</head>

<body>
   <nav id="navbar">
      <h2 id="title__nav">STRONG TESTER</h2>
   </nav>
   <section class="container-cam">
      <p>인식에 문제가 있다면 관리자에게 문의하시기 바랍니다</p>
      <form  id="buttonfrm" action="./gobackHome.do" method="POST">
         <input id="summit_input" type="submit" value="이전으로"/>
      </form>
<!-- 여기서부터 오늘 한 것들 -->

      <video autoplay="autoplay" id="myCam"></video>
   </section>
</body>
</html>