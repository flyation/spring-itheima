package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.sellProduct(10000F);

        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：不修改源码的基础上对方法增强
         *  分类：
         *      1.基于接口的动态代理：
         *          涉及的类：Proxy
         *          提供者：JDK官方
         *          如何创建代理对象：使用Proxy类中的newProxyInstance方法
         *          创建代理对象的要求：被代理对象至少实现一个接口，若没有则不能使用
         *          newProxyInstance方法的参数：
         *              ClassLoader：类加载器。用于加载代理对象的字节码，和被代理对象使用相同的类加载器。固定写法。
         *              Class[]：字节码数组。用于让代理对象和被代理对象拥有相同的方法。固定写法。
         *              InvocationHandler：用于提供增强的代码，它是让我们写如何代理，一般都是写一个接口的实现类。
         *                                  通常情况是匿名内部类，但不是必须。此接口的实现类都是谁用谁写。
         *      2.基于子类的动态代理
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy  代理对象
                     * @param method 当前执行的方法
                     * @param args   当前执行的方法的参数
                     * @return       和被代理对象方法有相同的返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 提供增强的代码
                        Object returnValue = null;
                        if ("sellProduct".equals(method.getName())) {
                            Float money = (Float) args[0];
                            returnValue = method.invoke(producer, money * 0.8F);
                        }
                        return returnValue;
                    }
                });
        proxyProducer.sellProduct(10000F);
    }
}
