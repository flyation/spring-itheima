package com.itheima.proxy;

/**
 * 对生产厂家要求的接口
 */
public interface IProducer {

    /**
     * 销售
     */
    void sellProduct(float money);

    /**
     * 销售
     */
    void afterService(float money);
}
