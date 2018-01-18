package common_toolkits.CORS_Support_inSpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/1/18.
 *
 * 使用java配置，开启全局配置跨域拦截
 */
@Configuration
public class MyConfiguration {

    /**
     * 配置bean，将WebMvcConfigurerAdapter加进容器
     * 重写addCorsMappings方法，自定义拦截域名等参数
     * 这里/**表示全部都可以访问，这种一般是在开发阶段使用
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    /**
     * 更加详细的自定义响应头参数
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer1(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://domain2.com")
                        .allowedMethods("PUT", "DELETE")
                        .allowedHeaders("header1", "header2", "header3")
                        .exposedHeaders("header1", "header2")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
