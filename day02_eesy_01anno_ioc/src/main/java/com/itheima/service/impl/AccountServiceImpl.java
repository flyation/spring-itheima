package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 使用注解
 *
 * 用于创建对象的：作用和xml文件中的<bean>标签功能相同
 *      Component：也可细分为Controller、Service、Repository
 *
 * 用于注入数据的：作用和<bean>标签中的<properties>标签功能相同
 *      Autowired：
 *          自动按照类型注入，只要IOC容器中存在唯一一个类型匹配的bean，就可以注入成功。
 *          若没有任何一个bean与之类型匹配，则报错。
 *          若存在多个bean与之类型匹配，则进一步以当前变量名为key进行匹配，若无匹配则报错。
 *      Qualifier：
 *          在按照类型注入的基础上再按名称注入。在给类成员注入时不能单独使用，但在给方法参数注入时可以。
 *      Resource：
 *          之间按照bean的id注入，可以独立使用。
 *      以上3个注解都只能注入bean，而基本类型和String无法用这三个注解注入。另外，集合类型只能通过xml文件注入。
 *
 *      Value：
 *          用于注入基本类型和String类型的数据。可以使用SpEL（Spring的el表达式）。
 *          SpEL的写法：${表达式}
 *          提一嘴：el表达式写在哪则去相应位置取数据。
 *              写在Sping的配置文件或注解中：去Spring指定的位置取数据
 *              写在Mybatis的配置文件中：去Mybatis指定的位置取数据
 *              写在jsp中：去四大域取数据
 *
 * 用于改变作用范围的：作用和<bean>标签中的scope属性功能相同
 *      Scope
 *
 * 生命周期相关的：作用和<bean>标签中的init-method、destroy-method属性功能相同
 *      PostConstruct
 *      PreDestroy
 */
@Service("accountService")
@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

//    @Autowired
//    @Qualifier("accountDaoImpl")
    @Resource(name = "accountDaoImpl2")
    private IAccountDao accountDao;

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }

    @Override
    public void saveAccount() {
        System.out.println("service saveAccount...");
        accountDao.saveAccount();
    }
}
