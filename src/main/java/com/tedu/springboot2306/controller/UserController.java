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
    //                    请求                            响应
    public void signUp(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
        String username = request.getParameter("username");//与输入用户名输入框属性的值相符
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + password + "," + nickname + "," + ageStr);
    }


    @RequestMapping("/loginUser")
    public void loginUser(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "," + password);
        ;
    }

}
