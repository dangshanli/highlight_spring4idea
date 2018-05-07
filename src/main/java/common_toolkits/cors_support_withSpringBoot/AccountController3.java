package common_toolkits.cors_support_withSpringBoot;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/1/18.
 * 跨域请求
 * class注解配合method级别的注解，进行联合注解，支持更加个性化的权限控制
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController3 {
    @CrossOrigin(origins = "http://domain2.com")
    @GetMapping("/{id}")
    public Account retrieve(@PathVariable Long id) {
        // ...
        return null;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        // ...
    }
}
