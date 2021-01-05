package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository()
public class AccountDaoImpl2 implements IAccountDao {

    @Override
    public void saveAccount() {
        System.out.println("dao2 saveAccount...");
    }
}
