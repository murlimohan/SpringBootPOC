package com.bookMyShowExample.demo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.bookMyShowExample.demo.controller.*.*(..))")
	public void myPointcut() {
		
	}
    
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		long startTime = System.currentTimeMillis();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] args = pjp.getArgs();
		log.info("method invoked" + className + ":" + methodName + "()" + "arguments :"
				+ mapper.writeValueAsString(args));
		Object object = pjp.proceed();
		long endtime = System.currentTimeMillis();
		log.info(className + ":" + methodName + "()" + "Response :" + mapper.writeValueAsString(object)
				+ " Time taken for Execution is : " + (endtime - startTime) + "ms");
		return object;
	}

}
