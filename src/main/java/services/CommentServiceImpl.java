package services;

import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CommentDAO;
import dto.CommentDTO;
import models.Comment;
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
    public Comment updateComment(CommentDTO comment) throws SQLException {
        Comment commentModel = comment.dtoToModel();
        return commentDAO.updateComment(commentModel);
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        if (commentDAO.getCommentById(id) != null){
            commentDAO.deleteComment(id);
        }
    }

    @Override
    public Comment getCommentById(int id) throws SQLException {
        if (commentDAO.getCommentById(id) != null){
            return commentDAO.getCommentById(id);
        }else{
            return null;
        }
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
