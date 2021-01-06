package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试
 */
public class AccountServiceTest {

    @Test
    public void listAllAcount() {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.itheima");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        List<Account> accounts = as.listAllAcount();
        accounts.forEach(System.out::println);
    }

    @Test
    public void findAcountById() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        Account acount = as.findAcountById(2);
        System.out.println(acount);
    }

    @Test
    public void saveAccount() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setName("alex");
        account.setMoney(199.9F);
        as.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        Account account = new Account();
        account.setId(2);
        account.setName("dog");
        account.setMoney(7.26F);
        as.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.deleteAccount(4);
    }
}
