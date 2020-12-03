package com.min.hb.webcam.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;



@Controller
public class WebcamCtrl {
   private Logger log = LoggerFactory.getLogger(this.getClass());

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
   public String FinishWebcamTest() {
      log.info("WebcamCtrl 웹캠 테스트 완료: \t > {}");
      System.out.println("끝");
        
      return "tester/testInit";
   }
}
