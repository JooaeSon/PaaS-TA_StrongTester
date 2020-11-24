<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>해당 시험 수험자 목록 조회</title>
<link rel="icon" type="img/png" href="img/favicon.png" />
<link
   href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
   rel="stylesheet" />
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" />
<link rel="stylesheet"
   href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css" />

<link rel="stylesheet" href="./css/studentInfoForm.css" />
</head>
<script src="https://kit.fontawesome.com/12ed482fdd.js"
   crossorigin="anonymous"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/studentInfoForm.js" defer></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<body>
   
   <nav id="navbar">
    <div>
        <img id="logoImg" src="img/logoImg.png" width="30px" >
      <a href="home.do" id="logo">STRONG TESTER</a>
    </div>
   
      <ul class="navbar__menu">
         <li class="navbar__menu__item"><a href="master-info.html">내
               정보</a></li>
         <li class="navbar__menu__item"><a href="index.html">로그아웃</a></li>
      </ul>
   </nav>
   <section class="section">
      <h6>숭실대학교</h6>
      <h2>수업명: 인공지능</h2>
      <div class="setting__buttons">
         <button>삭제</button>
         <button>수정</button>
         <button>추가</button>
      </div>
   <form id="frm" method="POST">
      <table class="student__table table table-striped table-bordered"
            id="allTable">

            <thead>
               <tr>
                  <th><input type="checkbox" id="select__all" /></th>
                  <th>학번</th>
                  <th>학과</th>
                  <th>이름</th>
                  <th>이메일</th>
                  <th>응시여부</th>
                  <th>세부사항</th>
               </tr>
            </thead>
            <tbody id="tbody">
               <c:forEach var="stdto" items="${stdAllList}" varStatus="vs">
                  
                 <tr>
                     <td><input type="checkbox" class="checkbox" name="std_id"
                        value="${stdto.student_code}" /></td>
                     <td>${stdto.student_code}</td>
                     <td>${stdto.student_deptm}</td>
                     <td>${stdto.student_name}</td>
                     <td>${stdto.student_email}</td>
                     <td class="data-flag">${stdto.test_flag}</td>
                      <td>
                     <!-- <button class="student__button showInfo" value="${stdto.student_code}">조회</button> -->
                        <input type="submit"  onclick="checkIt(${stdto.student_code});" class="student__button showInfo" name="stdId" value="${stdto.student_code}"/></td> 
                     
                  </tr>
                  
                  
               </c:forEach>
            </tbody>
         </table>

         <div id="chkEmail">
            <div class="form-group">
               <input type="button" onsubmit="sendEmail();" id="sendE"
                  value="이메일 전송" />
            </div>
         </div>
            </form>
   </section>

   <footer>
      <div class="line"></div>
      <div id="copyright">
         2020 STRONG TESTER - All rights reserved <br /> ☎ contact |
         strongtester@naver.com
      </div>
   </footer>
</body>
</html>