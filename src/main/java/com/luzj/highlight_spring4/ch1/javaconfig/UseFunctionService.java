package com.luzj.highlight_spring4.ch1.javaconfig;

import com.luzj.highlight_spring4.ch1.di.*;

/**
 * Created by MyPC on 2017/12/28.
 */
public class UseFunctionService {//没有@Service等
    FunctionService functionService;//没有@Autowired注解

    public void setFunctionService(FunctionService functionService){
        this.functionService = functionService;
    }
    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
