package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * 账户业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    private TransactionManager txManager;

    // setter用于IoC注入
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    // setter用于IoC注入
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public List<Account> listAllAccount() {
        try {
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
            List<Account> accounts = accountDao.listAllAccount();
            // 3. 提交事务
            txManager.commitTransaction();
            return accounts;
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }

    @Override
    public Account findAccountById(Integer id) {
        try {
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
            Account account = accountDao.findAccountById(id);
            // 3. 提交事务
            txManager.commitTransaction();
            return account;
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
            accountDao.saveAccount(account);
            // 3. 提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
            accountDao.updateAccount(account);
            // 3. 提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
            accountDao.deleteAccount(id);
            // 3. 提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {
        try {
            System.out.println("transfer...");
            // 1. 开启事务
            txManager.beganTransaction();
            // 2. 执行操作
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
            // 3. 提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4. 回滚事务
            txManager.rollbackTransaction();
            e.printStackTrace();
        } finally {
            // 5. 释放连接
            txManager.releaseConnection();
        }
    }
}
