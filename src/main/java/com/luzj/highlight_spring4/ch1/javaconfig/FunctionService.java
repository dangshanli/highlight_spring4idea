package com.luzj.highlight_spring4.ch1.javaconfig;

/**
 * Created by MyPC on 2017/12/28.
 */

/**
 * 功能类，没有注册@Service等
 */
public class FunctionService {

    public String sayHello(String word){
        return "Hello "+word+"!";
    }
}
