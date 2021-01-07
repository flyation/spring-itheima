package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
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
        as.deleteAccount(15);
    }
}
