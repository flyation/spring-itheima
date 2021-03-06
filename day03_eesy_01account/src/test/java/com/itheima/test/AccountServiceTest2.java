package com.itheima.test;

import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用AOP改造原来使用代理的方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean2.xml")
public class AccountServiceTest2 {

    @Autowired
    @Qualifier("accountServiceImpl_old")
    private IAccountService asOld;

    @Test
    public void transfer1() {
        asOld.transfer("aaa", "bbb", 100F);
    }
}
