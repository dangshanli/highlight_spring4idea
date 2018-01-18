package common_toolkits.CORS_Support_inSpringBoot;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/1/18.
 * 跨域支持，
 */

/**
 * 在class注解CrossOrigin表示给class下面全部的方法配置跨域，
 * origins参数许可域名
 * maxAge配置缓存请求时间，单位：秒
 */
@CrossOrigin(origins = "http://domain2.com",maxAge = 3000)
@RestController
@RequestMapping("account")
public class AccountController2 {
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
