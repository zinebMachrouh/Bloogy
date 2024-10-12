package controllers;

import dao.ArticleDAOImpl;
import dao.CategoryDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.Interfaces.UserDAO;
import dao.UsersDAOImpl;
import dto.ArticleDTO;
import org.hibernate.SessionFactory;
import services.ArticleServiceImpl;
import services.Interfaces.ArticleService;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArticleController extends HttpServlet {
    private ArticleService articleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);
        CategoryDAO categoryDAO = new CategoryDAOImpl(sessionFactory);
        UserDAO userDAO = new UsersDAOImpl(sessionFactory);

        articleService = new ArticleServiceImpl(articleDAO, categoryDAO, userDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/view".equals(path)) {
            // Display a specific article
            int articleId = Integer.parseInt(request.getParameter("id"));
            try {
                ArticleDTO article = ArticleDTO.modelToDTO(articleService.getArticleById(articleId));
                request.setAttribute("article", article);
                request.getRequestDispatcher("/WEB-INF/jsp/article/article_page.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Failed to fetch article: " + e.getMessage());
            }
        } else if ("/management".equals(path)) {
            // Display articles in a table format
            try {
                List<ArticleDTO> articles = articleService.getAllArticles();
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/WEB-INF/jsp/article/article_management.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Failed to fetch articles: " + e.getMessage());
            }
        } else {
            // Default action: fetch all articles for the main page
            try {
                List<ArticleDTO> articles = articleService.getAllArticles();
                request.setAttribute("articles", articles);
                request.getRequestDispatcher("/WEB-INF/jsp/article/article_main.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Failed to fetch articles: " + e.getMessage());
            }
        }
    }



}
