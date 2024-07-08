package com.gq.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    //所有方法的切入点相同，可以抽取一个公共方法
    @Pointcut("execution(* com.gq.service.impl.DeptServiceImpl.*(..))")
    private void pt(){};
    //除@Around外的四种通知，获取连接点信息只能使用JoinPoint，是ProceedingJoinPoint的父类型
    @Before("pt()")
    public void before(){
        log.info("前置通知Before运行。。。");
    }
    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {//@Around通知，获取连接点信息只能使用ProceedingJoinPoint
        log.info("环绕通知Around运行前。。。");
        //1、获取目标对象的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}",className);
        //2、获取目标方法的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("目标方法的方法名：{}",methodName);
        //3、获取目标方法运行时传入的参数
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("目标方法运行时传入的参数：{}", Arrays.toString(args));
        //4、放行目标方法执行
        Object result = proceedingJoinPoint.proceed();
        //5、获取目标方法运行的返回值
        log.info("目标方法运行的返回值：{}",result);

        log.info("环绕通知Around运行后。。。");

        return result;
    }
    @After("pt()")
    public void after(){
        log.info("后置通知before运行。。。");
    }
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("返回后通知afterReturning运行。。。");
    }
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("异常后通知afterThrowing运行。。。");
    }
}
