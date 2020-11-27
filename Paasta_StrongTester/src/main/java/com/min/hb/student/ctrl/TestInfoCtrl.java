package com.min.hb.student.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.hb.student.model.IService_Student;

@Controller
public class TestInfoCtrl {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	   private IService_Student service;
	
	@RequestMapping(value="/setTestTime.do", method=RequestMethod.GET)
	public String settingTestTime(String test_start, String test_end) {
		log.info("settingTestTime 시험시간 세팅하기: \t > {}");
		
		Map<String, Object> timeMap=new HashMap<String, Object>();
		timeMap.put("test_start", test_start);
		timeMap.put("test_end", test_end);
		service.setTestTime(timeMap);
		return "redirect:/studentsInfo.do";
	}
	
}
