package com.itheima.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Spring配置类，作用和xml一样
 *
 * Configuration：指定当前类是配置类
 * ComponentScan：指定IOC容器启动时扫描的包
 *      value：和basePackages作用相同，指定要扫描的类路径。等同于<context:component-scan base-package="com.itheima"/>
 * Bean：把当前方法的返回值作为bean对象存入IOC容器中
 *      name：用于指定bean的id，当不指定时，id默认为方法名。
 *      细节：当使用该注解配置方法时，若方法有参数，Spring框架会从IOC容器中寻找是否有可用的对象。查找方式和@Autowired相同
 */
@Configuration
@ComponentScan(basePackages = "com.itheima")
public class SpringConfiguration {

    @Bean(name = "runner")
    @Scope("prototype")
    QueryRunner CreateQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean
    DataSource dataSource() {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        ds.setJdbcUrl("jdbc:mysql://121.199.7.212:3306/eesy");
        ds.setUser("root");
        ds.setPassword("123456");
        return ds;
    }
}
