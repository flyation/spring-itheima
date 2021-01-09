package com.itheima.utils;

import java.sql.SQLException;

/**
 * 事务相关的工具类，包含：开启事务、提交事务、回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    // setter用于IoC注入
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
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
}
