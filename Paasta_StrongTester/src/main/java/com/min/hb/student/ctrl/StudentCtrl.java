package com.min.hb.student.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.min.hb.student.dtos.StudentAnswerDto;
import com.min.hb.student.dtos.StudentInfoDto;
import com.min.hb.student.model.IService_Student;

@Controller
public class StudentCtrl {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_Student service;

	/**
	 * 시험 감독자 홈 첫 페이지
	 * @return String 첫 홈 화면
	 */
	@RequestMapping(value="/home.do", method=RequestMethod.GET)
	public String MonitorFisrtPage() {
		log.info("Welcome to MonitorFisrtPage : \t > {}");
		return "login/home";
	}


	/**
	 * 학생들의 수험관련 전체정보 조회
	 * @param model
	 * @return 해당 과목 수험자 정보 전체 조회 화면
	 */
	@RequestMapping(value="/studentsInfo.do", method=RequestMethod.GET)
	public String studentsInfo(Model model) {
		//시험 시간 조회
		Map<String, Object> timeMap=service.selectTestTime();
		System.out.println("timeMap"+timeMap);
		String test_start=(String) timeMap.get("TEST_START");
		String test_end=(String) timeMap.get("TEST_END");
		log.info("test_start 시작시간: \t >{}", test_start);
		log.info("test_end 종료시간: \t >{}", test_end);

		String date=test_start.substring(0, test_start.indexOf("T"));
		String Stime=test_start.substring(test_start.indexOf("T")+1);
		String Etime=test_end.substring(test_end.indexOf("T")+1);
		log.info(date+"T"+Stime+"~"+Etime);
		
		model.addAttribute("date", date);
		model.addAttribute("sh", Stime.substring(0, Stime.indexOf(":")));
		model.addAttribute("sm", Stime.substring(Stime.indexOf(":")+1));
		model.addAttribute("eh", Etime.substring(0, Etime.indexOf(":")));
		model.addAttribute("em", Etime.substring(Etime.indexOf(":")+1));
		
		log.info("StudentCtrl 학생들 정보 목록 조회 : \t > {}");
		List<StudentInfoDto> stdAllList = service.StudentInfoSelect();
		model.addAttribute("stdAllList", stdAllList); //학생들 리스트

		return "student/studentsInfoForm";
	}

	/**
	 * 학생 시험 세부사항 목록 조회 ajax
	 * @param model
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/selectDetail.do", method=RequestMethod.POST)
	public Map<String, Object> StudentDetail(Model model, String student_code) {
		log.info("Ajax 학생시험 세부 목록 조회 : \t > {}", student_code);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("student_code", student_code);
		log.info("student_code!!!!!!!!!!!!!"+student_code);
		return map;
	}
	/**
	 * 학생 시험 세부사항 목록 조회
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/studentDetailView.do", method=RequestMethod.POST)
	public String StudentDetailView(Model model, String stdId) {
		log.info("StudentCtrl 학생시험 세부 목록 조회 : \t > {}", stdId);

		//학생 세부정보 조회
		StudentInfoDto stdto=service.StudentDetail(stdId);
		String point=service.StudentSumPoint(stdId);
		stdto.setTest_sumpoint(point); //총점
		model.addAttribute("stdto", stdto);

		//학생 답안 및 점수 조회
		Map<String, Object> amap =new HashMap<String, Object>();
		amap.put("student_code", stdId);
		List<StudentAnswerDto> aswlst=service.StudentAnswerInfo(amap);

		//웹캠 링크 정보 조회
		List<String> linklst=service.SelectVideoLink(stdId);

		model.addAttribute("aswlst", aswlst);
		model.addAttribute("linklst", linklst);

		return "tester/testScoring";

	}


}