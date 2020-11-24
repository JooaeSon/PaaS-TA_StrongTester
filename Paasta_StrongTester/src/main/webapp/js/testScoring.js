$questionList = document.querySelectorAll(".question__content");
$correctAnswerList = document.querySelectorAll(".answer-correct__text");
ajax();
console.log($correctAnswerList);
//문제 불러오기
function ajax() {
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function (data) {
    const obj = JSON.parse(this.response);
    $questionList.forEach((v, i) => {
      $questionList[i].innerText = obj.questions[i];
      $correctAnswerList[i].innerText = obj.correctAnswers[i];
//      $correctAnswerList[i].innerText = obj.correctAnswers[i];
    });
  });
  oReq.open("GET", "./js/question-json.txt"); // key
  oReq.send();
}
