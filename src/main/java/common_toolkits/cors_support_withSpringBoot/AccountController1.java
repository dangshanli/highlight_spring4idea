package common_toolkits.cors_support_withSpringBoot;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/1/18.
 * spring boot注解支持跨域访问
 */
@RestController
@RequestMapping("account")
public class AccountController1 {

    @CrossOrigin//不配置参数表示支持所有的跨域访问
    @GetMapping("/{id}")
    public String retrieve(@PathVariable Long id){
        //...
        return null;
    }
}
