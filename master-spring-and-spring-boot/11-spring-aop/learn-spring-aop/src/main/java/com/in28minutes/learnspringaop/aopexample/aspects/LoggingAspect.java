package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// @PointCut => @Before == before method call, intercept
//	@Before("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..) )")
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
	public void logMethodCallBefore(JoinPoint joinPoint) {
		logger.info("Before Aspect - Method is {}, args is {}", joinPoint, joinPoint.getArgs());
	}
	
	// @AfterThrowing , @AfterReturning ...
//	@After("execution(* com.in28minutes.learnspringaop.aopexample.*.*.*(..) )")
	@After("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()")
	public void logMethodCallAfter(JoinPoint joinPoint) {
		logger.info("After Aspect - method is {}", joinPoint);
	}
}
