package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 获取spring的ioc容器，并通过id获取对象
 *
 * ApplicationContext的三个常用实现类:
 *      ClassPathXmlApplicationContext：加载类路径下的配置文件
 *      FileSystemXmlApplicationContext：加载磁盘上的配置文件
 *      AnnotationConfigApplicationContext：读取注解创建容器
 *
 *
 * BeanFactory是ApplicationContext的顶级接口。
 *      BeanFactory构建核心容器时，采用立即加载的方式创建对象（单例对象适用）
 *      ApplicationContext构建核心容器时，采用延迟加载的方式创建对象（多例对象适用）
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
//        as.saveAccount();

    }
}
