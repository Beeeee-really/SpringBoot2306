package com.tedu.springboot2306.entity;

import java.io.Serializable;

public class Article implements Serializable {
    private String name;
    private String value;
    private String author;

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Article(String name, String value, String author) {
        this.name = name;
        this.value = value;
        this.author = author;
    }
}
