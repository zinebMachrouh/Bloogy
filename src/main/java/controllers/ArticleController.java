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
import models.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import services.ArticleServiceImpl;
import services.CommentServiceImpl;
import services.Interfaces.ArticleService;
import services.Interfaces.CommentService;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ArticleController extends HttpServlet {
    private ArticleService articleService;
    private CommentService commentService;
    private Logger logger = Logger.getLogger(ArticleController.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        logger.info("Initializing ArticleController");
        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);
        CategoryDAO categoryDAO = new CategoryDAOImpl(sessionFactory);
        UserDAO userDAO = new UsersDAOImpl(sessionFactory);
        CommentDAO commentDAO = new CommentDAOImpl(sessionFactory);

        articleService = new ArticleServiceImpl(articleDAO, categoryDAO, userDAO);
        commentService = new CommentServiceImpl(commentDAO, articleDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //String action2 = request.getPathInfo();

        if ("view".equals(action)) {
            // Display specific article
            int articleId = Integer.parseInt(request.getParameter("id")); // Get the article ID from the request
            try {
                ArticleDTO article = ArticleDTO.modelToDTO(articleService.getArticleById(articleId)); // Fetch the article by ID
                List<Comment> comments = commentService.getAllComments(articleId); // Fetch all comments for the article
                System.out.println("Comments: " + comments);
                request.setAttribute("comments", comments);
                request.setAttribute("article", article); // Set the article as request attribute
                request.getRequestDispatcher("/WEB-INF/jsp/article/article_page.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Failed to fetch article: " + e.getMessage());
            }
        } else {
            // Default action: fetch all articles
            try {
                List<ArticleDTO> articles = articleService.getAllArticles();
                request.setAttribute("articles", articles); // Set articles as request attribute
                request.getRequestDispatcher("/WEB-INF/jsp/article/article_main.jsp").forward(request, response); // Forward to main JSP
            } catch (SQLException e) {
                System.out.println("Failed to fetch articles: " + e.getMessage());
            }
        }
    }

}
