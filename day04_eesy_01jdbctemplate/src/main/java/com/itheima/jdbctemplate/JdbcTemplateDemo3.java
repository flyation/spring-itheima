package com.itheima.jdbctemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的CRUD
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        // 1.获取IoC
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取Bean
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        // 3.执行操作
        // 保存
//        jt.update("insert into account(name, money) values (?, ?)", "eee" , 2222);
        // 更新
//        jt.update("update account set money = ? where id = ?", 2333, 5);
        // 删除
//        jt.update("delete from account where id = ?", 8);
        // 查询所有
//        List<Account> accounts = jt.query("select * from account where id > ?", new AccountRowMapper(), 2);
//        List<Account> accounts = jt.query("select * from account where id > ?", new BeanPropertyRowMapper<>(Account.class), 2);
//        System.out.println(accounts);
        // 查询一个
        List<Account> accounts = jt.query("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class), 20);
        System.out.println(accounts.isEmpty() ? "not found" : accounts.get(0));
        // 查询返回一行一列（使用聚合函数但不加group by）
//        String name = jt.queryForObject("select name from account where id = ?", String.class, 20);
//        System.out.println(name);


//        List<String> list = jt.queryForList("select name from account where id > ?", String.class, 20);
//        System.out.println(list);
    }
}

/**
 * 自定义一个RowMapper
 */
class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}
