package com.min.hb.aop.AOP_Logger;


import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AOP에서 사용하는 Advice 메소드 
 * @author happybug
 *
 */
public class AOP_Logger {
	
	//pointcut에서 @Before
	public void before(JoinPoint j) {//joinpoint는 필터와 같은 역할 이다. 안에있는 메소드 아규먼트 사용 가능
		Logger logger=LoggerFactory.getLogger(j.getTarget()+"");//j.getTarget() 대상이 되는 애들 다가져옴 object
		logger.debug("##########시작##########");
		Object[] obj=j.getArgs();
		if(obj != null || obj.length !=0) {
			logger.debug("Method명:"+j.getSignature().getName());
			for (int i = 0; i < obj.length; i++) {
				logger.debug(i+"번째:\t"+obj[i]);
			}
			logger.debug("Method명:"+j.getSignature().getName());
		}
	}
	
	//pointcut에서 @AfterReturning
	public void afterReturning(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.debug("############## 반환 완료 ##############");
	}
	
	//pointcut에서 @AfterThrowing
	public void daoError(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.debug("에러:\t"+j.getArgs());
		logger.debug("에러:\t"+j.toString());
	}
}
