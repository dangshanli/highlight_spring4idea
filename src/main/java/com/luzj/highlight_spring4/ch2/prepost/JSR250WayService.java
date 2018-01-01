package com.luzj.highlight_spring4.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by MyPC on 2018/1/1.
 */
public class JSR250WayService {
    @PostConstruct
    public void init(){
        System.out.println("jsr250-init-method");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("jsr250-destroy-method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-jsr250WayService");
    }
}
