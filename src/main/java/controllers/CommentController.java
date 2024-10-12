package controllers;

import dao.ArticleDAOImpl;
import dao.CategoryDAOImpl;
import dao.CommentDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.Interfaces.CommentDAO;
import dao.Interfaces.UserDAO;
import dao.UsersDAOImpl;
import dto.ArticleDTO;
import dto.CommentDTO;
import dto.UserDTO;
import models.Comment;
import models.enums.CommentStatus;
import org.hibernate.SessionFactory;
import services.ArticleServiceImpl;
import services.CommentServiceImpl;
import services.Interfaces.ArticleService;
import services.Interfaces.CommentService;
import services.UserServiceImpl;
import utils.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@WebServlet(name = "CommentController", value = "/CommentController")
public class CommentController extends HttpServlet {

    private CommentService commentService;
    private ArticleService articleService;
    private UserServiceImpl userService;

    @Override
    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CommentDAO commentDAO = new CommentDAOImpl(sessionFactory);
        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);
        CategoryDAO categoryDAO = new CategoryDAOImpl(sessionFactory);
        UserDAO userDAO = new UsersDAOImpl(sessionFactory);

        commentService = new CommentServiceImpl(commentDAO, articleDAO);
        articleService = new ArticleServiceImpl(articleDAO, categoryDAO, userDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDTO authUser = (UserDTO) request.getSession().getAttribute("authUser");

        try {
            switch (action) {
                case "create":
                    createComment(request, response, authUser);
                    break;
                case "delete":
                    deleteComment(request, response);
                    break;
                case "updateContent":
                    updateCommentContent(request, response);
                    break;
                case "updateStatus":
                    updateCommentStatus(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void createComment(HttpServletRequest request, HttpServletResponse response, UserDTO authUser) throws SQLException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String content = request.getParameter("content");

        if (authUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArticleDTO article = ArticleDTO.modelToDTO(articleService.getArticleById(articleId));

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(content);
        commentDTO.setCreatedDate(new Date());
        commentDTO.setStatus(CommentStatus.APPROVED);
        commentDTO.setArticle(article);
        commentDTO.setUser(authUser);

        commentService.addComment(commentDTO);

        response.sendRedirect("article.jsp?id=" + articleId);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));

        commentService.deleteComment(commentId);

        String articleId = request.getParameter("articleId");
        response.sendRedirect("article.jsp?id=" + articleId);
    }

    private void updateCommentContent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        String newContent = request.getParameter("content");

        CommentDTO commentDTO = CommentDTO.modelToDTO(commentService.getCommentById(commentId));
        commentDTO.setContent(newContent);
        commentService.updateComment(commentDTO);

        String articleId = request.getParameter("articleId");
        response.sendRedirect("article.jsp?id=" + articleId);
    }

    private void updateCommentStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        String status = request.getParameter("status");

        CommentDTO commentDTO = CommentDTO.modelToDTO(commentService.getCommentById(commentId));

        if ("approve".equalsIgnoreCase(status)) {
            commentDTO.setStatus(CommentStatus.APPROVED);
        } else if ("reject".equalsIgnoreCase(status)) {
            commentDTO.setStatus(CommentStatus.REJECTED);
        }

        commentService.updateComment(commentDTO);

        String articleId = request.getParameter("articleId");
        response.sendRedirect("article.jsp?id=" + articleId);
    }
}
