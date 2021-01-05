package com.itheima.service.impl;

import com.itheima.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl Constructor...");
    }

    @Override
    public void saveAccount() {}

    public void init() {
        System.out.println("init...");
    }

    public void destroy() {
        System.out.println("destroy...");
    }
}
