package controllers;

import dao.ArticleDAOImpl;
import dao.CommentDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CommentDAO;
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
import services.UserService;
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
    private UserService userService;

    @Override
    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CommentDAO commentDAO = new CommentDAOImpl(sessionFactory);
        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);

        commentService = new CommentServiceImpl(commentDAO, articleDAO);
        articleService = new ArticleServiceImpl(articleDAO);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        UserDTO authUser = (UserDTO) request.getSession().getAttribute("authUser");
        
    }
}