package com.example.mybatis_syntax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luzj
 * @description:
 * @date 2018/4/19
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "heel mountain";
    }
}
