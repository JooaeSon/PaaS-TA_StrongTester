package com.min.hb.student.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * @throws IOException 
	 */
	@RequestMapping(value="/insertStudent.do", method=RequestMethod.POST)
	public String insertStudentinfo(StudentInfoDto sdto, HttpServletResponse response,
						Model model) throws IOException {
		log.info("insertStudentinfo 수험자 정보입력: \t > {}");
		if (service.ChkStudentCode(sdto.getStudent_code())) {//학번 중복 확인:중복 안한다면
			service.InsertStudent(sdto);

			log.info("studentAnswerBasic 학생 답안저장 기본 정보 세팅: \t > {}");
			Map<String, Object> basicAswmap = new HashMap<String, Object>();
			for(int i=1;i<=10;i++) {
				basicAswmap.put("student_code", sdto.getStudent_code());
				basicAswmap.put("test_num", i);
				service.studentAnswerBasic(basicAswmap);
			}

			return "redirect:/studentsInfo.do";
		}else {
			log.info("수험자 학번 중복: \t > {}");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>alert('이미 존재하는 학번입니다. 다른학번을 다시 입력해주세요.');</script>");
			out.flush();

			List<StudentInfoDto> stdAllList = service.StudentInfoSelect();
			model.addAttribute("stdAllList", stdAllList); //학생들 리스트
			log.info("StudentCtrl 학생들 정보 목록 조회 : \t > {}");
			return "student/studentsInfoForm";
		}
	}
	
	/**
	 * 학생기본정보 수정
	 * @param sdto
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/updateStdInfo.do", method=RequestMethod.POST)
	public String updateStudentInfo(StudentInfoDto sdto, 
			HttpServletResponse response, Model model) throws IOException {
		log.info("updateStudentInfo 학생정보 수정: \t >{}", sdto);
		//학번 중복 확인:중복 안한다면
		//1. 바꾸려는 학번과 전 학번이 같으면 수정가능.
		//2. 바꾸려는 학번과 전학번이 다를 경우, 학생리스트에 중복되는 학번이 있는지 체크
		if (sdto.getStudent_code().equalsIgnoreCase(sdto.getSeq()) ||
				(!(sdto.getStudent_code().equalsIgnoreCase(sdto.getSeq())) && 
				service.ChkStudentCode(sdto.getStudent_code()))) {
			//학생정보수정
			service.updateStudentInfo(sdto);
			return "successUpdate";
		}else {
			log.info("수험자 학번 중복: \t > {}");
			//response.setContentType("text/html; charset=UTF-8");

			//PrintWriter out = response.getWriter();

			//out.println("<script>alert('이미 존재하는 학번입니다. 다른학번을 다시 입력해주세요.');</script>");
			//out.flush();
			
//			List<StudentInfoDto> stdAllList = service.StudentInfoSelect();
//			model.addAttribute("stdAllList", stdAllList); //학생들 리스트
//			log.info("StudentCtrl 학생들 정보 목록 조회 : \t > {}");
			return "failUpdate";
		}
	
	}
	
	/**
	 * 학생기본정보 삭제
	 * @param student_code
	 * @return
	 */
	@RequestMapping(value="/deleteStudent.do", method=RequestMethod.GET)
	public String deleteStudent(String student_code) {
		log.info("deleteStudent 학생정보 삭제: \t >{}", student_code);
		service.deleteStudentInfo(student_code);
		return "redirect:/studentsInfo.do";
	}

}
