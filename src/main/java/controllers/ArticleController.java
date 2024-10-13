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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length > 1) {
                String action = pathParts[1];

                if ("view".equals(action) && pathParts.length > 2) {
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
            Integer articleId = Integer.parseInt(request.getParameter("id"));
            try {
                articleService.deleteArticle(articleId);
                response.sendRedirect(request.getContextPath() + "/article/management");
            } catch (SQLException e) {
                System.out.println("Failed to delete article: " + e.getMessage());
            }
        } else if ("update".equals(action)) {

            Integer articleId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String statusParam = request.getParameter("status");
            ArticleStatus status = null;

            if (statusParam != null && !statusParam.isEmpty()) {
                try {
                    status = ArticleStatus.valueOf(statusParam);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status value: " + statusParam);
                }
            } else {
                System.out.println("Status is null or empty.");
                // Handle the case where status is not provided
            }
            Integer categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : null;
            Integer userId = request.getParameter("userId") != null ? Integer.parseInt(request.getParameter("userId")) : null;

            if (articleId == null || categoryId == null || userId == null) {
                response.sendRedirect(request.getContextPath() + "/article/management?error=missingParameters");
                return;
            }

            // Date parsing
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate createdAt = null;
            LocalDate lunchedAt = null;

            try {
                createdAt = LocalDate.parse(request.getParameter("createdAt"), formatter);
                String lunchedAtStr = request.getParameter("lunchedAt");
                if (lunchedAtStr != null && !lunchedAtStr.isEmpty()) {
                    lunchedAt = LocalDate.parse(lunchedAtStr, formatter);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Failed to parse date: " + e.getMessage());
            }

            ArticleDTO updatedArticle = new ArticleDTO();
            updatedArticle.setId(articleId);
            updatedArticle.setTitle(title);
            updatedArticle.setContent(content);
            updatedArticle.setCreatedAt(java.sql.Date.valueOf(createdAt));
            updatedArticle.setLunchedAt(lunchedAt != null ? java.sql.Date.valueOf(lunchedAt) : null);
            updatedArticle.setStatus(status);
            updatedArticle.setCategory(new CategoryDTO(categoryId, null, null));
            updatedArticle.setUser(new UserDTO(userId, null, null, null, null));

            try {
                articleService.updateArticle(updatedArticle);
                response.sendRedirect(request.getContextPath() + "/article/management");
            } catch (SQLException e) {
                System.out.println("Failed to update article: " + e.getMessage());
            }
        } else {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            ArticleStatus status = ArticleStatus.valueOf(request.getParameter("status"));
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Integer userId = Integer.parseInt(request.getParameter("userId"));

            ArticleDTO newArticle = new ArticleDTO();
            newArticle.setTitle(title);
            newArticle.setContent(content);
            newArticle.setCreatedAt(null);
            newArticle.setLunchedAt(null);
            newArticle.setStatus(status);
            newArticle.setCategory(new CategoryDTO(categoryId, null, null));
            newArticle.setUser(new UserDTO(userId, null, null, null, null));

            try {
                articleService.addArticle(newArticle);
                response.sendRedirect(request.getContextPath() + "/article/management");
            } catch (SQLException e) {
                System.out.println("Failed to add article: " + e.getMessage());
            }
        }
    }

}
