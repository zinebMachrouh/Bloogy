package services.Interfaces;

import dto.CommentDTO;
import models.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    public Comment addComment(CommentDTO comment) throws SQLException;
    public Comment updateComment(CommentDTO comment) throws SQLException;
    void deleteComment(int id) throws SQLException;
    public Comment getCommentById(int id) throws SQLException;
    public List<Comment> getAllComments(int articleId) throws SQLException;
}
