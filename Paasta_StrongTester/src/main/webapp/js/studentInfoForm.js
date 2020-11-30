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
                    //frm.submit();
                },
                error: function () {
                  alert("오류", "잘못된 요청입니다.");
                },
              });
}

const $addBtn = document.querySelector('#student-add__button');
const $stdNumInput = document.querySelector('#std-num__input');
const $stdMajorInput = document.querySelector('#std-major__input');
const $stdNameInput = document.querySelector('#std-name__input');
const $stdEmailInput = document.querySelector('#std-email__input');


$stdNumInput.addEventListener('keydown', ()=>{
	limitStdNum($stdNumInput);
});

//학번 글자수 입력 제한
function limitStdNum(input){
	if (input.value.length >= 8){
		input.value = input.value.substring(0, 8); 
	}
}

// 학생 추가
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
	if ($startTime.value == '' || $endTime.value == ''){
		swal('','시간을 선택해 주세요.','info');	
	}
	else{
		
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
	  
	  if (startH > endH || (startH == endH && startM > endM)){
		  swal('','시작 시간은 종료시간 이전이어야 합니다.', 'info');
	  }
	  else{
	    // TODO: 날짜를 DB에 저장해야됨.
	    alert(`${year}-${month}-${day} ${startH}시 ${startM}분 ~ ${endH}시 ${endM}분`);
	    var startTest=""+year+"-"+month+"-"+day+"T"+startH+":"+startM+"";
	    var endTest=""+year+"-"+month+"-"+day+"T"+endH+":"+endM+"";
	    location.href="./setTestTime.do?test_start="+startTest+"&test_end="+endTest;
	  }
  }

});


//학생 삭제
function del(stdCode){
   //alert(stdCode);
   location.href="./deleteStudent.do?student_code="+stdCode;
}

const $deleteBtns = document.querySelectorAll('.delete__btn');
for (let i = 0; i < $deleteBtns.length ; i++){
  $deleteBtns[i].addEventListener('click', () => {
    // TODO: db에서 삭제
	stdCode=$deleteBtns[i].value;
    swal({
    	  title: "Are you sure?",
    	  text: "삭제하는 순간 모든 학생의 시험정보와 답안이 삭제됩니다.    \n 정말 삭제하시겠습니까?",
    	  icon: "warning",
    	  buttons: true,
    	  dangerMode: true,
    	})
    	.then((willDelete) => {
    	  if (willDelete) {
    		del(stdCode);
    	    swal("삭제했습니다.", {
    	      icon: "success",
    	    });
    	  }
    	});
  });
}



// 학생 정보 수정
const $editModal = document.querySelector('.edit__modal');
const $editBtns = document.querySelectorAll('.edit__btn');
const $closeBtn2 = document.querySelector('#close__button2');
$closeBtn2.addEventListener('click', (e) => {
	e.target.parentNode.parentNode.parentNode.parentNode.style.display = 'none';
});

const $studentInput = document.querySelectorAll('.edit__input');


for (let i = 0; i < $editBtns.length; i++){
$editBtns[i].addEventListener('click', () => {
	
 $editModal.style.display='block';

 $studentInput.value = $editBtns[i].value;
 $tr = $editBtns[i].parentNode.parentNode; // tr
 
 tr = $tr.getElementsByTagName("td");

 $studentInput[0].value = tr[1].innerHTML;
 for (let i = 1 ; i < 6; i++){    
   $studentInput[i].value = tr[i].innerHTML;
 }
})};

const $confirmEditBtn = document.querySelector('#confirm-edit__button');
$confirmEditBtn.addEventListener('click', (e) => {
	$.ajax({
		url:"./updateStdInfo.do",
		type:"post",
		data:"seq="+$studentInput[0].value+"&student_code="+$studentInput[1].value+
		"&student_deptm="+$studentInput[2].value+"&student_name="+$studentInput[3].value+
		"&student_email="+$studentInput[4].value+"&test_flag="+$studentInput[5].value,
		success:function(msg){
			if(msg=="successUpdate"){
				//alert("ddd");
				swal('수정 완료', '수정되었습니다.','success');
				
				let $confirmBtn = document.querySelector('.swal-button--confirm');
				$confirmBtn.addEventListener('click', () => {
					location.href='./studentsInfo.do';
				});
				 
			}else{
				swal('이미 존재하는 학번입니다.', '다른 학번을 입력해주세요.', 'info');				
			}
			
		},
		error:function(){
			alert("잘못된 요청입니다.");
		}
	});

});

function goAllList(){
	location.href="./studentsInfo.do";
}

// time display
const $time__display = document.querySelector('#time__display');


/*
if (DB 시간이 정해져있지 않으면){
    $time__display.innerText = '';
}
else{
	sh = 시작 시;
	sm = 시작 분;
	eh = 끝나는 시;
	em = 끝나는 분;
    $time__display.innerHTML = `<i class="far fa-clock"></i> ${sh}시 ${sm}분 ~ ${eh}시 {em}분`;
}
*/

/*
sh = 1;
sm = 2;
eh = 1;
em = 22;
$time__display.innerHTML = `<i class="far fa-clock"></i> 2020-01-01  ${sh}시 ${sm}분 ~ ${eh}시 ${em}분`;
*/
