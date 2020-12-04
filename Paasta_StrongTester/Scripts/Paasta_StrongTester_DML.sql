#DATA 검색
SELECT * FROM TESTER;
SELECT * FROM STUDENT;
SELECT * FROM STUDENTCAPTURE;
SELECT * FROM STUDENTANSWER order by student_code;

delete from student;
update tester set TEST_START=null , TEST_END=null;

select student_uuid from student where STUDENT_CODE='20162579';

update student set STUDENT_UUID ='a1248d71-6fd7-4079-b9ff-1c1ed77ea8a5';
delete from student where STUDENT_CODE='20172391';

DELETE FROM STUDENTCAPTURE;

WHERE VIDEO_NUM='5';

#웹캠 링크 업데이트 (임의로 사용)
UPDATE STUDENTCAPTURE SET CAPTURE_CONTENT='SSUITA033212201624232020101217-07-05.mp4';

#DATA 기본 정보 생성--
#-TESTER 테이블 디폴트 데이터
INSERT INTO TESTER(TEST_CODE, USER_ID, TEST_NUMOFQUST, TEST_NAME, TEST_DELFLAG) 
VALUES('A033212', 'SSUIT', '10', '인공지능','N');

#STUDENT 테이블 디폴트 데이터
INSERT INTO STUDENT(STUDENT_CODE, TEST_CODE, USER_ID,
	STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG) 
VALUES('20162579','A033212', 'SSUIT', '글로벌미디어학부', '손주애', '9apples1718@gmail.com', 'N');

INSERT INTO STUDENT(STUDENT_CODE, TEST_CODE, USER_ID,
	STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG) 
VALUES('20162423','A033212', 'SSUIT', '간호학과', '백주영', 'qorwndud4965@naver.com', 'N');

INSERT INTO STUDENT(STUDENT_CODE, TEST_CODE, USER_ID,
	STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG) 
VALUES('20162580','A033212', 'SSUIT', '유아교육과', '유하경', 'shyoo447@naver.com', 'N');

UPDATE STUDENT SET STUDENT_EMAIL ='daon9apples@naver.com'
WHERE STUDENT_CODE ='20162579';

UPDATE STUDENT SET STUDENT_DEPTM='글로벌미디어학부'
WHERE STUDENT_CODE='20162579';

UPDATE STUDENT SET TEST_FLAG='N';

UPDATE STUDENTANSWER SET STUDENT_SCORE='' , STUDENT_ANSWER='';

INSERT INTO STUDENT(STUDENT_CODE, TEST_CODE, USER_ID,
	STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG) 
VALUES('20162625','A033212', 'SSUIT', '글로벌미디어학부', '현은빈', 'dmsqlsgus@naver.com', 'N');

INSERT INTO STUDENT(STUDENT_CODE, TEST_CODE, USER_ID,
	STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG) 
VALUES('20162623','A033212', 'SSUIT', '글로벌미디어학부', '최지영', 'curryisgood@naver.com', 'N');

#STUDENTANSWER 테이블 디폴트 데이터
INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('1', '20162579', 'A033212', 'SSUIT', '20');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('2', '20162579', 'A033212', 'SSUIT', '17');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('3', '20162579', 'A033212', 'SSUIT', '15');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('4', '20162579', 'A033212', 'SSUIT', '20');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('5', '20162579', 'A033212', 'SSUIT', '20');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('6', '20162579', 'A033212', 'SSUIT', '20');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('7', '20162579', 'A033212', 'SSUIT', '10');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('8', '20162579', 'A033212', 'SSUIT', '5');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('9', '20162579', 'A033212', 'SSUIT', '12');

INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_SCORE) 
VALUES ('10', '20162579', 'A033212', 'SSUIT', '20');

UPDATE STUDENTANSWER SET STUDENT_ANSWER= '안녕하세요'
WHERE STUDENT_CODE ='20162579' AND TEST_NUM='1';

SELECT * FROM STUDENTANSWER;
delete from studentanswer;
delete from studentcapture;
#웹캠 정보 세팅
INSERT INTO STUDENTCAPTURE (TEST_CODE, STUDENT_CODE, USER_ID)
VALUES ('A033212', '20162623', 'SSUIT');
#--학생입력----------------------------------------------------------
#학생기본정보 입력(학번, 테스코드, 주관사ID, 학과, 이름, 이메일, 응시여부)
insert into STUDENT (STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_DEPTM, STUDENT_NAME, STUDENT_EMAIL, TEST_FLAG)
values ('20172391', 'A033212', 'SSUIT', '컴퓨터공학부', '하진우', 'gkwlsdn@naver.com', 'N');

#학생 답안 기본 정보 세팅
INSERT INTO STUDENTANSWER (TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID) 
VALUES ('5', '20162579', 'A033212', 'SSUIT');

#학번 중복성 체크
select count(student_code) from student where student_code='20162579';

