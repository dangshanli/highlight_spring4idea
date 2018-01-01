package com.luzj.highlight_spring4.ch2.prepost;

/**
 * Created by MyPC on 2018/1/1.
 */
public class BeanWayService {
    public void init(){
        System.out.println("@bean-init-method");
    }

    public void destroy(){
        System.out.println("@bean-destroy-method");
    }

    public BeanWayService(){
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }
}
