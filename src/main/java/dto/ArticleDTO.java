package dto;

import models.Article;
import models.Category;
import models.enums.ArticleStatus;

import java.util.Date;
import java.util.Objects;

public class ArticleDTO {
    private Integer id;
    private String title;
    private String content;
    private Date createdAt;
    private Date lunchedAt;
    private ArticleStatus status;
    private CategoryDTO category;
    //private UserDTO user;


    public ArticleDTO() {
    }

    /*public ArticleDTO(Integer id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status, CategoryDTO category, UserDTO user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
        this.category = category;
        this.user = user;
    }*/

    public ArticleDTO(Integer id, String title, String content, Date createdAt, Date lunchedAt, ArticleStatus status, CategoryDTO categoryDTO) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lunchedAt = lunchedAt;
        this.status = status;
        this.category = categoryDTO;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
/*
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArticleDTO that = (ArticleDTO) obj;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lunchedAt, that.lunchedAt) && status == that.status && Objects.equals(category, that.category) ;
    }

    public Article dtoToModel() {
        //return new Article(this.id, this.title, this.content, this.createdAt, this.lunchedAt, this.status, this.category.dtoToModel(), this.user.dtoToModel());
        return new Article(this.id, this.title, this.content, this.createdAt, this.lunchedAt, this.status, this.category.dtoToModel());
    }

    public static ArticleDTO modelToDTO(Article article) {
        //return new ArticleDTO(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getLunchedAt(), article.getStatus(), CategoryDTO.modelToDTO(article.getCategory()), UserDTO.modelToDTO(article.getUser()));
        return new ArticleDTO(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getLunchedAt(), article.getStatus(), CategoryDTO.modelToDTO(article.getCategory()));
    }


}
