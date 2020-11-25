const $selectAll = document.querySelector("#select__all");
const $checkboxList = document.querySelectorAll(".checkbox");


$checkboxFlag = document.querySelectorAll(".data-flag");
$checkboxFlag.forEach((tag, i) => {
  if (tag.innerText === "Y") {
    $checkboxList[i].disabled = true;
    $checkboxList[i].classList.add("checkbox-disabled");
  }
});

$selectAll.addEventListener("change", () => {
  if ($selectAll.checked) {
    $checkboxList.forEach((v, i) => {
      if (!$checkboxList[i].classList.contains("checkbox-disabled")) {
        $checkboxList[i].checked = true;
      }
    });
  } else {
    // 전체선택해제
    $checkboxList.forEach((v, i) => {
      $checkboxList[i].checked = false;
    });
  }
});

// email
const $sendBtn = document.querySelector("#sendE");
$sendBtn.addEventListener("click", () => {
  // 전송버튼 클릭시 checkbox의 checked가 true인것의 value를 가져옴
  let stdArray = [];
  $checkboxList.forEach((v, i) => {
    if ($checkboxList[i].checked == true) {
      stdArray.push($checkboxList[i].value);
    }
  });

  sendEmail(stdArray);
});


function sendEmail(stdArray) {
//  console.log(stdArray);
  // stdArray = ["20162579", "20162623", "20162625"];
  var std_code = {
    "std_list": stdArray,
  };
  std_code = JSON.stringify(std_code);
  $.ajax({
    url: "./sendmail.do",
    data: std_code, // 학번을 post로 전달
    contentType: "application/json; charset=utf-8;",
    type: "POST",
    dataType: "json",
    success: function (msg) {
      if (msg.isc == "true") {
        swal("Email전송", "인증키 전송 성공");
      }else{
        swal("Email전송 실패", "인증키 전송 실패");
      }
    },
    error: function () {
      //alert("오류", "잘못된 요청입니다.");
    },
  });
}







function checkIt(stdCode){
   alert(stdCode);
   showInfo(stdCode);
}
function showInfo(stdNumber) {
      alert(stdNumber);
       //전달할 폼 영역
         var frm = document.getElementById("frm");
         frm.action = "./studentDetailView.do";
           $.ajax({
                url: "./selectDetail.do",
                data: "student_code=" + stdNumber, // 학번을 post로 전달
                type:"post",
                success: function (msg) {
                    //alert("학번전송", "인증키 전송 성공");
                    //alert(msg.student_code);
                    frm.stdId.value=msg.student_code;
                    frm.submit();
                },
                error: function () {
                  alert("오류", "잘못된 요청입니다.");
                },
              });
}

//11.21 추가
const $addBtn = document.querySelector('#student-add__button');
const $stdNumInput = document.querySelector('#std-num__input');
const $stdMajorInput = document.querySelector('#std-major__input');
const $stdNameInput = document.querySelector('#std-name__input');
const $stdEmailInput = document.querySelector('#std-email__input');


$stdNumInput.addEventListener('keydown', (e)=>{
  if ($stdNumInput.value.length >= 8){ // 학번 글자 수 제한
    $stdNumInput.value = $stdNumInput.value.substring(0, 8);
  } 
});

$addBtn.addEventListener('click', ()=>{
  alert($stdNumInput.value +$stdMajorInput.value+ $stdNameInput.value+$stdEmailInput.value);

});

// 시간설정 - 모달 열기
const $setTimeBtn = document.querySelector('#set-time__button');
const $modal = document.querySelector('.modal');
$setTimeBtn.addEventListener('click', () => {
  $modal.style.display = 'block';
});

//모달 닫기
const $closeBtn = document.querySelector('#close__button');
$closeBtn.addEventListener('click', () => {
  $modal.style.display = 'none';
});

//시간설정후 확인 
const $confirmTimeBtn = document.querySelector('#confirm-time__button');
const $startTime = document.querySelector('#start-time');
const $endTime = document.querySelector('#end-time');

$confirmTimeBtn.addEventListener('click', () => {
  startDate = $startTime.value.split('T')[0].split('-'); // 날짜
  year = startDate[0];
  month = startDate[1];
  day = startDate[2];

  startTime = $startTime.value.split('T')[1].split(':'); //T를 기준으로 스플릿 날짜/시간
  startH = startTime[0]; // 시작 시
  startM = startTime[1]; // 시작 분

  endTime = $endTime.value.split(':');
  endH = endTime[0];
  endM = endTime[1];

  if (startH > endH || startM >= endM ){
    alert('시작 시간은 종료시간 이전이어야 합니다.');
  }
  else{
    // TODO: 날짜를 DB에 저장해야됨.
    alert(`${year}-${month}-${day} ${startH}시 ${startM}분 ~ ${endH}시 ${endM}분`);
  }
});


//학생 삭제
const $deleteBtns = document.querySelectorAll('.delete__btn');
for (let i = 0; i < $deleteBtns.length ; i++){
  $deleteBtns[i].addEventListener('click', () => {
    // TODO: db에서 삭제
    alert(`${$deleteBtns[i].value}삭제`); 
  });
}



// 학생 정보 수정

//수정
const $editModal = document.querySelector('.edit__modal');
const $editBtns = document.querySelectorAll('.edit__btn');
const $closeBtn2 = document.querySelector('#close__button2');
$closeBtn2.addEventListener('click', (e) => {
e.target.parentNode.parentNode.parentNode.style.display = 'none';

});
for (let i = 0; i < $editBtns.length; i++){
$editBtns[i].addEventListener('click', () => {
 $editModal.style.display='block';
 const $studentInput = document.querySelectorAll('.edit__input');


 $studentInput.value = $editBtns[i].value;
 $tr = $editBtns[i].parentNode.parentNode; // tr
 
 tr = $tr.getElementsByTagName("td");


 for (let i = 1 ; i < 6; i++){    
   $studentInput[i-1].value = tr[i].innerHTML;
 }

})};

const $confirmEditBtn = document.querySelector('#confirm-edit__button');
$confirmEditBtn.addEventListener('click', (e) => {
  alert('수정완료');
});