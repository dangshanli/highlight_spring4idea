package common_toolkits.corsinterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/1/18.
 * java配置类，将拦截器配置进入DI容器
 */
@Component
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPath即添加要拦截请求路径，这里的controller路径是 Controller/path
        //addPathPatterns(String... patterns)看出，可以配置多个url
        //pathMatcher(PathMatcher pathMatcher)看出，可以使用通配符或者正则表达式的方式匹配路径模式
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("Controller/path");
    }
}
