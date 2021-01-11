package com.itheima.jdbctemplate;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * JdbcTemplate基本用法
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        // 1.获取IoC
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取Bean
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        // 3.执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);

        try {
            account = accountDao.findAccountByName("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(account);

        account.setMoney(account.getMoney() + 100f);
        accountDao.updateAccount(account);

        account = accountDao.findAccountById(1);
        System.out.println(account);
    }
}
