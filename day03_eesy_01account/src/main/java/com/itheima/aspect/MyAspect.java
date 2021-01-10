package com.itheima.aspect;

import com.itheima.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Autowired
    private TransactionManager txManager;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt(){}

//    @Before("pt()")
    public void before() {
        System.out.println("=== beganTransaction ===");
        txManager.beganTransaction();
    }

//    @AfterReturning("pt()")
    public void afterReturning() {
        System.out.println("=== commitTransaction ===");
        txManager.commitTransaction();
    }

//    @AfterThrowing("pt()")
    public void afterThrowing() {
        System.out.println("=== rollbackTransaction ===");
        txManager.rollbackTransaction();
    }

//    @After("pt()")
    public void after() {
        System.out.println("=== releaseConnection ===");
        txManager.releaseConnection();
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint ppj) {
        Object rtValue = null;
        try {
            System.out.println("=== around: beganTransaction ===");
            txManager.beganTransaction();
            // proceed...
            System.out.println("proceed...");
            rtValue = ppj.proceed(ppj.getArgs());
            System.out.println("=== around: commitTransaction ===");
            txManager.commitTransaction();
        } catch (Throwable throwable) {
            System.out.println("=== around: rollbackTransaction ===");
            txManager.rollbackTransaction();
            throwable.printStackTrace();
        } finally {
            System.out.println("=== around: releaseConnection ===");
            txManager.releaseConnection();
        }
        return rtValue;
    }
}
