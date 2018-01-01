package com.luzj.highlight_spring4.ch2.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by MyPC on 2018/1/1.
 * 通过EL表达式，直接给变量注入信息：字符串、环境变量、表达式、其他bean的属性、文件、网址、配置文件
 */
@Configuration
@ComponentScan("com.luzj.highlight_spring4.ch2.el")
@PropertySource("classpath:main/resource/test.properties")/*配置文件地址*/
public class ElConfig {

    @Value("普通字符串，i love you")
    private String normal;/*普通字符串*/
    
    @Value("#{systemProperties['os.name']}")
    private String osName;/*系统环境属性*/

    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randomAnoher;

    @Value("#{demoService.author}")
    private String fromAuthor;/*其他bean的属性*/
    
    @Value("classpath:main/resource/file1.txt")
    private Resource testFile;/*文件资源*/
    
    @Value("https://www.baidu.com")
    private  Resource testUrl;/*URL资源*/
    
    @Value("${book.name}")
    private String bookName;/*配置文件*/
    
    @Autowired
    private Environment environment;//注入的property，也可以用environment获取
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){  /*配置文件需要加载的bean*/
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     *  测试注入属性
     */
    public void outputResource(){
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomAnoher);
            System.out.println(fromAuthor);

            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
