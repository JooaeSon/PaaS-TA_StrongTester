package com.min.hb.tester.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.min.hb.student.dtos.StudentInfoDto;
import com.min.hb.student.model.IService_Student;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

@Controller
public class TesterCtrl {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//   public static int i=0;
	int count;
	public static VideoWriter writer;
	public static VideoCapture capture;
	Timer r_timer;
	Timer m_timer;
	public static boolean isTestEnd = false;
	public static String stdCode;


	@Autowired
	private IService_Student service;
	
	/**
	 * 얼굴 탐지
	 * @param frame
	 * @param faceCascade
	 */
	@Async
	public void detectFace(Mat frame, CascadeClassifier faceCascade) {
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
			//System.out.println(count);
		}
	}

	/**
	 * 실행하여 영상 파일(mp4)로 만들고 해당 경로에 저장
	 * 파일 이름은 SSUIT(주관사 이름)+A033212(시험코드명)+stdCode(학번)+time1(웹캠이 찍힌 시간)+".mp4 형식으로 저장된다.
	 * 캡처된 비디오 파일이 해당경로에 들어가게 되면 영상 파일 이름 자체(루트경로 빼고)가 DB에 저장 되도록 한다.
	 * EX) "SSUITA033212201624232020101217-07-05.mp4"
	 */
	//videomap: 학번과 비디오 링크를 저장해줄 객체
	public static Map<String, Object> videomap = new HashMap<String, Object>();
	public static List<Map<String, Object>> videolist = new ArrayList<Map<String,Object>>(); 
	@Async
	public void run() {
		log.info("run, let's Start!!!");
		String[] args= {""};
		String filenameFaceCascade = args.length > 2 ? args[0] : "C:\\HappyBugs\\git\\Paasta_StrongTester\\Paasta_StrongTester\\frontalface_alt.xml";
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

		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHH-mm-ss");
		//*
		//비디오설정==============================================
		int fourcc = VideoWriter.fourcc('m', 'p', '4', 'v');
		writer= new VideoWriter();
		Size frameSize = new Size((int) capture.get(Videoio.CAP_PROP_FRAME_WIDTH),(int) capture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
		
		
		// 저장 타이머
		r_timer = new Timer();
		

		TimerTask r_task = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				log.info("second runinng!!!");
				Date time = new Date();
				String time1 = format1.format(time);
				
				if (isTestEnd) {
					r_timer.cancel();
					m_timer.cancel();
					capture.release();
					writer.release();

					System.out.println("종료뿡");
				}
				else if (count==0) {      
					System.out.println("0명감지");
					//./video/주관사id+시험코드+학번+영상번호(시간)
					String name ="C:\\HappyBugs\\git\\Paasta_StrongTester\\Paasta_StrongTester\\src\\main\\webapp\\video\\SSUITA033212"+stdCode+time1+".mp4";
					
					//비디오 링크 저장
					Map<String, Object> vmap = new HashMap<String, Object>();
					String videofilename=name.substring(79);
					log.info("videofilename: "+videofilename);
					vmap.put("student_code", stdCode);
					vmap.put("capture_content", videofilename);
				
					videolist.add(vmap);
					System.out.println("videolist!!!!:"+videolist);
					System.out.println("videomap:!!!!!"+vmap);
					writer.open(name, fourcc, 10, frameSize,true);
				}
				else if (count >= 2) {      
					System.out.println("2명 이상 감지");
					String name ="C:\\HappyBugs\\git\\Paasta_StrongTester\\Paasta_StrongTester\\src\\main\\webapp\\video\\SSUITA033212"+stdCode+time1+".mp4";
					
					//비디오 링크 저장
					Map<String, Object> vmap = new HashMap<String, Object>();
					String videofilename=name.substring(79);
					log.info("videofilename: "+videofilename);
					vmap.put("student_code", stdCode);
					vmap.put("capture_content", videofilename);
					videolist.add(vmap);
					System.out.println("videolist!!!!:"+videolist);
					System.out.println("videomap:!!!!!"+vmap);
					
					writer.open(name, fourcc, 10, frameSize,true);
					//writer.write(frame);
				}
			}   
		};
		r_timer.schedule(r_task, 2000,8000); 

		//인식
		//*
		m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub   
				while (capture.read(frame)) {
					writer.write(frame);
					detectFace(frame,faceCascade);
				}   
			}
		};
		m_timer.schedule(m_task, 3000);
		
		
	}


	/**
	 * 시험 응시 하는 학생의 ip를 가져온다.
	 * @param req
	 * @param workspacesession
	 * @return
	 */
	//remote-ip:모바일이든 다른 디바이스에서 접근하는 ip주소
	public String ChkIp(ServletRequest req, String student_code) {
		//아이피 주소 가져오기
		//InetAddress local;
		String remoteAddr = StringUtils.defaultString(req.getRemoteAddr(), "-");
		log.info("학생의 현재 ip: \t > {}", remoteAddr);
		String IP=remoteAddr;

		return IP;
	}

	/**
	 * 학생들이 수험 응시 확인과 시작하는 첫 화면
	 * @param model
	 * @return 수험자 응시 로그인 화면
	 */
	@RequestMapping(value="/tester.do", method=RequestMethod.GET)
	public String testPageInit(Model model) {
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

	/**
	 * 로그인이 가능한 정보인지를 확인
	 * @param dto 자동으로 id pw를 set해줌
	 * @return Map
	 */
	@RequestMapping(value="/loginCheckMap.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginCheckMap(StudentInfoDto sdto){
		log.info("Welcome loginCheckMap.do : \t {}", sdto);
		Map<String, String> map = new HashMap<String, String>();
		StudentInfoDto mDto = service.TestLogin(sdto);

		if(mDto ==null) {
			map.put("isc", "실패");
		}else {
			map.put("isc", "성공");
		}

		return map;
	}



	/**
	 * 수험자 응시 페이지로 넘어가는 화면
	 * >>수험자가 아직 응시여부가 'N'일 경우, 수험자의 IP가 입력되고 응시 여부는 바로 'Y'로 바뀐다.
	 * >>만약 이미 응시 했다면 시험 페이지로 넘어갈 수 없다.
	 * @param HttpServletResponse response, ServletRequest req, 
	 *          HttpSession session, StudentInfoDto sdto
	 * @return 수험자가 응시하는 페이지로 넘어간다.
	 * @throws IOException 
	 */
	@RequestMapping(value="/TestStart.do", method=RequestMethod.POST)
	public String testStart(HttpServletResponse response, ServletRequest req,
			HttpSession session, StudentInfoDto sdto) throws IOException {
		log.info("TestCtrl 수험자 시험 화면: \t > {}", sdto);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		//StudentInfoDto mDto = service.TestLogin(sdto);


		String student_code=sdto.getStudent_code(); //나중에 session객체로 받아오기
		String student_name=sdto.getStudent_name();
		String student_uuid=sdto.getStudent_uuid();

		stdCode = student_code;
		log.info("student_code:>>>>>>>>>>>>>"+student_code
				+" student_name:>>>>>>"+student_name+"student_uuid:>>>>>"+student_uuid);
		//시험을 아직 응시 하지 않은 학생일 경우
		if("N".equalsIgnoreCase(service.ChkTestFlag(student_code))) {
			//session.setAttribute("stdInfo", mDto);
			//세션 담기
			session.setAttribute("student_code", student_code);
			session.setAttribute("student_name", student_name);
			session.setAttribute("student_uuid", student_uuid);
			log.info("Welcome TestStart.do 시험응시 여부 확인 : \t {}");
			//log.info("Welcome TestStart.do 시험응시 여부 확인 : \t {}", session.getAttribute("stdInfo"));
			//IP
			//ChkIp(req, student_code);
			//아래 ip는 테스트 용입니다.
			//"172.30.1.9"
			String stdIP=ChkIp(req, student_code);
			Map<String, Object> ipMap = new HashMap<String, Object>();
			ipMap.put("student_code", student_code);
			ipMap.put("student_ip", stdIP);
			service.InputStdIp(ipMap);

			//응시 여부 'Y'로 변경
			service.UpdateTestFlag(student_code);


			log.info(student_code+"님 시험 응시가 시작되었습니다.");
			//out.println("<script>alert('시험 응시가 시작되었습니다.');</script>");
			//out.flush();
			
			///////////////
			//<웹캠이 켜지는 순간>
			System.out.println("인식시작");
			nu.pattern.OpenCV.loadShared();
			//new ObjectDetection();
			nu.pattern.OpenCV.loadLocally();
			System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);         
			new TesterCtrl().run();

			return "tester/testPage";

			////////////////

		}else {
			log.info(student_code+"님은 시험에 응시할 수 없습니다.");
			out.println("<script>alert('시험에 응시할 수 없습니다.');</script>");
			out.flush();
			return "tester/testInit";
		}

	}

	/**
	 * 학생 답안 저장
	 * @param String allData
	 * @return Map<String, String> map 
	 */
	@ResponseBody
	@RequestMapping(value="/testAfter.do", method=RequestMethod.POST)
	public Map<String, Object> storeAnswer(Model model, @RequestBody String allData,
			HttpSession session) {
		log.info("allData: >\t"+ allData);

		Map<String, Object> answermap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		//session.getAttribute("stdInfo");
		//StudentInfoDto sdto=new StudentInfoDto();

		String student_code=(String) session.getAttribute("student_code");
		answermap.put("student_code", student_code);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(allData);
		String answer="";
		int cnt=0;
		double randomPoint=0;

		log.info("element:   "+element);
		for(int i=1; i <= 10; i++) {
			randomPoint=Math.random();
			int point=(int)(randomPoint*11);
			answermap.put("test_num", Integer.toString(i));
			answer=element.getAsJsonObject().get(i+"").getAsString();
			if (answer !=null) {
				answermap.put("student_answer", answer);
				answermap.put("student_score", Integer.toString(point));
				service.StoreAnswer(answermap);
			}
			cnt++;
		}

		if(cnt==10) { //문제 수 만큼 답 저장 성공하면
			map.put("isc", "true");
		} else{
			map.put("isc", "false");
		}

		return map;
	}

	/**
	 * 답안 제출 화면 이동
	 * session remove
	 * @param HttpSession session
	 * @return String tester/testAfter
	 * 
	 */
	@RequestMapping(value="/testAftersummit.do", method=RequestMethod.GET)
	public String testAftersummit(HttpSession session) {

		log.info("TestCtrl_Aftersummit 제출 완료");
		log.info("시험이 끝났습니다. : \t {}");
		
		log.info("videolist:"+videolist);
		//인코딩 안된 마지막 영상리스트 제거
		videolist.remove(videolist.size()-1);
		log.info("Encoding videolist:"+videolist);
		
		//최종 비디오 링크들 저장
		for(Map<String, Object> video : videolist) {
			service.InsertVideo(video);
		}
		
		isTestEnd = true;
		//인식종료
		/////////////////////
		//r_timer.cancel();
		//m_timer.cancel();
		//capture.release();
		/////////////////////
		//세션 제거
		if(session.getAttribute("student_code") !=null) {
			log.info("session 제거 완료");
			session.removeAttribute("student_code");
			session.removeAttribute("student_name");
			session.removeAttribute("student_uuid");
		}
		
		//garbage collector호출하여 openCv인식 종료.
		//System.gc();
		
		return "tester/testAfter";
	}
	
//	public void finalize() {
//		log.info("OpenCv인식 종료");
//	}

}