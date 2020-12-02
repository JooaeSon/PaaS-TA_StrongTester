package com.min.hb.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class testStartInterceptor extends HandlerInterceptorAdapter{
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//*.do 실행되기 전에 실행되는 Handler
	//로그인 정보를 확인 : (ServletRequest >HttpServletRequest > HttpSession) 확인 -> t/f ->init.do
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒tester인터셉터 시작▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return true;
	}
	
	//컨트롤러 실행 직후에 작동
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,// handler실행되는 메소드의 집합
			ModelAndView modelAndView) throws Exception { //modelAndView는 끝나고 볼 수 있다.
		logger.info("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒tester인터셉터 종료▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		super.postHandle(request, response, handler, modelAndView);
		
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT"); 
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0"); 
		response.setHeader("Pragma", "no-cache");
	}
	
	//View의 렌더링이 끝난 직후에 실행(화면 다 뿌려진 다음에 애가 동작되는 거임)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	//비동기식 호출시 실행
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
