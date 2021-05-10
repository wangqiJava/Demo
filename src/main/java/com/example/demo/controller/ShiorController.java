package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: ShiorController
 * @Description: 权限配置练习
 * @Date: 2021/5/10 18:01
 * @Created: by WangQ
 */
@Controller
@RequestMapping("/shiorController")
public class ShiorController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "list.html";
    }
}
