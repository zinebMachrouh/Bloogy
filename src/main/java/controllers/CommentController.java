package controllers;

import dao.ArticleDAOImpl;
import dao.CommentDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CommentDAO;
import dto.CommentDTO;
import models.Comment;
import org.hibernate.SessionFactory;
import services.CommentServiceImpl;
import services.Interfaces.CommentService;
import utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
@WebServlet(name = "CommentController", value = "/CommentController")
public class CommentController extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CommentDAO commentDAO = new CommentDAOImpl(sessionFactory);
        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);

        commentService = new CommentServiceImpl(commentDAO, articleDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(idStr);
                Comment comment = commentService.getCommentById(id);
                if (comment != null) {
                    request.setAttribute("comment", comment);
                    request.getRequestDispatcher("/WEB-INF/jsp/comment/CommentForm.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Comment not found");
                }
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(idStr);
                commentService.deleteComment(id);
                response.sendRedirect("CommentController?action=list");
            } else if ("list".equals(action)) {
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                List<Comment> comments = commentService.getAllComments(articleId);
                request.setAttribute("comments", comments);
                request.getRequestDispatcher("/WEB-INF/jsp/comment/CommentList.jsp").forward(request, response);
            } else {
                response.sendRedirect("CommentController?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setContent(request.getParameter("content"));
                commentDTO.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
                commentDTO.setArticleId(Integer.parseInt(request.getParameter("articleId")));

                commentService.addComment(commentDTO);
                response.sendRedirect("CommentController?action=list&articleId=" + commentDTO.getArticleId());
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(id);
                commentDTO.setContent(request.getParameter("content"));
                commentDTO.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
                commentDTO.setArticleId(Integer.parseInt(request.getParameter("articleId")));

                commentService.updateComment(commentDTO);
                response.sendRedirect("CommentController?action=list&articleId=" + commentDTO.getArticleId());
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
*/