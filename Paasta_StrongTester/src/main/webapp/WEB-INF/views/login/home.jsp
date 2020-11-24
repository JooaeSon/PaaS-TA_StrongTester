<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	 <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./css/home.css" />
    <link rel="icon" type="img/png" href="img/favicon.png" />
</head>
	
 <body>
    <nav id="navbar">
      <a href="home.do" id="logo">STRONG TESTER</a>
      <ul class="navbar__menu">
        <li class="navbar__menu__item"></li>
      </ul>
    </nav>
    <section class="section">
      <div class="section__content">
        <form action="./studentsInfo.do" method="POST">
          <h1>STRONG TESTER 교수용</h1>
          <input type="submit" value="입장" id="master-login" />
        </form>
      </div>
    </section>
    <footer>
      <div class="line"></div>
      <div id="copyright">
        2020 STRONG TESTER - All rights reserved
        <br />
        ☎ contact | strongtester@naver.com
      </div>
    </footer>
  </body>
</html>
