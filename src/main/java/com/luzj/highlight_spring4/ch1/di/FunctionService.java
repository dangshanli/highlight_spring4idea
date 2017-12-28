package com.luzj.highlight_spring4.ch1.di;

import org.springframework.stereotype.Service;

/**
 * Created by MyPC on 2017/12/28.
 */
@Service
public class FunctionService {
    public String sayHello(String word){
        return "Hello "+ word+" !";
    }
}
