package com.br.admin.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContext {

    private static ApplicationContext context;

    public static void initContext(Class<?>... configClasses) {
        context = new AnnotationConfigApplicationContext(configClasses);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

}
