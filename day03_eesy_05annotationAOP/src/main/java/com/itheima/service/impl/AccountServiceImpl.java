package com.itheima.service.impl;

import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存。。。");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新。。。");
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除。。。");
        int i = 1 / 0;
        return 0;
    }
}
