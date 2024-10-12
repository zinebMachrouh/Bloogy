package controllers;

import dao.ArticleDAOImpl;
import dao.CategoryDAOImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.Interfaces.UserDAO;
import dao.UsersDAOImpl;
import dto.ArticleDTO;
import dto.CategoryDTO;
import dto.UserDTO;
import models.enums.ArticleStatus;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String pathInfo = request.getPathInfo(); // This will give you the path after "/article"

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length > 1) {
                String action = pathParts[1]; // Get the action (e.g., "view" or "management")

                if ("view".equals(action) && pathParts.length > 2) {
                    // Extract article ID from the URL
                    int articleId = Integer.parseInt(pathParts[2]);
                    try {
                        ArticleDTO article = ArticleDTO.modelToDTO(articleService.getArticleById(articleId));
                        request.setAttribute("article", article);
                        request.getRequestDispatcher("/WEB-INF/jsp/article/article_page.jsp").forward(request, response);
                    } catch (SQLException e) {
                        System.out.println("Failed to fetch article: " + e.getMessage());
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else if ("management".equals(action)) {
                    // Handle article management
                    try {
                        List<ArticleDTO> articles = articleService.getAllArticles();
                        request.setAttribute("articles", articles);
                        request.getRequestDispatcher("/WEB-INF/jsp/article/article_management.jsp").forward(request, response);
                    } catch (SQLException e) {
                        System.out.println("Failed to fetch articles: " + e.getMessage());
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else {
                    // Default action: show all articles on the main page
                    try {
                        List<ArticleDTO> articles = articleService.getAllArticles();
                        request.setAttribute("articles", articles);
                        request.getRequestDispatcher("/WEB-INF/jsp/article/article_main.jsp").forward(request, response);
                    } catch (SQLException e) {
                        System.out.println("Failed to fetch articles: " + e.getMessage());
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid URL format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Path information is missing");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            // Handle deletion of the article
            Integer articleId = Integer.parseInt(request.getParameter("id"));
            try {
                // Call the service to delete the article
                articleService.deleteArticle(articleId); // Ensure this method exists in your service
                // Redirect after deleting
                response.sendRedirect(request.getContextPath() + "/article/management");
            } catch (SQLException e) {
                System.out.println("Failed to delete article: " + e.getMessage());
                // Handle error (e.g., show an error message)
            }
        } else {
            // Collect form data for adding a new article
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            ArticleStatus status = ArticleStatus.valueOf(request.getParameter("status")); // Convert to enum
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Integer userId = Integer.parseInt(request.getParameter("userId"));

            // Create ArticleDTO
            ArticleDTO newArticle = new ArticleDTO();
            newArticle.setTitle(title);
            newArticle.setContent(content);
            newArticle.setCreatedAt(null); // Set createdAt as needed (consider setting to current date)
            newArticle.setLunchedAt(null); // Set lunchedAt as needed
            newArticle.setStatus(status);
            newArticle.setCategory(new CategoryDTO(categoryId, null, null)); // Populate as needed
            newArticle.setUser(new UserDTO(userId, null, null, null, null)); // Populate as needed

            try {
                // Call the service to add the article
                articleService.addArticle(newArticle);
                // Redirect or forward after adding
                response.sendRedirect(request.getContextPath() + "/article/management");
            } catch (SQLException e) {
                System.out.println("Failed to add article: " + e.getMessage());
                // Handle error (e.g., show an error message)
            }
        }
    }






}
