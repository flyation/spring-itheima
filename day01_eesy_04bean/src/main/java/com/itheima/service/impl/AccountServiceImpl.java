package com.itheima.service.impl;

import com.itheima.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl Constructor...");
    }

    @Override
    public void saveAccount() {
        System.out.println("saveAccount...");
    }
}
