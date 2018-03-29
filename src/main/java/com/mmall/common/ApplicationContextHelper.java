package com.mmall.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 获取Spring 上下文
 * User: lenovo
 * Date: 2018-03-29
 * Time: 14:01
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware{

    //定义全局
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 取上下文spring 的bean
     * 需要配置启动并加载
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T popBean (Class<T> clazz){
        if (applicationContext == null)
            return null;
        return applicationContext.getBean(clazz);
    }

    public static <T> T  popBean(String name, Class<T> clazz){
        if (applicationContext == null)
            return null;
        return applicationContext.getBean(name,clazz);
    }
}
