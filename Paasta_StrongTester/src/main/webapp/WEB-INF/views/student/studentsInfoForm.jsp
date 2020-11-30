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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://kit.fontawesome.com/12ed482fdd.js"
   crossorigin="anonymous"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./js/studentInfoForm.js" defer></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<body>

   <nav id="navbar">
      <div>
         <img id="logoImg" src="img/logoImg.png" width="30px"> <a
            href="home.do" id="logo">STRONG TESTER</a>
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

      <article>

         <div id="set__time">
            <button id="set-time__button">시험 시간 설정</button>
            <span id="time__display"><i class="far fa-clock"></i> ${date} ${sh}시 ${sm}분 ~ ${eh}시 ${em}분</span>
         </div>
         <div id="student__add__article">

            <div>
               <form id="student__inputs" action="./insertStudent.do" method="POST">

                  <input type="number" id="std-num__input" name="student_code" class="student__input"
                     placeholder="학번" required> <input type="text"
                     id="std-major__input" name="student_deptm" class="student__input" placeholder="학과"
                     required> <input type="text" id="std-name__input" name="student_name"
                     class="student__input" placeholder="이름" required> <input
                     type="email" id="std-email__input" name="student_email" class="student__input"
                     placeholder="이메일" required>

                  <button type="submit" id="student-add__button">
                     <i class="fas fa-plus-square"></i>
                  </button>

               </form>
            </div>
         </div>
      </article>





      <form id="frm" method="POST">
         <table class="student__table table table-striped table-bordered"
            id="allTable">

            <thead>
               <tr>
                  <th><input type="checkbox" id="select__all" /></th>
                  <th width="100">학번</th>
                  <th>학과</th>
                  <th>이름</th>
                  <th>이메일</th>
                  <th width="100">응시여부</th>
                  <th width="100">세부사항</th>
                  <th colspan="2">Actions</th>
               </tr>
            </thead>
            <tbody id="tbody">
				<jsp:useBean id="format" class="com.min.hb.bean.InputList" scope="page"/>
               	<jsp:setProperty property="lists" name="format" value="${stdAllList}"/>
            	<jsp:getProperty property="listForm" name="format"/>
            </tbody>
         </table>

         <div id="chkEmail">
            <div class="form-group">
               <input type="button" id="sendE"
                  value="이메일 전송" />
            </div>
         </div>
      </form>
   </section>

   <section class="modal">
      <article class="modal__content">
         <h2>시험 시간 설정</h2>

         <div>
            <button id="close__button" class="close__button" type="button">X</button>
            <label for="">시작</label> <input id="start-time"
               type="datetime-local"><label for="">종료</label> <input
               id="end-time" type="time">
          

         </div>
           <div>
               <button id="confirm-time__button" class="confirm__button">확인</button>

            </div>
      </article>
   </section>


   <!-- 학생 수정 모달-->
   <section class="edit__modal">
      <article class="edit__modal__content">
      <form id="updateStudentInfo" action="./updateStdInfo.do" method="POST">
         <h2>학생 수정</h2>
         
            <div>
               <button id="close__button2" class="close__button" type="button" data-dismiss='modal'>X</button>
               <div>
               	  <input type="hidden" name="seq" class="student__input edit__input">
                  <label for="">학번</label> <input type="number"
                     name="student_code" class="student__input edit__input" required>
               </div>
               <div>
                  <label for="">학과</label> <input type="text"
                     name="student_deptm" class="student__input edit__input" required>
               </div>
               <div>
                  <label for="">이름</label> <input id="text"
                     name="student_name" class="student__input edit__input" required>
               </div>
               <div>
                  <label for="">이메일</label> <input type="email"
                     name="student_email" class="student__input edit__input" required>
               </div>
               <div>
                  <label for="">응시 여부 (Y / N)</label> <input type="text"
                     name="test_flag" class="student__input  edit__input" required>
               </div>
              

            </div>
             <div>
                  <button type="button" id="confirm-edit__button" class="confirm__button">확인</button>
               </div>
         </form>
      </article>
   </section>
   <!--End-->

   <footer>
      <div class="line"></div>
      <div id="copyright">
         2020 STRONG TESTER - All rights reserved <br /> ☎ contact |
         strongtester@naver.com
      </div>
   </footer>
</body>
</html>