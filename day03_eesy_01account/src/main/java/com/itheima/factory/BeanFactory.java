package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.Proxy;

/**
 * 创建Service的代理对象
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager txManager;

    /**
     * 用于Spring注入的setter
     */
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 用于Spring注入的setter
     */
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 返回代理对象
     */
    public IAccountService getAccountService() {
        // 添加事务的支持
        IAccountService proxyAccountService = (IAccountService) Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object returnValue = null;
                    try {
                        // 1.开启事务
                        txManager.beganTransaction();
                        // 2.执行操作
                        returnValue = method.invoke(accountService, args);
                        // 3.提交事务、回滚事务、释放连接
                        txManager.commitTransaction();
                    } catch (Exception e) {
                        txManager.rollbackTransaction();
                        throw new RuntimeException(e);
                    } finally {
                        txManager.releaseConnection();
                    }
                    return returnValue;
                }
        );
        return proxyAccountService;
    }
}
