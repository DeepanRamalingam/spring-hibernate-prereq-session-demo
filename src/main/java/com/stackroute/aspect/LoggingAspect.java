package com.stackroute.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.stackroute.model.Employee;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.stackroute.service.EmployeeService.*(..))")
	public void logBeforeAllMethods(JoinPoint joinPoint) {
		LOG.info("****LoggingAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
	}


	// Log after executing methods of particular class
	@After("execution(* com.stackroute.service.EmployeeService.*(..))")
	public void logAfterAllMethods(JoinPoint joinPoint) {
		LOG.info("****LoggingAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
	}

	// Log before executing methods of particular class setter methods
	@Before("execution(public void com.stackroute.model..set*(*))")
	public void loggingAdvice(JoinPoint joinPoint) {
		LOG.info("Before running loggingAdvice on method=" + joinPoint.toString());

		LOG.info("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));

	}

	// Log complete method signature before and after executing methods
	@Around("execution(* com.stackroute.service.EmployeeService.*(..))")
	public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		LOG.info("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName()
				+ ": Before Method Execution");
		try {
			joinPoint.proceed();
		} finally {
			// Do Something useful, If you have
		}
		LOG.info("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName()
				+ ": After Method Execution");
	}

	// Log when it throw any exception

	@AfterThrowing(pointcut = "execution(* com.stackroute.service.EmployeeService.*(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
		LOG.info("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
	}
	
	// Log around all the methods of player service
	
	@AfterReturning("execution(* com.stackroute.service.EmployeeService.*(..))")
    public void logAfterReturningAllMethods() throws Throwable 
    {
		LOG.info("****LoggingAspect.logAfterReturningAllMethods() ");
    }
	
	// Log around getTeam of Team Service
	
	@AfterReturning(pointcut="execution(* com.stackroute.service.EmployeeService.findById(..))", returning="retVal") 
    public void logAfterReturningGetEmployee(Object retVal) throws Throwable 
    {
		LOG.info("****LoggingAspect.logAfterReturningGetTeam() ");
		LOG.info(((Employee)retVal).getEmpname());
    }

}
