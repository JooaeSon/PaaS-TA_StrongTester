select * from student;
select * from tester;
select * from studentanswer order by student_code;
select * from studentcapture order by student_code;

#테이블 만들기
CREATE TABLE TESTER(
	TEST_CODE VARCHAR(30) NOT NULL,
	USER_ID VARCHAR(30) NOT NULL,
	TEST_NUMOFQUST VARCHAR(10),
	TEST_START VARCHAR(50),
	TEST_END VARCHAR(50),
	TEST_NAME VARCHAR(30) NOT NULL,
	TEST_DELFLAG VARCHAR(30) NOT NULL,
	PRIMARY KEY(TEST_CODE, USER_ID)
);

CREATE TABLE STUDENT(
	STUDENT_CODE VARCHAR(30) NOT NULL,
	TEST_CODE VARCHAR(30) NOT NULL,
	USER_ID VARCHAR(30) NOT NULL,
	STUDENT_DEPTM VARCHAR(100) NOT NULL,
	STUDENT_NAME VARCHAR(30) NOT NULL,
	STUDENT_EMAIL VARCHAR(320) NOT NULL,
	STUDENT_IP VARCHAR(20),
	STUDENT_UUID VARCHAR(100),
	TEST_FLAG VARCHAR(1) NOT NULL,
	PRIMARY KEY(STUDENT_CODE, TEST_CODE, USER_ID),
	FOREIGN KEY (TEST_CODE, USER_ID)
    REFERENCES TESTER(TEST_CODE, USER_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE STUDENTANSWER(
	TEST_NUM VARCHAR(10) NOT NULL,
	STUDENT_CODE VARCHAR(30) NOT NULL,
	TEST_CODE VARCHAR(30) NOT NULL,
	USER_ID VARCHAR(30) NOT NULL,
	STUDENT_SCORE VARCHAR(10),
	STUDENT_ANSWER VARCHAR(4000),
	PRIMARY KEY(TEST_NUM, STUDENT_CODE, TEST_CODE, USER_ID),
	FOREIGN KEY (STUDENT_CODE, TEST_CODE, USER_ID)
    REFERENCES STUDENT(STUDENT_CODE, TEST_CODE, USER_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE STUDENTCAPTURE(
	VIDEO_NUM BIGINT(255) UNSIGNED NOT NULL AUTO_INCREMENT,
	STUDENT_CODE VARCHAR(30) NOT NULL,
	TEST_CODE VARCHAR(30) NOT NULL,
	USER_ID VARCHAR(30) NOT NULL,
	CAPTURE_CONTENT VARCHAR(4000),
	PRIMARY KEY(VIDEO_NUM, STUDENT_CODE, TEST_CODE, USER_ID),
	FOREIGN KEY (STUDENT_CODE, TEST_CODE, USER_ID)
    REFERENCES STUDENT(STUDENT_CODE, TEST_CODE, USER_ID) ON UPDATE CASCADE ON DELETE CASCADE
);
# 자동 시퀀스 초기화
ALTER TABLE STUDENTCAPTURE AUTO_INCREMENT=1;

#-TESTER 테이블 디폴트 데이터
INSERT INTO TESTER(TEST_CODE, USER_ID, TEST_NUMOFQUST, TEST_NAME, TEST_DELFLAG) 
VALUES('A033212', 'SSUIT', '10', '인공지능','N');

#테이블 삭제
DROP TABLE TESTER;
DROP TABLE STUDENT;
DROP TABLE STUDENTANSWER;
DROP TABLE STUDENTCAPTURE;
