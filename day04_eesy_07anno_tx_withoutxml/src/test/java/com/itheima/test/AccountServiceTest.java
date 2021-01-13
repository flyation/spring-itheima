package com.itheima.test;

import com.itheima.config.SpringConfiguration;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)  // 指定Spring的配置类
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    @Test
    public void transfer() {
        as.transfer("aaa", "bbb", 100F);
    }
}
