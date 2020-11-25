package com.min.hb.student.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.hb.student.dtos.StudentInfoDto;
import com.min.hb.student.model.IService_Student;

@Controller
public class StudentInfoCtrl {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IService_Student service;
	
	/**
	 * 수험자 정보 입력
	 * @param student_code
	 * @param student_deptm
	 * @param student_name
	 * @param student_email
	 * @return
	 */
	@RequestMapping(value="/insertStudent.do", method=RequestMethod.POST)
	public String insertStudentinfo(StudentInfoDto sdto) {
		log.info("insertStudentinfo 수험자 정보입력: \t > {}");
		if (false) {//학번 중복 확인:중복 안한다면
			service.InsertStudent(sdto);
			
			log.info("studentAnswerBasic 학생 답안저장 기본 정보 세팅: \t > {}");
			Map<String, Object> basicAswmap = new HashMap<String, Object>();
			for(int i=1;i<=10;i++) {
				basicAswmap.put("student_code", sdto.getStudent_code());
				basicAswmap.put("test_num", i);
				service.studentAnswerBasic(basicAswmap);
			}
		}
		
		return "redirect:/studentsInfo.do";
	}
	
}
