package com.min.hb.student.ctrl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.min.hb.student.dtos.StudentInfoDto;
import com.min.hb.student.model.IService_Student;

@Controller
public class StudentMailCtrl {
   
   private Logger log = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   private IService_Student service;
   
   @Autowired
   private JavaMailSender mailSender;
   
   private String key; //생성되는 인증키를 담음.
   private String setFrom = "9apples1718@gmail.com"; //발신자
   private String title = "Strong Tester 인증 코드 발송"; //메일제목
   
   /**
    * 메일 전송 버튼을 누르는 순간 UUID가 생성
    * @return String
    */
   public String makeUUID() {
      //UUID 생성하기
      String uuid = UUID.randomUUID().toString();
      log.info("makeUUID UUID학생별 랜덤으로 생성: \t > {}", uuid);
      return uuid;
   }
   
   
   /**
    * 시험 정보 메일 전송 Ajax 처리 
    * @param List<String>
    * @return Map<String, String> map
    * <br> : isc > 메일 전송에 성공했을 시 "true" /메일 전송 실패 시 "false"
    * @thows IOException
    */
   
   @ResponseBody
   @RequestMapping(value="/sendmail.do", method=RequestMethod.POST)
   public Map<String, String> sendMail(Model model, @RequestBody String std_code) throws IOException {
      log.info("sendMailToStudent 학생들에게 메일 보내기: \t > {}");
      //List<String> chkVal = new ArrayList<>(Arrays.asList("20162579", "20162625", "20162623", "20162423", "20162580")); 
      Map<String, String> map = new HashMap<String, String>();
      JsonElement element = new JsonParser().parse(std_code);
      log.info("element:"+element);
      JsonObject jsonObject=element.getAsJsonObject();
      JsonArray std_list = jsonObject.getAsJsonArray("std_list");
      log.info("std_list:"+std_list);
      int cnt=0;
      if(std_list !=null) {
         for(int i=0; i < std_list.size(); i++) {
            String stdId=std_list.get(i)+"";
            log.info("stdId"+stdId);
         }
         StudentInfoDto sdto=new StudentInfoDto();
         for(int i=0; i < std_list.size(); i++) {
            
            String stdId=std_list.get(i)+""; //학생 번호
            stdId=stdId.replaceAll("\\\"", "");
            log.info("iddddd:\t"+stdId);
            String uuid=makeUUID();
            log.info("uuid-----:\t"+uuid);
            sdto.setStudent_code(stdId);
            sdto.setStudent_uuid(uuid);
            boolean isc=false;
            isc=service.MakeUUID(sdto);
            if(isc) {
               cnt++;
            }
            log.info("cnt:++"+cnt);
         }
         
         log.info("uuid 생성 성공: \t>{}", cnt);
         
         log.info("StudentMailCtrl 메일 전송 시도 : \t {}");

         
         int succesMailCounting = 0;
         for(int i=0; i < std_list.size(); i++) {
            String student_code=std_list.get(i).getAsString();
            log.info("|||||||||"+student_code);
            //학생들 개인 정보 가져오기
            StudentInfoDto mailInfo = service.MailBasicInfo(student_code);
            this.key=mailInfo.getStudent_uuid();
            InetAddress local = InetAddress.getLocalHost();
            String ip = local.getHostAddress();

            String content = "안녕하세요."+mailInfo.getStudent_name()+"님은 이번 STRONG TESTER에서 시험을 응시하게 되었습니다. <br><br>"
                  + "<div style='font-size:15px; text-align:center; border:1px solid #ccc; padding:10px;'>"
                  + "시험 코드:"+mailInfo.getTest_code()+"<br>"
                  + "학과:"+mailInfo.getStudent_deptm()+"<br>"
                  + "학번:"+mailInfo.getStudent_code()+"<br>"
                  + "인증 코드: [ " + key + " ] <br>"
                  + "시험 링크: <a href='http://strongtester.paas-ta.org/tester.do'> http://strongtester.paas-ta.org/tester.do</a>"
                  + "<br></div>"
                  + "****시험코드는 본인이 응시하는 과목과 맞는지 <br>"
                  + "확인해 주시고 인증코드는 정확하게 입력해주세요"
                  + "시험 환경 웹브라우저는 "+"<b style='font-size:15px;'>\"크롬\"</b>"+"입니다. ****<br><br>"
                  + "<b style='color:blue; text-decoration:underline;'>한번 시험에 응시하면 인증키는 다시 재발급 될 수 없으며 , <br>"
                  + "시험 창이 중간에 꺼지면 응시처리로 재응시가 불가하오니 <br>"
                  + "시험에 적합한 환경에서 응시하여 주시기 바랍니다.<br>"
                  + "또한 시험 부정행위는 0점으로 처리되오니  이점  유의 하여 주십시오.</b><br><br>"
                  + "혹여 시험 응시창에 인증키가 인증 되지 않거나 서비스 오류가 생긴 경우 "
                  + "<p style='text-decoration:underline;'>9apples1718@gmail.com</p>로 메일을 보내주시면 빠른 시일 내 재발급해드립니다."
                  + "<br><br> 감사합니다.</div>";
            
            log.info("UserCtrl_Mail 이메일 전송 결과 : \t {} : {} : {}", mailInfo.getStudent_email(), title, content);
            // toEmail 받는사람 주소, title 메일 제목, content 메일 내용
            
            
            MimeMessage message = mailSender.createMimeMessage();
   
            try {
               MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
               messageHelper.setFrom(setFrom);
               messageHelper.setTo(mailInfo.getStudent_email());
               messageHelper.setSubject(title);
               messageHelper.setText(content, true);
   
               mailSender.send(message);
               succesMailCounting++;
               
            } catch (MessagingException e) {
               e.printStackTrace();
            }
               
         }
         
         
         log.info("succesMailCounting:>>>"+succesMailCounting);
         //map반환을 위한 식별
         if(succesMailCounting==std_list.size()) { //학생 수 만큼 메일 다 보냈으면
            map.put("isc", "true");
         } else{
            map.put("isc", "false");
         }
         
      }
      return map; 
   }
}