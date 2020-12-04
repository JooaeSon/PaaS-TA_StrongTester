package com.min.hb.webcam.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.hb.student.model.IService_Student;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;



@Controller
public class WebcamCtrl {
   private Logger log = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
	private IService_Student service;
	
   @RequestMapping(value="/webcamTest.do", method=RequestMethod.POST)
   public String ChkWebcam(Model model, HttpServletRequest request){
      log.info("WebcamCtrl 웹캠 테스트 화면으로 이동: \t > {}");
      
      nu.pattern.OpenCV.loadShared();
      //new ObjectDetection();
      nu.pattern.OpenCV.loadLocally();
      System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);


      return "tester/testCam";
   }



   /**
    * 웹캠 확인이 완료되면 다시 시험응시 로그인화면으로 이동
    */
   @RequestMapping(value="/gobackHome.do", method=RequestMethod.POST)
   public String FinishWebcamTest(Model model) {
      log.info("WebcamCtrl 웹캠 테스트 완료: \t > {}");
      log.info("TestCtrl 수험자들의 응시 로그인 화면: \t > {}");
		//시험 시간 조회
		Map<String, Object> timeMap=service.selectTestTime();
		if(timeMap!=null) {
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
			model.addAttribute("Stime", Stime);
			model.addAttribute("Etime", Etime);
		}
        
      return "tester/testInit";
   }
}
