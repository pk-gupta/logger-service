package com.poc.logger.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(AOPLogger.class);

    @Before("execution(* com.poc.logger.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.info("---------- Before {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.poc.logger.controller.*.*(..))", returning = "result")
    public void afterReturningFromController(JoinPoint joinPoint, Object result) {
        LOGGER.info("---------- After {} returned with value {}", joinPoint, result.toString());
    }

    @After(value = "execution(* com.poc.logger.controller.*.*(..))")
    public void afterFromController(JoinPoint joinPoint) {
        LOGGER.info("---------- After {}", joinPoint);
    }

    //Service
    @Before("execution(* com.poc.logger.service.*.*(..))")
    public void beforeService(JoinPoint joinPoint){
        LOGGER.info("---------- Before {}", joinPoint);
    }

    @AfterReturning(value = "execution(* com.poc.logger.service.*.*(..))", returning = "result")
    public void afterReturningFromService(JoinPoint joinPoint, Object result) {
        LOGGER.info("---------- After {} returned with value {}", joinPoint, result);
    }

    @After(value = "execution(* com.poc.logger.service.*.*(..))")
    public void afterFromService(JoinPoint joinPoint) {
        LOGGER.info("---------- After {}", joinPoint);
    }




    @Around("@annotation(LogTimer)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        LOGGER.info("********** Execution Time of {}.{} :: {} ms ", className, methodName, executionTime);
        return result;
    }
}
