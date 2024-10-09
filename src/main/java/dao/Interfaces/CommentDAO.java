package dao.Interfaces;

import models.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    public Comment addComment(Comment comment) throws SQLException;
    public Comment updateComment(Comment comment) throws SQLException;
    void deleteComment(int id) throws SQLException;
    public Comment getCommentById(int id) throws SQLException;
    public List<Comment> getAllComments(int articleId) throws SQLException;
}
