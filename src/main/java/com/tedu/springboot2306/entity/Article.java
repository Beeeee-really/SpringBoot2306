package com.tedu.springboot2306.entity;

import java.io.Serializable;

public class Article implements Serializable {
    private String name;
    private String value;

    public Article(){}
    public Article(String name, String value) {
        this.name = name;
        this.value = value;
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
}
