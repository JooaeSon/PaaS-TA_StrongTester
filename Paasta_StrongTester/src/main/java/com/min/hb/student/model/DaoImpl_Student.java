package com.min.hb.student.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.min.hb.student.dtos.StudentAnswerDto;
import com.min.hb.student.dtos.StudentInfoDto;

@Repository
public class DaoImpl_Student implements IDao_Student {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS ="com.min.hb.student.";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<StudentInfoDto> StudentInfoSelect() {
		log.info("DaoImpl_Student StudentInfoSelect 학생정보 조회:{}");
		return session.selectList(NS+"StudentInfoSelect");
	}

	@Override
	public boolean MakeUUID(StudentInfoDto sdto) {
		log.info("DaoImpl_Student MakeUUID uuid 생성:{}", sdto);
		int cnt=session.update(NS+"MakeUUID", sdto);
		
		return cnt>0? true:false;
	}

	@Override
	public StudentInfoDto MailBasicInfo(String student_code) {
		log.info("DaoImpl_Student MailBasicInfo 메일 기본정보 생성:{}", student_code);
		return session.selectOne(NS+"MailBasicInfo", student_code);
	}
	
	@Override
	public StudentInfoDto TestLogin(StudentInfoDto sdto) {
		log.info("DaoImpl_Student TestLogin 수험자응시 로그인:{}", sdto);
		return session.selectOne(NS+"TestLogin", sdto);
	}
	
	@Override
	public boolean InputStdIp(Map<String, Object> map) {
		log.info("DaoImpl_Student InputStdIp 학생 ip입력:{}", map);
		int cnt=session.update(NS+"InputStdIp", map);
		return cnt>0? true:false;
	}
	
	@Override
	public String ChkTestFlag(String student_code) {
		log.info("DaoImpl_Student ChkTestFlag 응시여부 확인:{}", student_code);
		return session.selectOne(NS+"ChkTestFlag", student_code);
	}
	
	@Override
	public boolean UpdateTestFlag(String student_code) {
		log.info("DaoImpl_Student UpdateTestFlag 응시여부 업데이트 'Y':{}", student_code);
		int cnt=session.update(NS+"UpdateTestFlag", student_code);
		return cnt>0? true:false;
	}

	@Override
	public boolean StoreAnswer(Map<String, Object> map) {
		log.info("DaoImpl_StoreAnswer 학생답안 저장 :{}", map);
		int cnt=session.update(NS+"StoreAnswer", map);
		return cnt>0? true:false;
	}

	@Override
	public StudentInfoDto StudentDetail(String student_code) {
		log.info("DaoImpl_StudentDetail 학생세부내용 조회 :{}", student_code);
		return session.selectOne(NS+"StudentDetail", student_code);
	}

	@Override
	public String StudentSumPoint(String student_code) {
		log.info("DaoImpl_StudentSumPoint 학생점수 합계 :{}", student_code);
		return session.selectOne(NS+"StudentSumPoint", student_code);
	}

	@Override
	public List<StudentAnswerDto> StudentAnswerInfo(Map<String, Object> amap) {
		log.info("DaoImpl_StudentAnswerInfo 학생답안 및 각 답안 점수 조회 :{}", amap);
		return session.selectList(NS+"StudentAnswerInfo", amap);
	}
	
	@Override
	public boolean InsertVideo(Map<String, Object> vmap) {
		log.info("DaoImpl_InsertVideo 비디오 링크 생성 :{}", vmap);
		int cnt=session.insert(NS+"InsertVideo", vmap);
		return cnt>0? true:false;
	}

	@Override
	public List<String> SelectVideoLink(String capture_content) {
		log.info("DaoImpl_SelectVideoLink 비디오 링크 선택 :{}", capture_content);
		return session.selectList(NS+"SelectVideoLink", capture_content);
	}

	@Override
	public boolean InsertStudent(StudentInfoDto sdto) {
		log.info("DaoImpl_InsertStudent 수험자 정보입력 :{}", sdto);
		int cnt=session.insert(NS+"insertStudent", sdto);
		return cnt>0? true:false;
	}

	@Override
	public boolean studentAnswerBasic(Map<String, Object> basicAswmap) {
		log.info("DaoImpl_studentAnswerBasic 학생 답안저장 기본 정보 세팅 :{}", basicAswmap);
		int cnt=session.insert(NS+"studentAnswerBasic", basicAswmap);
		return cnt>0? true:false;
	}

	@Override
	public boolean ChkStudentCode(String student_code) {
		log.info("DaoImpl_ChkStudentCode 학번 중복성 체크 :{}", student_code);
		int cnt=session.selectOne(NS+"ChkStudentCode", student_code);

		return cnt<1? true:false;
	}

	@Override
	public boolean updateStudentInfo(StudentInfoDto sdto) {
		log.info("DaoImpl_updateStudentInfo 학생기본정보 수정 :{}", sdto);
		int cnt=session.update(NS+"updateStudentInfo", sdto);
		return cnt>0? true:false;
	}

	@Override
	public boolean deleteStudentInfo(String student_code) {
		log.info("DaoImpl_deleteStudentInfo 학생기본정보 삭제 :{}", student_code);
		int cnt=session.delete(NS+"deleteStudentInfo", student_code);
		return cnt>0? true:false;
	}

	@Override
	public boolean setTestTime(Map<String, Object> timeMap) {
		log.info("DaoImpl_setTestTime 시험시간세팅:{}", timeMap);
		int cnt=session.update(NS+"setTestTime", timeMap);
		return cnt>0? true:false;
	}

}
