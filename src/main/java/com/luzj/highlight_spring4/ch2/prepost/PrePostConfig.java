package com.luzj.highlight_spring4.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MyPC on 2018/1/1.
 */
@Configuration
@ComponentScan("com.luzj.highlight_spring4.ch2.prepost")
public class PrePostConfig {

    @Bean(initMethod = "init",destroyMethod = "destroy")//Java Bean way
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService(){ //jsr250 bean way
        return new JSR250WayService();
    }
}
