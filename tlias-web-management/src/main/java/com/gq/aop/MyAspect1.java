package com.gq.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    @Before("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        log.info("前置通知Before运行。。。");
    }
    @Around("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("环绕通知Around运行前。。。");

        Object result = proceedingJoinPoint.proceed();

        log.info("环绕通知Around运行后。。。");
    }
    @After("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        log.info("后置通知before运行。。。");
    }
    @AfterReturning("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    public void afterReturning(){
        log.info("返回后通知afterReturning运行。。。");
    }
    @AfterThrowing("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing(){
        log.info("异常后通知afterThrowing运行。。。");
    }
}
