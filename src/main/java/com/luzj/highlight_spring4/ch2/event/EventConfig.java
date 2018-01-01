package com.luzj.highlight_spring4.ch2.event;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MyPC on 2018/1/1.
 * 配置类,将监听器和发布类注册到bean容器里面
 */
@Configuration
@ComponentScan("com.luzj.highlight_spring4.ch2.event")
public class EventConfig {
}
