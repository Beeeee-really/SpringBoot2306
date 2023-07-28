package com.tedu.springboot2306.controller;

import com.tedu.springboot2306.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 当前类用于处理用户数据相关的业务
 */
//类上要使用@Controller注解标注，否则不能被调用
@Controller
public class UserController {
    //userDir保存目录
    private static File userDir;

    static {
        userDir = new File("./users");
        if (!userDir.exists()){
            userDir.mkdirs();
        }
    }

    @RequestMapping("/signupUser")
    //                    请求                            响应
    public void signUp(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
        String username = request.getParameter("username");//与输入用户名输入框属性的值相符
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + password + "," + nickname + "," + ageStr);
        //对表单信息进行必要的验证
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() ||
                ageStr == null || ageStr.isEmpty() ||
                !ageStr.matches("[0-9]+")) {
            try {
                response.sendRedirect("/signup_info_error.html");
                return;//不再执行后续代码
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //数据不正确，响应错误页面
        }
        int age = Integer.parseInt(ageStr);
        User user = new User(username, password, nickname, age);

        File file = new File(userDir,username+".obj");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



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
