package com.min.hb.student.model;

import java.util.List;
import java.util.Map;

import com.min.hb.student.dtos.StudentAnswerDto;
import com.min.hb.student.dtos.StudentInfoDto;

public interface IDao_Student {
	
	/**
	 * 학생들 기본 정보 조회 (학생 테이블)
	 * @return StudentInfoDto
	 */
	public List<StudentInfoDto> StudentInfoSelect();
	
	/**
	 * 메일 전송 버튼을 누르는 순간 UUID가 생성
	 * @param Map
	 * @return boolean
	 */
	public boolean MakeUUID(StudentInfoDto sdto);
	
	/**
	 * 메일전송에 필요한 학생들 기본정보
	 * @return StudentInfoDto
	 */
	public StudentInfoDto MailBasicInfo(String student_code);
	
	/**
	 * 시험응시 로그인
	 * @param StudentInfoDto
	 * @return StudentInfoDto
	 */
	public StudentInfoDto TestLogin(StudentInfoDto sdto);
	
	/**
	 * 학생들의 ip 체크
	 * @param Map
	 * @return boolean
	 */
	public boolean InputStdIp(Map<String, Object> map);
	
	/**
	 * 시험 응시 여부를 판단하여 시험지에 접속할지 말지 판단
	 * @param String
	 * @return String
	 */
	public String ChkTestFlag (String student_code);
	
	
	/**
	 * 시험 응시 버튼을 누르는 순간 응시여부가 'Y'로 업데이트 됨.
	 * @param String
	 * @return boolean
	 */
	public boolean UpdateTestFlag(String student_code);
	
	/**
	 * 답안 저장
	 * @param Map
	 * @return boolean
	 */
	public boolean StoreAnswer(Map<String, Object> map);
	
	/**
	 * 학생 세부사항 정보조회
	 * @param String
	 * @return StudentInfoDto
	 */
	public StudentInfoDto StudentDetail(String student_code);
	
	/**
	 * 학생 점수 합계
	 * @param String
	 * @return String
	 */
	public String StudentSumPoint(String student_code);
	
	/**
	 * 학생 답안 및 점수 조회
	 * @param Map
	 * @result StudentAnswerDto
	 */
	public List<StudentAnswerDto> StudentAnswerInfo(Map<String, Object> amap);
	
	/**
	 * 비디오 링크 삽입
	 * @param Map
	 * @result boolean
	 */
	public boolean InsertVideo(Map<String, Object> vmap);
	
	/**
	 * 수험자 비디오 링크 조회
	 * @param String
	 * @result String
	 */
	public List<String> SelectVideoLink(String capture_content);
	
	/**
	 * 수험자 정보 입력
	 * @param sdto
	 * @return
	 */
	public boolean InsertStudent(StudentInfoDto sdto);
	
	/**
	 * 학생 답안저장 기본정보 세팅
	 * @param basicAswmap
	 * @return
	 */
	public boolean studentAnswerBasic(Map<String, Object> basicAswmap);
	
}
