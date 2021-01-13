package com.itheima.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring配置类，相当于bean.xml
 */
@Configuration                                          // 表明Spring配置类
@ComponentScan("com.itheima")                           // 注解扫描位置
@Import({JdbcConfig.class, TransactionConfig.class})    // 引入其他配置类
@PropertySource("jdbcConfig.properties")                // 关联配置文件

@EnableTransactionManagement                            // 开启注解事务
public class SpringConfiguration {

}
