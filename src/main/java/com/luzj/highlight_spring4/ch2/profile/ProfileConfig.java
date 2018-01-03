package com.luzj.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by MyPC on 2018/1/1.
 * profile关系到使用哪个系统环境配置的问题，生产环境和开发环境肯定不一样
 */
@Configuration
public class ProfileConfig {
    @Bean
    @Profile("dev")
    public DemoBean devDemoBean(){
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod")//production环境配置
    public DemoBean prodDemoBean(){
        return new DemoBean("from production profile");
    }
}
