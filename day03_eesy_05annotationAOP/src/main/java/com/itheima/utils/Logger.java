package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，提供了公共方法
 */
@Component("logger")
@Aspect //表示当前类是一个切面类
public class Logger {

    /**
     * 切入点表达式
     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 前置通知
     */
//    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("=== 前置通知 ===");
    }

    /**
     * 后置通知
     */
//    @AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("=== 后置通知 ===");
    }

    /**
     * 异常通知
     */
//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("=== 异常通知 ===");
    }

    /**
     * 最终通知
     */
//    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("=== 最终通知 ===");
    }


    /**
     * 环绕通知
     * Spring中的环绕通知：Spring为我们提供的一种可以在代码中手动增强方法何时执行的方式
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            System.out.println("=== 环绕通知（前置） ===");
            Object[] args = pjp.getArgs();
            // 必须有明确的切入点方法调用
            rtValue = pjp.proceed(args);
            System.out.println("=== 环绕通知（后置） ===");
        } catch (Throwable t) {
            System.out.println("=== 环绕通知（异常） ===");
            throw new RuntimeException(t);
        } finally {
            System.out.println("=== 环绕通知（最终） ===");
        }
        return rtValue;
    }
}
