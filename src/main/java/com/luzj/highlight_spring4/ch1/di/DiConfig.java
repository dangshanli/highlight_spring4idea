package com.luzj.highlight_spring4.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MyPC on 2017/12/28.
 */

/**
 * 配置类
 */
@Configuration//配置类注解
@ComponentScan("com.luzj.highlight_spring4.ch1.di")//扫描package下的@service等注解，注册进入容器Bean
public class DiConfig {
}