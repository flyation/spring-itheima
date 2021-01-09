package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    private DataSource dataSource;

    // setter用于IoC注入
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取连接
     */
    public Connection getConnection() {
        Connection conn = tl.get();
        if (conn == null) {
            // 从数据源中取一个连接，并存入tl
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tl.set(conn);
        }
        return conn;
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
