<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험보는 창</title>
<link rel="stylesheet" href="./css/testInit.css" />
<link rel="icon" type="img/png" href="img/favicon.png" />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
	rel="stylesheet" />
</head>
<script type="text/javascript" src="./js/testLogin.js" defer></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
<body>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
	<nav id="navbar">
		<h2 id="title__nav">STRONG TESTER</h2>
	</nav>

	<section class="container">
		<form id="frm" method="post">
			<h3>인공지능</h3>
			<h4>시험 응시일: <span id="test__day">${date}</span></h4>
			<h4>시험 시간: <span id="test__time">${Stime} ~ ${Etime}</span></h4>
			
		
			<h4>학교명: 숭실대학교</h4>
			<div>
				<input type="text" id="inputName" name="student_name" placeholder="이름" autofocus />
			</div>
			<div>
				<input type="number" id="inputId" name="student_code" placeholder="학번" />
			</div>
			<div>
				<input type="text" id="inputUuid" name="student_uuid" placeholder="UUID" />
			</div>
			<input id="submit__input" type="button" name="login" value="시험 입장" class="disabled" disabled onclick="loginCheck()"/>

		</form>
		<form action="./webcamTest.do" method="POST">
			<input id="cam-test__button" type="submit" value="웹캠 테스트"/>
		</form>
	</section>
	
</body>
</html>