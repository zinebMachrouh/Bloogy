package controllers;

import dao.ArticleDAOImpl;
import dao.CategoryDAOImpl;
import dao.CommentDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.Interfaces.CommentDAO;
import dao.Interfaces.UserDAO;
import dao.UsersDAOImpl;
import dto.UserDTO;
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
        articleService = new ArticleServiceImpl(articleDAO,categoryDAO, userDAO);

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