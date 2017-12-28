package com.luzj.highlight_spring4.ch1.javaconfig;

import com.luzj.highlight_spring4.ch1.di.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MyPC on 2017/12/28.
 */
@Configuration//使用Java配置类，通过@bean实现方法返回一个bean
public class JavaConfig {
    @Bean//Bean注解，返回实例，注册到容器里
    public FunctionService functionService(){
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }


}
