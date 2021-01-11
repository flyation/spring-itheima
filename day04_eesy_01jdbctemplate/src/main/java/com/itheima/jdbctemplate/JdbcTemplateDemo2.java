package com.itheima.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate基本用法
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        // 1.获取IoC
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取Bean
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        // 3.执行操作
        jt.execute("insert into account(name, money) values ('eee', 1000)");
    }
}
