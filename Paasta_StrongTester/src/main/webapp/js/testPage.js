"use strict";
const $saveBtn = document.querySelector("#save");
const $answer = document.querySelector("#answer1");
const $saveAlert = document.querySelector("#save__alert");
var curPos = 0; // 현재 문제 위치

const $nextBtn = document.querySelector(".next__button");
const $prevBtn = document.querySelector(".prev__button");
const $showPage = document.querySelector("#show__page");
const $questionAnswer = document.querySelector("#answer1");
const $questionNode = document.querySelector("#question");
const $questions = document.querySelectorAll(".question__list__item");
const $questionNumber = document.querySelector("#question__number");
//displayQuestion();

/* 뒤로 가기 버튼 시 */
ajax(0);
$questionAnswer.value = localStorage.getItem('answer1');
for(let i = 1; i < 11 ; i++){
	let localAnswer = localStorage.getItem(`answer${i}`);
	if (localAnswer){
		  $questions[i-1].classList.add("active");
	}
}

//임시저장 localStorge에 초기화
if (!localStorage.getItem("isFirstVisit")) {
  for (let i = 0; i < 10; i++) {
    localStorage.setItem(`answer${i + 1}`, "");
  }
}

/*
$answer.addEventListener("keydown", () => {
  $questions[curPos].classList.add("active");
});

 * *
 */

/* 임시 저장 알림 */
$saveBtn.addEventListener("click", () => {
	$questions[curPos].classList.add("active");
	$answer.addEventListener("keydown", () => {
		  $questions[curPos].classList.add("active");
		});

	
   localStorage.setItem("isFirstVisit", "Y");
  localStorage.setItem(`answer${curPos + 1}`, $answer.value);
  saveAlertAnimation();
  ajax(curPos - 1);
   function ajax(i) {
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function (data) {

      const obj = JSON.parse(this.response);
      obj.answers[i] = localStorage.getItem(`answer${curPos + 1}`);
    });
    oReq.open("GET", "./js/question-json.txt"); // key
    oReq.send();
  }
});

// 임시 저장 내용 불러오기 : 작성한 답
function loadAnswer() {
  if (localStorage.getItem(`answer${curPos + 1}`)) {
    const savedAnswer = localStorage.getItem(`answer${curPos + 1}`);
    $answer.value = savedAnswer;
  } else {
    $answer.value = "";
  }
}

// 키 제어 : ctrl c, ctrl v 방지 
document.addEventListener("keydown", (e) => {
  if ((e.ctrlKey && e.key === "c") || (e.ctrlKey && e.key === "v")) {
    e.returnValue = false;
  }
});

$nextBtn.addEventListener("click", () => {
  if (curPos >= 9) {
    //이동 불가 , 버튼색바뀜
  } else {
    curPos += 1;
  }
  displayQuestion();
});

$prevBtn.addEventListener("click", () => {
  if (curPos <= 0) {
    //이동 불가 , 버튼색바뀜
  } else {
    curPos -= 1;
  }
  displayQuestion();
});

// jQuery
$(document).ready(function () {
  // 화면 사이즈 변경시 가림
  $(window).resize(function () {
    var heightSize = window.outerHeight;
    var widthSize = window.outerWidth;
   
    if (
      heightSize < screen.availHeight - 10 ||
      widthSize < screen.availWidth - 10
    ) {
      $("#resize__dialog").css("display", "block");
    } else {
      $("#resize__dialog").css("display", "none");
    }
  });
});

//ajax
function ajax(i) {
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function (data) {
    const obj = JSON.parse(this.response);
    $questionNode.innerText = obj.questions[i];
    $questionNumber.innerText = `문제 ${obj.numbers[i]}`;

    $questionAnswer.value = localStorage.getItem(`answer${i+1}`);// 지운부분
  }); 
  oReq.open("GET", "./js/question-json.txt"); // key
  oReq.send();
}


//문제에 클릭이벤트
for (let i = 0; i < $questions.length; i++) {
  $questions[i].addEventListener("click", () => {
    curPos = i;
    displayQuestion();
  });
}

function displayQuestion() {
  ajax(curPos);

  loadAnswer();
  if (curPos == 9) {
    $nextBtn.classList.add("non-active");
  } else {
    $nextBtn.classList.remove("non-active");
  }
  if (curPos == 0) {
    $prevBtn.classList.add("non-active");
  } else {
    $prevBtn.classList.remove("non-active");
  }
  $showPage.innerText = `${curPos + 1}/10`;
}

