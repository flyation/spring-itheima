package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户业务层实现类
 */
@Service("accountSerice")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> listAllAccount() {
        List<Account> accounts = accountDao.listAllAccount();
        return accounts;
    }

    @Override
    public Account findAccountById(Integer id) {
        Account account = accountDao.findAccountById(id);
        return account;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {
        System.out.println("transfer...");
        // 查出账户
        Account sourceAccount = accountDao.findAccountByName(sourceName);
        Account targetAccount = accountDao.findAccountByName(targetName);
        // 减钱、加钱
        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        targetAccount.setMoney(targetAccount.getMoney() + money);
        // 数据库更新
        accountDao.updateAccount(sourceAccount);
        int i = 1 / 0;
        accountDao.updateAccount(targetAccount);
    }
}
