package com.tedu.springboot2306.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 当前类用于处理用户数据相关的业务
 */
//类上要使用@Controller注解标注，否则不能被调用
@Controller
public class UserController {

    @RequestMapping("/signupUser")
    public void signUp(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
    }

}
