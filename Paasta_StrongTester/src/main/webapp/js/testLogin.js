/* Display test time */
const $timeDisplay = document.querySelector('#test__time');
const $dateDisplay = document.querySelector('#test__day');
const timeVal = $timeDisplay.innerText;
const $loginBtn = document.querySelector("#submit__input");
let isSetTime = true;

if(timeVal.indexOf(':') == -1){
	isSetTime = false;
}

if (isSetTime){
  getStartTime(timeVal);
 
}else{
	$timeDisplay.innerHTML= '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';	
	$dateDisplay.innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
}


function getTestDate(){
	
	let dateVal = $dateDisplay.innerText;
	dateVal = dateVal.split('-');
	return dateVal
	
}
function getStartTime(timeVal){

	timeVal = timeVal.split(' ');
	let dateVal = getTestDate();
	let startTime = timeVal[0].split(':');
	let endTime = getEndTime(timeVal);
	// 로컬스토리지에 시험 끝나는 시간 저장	
	let obj = {year: dateVal[0], month: dateVal[1], date: dateVal[2], hour: endTime[0], minute: endTime[1]};
	localStorage.setItem('endTime', JSON.stringify(obj));
	
	return [dateVal[0], dateVal[1], dateVal[2], startTime[0], startTime[1]];
}

function getEndTime(timeVal){
	
	let endTime = timeVal[2].split(':');
	console.log(endTime, 'endTime return');
	return endTime;
}



/* Timer */
var nowTime = new Date();
var startTime = isSetTime ? getStartTime(timeVal) : 00;
const monthDic = { 1: 'January', 2: 'February', 3: 'March', 4: 'April', 5: 'May', 6: 'June', 
		7: 'July', 8: 'August', 9: 'September' , 10: 'October', 11: 'November', 12: 'December'};

var startTime = new Date(`${monthDic[startTime[1]]} ${startTime[2]}, ${startTime[0]} ${startTime[3]}:${startTime[4]}:00`);
var diff = startTime.getTime() - nowTime.getTime();

function leftTimeCalculate(millisec) {
  var seconds = (millisec / 1000).toFixed(0);
  var minutes = Math.floor(seconds / 60);
  var hours = "";
  if (minutes > 59) {
    hours = Math.floor(minutes / 60);
    hours = hours >= 10 ? hours : "0" + hours;
    minutes = minutes - hours * 60;
    minutes = minutes >= 10 ? minutes : "0" + minutes;
  }

  seconds = Math.floor(seconds % 60);
  seconds = seconds >= 10 ? seconds : "0" + seconds;
  if (hours != "") {
    return hours + "시간 " + minutes + "분 " + seconds + "초";
  }
  return minutes + "분 " + seconds + "초";
}


let testStart = false;
console.log(startTime);
let timer = setInterval(function () {

  var nowTime = new Date();
  var diff = startTime.getTime() - nowTime.getTime();
  
  let time = leftTimeCalculate(diff);
  $loginBtn.value = `${time} 후 시작`;
  
  if (!isSetTime){
	  $loginBtn.value = '입장 불가';
	  clearInterval(timer);
  }
  else if (diff <= 0) { // 시험 시작 시간이 지나면
    clearInterval(timer);
    $loginBtn.value = '시험 입장';
    //isTestEnd();
    
    //timerEnd();
    testStart = true;
  }
  else{
  }
}, 1000);



/* Check isTestEnd */
function isTestEnd(){
	
   let diff = getEndDiff();
   //console.log(diff);
	if (diff >= 0){ // 입장 가능
		$loginBtn.disabled = false;
//		$loginBtn.removeAttribute('disabled');
		   $loginBtn.classList.add('active');
		   $loginBtn.classList.remove('disabled');
		   //console.log('false');
		 return false;
	}
	return true;
}

	let timerInter = setInterval(function () {

		if(testStart && isTestEnd()){ // 시험 끝 입장불가
			 clearInterval(timerInter);
			 $loginBtn.disabled = true;
			   $loginBtn.classList.add('disabled');
			   $loginBtn.classList.remove('active');
		}
	}, 1000);
	







/* get EndTime List*/
function getEndDiff(){
	let nowTime = new Date();
  let testDate = getTestDate();
  let endTimeVal = isSetTime? timeVal.split(' ')[2].split(' ')[0].split(':') : 00;
  let endTimeList = [testDate[0], testDate[1], testDate[2], endTimeVal[0], endTimeVal[1]];
  let endTime = new Date(`${monthDic[testDate[1]]} ${testDate[2]}, ${testDate[0]} ${endTimeVal[0]}:${endTimeVal[1]}:00`);
  let diff = endTime.getTime() - nowTime.getTime();
 // console.log(nowTime.getTime());
  return diff;
}

/* Check login */
function loginCheck(){

	
	
   var name = document.getElementById("inputName").value;
   var id = document.getElementById("inputId").value; 
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
      let testAlert = "1. 화상 화면에 눈,코,입이 다 들어와야됩니다.\n2. 크롬에서 시험을 봐주세요.\n3. 인터넷 연결을 확인해주세요.";
      testAlert.replace(/\n/g, '<br/>');

      swal("시험 전 주의사항",testAlert, "info");
      
      const $confirmBtn = document.querySelector('.swal-button');

	   $confirmBtn.addEventListener('click', () => {
		   
	      $.ajax({
	      url:"./loginCheckMap.do",
	      type:"post",
	      data:"student_name="+name+"&student_code="+id+"&student_uuid="+uuid,
	      success:function(msg){
	         frm.submit();
	      },error:function(){
	         swal("StrongTester", "시험응시정보에 문제가 있습니다.");
	      }
	      });
      
      
      
   });
   }
}



