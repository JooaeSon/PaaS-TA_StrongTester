//시험 시간 표시
const $timeDisplay = document.querySelector('#test__time');

// TODO: DB에서 시간 가져오기

// 임시값
/*
y = 2020
m = 01
d = 12
sh = 01
sm = 11
eh = 02
em = 22
$timeDisplay.innerText = `${y}년 ${m}월 ${d}일 ${sh}시 ${sm}분 ~ ${eh}시 ${em}분`;

*/



function loginCheck(){
	
	
	
   var name = document.getElementById("inputName").value;
   var id = document.getElementById("inputId").value; //$(inputId).val()
   var uuid = document.getElementById("inputUuid").value;
   
   //전달할 폼 영역
   var frm = document.getElementById("frm");
   frm.action = "./TestStart.do";
   
 
   
   var result = "";
   
   if(name==null || name.trim()==""){
      document.getElementById("inputName").focus();
      $("#inputName").val();
      swal("StrongTester","이름을 입력해주세요");
   }else if(id==null || id.trim()==""){
      document.getElementById("inputId").focus();
      $("#inputId").val();
      swal("StrongTester","학번을 입력해주세요");
   }else if(uuid==null || uuid.trim()==""){
      document.getElementById("inputUuid").focus();
      $("#inputUuid").val();
      swal("StrongTester","UUID를 입력해주세요");
   }else{
      // 주의사항 alert
      let testAlert = "1. 화상 화면에 눈코입이 다 들어와야됩니다.\n2. 크롬에서 시험을 봐주세요.\n3. 인터넷 연결을 확인해주세요.";
      testAlert.replace(/\n/g, '<br/>');

      swal("시험 전 주의사항",testAlert, "info");
      
      const $confirmBtn = document.querySelector('.swal-button');

	   $confirmBtn.addEventListener('click', () => {
	      $.ajax({
	      url:"./loginCheckMap.do",
	      type:"post",
	      data:"student_name="+name+"&student_code="+id+"&student_uuid="+uuid,
	      success:function(msg){
	         //swal(msg.isc);
	         frm.submit();
	      },error:function(){
	         swal("StrongTester", "시험응시정보에 문제가 있습니다.");
	      }
	      });
      
      
      
   });
   }
}



