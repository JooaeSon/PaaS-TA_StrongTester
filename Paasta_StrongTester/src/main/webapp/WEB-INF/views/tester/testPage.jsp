<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험지</title>
<link rel="icon" type="img/png" href="img/favicon.png" />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./css/testPage.css" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/12ed482fdd.js" crossorigin="anonymous"></script>
<script src="./js/testPage.js" defer></script>
<body oncontextmenu="return false" ondragstart="return false"
	onselectstart="return false">
	<nav id="navbar">
		<h2 id="title__nav">과목명: 인공지능</h2>
		<span> <span id="time-left">종료까지 남은 시간: 00분 00초</span>

			<button id="submit">제출</button>
		</span>
	</nav>
	<div id="save__alert">
		<span>임시저장했습니다.</span>
	</div>
	<!-- 문제 번호 -->
	<div>
		<ul id="question__list">
			<li>문제:</li>
			<li class="question__list__item">1</li>
			<li class="question__list__item">2</li>
			<li class="question__list__item">3</li>
			<li class="question__list__item">4</li>
			<li class="question__list__item">5</li>
			<li class="question__list__item">6</li>
			<li class="question__list__item">7</li>
			<li class="question__list__item">8</li>
			<li class="question__list__item">9</li>
			<li class="question__list__item">10</li>
		</ul>
	</div>
	<!-- 문제푸는 곳-->
	<section id="test__page">
		<h3 id="question__number"></h3>
		<div id="question"></div>
		<div class="answer">
			<textarea name="" id="answer1" cols="30" rows="10" maxlength="2000"
				placeholder="이곳에 답안을 작성해주세요."></textarea>
		</div>
	</section>

	<footer class="footer">
		<div id="show__page">1/15</div>
		<div class="footer__button">
			<button class="prev__button">
				<i class="fas fa-chevron-left"></i> 이전 문제
			</button>
			<button id="save">임시 저장</button>
			<button class="next__button">
				다음 문제 <i class="fas fa-chevron-right"></i>
			</button>
		</div>
	</footer>

	<section class="modal">
		<article class="modal__content">
			<div>
				<h2>답안을 제출하시겠습니까?</h2>
				<p>답안을 제출하시면 시험이 종료되고 수정이 불가합니다.</p>
				<div class="buttons">
					<button id="confirm">확인</button>
					<button id="cancel">취소</button>
				</div>
			</div>
		</article>
	</section>
	<div id="resize__dialog">브라우저 크기를 최대로 해주세요.</div>
</body>
</html>