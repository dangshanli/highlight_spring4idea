package com.luzj.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by MyPC on 2017/12/28.
 */
@Service
@Scope("prototype")//原型范围
public class DemoPrototypeService {
}
