package com.luzj.highlight_spring4.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/12/28.
 */
@Configuration
@ComponentScan("com.luzj.highlight_spring4.ch1.aop")
@EnableAspectJAutoProxy//支持aspectj
public class AopConfig {
}
