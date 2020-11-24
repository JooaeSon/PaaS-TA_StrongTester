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
                data: "student_code="+stdNumber, // 학번을 post로 전달
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


//