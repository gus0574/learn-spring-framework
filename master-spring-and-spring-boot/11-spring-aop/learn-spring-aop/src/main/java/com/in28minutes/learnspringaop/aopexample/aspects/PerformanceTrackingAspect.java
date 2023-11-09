package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	//메소드 실쟁 전, 후 작동
	@Around(
//			"execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..))"
			"com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()"
			)
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(getClass());
		
		long startTimeMillis = System.currentTimeMillis();
		
		Object interceptMethod = proceedingJoinPoint.proceed();
		
		long stopTimeMillis = System.currentTimeMillis();
		
		long runTimeMillis = stopTimeMillis - startTimeMillis;
		
		logger.info("Method {} - runTime is {}",proceedingJoinPoint, runTimeMillis);
		
		return interceptMethod;
	}
}
