package com.itheima.ui;

import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 模拟一个表现层
 */
public class Client {
    public static void main(String[] args) {
//        IAccountService as = new AccountServiceImpl();
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        as.saveAccount();
    }
}
