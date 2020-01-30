package com.raj.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.raj.pma.controllers..*)")
	public void definePackagePointcuts() {
		// empty method just to name the location specified in the pointcut
	}
	
	@Around("definePackagePointcuts()")
	public Object logAfter(ProceedingJoinPoint jp) {
		log.debug("\n \n \n");
		log.debug("************** Before Method Excecution ************* \n {}.{} () with arguments[s]= {}",
				jp.getSignature().getDeclaringTypeName(), 
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("--------------------------------------\n \n \n");
		
		Object proceed = null;
		try {
			proceed = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("************** After Method Excecution ************* \n {}.{} () with arguments[s]= {}",
				jp.getSignature().getDeclaringTypeName(), 
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("--------------------------------------\n \n \n");
		
		return proceed;
	}

}