#--학생 수정-----------------------------------------------------------------
update student set STUDENT_CODE='20192031', STUDENT_NAME='김지원', STUDENT_EMAIL='onsemiro@naver.com', STUDENT_DEPTM='글로벌미디어학부', TEST_FLAG='N'
where TEST_CODE='A033212' and USER_ID ='SSUIT' and STUDENT_CODE ='20192032';

#--학생 삭제----------------------------------------------------------------------
delete from student where STUDENT_CODE='20162623';

#시험시간 설정
update tester set TEST_START='90DD', TEST_END='1023'
where TEST_CODE='A033212' and USER_ID ='SSUIT';

#시험시간 조회
SELECT TEST_START, TEST_END FROM TESTER
WHERE TEST_CODE='A033212' and USER_ID ='SSUIT';

#1.학생 정보 출력(학번, 이름, 이메일, 학과, 응시 여부)
SELECT STUDENT_CODE, STUDENT_NAME, STUDENT_EMAIL, STUDENT_DEPTM, TEST_FLAG
FROM STUDENT
WHERE TEST_CODE='A033212' AND USER_ID='SSUIT';

#2.UUID생성 (메일로 보내기전에 생성-같은 학교, 같은 시험 보는 학생들에게 동일하게 부여)
UPDATE STUDENT SET STUDENT_UUID='a1248d71-6ddddddddddfd7-4079-b9ff-1c1ed77ea8a5'
WHERE STUDENT_CODE='20162579' AND TEST_CODE='A033212' AND USER_ID='SSUIT';

select * from student;

#3.메일로 보낼 정보들(학번, 학과, 학생이름, 학생이메일, UUID, 링크)
SELECT STUDENT_CODE, TEST_CODE, USER_ID, STUDENT_DEPTM,
STUDENT_NAME, STUDENT_EMAIL, STUDENT_UUID, TEST_LINK
FROM STUDENT
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212' AND STUDENT_CODE='20162579';

#4.수험자 시험응시 응시로그인 확인(시험코드, 학번, UUID)
SELECT STUDENT_CODE, STUDENT_NAME, STUDENT_UUID
FROM STUDENT
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212'
AND STUDENT_CODE='20162579' AND STUDENT_UUID='0f4d0b29-023e-4e5d-970d-8689c1d31dea';

#5.IP 정보 입력(응시로그인하는 순간)
UPDATE STUDENT SET STUDENT_IP='190.803.123'
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212' 
AND STUDENT_CODE='20162579' AND TEST_FLAG='N';

#6.시험 응시 여부 선행 확인
SELECT TEST_FLAG
FROM STUDENT
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212'
AND STUDENT_CODE='20162579';

#7.시험응시 여부 'Y'로 갱신
UPDATE STUDENT SET TEST_FLAG='Y'
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212'
AND STUDENT_CODE='20162579';

#8.답안 저장. 응시자가 시험 본 답안을 저장함.
UPDATE STUDENTANSWER SET STUDENT_ANSWER='케스케이드 모델', STUDENT_SCORE='30'
WHERE USER_ID='SSUIT' AND TEST_CODE='A033212'
AND STUDENT_CODE='20162579' AND TEST_NUM='1';

#9.시험 세부사항 조회: 이름, 학번, 학과, IP, 이메일, 총점
SELECT STUDENT_NAME, STUDENT_CODE, 
STUDENT_DEPTM, STUDENT_IP, STUDENT_EMAIL
FROM STUDENT
WHERE USER_ID='SSUIT' AND TEST_CODE ='A033212'
AND STUDENT_CODE ='20162579';

#ip수정
UPDATE STUDENT SET STUDENT_IP ='10.21.20.122'
WHERE STUDENT_CODE ='20162625';

#10.시험 총점 구하기
SELECT SUM(STUDENT_SCORE) FROM STUDENTANSWER
	WHERE USER_ID='SSUIT' AND TEST_CODE ='A033212'
	AND STUDENT_CODE ='20162579';

#11.답안 테이블 답안 및 점수 조회
SELECT TEST_NUM, STUDENT_SCORE, STUDENT_ANSWER
FROM STUDENTANSWER
WHERE USER_ID='SSUIT' AND TEST_CODE ='A033212'
AND STUDENT_CODE ='20162579';

#12.비디오 칼럼 새로 생성
INSERT INTO STUDENTCAPTURE (STUDENT_CODE, TEST_CODE, USER_ID, CAPTURE_CONTENT)
VALUES('20162580', 'A033212', 'SSUIT', 'SSUITA033212201624232020111917-07-05.mp4');

SELECT * FROM STUDENTCAPTURE;

DELETE FROM STUDENTCAPTURE;

#13.수험자 개인 캡처 비디오 클립 링크 조회
SELECT CAPTURE_CONTENT FROM STUDENTCAPTURE
WHERE TEST_CODE='A033212' AND USER_ID='SSUIT' AND STUDENT_CODE='20162579';

