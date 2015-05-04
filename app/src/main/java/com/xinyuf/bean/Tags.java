package com.xinyuf.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhaofg on 15/5/3.
 */
public class Tags extends DataSupport{

    private int id;
    private String url;
    private String tag;
    private Article article;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    @Override
    public String toString() {
        return "Tags{" +
                "url='" + url + '\'' +
                ", tag='" + tag + '\'' +
                ", article'" + article +'\''+
                '}';
    }
}
