package models;

import models.enums.ArticleStatus;

import java.util.Date;

public class Articles {
    private int id;
    private String title;
    private String content;
    private Date createdAt;
    private Date lunchedAt;
    private ArticleStatus status;

    public Articles(int id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLunchedAt() {
        return lunchedAt;
    }

    public void setLunchedAt(Date lunchedAt) {
        this.lunchedAt = lunchedAt;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }


}
