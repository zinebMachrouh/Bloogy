package models;
import models.enums.CommentStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Column(name = "status", nullable = false)
    private CommentStatus status;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "comments")
    private List<Tag> tags;

    public Comment() {
    }

    public Comment(Integer id, String content, Date creationDate, CommentStatus status, Article article, User user) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.status = status;
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

    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CommentStatus getStatus() {
        return status;
    }
    public void setStatus(CommentStatus status) {
        this.status = status;
    }

    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", article=" + article +
                ", user=" + user +
                '}';
    }



}
