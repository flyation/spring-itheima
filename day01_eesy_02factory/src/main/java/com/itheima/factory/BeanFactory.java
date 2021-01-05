package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * bean工厂
 */
public class BeanFactory {

    // 定义一个props用于读取配置文件
    private static Properties props;
    // 定义一个map用于存放我们要创建的对象，称之为容器
    private static Map<String, Object> beans;

    // 使用静态代码块为props赋值
    static {
        props = new Properties();
        try {
            // 读取配置文件
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(is);
            // 实例化容器
            beans = new HashMap<>();
            // 取出配置文件的所有key
            Enumeration<Object> keys = props.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                Object value = Class.forName(props.getProperty(key)).newInstance();
                // 将key-value放进beans
                beans.put(key, value);
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据bean名称获取bean对象
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
