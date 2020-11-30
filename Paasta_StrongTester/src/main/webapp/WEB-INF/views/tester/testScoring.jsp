<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./css/testScoring.css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
        <script
    src="https://kit.fontawesome.com/12ed482fdd.js"
    crossorigin="anonymous"
  ></script>
    
   <script src="./js/testScoring.js" defer></script>
  </head>
  <body>
    <nav id="navbar">
<h3><i class="fas fa-pencil-alt"></i>인공지능</h3>
      <div>

          <a href="./home.do"><button class="nav__button"><i class="fas fa-home"></i></button></a>
          <a href="./studentsInfo.do"><button class="nav__button"><i class="fas fa-bars"></i></button></a>
         
       </div>
    </nav>

    <section class="section">
    <article class="section__container">
      <table id="student__info">
        <tr cols="3">
          <td colspan="3">학생정보</td>
        </tr>
        <tr id="name__tr">
          <th>이름</th>
          <td>${stdto.student_name}</td>
        </tr>
        <tr>
          <th>학과</th>
          <td>${stdto.student_deptm}</td>
        </tr>
        <tr id="stuent__number__tr">
          <th>학번</th>
        <td>${stdto.student_code}</td> 
        </tr>
        <tr>
          <th>이메일</th>
          <td>${stdto.student_email}</td>
        </tr>
        <tr>
          <th>IP</th>
          <td>${stdto.student_ip}</td>
        </tr>
           <tr>
          <th>총점</th>
          <td>${stdto.test_sumpoint} 점</td>
        </tr>
      </table>
      
      <h3 id="webcam-title">웹캠 기록내역</h3>
      <article class="webcam">
      <c:forEach var="link" items="${linklst}" varStatus="vs">
     <video width="300" height="300" src="./video/${link}" controls autoplay></video>	
      </c:forEach>
      </article>
      </article>
    </section>
    
  
    <c:forEach var="aswDto" items="${aswlst}" varStatus="vs">  
    <section class="section">
      <article class="section__container">
        <div class="section__title">
          <h3>문제 ${aswDto.test_num}</h3>
          <span>점수: ${aswDto.student_score}/10(점)</span>
        </div>
        
        <article class="question__content"> </article>

        <article class="answer__content">
          <h3>답</h3>
          ${aswDto.student_answer}
        </article>

        <article class="answer-correct__content">
          <h3>정답</h3>
          <div class="answer-correct__text"> </div>
        </article>

      </article>
    </section>
     </c:forEach> 

    
  </body>
</html>