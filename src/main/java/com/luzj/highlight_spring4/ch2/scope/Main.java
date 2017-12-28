package com.luzj.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MyPC on 2017/12/28.
 */
public class Main {
    /**
     * singleton模式，容器一个bean只有一个实例
     * prototype模式，容器一个bean，调用一次新建一个实例，最后多个实例
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ScopeConfig.class);

        DemoSingletonService singletonService1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService singletonService2 = context.getBean(DemoSingletonService.class);

        DemoPrototypeService prototypeService1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService prototypeService2 = context.getBean(DemoPrototypeService.class);

        System.out.println("s1 == s2?"+(singletonService1 == singletonService2));
        System.out.println("p1 == p2?"+(prototypeService1 == prototypeService2));
        context.close();
    }
}
