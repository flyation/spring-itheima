package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface IAccountService {

    /**
     * 查询所有
     */
    List<Account> listAllAccount();

    /**
     * 查询一个
     */
    Account findAccountById(Integer id);

    /**
     * 保存
     */
    void saveAccount(Account account);

    /**
     * 更新
     */
    void updateAccount(Account account);

    /**
     * 删除
     */
    void deleteAccount(Integer id);
}
