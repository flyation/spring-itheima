package com.itheima.cglib;

/**
 * 生产厂家
 */
public class Producer {

    /**
     * 销售
     */
    public void sellProduct(float money) {
        System.out.println("销售商品，并拿到钱：" + money);

    }
    /**
     * 售后
     */
    public void afterService(float money) {
        System.out.println("提供售后，并拿到钱：" + money);

    }
}
