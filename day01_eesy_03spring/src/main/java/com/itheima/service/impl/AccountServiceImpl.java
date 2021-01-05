package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = new AccountDaoImpl();

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl Constructor...");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
