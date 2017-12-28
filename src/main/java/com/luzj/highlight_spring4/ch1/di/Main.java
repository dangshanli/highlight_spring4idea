package com.luzj.highlight_spring4.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MyPC on 2017/12/28.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DiConfig.class);//加载配置类

        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);//加载Service
        System.out.println(useFunctionService.sayHello("World"));

        context.close();
    }
}
