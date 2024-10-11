package controllers;

import dao.ArticleDAOImpl;
import dao.CategoryDAOImpl;
import dao.Interfaces.UserDAO;
import dto.ArticleDTO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.Interfaces.ArticleService;

import services.Interfaces.ArticleService;
import services.ArticleServiceImpl;
import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.UsersDAOImpl;
import dto.ArticleDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ArticleServlet extends HttpServlet {

    private ArticleService articleService;

    @Override
    public void init() throws ServletException {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        ArticleDAO articleDAO = new ArticleDAOImpl(sessionFactory);
        CategoryDAO categoryDAO = new CategoryDAOImpl(sessionFactory);
        UserDAO userDAO = new UsersDAOImpl(sessionFactory);

        articleService = new ArticleServiceImpl(articleDAO, categoryDAO, userDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ArticleDTO> articles = articleService.getAllArticles();

            request.setAttribute("articles", articles);

            request.getRequestDispatcher("/articles.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve articles.");
        }
    }
}