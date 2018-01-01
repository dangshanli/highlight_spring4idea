package com.luzj.highlight_spring4.ch2.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MyPC on 2018/1/1.
 *
 */
public class Main {
    /**
     * 逻辑：拿到事件发布类，一旦进行发布，对应事件的事件监听器就开始运行，对事件作出反应
     *
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EventConfig.class);

        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("hello application event");
        context.close();
    }
}
