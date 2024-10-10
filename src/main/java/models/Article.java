package models;

import models.enums.ArticleStatus;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "lunched_at", nullable = false)
    private Date lunchedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ArticleStatus status;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;





    public Article() {
    }

    public Article(Integer id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status, Category category, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
        this.category = category;
        this.user = user;

    }

    public Article(Integer id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
