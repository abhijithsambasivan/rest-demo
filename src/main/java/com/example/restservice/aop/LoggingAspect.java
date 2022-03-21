package com.example.restservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("within(com.example..*)")
	public void methods() {
	};

	@Around("methods()")
	public Object loggingMethods(ProceedingJoinPoint jp) throws Throwable {
		Object obj = null;
		System.out.println("Starting" + jp.getSignature().getName());
		obj = jp.proceed();
		System.out.println("Finished" + jp.getSignature().getName());
		return obj;
	}

}
