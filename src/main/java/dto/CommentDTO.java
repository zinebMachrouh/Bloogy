package dto;

import models.Comment;
import models.enums.CommentStatus;

import java.util.Date;

public class CommentDTO {
    private Integer id;
    private String content;
    private Date creationDate;
    private CommentStatus status;
    private ArticleDTO article;
    private UserDTO user;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String content, Date creationDate, ArticleDTO article, UserDTO user) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
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
        return creationDate;
    }
    public void setCreatedDate(Date creationDate) {
        this.creationDate = creationDate;
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
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", article=" + article +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CommentDTO comment = (CommentDTO) obj;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (creationDate != null ? !creationDate.equals(comment.creationDate) : comment.creationDate != null) return false;
        if (status != comment.status) return false;
        return article != null ? article.equals(comment.article) : comment.article == null;
    }

    public Comment dtoToModel(){
        return new Comment(this.id, this.content, this.creationDate, this.status, this.article.dtoToModel(), this.user.dtoToModel());
    }

    public static CommentDTO modelToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getContent(), comment.getCreationDate(), ArticleDTO.modelToDTO(comment.getArticle()), UserDTO.modelToDTO(comment.getUser()));
    }
}