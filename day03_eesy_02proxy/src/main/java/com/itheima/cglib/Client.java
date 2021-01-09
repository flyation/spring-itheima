package com.itheima.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

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
         *      2.基于子类的动态代理
         *          涉及的类：Enhancer
         *          提供者：第三方cglib
         *          如何创建代理对象：使用Enhancer类中的create方法
         *          创建代理对象的要求：被代理类不是final类
         *          newProxyInstance方法的参数：
         *              Class：被代理对象的字节码。
         *              Callback：用于提供增强的代码，它是让我们写如何代理，一般都是写一个接口的实现类。
         *                        通常情况是匿名内部类，但不是必须。此接口的实现类都是谁用谁写。
         *                        我们一般写的是该接口的子接口实现类：MethodInterceptor
         */
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param o           代理对象
             * @param method      当前方法
             * @param objects     当前方法的参数
             * 以上三个参数和InvocationHandler类中invoke方法的三个参数相同
             * @param methodProxy 代理方法
             * @return 和被代理对象方法有相同的返回值
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 提供增强的代码
                Object returnValue = null;
                if ("sellProduct".equals(method.getName())) {
                    Float money = (Float) objects[0];
                    returnValue = method.invoke(producer, money * 0.7F);
                }
                return returnValue;
            }
        });
        cglibProducer.sellProduct(10000F);
    }
}
