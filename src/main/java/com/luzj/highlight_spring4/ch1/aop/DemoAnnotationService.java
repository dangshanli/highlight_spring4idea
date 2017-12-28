package com.luzj.highlight_spring4.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/28.
 */
@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void add(){}
}
