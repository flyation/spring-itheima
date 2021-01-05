package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {

    public AccountDaoImpl() {
        System.out.println("AccountDaoImpl Constructor...");
    }

    @Override
    public void saveAccount() {
        System.out.println("保存了账户...");
    }
}
