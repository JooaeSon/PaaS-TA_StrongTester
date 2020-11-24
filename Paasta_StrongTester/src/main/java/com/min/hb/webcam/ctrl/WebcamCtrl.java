package com.min.hb.webcam.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.hb.student.dtos.StudentInfoDto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

@Controller
public class WebcamCtrl {
   private Logger log = LoggerFactory.getLogger(this.getClass());
   int count;
   public static VideoWriter writer;
   public static VideoCapture capture;
   Timer r_timer;
   Timer m_timer;
   

   /**
    * 웹캠테스트 버튼을 누르면 웹캠 응시 체크 화면으로 이동
    * @return 
    * @throws IOException 
    */
   //얼굴감지
         public   void detectFace(Mat frame, CascadeClassifier faceCascade) {
            // TODO Auto-generated method stub
            Mat frameGray = new Mat();
              Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
              Imgproc.equalizeHist(frameGray, frameGray);
              // -- Detect faces
              MatOfRect faces = new MatOfRect();
              faceCascade.detectMultiScale(frameGray, faces);
              List<Rect> listOfFaces = faces.toList();
              
              count=0; 
              for (Rect face : listOfFaces) {
                  Point center = new Point(face.x + face.width / 2, face.y + face.height / 2);
                  Imgproc.ellipse(frame, center, new Size(face.width / 2, face.height / 2), 0, 0, 360,
                          new Scalar(255, 0, 0),2);
                  // -- In each face, detect eyes    
                  count= count+1;    
                  System.out.println(count);
              }
         }
   
      void run(HttpSession session) {
         String[] args= {""};
         String student_code=(String)session.getAttribute("student_code");
         
         System.out.println(student_code+"dfdfsdfsdfsdf.");
           String filenameFaceCascade = args.length > 2 ? args[0] : "C:\\HappyBugs\\workspace_project\\StrongTester\\frontalface.xml";
           int cameraDevice = args.length > 2 ? Integer.parseInt(args[2]) : 0;
           CascadeClassifier faceCascade = new CascadeClassifier();
           
           if (!faceCascade.load(filenameFaceCascade)) {
               System.err.println("--(!)Error loading face cascade: " + filenameFaceCascade);
               System.exit(0);
           }
           capture = new VideoCapture(cameraDevice);
           if (!capture.isOpened()) {
               System.err.println("--(!)Error opening video capture");
               System.exit(0);
           }
           else {System.out.println("성공");}
           Mat frame = new Mat();
           
           SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd HH-mm-ss");
           //*
         //비디오설정==============================================
           int fourcc = VideoWriter.fourcc('M','J','P','G');
           writer= new VideoWriter();
           Size frameSize = new Size((int) capture.get(Videoio.CAP_PROP_FRAME_WIDTH),(int) capture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
           
           
           
           
        // 저장 타이머
           List<String> lst = new ArrayList<String>();
           r_timer = new Timer();
           TimerTask r_task = new TimerTask() {
            @Override
            public void run() {
            	
               // TODO Auto-generated method stub   
               Date time = new Date();
                 String time1 = format1.format(time);
            if (count==0) {      
               System.out.println("0명감지");
       		System.out.println("냐");

//               String name = "C:\\HappyBugs\\workspace_project\\HappyBug\\StrongTester\\src\\main\\webapp\\video\\SSUIT%A033212?"+student_code+".avi";
              
          
              
               //C:\HappyBugs\workspace_project\HappyBug\StrongTester\src\main\webapp\video
               //  ./video/주관사id+%시험코드+"?"+학번+%영상번호
            
             //  StudentInfoDto stdto = service.StudentDetail(stdId);
//               List<StudentInfoDto> studentInfo = service.StudentInfoSelect();
				
               //./video/주관사id+/시험코드+"?"+학번+영상번호
               String name ="C:\\HappyBugs\\workspace_project\\cnt0_"+time1+".avi";
         //writer.open(name, fourcc, 10, frameSize,true);
               
               }
            else if (count==2) {      
               System.out.println("2명감지");
               String name ="C:\\HappyBugs\\workspace_project\\cnt2_"+time1+".avi";
               //writer.open(name, fourcc, 10, frameSize,true);
               //writer.write(frame);
               }
            else {System.out.println(count+"명");}
            }   
           };
           r_timer.schedule(r_task, 2000,4000);   
           //*
           while (capture.read(frame)) {
              //writer.write(frame);
              detectFace(frame,faceCascade);
               }      
               //*/
      }
         
    
   
   @RequestMapping(value="/webcamTest.do", method=RequestMethod.POST)
   public String ChkWebcam(Model model, HttpServletRequest request){
      log.info("WebcamCtrl 웹캠 테스트 화면으로 이동: \t > {}");
      
      nu.pattern.OpenCV.loadShared();
      //new ObjectDetection();
      nu.pattern.OpenCV.loadLocally();
      System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

      
   //////////////////////////////////////////////여기까지만 실행하면 일반 웹캠(확인용)
      //void 
      ////
      //void run()
//new WebcamCtrl().run();

      /////////////////////////////////////////
      return "tester/testCam";
   }
   
   //////

   /*
    public static void main (String [] args) { 
       
       
       } 
       //*/


   /**
    * 웹캠 확인이 완료되면 다시 시험응시 로그인화면으로 이동
    */
   @RequestMapping(value="/gobackHome.do", method=RequestMethod.POST)
   public String FinishWebcamTest() {
      log.info("WebcamCtrl 웹캠 테스트 완료: \t > {}");
      System.out.println("끝");
      /*
      HighGui.destroyAllWindows();
       r_timer.cancel();
       writer.release();
       capture.release();
       //*/
        
      return "tester/testInit";
   }
}