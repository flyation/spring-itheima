package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 事务相关的工具类，包含：开启事务、提交事务、回滚事务和释放连接
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 开启事务
     */
//    @Before("pt1()")
    public void beganTransaction() {
        try {
            connectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("pt1()")
    public void commitTransaction() {
        try {
            connectionUtils.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("pt1()")
    public void rollbackTransaction() {
        try {
            connectionUtils.getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放连接
     */
//    @After("pt1()")
    public void releaseConnection() {
        try {
            // 将连接还回池中
            connectionUtils.getConnection().close();
            // 将线程和连接解绑
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 环绕通知
     */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            // 前置
            beganTransaction();
            rtValue = pjp.proceed(pjp.getArgs());
            // 后置
            commitTransaction();
            return rtValue;
        } catch (Throwable throwable) {
            // 异常
            rollbackTransaction();
            throw new RuntimeException(throwable);
        } finally {
            // 最终
            releaseConnection();
        }
    }
}
