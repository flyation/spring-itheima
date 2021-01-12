package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> listAllAccount() {
        try {
            return runner.query(connectionUtils.getConnection(),
                    "select * from account",
                    new BeanListHandler<>(Account.class));
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer id) {
        try {
            return runner.query(connectionUtils.getConnection(),
                    "select * from account where id = ?",
                    new BeanHandler<>(Account.class), id);
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getConnection(),
                    "insert into account(name, money) values(?, ?)",
                    account.getName(), account.getMoney());
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getConnection(),
                    "update account set name = ?,  money = ? where id = ?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            runner.update(connectionUtils.getConnection(), "delete from account where id = ?", id);
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getConnection(),
                    "select * from account where name = ?",
                    new BeanListHandler<>(Account.class), accountName);
            if (accounts == null || accounts.size() == 0) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("通过name查找账户，结果集不唯一");
            }
            return accounts.get(0);
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }
}
