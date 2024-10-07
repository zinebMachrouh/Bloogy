package dto;

import models.Article;
import models.enums.ArticleStatus;

import java.util.Date;
import java.util.Objects;

public class ArticleDTO {
    private int id;
    private String title;
    private String content;
    private Date createdAt;
    private Date lunchedAt;
    private ArticleStatus status;
    private int categoryId;
    private int authorId;

    public ArticleDTO() {
    }

    public ArticleDTO(int id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status, int categoryId, int authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
        this.categoryId = categoryId;
        this.authorId = authorId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDTO that = (ArticleDTO) o;
        return id == that.id && categoryId == that.categoryId && authorId == that.authorId && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lunchedAt, that.lunchedAt) && status == that.status;
    }

    public Article dtoToModel(){
        return new Article(this.id, this.title, this.content, this.createdAt, this.lunchedAt, this.status, this.categoryId, this.authorId);
    }

    public static ArticleDTO modelToDTO(Article article){
        return new ArticleDTO(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getLunchedAt(), article.getStatus(), article.getCategoryId(), article.getAuthorId());
    }

}
