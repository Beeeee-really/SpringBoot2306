package com.tedu.springboot2306.controller;

import com.tedu.springboot2306.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Controller
public class ArticleController {

    private static File articleDir;

    static {
        articleDir = new File("./article");
        if (!articleDir.exists()) {
            articleDir.mkdirs();
        }
    }

    @RequestMapping("/writeArticle")
    public void writeArticle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
        String name = request.getParameter("name");
        String value = request.getParameter("text");

        System.out.println(name + "," + value);

        if (name == null || name.isEmpty() ||
                value == null || value.isEmpty()) {
            try {
                response.sendRedirect("/article_fail.html");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        Article article = new Article(name, value);
        File file = new File(articleDir, name+".obj");

        if (file.exists()) {
            try {
                response.sendRedirect("/article_fail.html");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(file);
            response.sendRedirect("/article_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
