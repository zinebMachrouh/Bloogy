package dto;

import models.Comment;
import models.enums.CommentStatus;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

public class CommentDTO {
    private Integer id;
    private String content;
    private Date createdDate;
    private CommentStatus status;
    private ArticleDTO article;
    private UserDTO user;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String content, Date createdDate, ArticleDTO article, UserDTO user) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.status = CommentStatus.APPROVED;
        this.article = article;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public CommentStatus getStatus() {
        return status;
    }
    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public ArticleDTO getArticle() {
        return article;
    }
    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public UserDTO getUser() {
         return user;
    }
    public void setUser(UserDTO user) {
         this.user = user;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", article=" + article +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;

        CommentDTO that = (CommentDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (status != that.status) return false;
        return article != null ? article.equals(that.article) : that.article == null;
    }

    public Comment dtoToModel(){
        return new Comment(this.id, this.content, this.createdDate, this.status, this.article.dtoToModel(), this.user.dtoToModel());
    }

    public static CommentDTO modelToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getCreationDate(), ArticleDTO.modelToDTO(comment.getArticle()), UserDTO.modelToDTO(comment.getUser()));
    }
}
