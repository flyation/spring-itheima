package com.itheima.jdbc;

import java.sql.*;

/**
 * 展现了严重的耦合性
 */
public class JdbcDemo1 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
//            DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://121.199.7.212:3306/eesy", "root", "todo");
            // 3.预处理对象
            ps = connection.prepareStatement("select * from account ");
            // 4.执行sql结果集
            rs = ps.executeQuery();
            // 5.遍历结果集
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getFloat("money"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.释放资源
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
