package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    /**
     * 没有事务控制
     */
    @Autowired
    @Qualifier("accountServiceImpl_old")
    private IAccountService asOld;

    /**
     * 在业务方法中加入事务控制
     */
    @Autowired
    @Qualifier("accountService")
    private IAccountService as;

    /**
     * 使用动态代理实现事务控制
     */
    @Autowired
    @Qualifier("proxyAccountServiceImpl")
    private IAccountService proxyAs;

    @Test
    public void listAllAccount() {
        List<Account> accounts = as.listAllAccount();
        accounts.forEach(System.out::println);
    }

    @Test
    public void transfer1() {
        asOld.transfer("aaa", "bbb", 100F);
    }

    @Test
    public void transfer2() {
        as.transfer("aaa", "bbb", 100F);
    }

    @Test
    public void transfer3() {
        proxyAs.transfer("aaa", "bbb", 100F);
    }
}
