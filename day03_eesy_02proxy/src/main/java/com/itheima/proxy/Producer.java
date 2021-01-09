package com.itheima.proxy;

/**
 * 生产厂家
 */
public class Producer implements IProducer {

    /**
     * 销售
     */
    @Override
    public void sellProduct(float money) {
        System.out.println("销售商品，并拿到钱：" + money);

    }
    /**
     * 售后
     */
    @Override
    public void afterService(float money) {
        System.out.println("提供售后，并拿到钱：" + money);

    }
}