// 임시저장 Animation
function saveAlertAnimation() {
  var height = 0;
  var opacity = 0;
  var id = setInterval(frame, 0.5);
  $saveAlert.style.display = "block";

  function frame() {

    if (height == 50) {
      clearInterval(id);
      setTimeout(() => {
        $saveAlert.style.display = "none";
        $saveAlert.style.opacity = 0;
        $saveAlert.style.height = 0 + "px";
      }, 700);
    } else {
      height++;
      opacity += 0.02;
      $saveAlert.style.height = height + "px";
      $saveAlert.style.opacity = opacity;
    }
  }
}

//제출 버튼
const $submitBtn = document.querySelector("#submit");
const $modal = document.querySelector(".modal");
$submitBtn.addEventListener("click", () => {
  $modal.style.display = "block";
});

// 모달 > 제출
const $confirmBtn = document.querySelector("#confirm");
$confirmBtn.addEventListener("click", () => {
  var answer = [];
  for (var i = 1; i <= 10; i++) {
    answer.push(localStorage.getItem(`answer${i}`));
  }

  ajaxExample();

});

/* 시험 제출 */
function ajaxExample() {
  var answer = [];
  for (var i = 1; i <= 10; i++) {
    answer.push(localStorage.getItem(`answer${i}`));
  }

  var allData = {
    "1": answer[0],
    "2": answer[1],
    "3": answer[2],
    "4": answer[3],
    "5": answer[4],
    "6": answer[5],
    "7": answer[6],
    "8": answer[7],
    "9": answer[8],
    "10": answer[9]
  };

  cleanLocalStorage();

  
  allData = JSON.stringify(allData);
  $.ajax({
    url: "./testAfter.do",
    type: "POST",
    data: allData,
    contentType: "application/json; charset=utf-8;",
    dataType: "json",
    success: function (data) {
       if(data.isc=="true"){
           alert("완료!");
           location.href="./testAftersummit.do";
       }else{
          alert("저장 실패");
       }
    },
    error: function () {
      alert("에러 \n");
    },
  });
}

function cleanLocalStorage(){
	  localStorage.removeItem("isFirstVisit");
	  for (var i = 1; i <= 10; i++) {
	    localStorage.removeItem(`answer${i}`);
	  }
	  localStorage.removeItem('endTime');
}


const $cancelBtn = document.querySelector("#cancel");
$cancelBtn.addEventListener("click", () => {
  $modal.style.display = "none";
});

document.oncontextmenu = function (e) {
  alert("마우스 우클릭 사용 금지입니다. ");
  return false;
};

/* Timer */
const monthDic = { 1: 'January', 2: 'February', 3: 'March', 4: 'April', 5: 'May', 6: 'June', 
	      7: 'July', 8: 'August', 9: 'September' , 10: 'October', 11: 'November', 12: 'December'};

var endTimeVal = JSON.parse(localStorage.getItem('endTime'));
var endTime = new Date(`${monthDic[endTimeVal['month']]} ${endTimeVal['date']}, ${endTimeVal['year']} ${endTimeVal['hour']}:${endTimeVal['minute']}:00`);
var nowTime = new Date();
var diff = endTime.getTime() - nowTime.getTime();

function leftTimeCalculate(millisec) {
  var seconds = (millisec / 1000).toFixed(0);
  var minutes = Math.floor(seconds / 60);
  var hours = "";
  if (minutes > 59) {
    hours = Math.floor(minutes / 60);
    hours = hours >= 10 ? hours : "0" + hours;
    minutes = minutes - hours * 60;
  }
  minutes = minutes >= 10 ? minutes : "0" + minutes;

  seconds = Math.floor(seconds % 60);
  seconds = seconds >= 10 ? seconds : "0" + seconds;
  if (hours != "") {
    return hours + "시간 " + minutes + "분 " + seconds + "초";
  }
  else if(minutes == "00"){
	  return seconds + "초"; 
  }
  return minutes + "분 " + seconds + "초";
}

const $timeLeft = document.querySelector("#time-left");
let timer = setInterval(function () {
  var nowTime = new Date();
  var diff = endTime.getTime() - nowTime.getTime();
  let time = leftTimeCalculate(diff);
  if (time < 0){
	  $timeLeft.innerText = `시험 종료`;
  }
  else{
  $timeLeft.innerText = `종료까지 남은 시간:  ${time}`;
  }
  if (diff <= 0) {
    clearInterval(timer);
    cleanLocalStorage();
    window.location.href = "./testAftersummit.do"; 
  }
}, 1000);


