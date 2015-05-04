package com.xinyuf.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhaofg on 15/5/3.
 */
public class Category extends DataSupport{
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String url;
    private String name;
    private Article article;

    @Override
    public String toString() {
        return "Category{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", article'" + article +'\''+
                '}';
    }
}
