package com.xinyuf.bean;

import org.litepal.crud.DataSupport;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaofg on 15/5/2.
 */
public class Article extends DataSupport{

    private int id;
    private String title;
    private String content;
    private String author;
    private List<Tags> tags;
    private List<Category> category;
    private String audio;
    private String picture;
    private String url;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    private Date effectDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags.toString() +
                ", category='" + category.toString() + '\'' +
                ", audio='" + audio + '\'' +
                ", picture='" + picture + '\'' +
                ", url='" + url + '\'' +
                ", effectDate=" + effectDate +
                ", date=" + date +
                '}';
    }
}
