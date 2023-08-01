package com.tedu.springboot2306.controller;

import com.tedu.springboot2306.entity.Article;
import com.tedu.springboot2306.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        String author = request.getParameter("author");

        System.out.println(name + "," + value);

        if (name == null || name.isEmpty() ||
                value == null || value.isEmpty()) {
            try {
                response.sendRedirect("/article_fail.html");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;

        }

        File file = new File(articleDir, name + ".obj");

        if (file.exists()) {
            try {
                response.sendRedirect("/article_fail.html");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        Article article = new Article(name, value, author);

        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(article);
            response.sendRedirect("/article_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/articleList")
    public void articleList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");


        List<Article> articleList = new ArrayList<>();
        File[] subs = articleDir.listFiles(f -> f.getName().endsWith(".obj"));
        for (File sub : subs) {
            System.out.println(sub.getName());
            try (
                    FileInputStream fis = new FileInputStream(sub);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                Article article = (Article) ois.readObject();
                articleList.add(article);
            } catch (IOException | ClassCastException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();

            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset=\"UTF-8\">");
            pw.println("    <title>文章</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("    <a href=\"/index.html\">返回首页</a>");
            pw.println("    <a href=\"/write_article.html\">写文章</a>\n");
            pw.println("<center>");
            pw.println("<h1>所有文章</h1>");
            pw.println("<br>");
            pw.println("<table>");


            for (Article article : articleList) {
                pw.println("<tr>");
                pw.println("<td><a href=\"/showArticle?name=" + article.getName() + "\">" + article.getName() + "</a></td>");
                pw.println("<td>" + article.getAuthor() + "</td>");
                pw.println("</tr>");


            }

            pw.println("</table>");
            pw.println("</center>");
            pw.println("</body>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping("/showArticle")
    public void shoeArticle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理...");
        String name = request.getParameter("name");
        System.out.println(name + ".obj");
        File file = new File("./article/" + name + ".obj");
        if (name == null || name.isEmpty()) {
            try {
                response.sendRedirect("/error.html");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if (!file.exists()) {
            try {
                response.sendRedirect("/error.html");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Article article = (Article) ois.readObject();
            try {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter pw = response.getWriter();
                
                pw.println("<!DOCTYPE html>");
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<meta charset=\"UTF-8\">");
                pw.println("<title>文章详情</title>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<a href=\"/articleList\">返回</a>");
                pw.println("<center>");
                pw.println("<h1>" + article.getName() + "</h1>");

                pw.println("<h3>" + article.getAuthor() + "</h3>");

                pw.println("<h4>" + article.getValue() + "</h4>");

                pw.println("</center>");
                pw.println("</body>");
                pw.println("</html>");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
