package services;

import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CommentDAO;
import dto.CommentDTO;
import models.Comment;
import models.enums.CommentStatus;
import services.Interfaces.CommentService;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final ArticleDAO articleDAO;

    public CommentServiceImpl(CommentDAO commentDAO, ArticleDAO articleDAO) {
        this.commentDAO = commentDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    public Comment addComment(CommentDTO comment) throws SQLException {
        Comment commentModel = comment.dtoToModel();
        return commentDAO.addComment(commentModel);
    }

    @Override
    public Comment updateComment(CommentDTO commentDTO) throws SQLException {
        Comment comment = commentDAO.getCommentById(commentDTO.getId()); // Retrieve the comment first
        if (comment != null) {
            comment.setContent(commentDTO.getContent());
            //comment.setStatus(CommentStatus.APPROVED);  // Example: Update status or other fields
            return commentDAO.updateComment(comment);  // Then update the comment
        }
        throw new SQLException("Comment not found");
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        if (commentDAO.getCommentById(id) != null){
            commentDAO.deleteComment(id);
        }
    }

    @Override
    public Comment getCommentById(int id) throws SQLException {
        Comment comment = commentDAO.getCommentById(id);
        if (comment == null) {
            throw new SQLException("Comment not found");
        }
        return comment;
    }

    @Override
    public List<Comment> getAllComments(int articleId) throws SQLException {
        if (articleDAO.getArticleById(articleId) != null) {
            return commentDAO.getAllComments(articleId);
        }else {
            return null;
        }
    }

}
