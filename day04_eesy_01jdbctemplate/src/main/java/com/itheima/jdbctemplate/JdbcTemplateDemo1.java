package com.itheima.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate基本用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        // Spring内置的数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://121.199.7.212:3306/eesy");
        ds.setUsername("root");
        ds.setPassword("***");

        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        jt.execute("insert into account(name, money) values ('ccc', 1000)");
    }
}
