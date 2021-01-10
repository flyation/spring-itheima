package com.itheima.service;

/**
 * 账户业务层接口
 */
public interface IAccountService {

    /**
     * 无参、无返回值
     */
    void saveAccount();

    /**
     * 有参、无返回值
     */
    void updateAccount(int i);

    /**
     * 无参、有返回值
     */
    int deleteAccount();
}
