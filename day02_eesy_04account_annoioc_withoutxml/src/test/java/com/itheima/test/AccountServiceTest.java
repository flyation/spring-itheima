package com.itheima.test;

import com.itheima.config.SpringConfiguration;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring整合Junit：
 *      1.导入spring-test的坐标
 *      2.使用@RunWith注解将原来的mian方法替换成Spring提供的
 *      3.使用@ContextConfiguration指定Spring的配置方式
 *          若为xml方式，则使用location="classpath:xxx"。指定配置文件的位置，加上classpath关键字表示在类路径下。
 *          若为注解方式，则使用classes=xxx.class。指定注解类的字节码文件。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    @Test
    public void listAllAccount() {
        List<Account> accounts = as.listAllAccount();
        accounts.forEach(System.out::println);
    }

    @Test
    public void findAccountById() {
        Account Account = as.findAccountById(2);
        System.out.println(Account);
    }

    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setName("alex");
        account.setMoney(199.9F);
        as.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        Account account = new Account();
        account.setId(2);
        account.setName("dog");
        account.setMoney(7.26F);
        as.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
        as.deleteAccount(4);
    }
}
