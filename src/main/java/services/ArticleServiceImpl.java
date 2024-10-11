package services;

import dao.Interfaces.ArticleDAO;
import dao.Interfaces.CategoryDAO;
import dao.Interfaces.UserDAO;
import dto.ArticleDTO;
import models.Article;
import models.Category;
import services.Interfaces.ArticleService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO;
    private CategoryDAO categoryDAO;
    private UserDAO userDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
        this.categoryDAO = categoryDAO;


    }



    @Override
    public Article addArticle(ArticleDTO articleDTO) throws SQLException {
        Article article = new Article(
                articleDTO.getId(),
                articleDTO.getTitle(),
                articleDTO.getContent(),
                articleDTO.getCreatedAt(),
                articleDTO.getLunchedAt(),
                articleDTO.getStatus(),
                articleDTO.getCategory().dtoToModel()
        );
        return articleDAO.addArticle(article);
    }


    @Override
    public Article updateArticle(ArticleDTO articleDTO) throws SQLException {
        if (articleDAO.getArticleById(articleDTO.getId()) == null) {
            return null;
        } else {
            Article article = new Article(
                    articleDTO.getId(),
                    articleDTO.getTitle(),
                    articleDTO.getContent(),
                    articleDTO.getCreatedAt(),
                    articleDTO.getLunchedAt(),
                    articleDTO.getStatus(),
                    articleDTO.getCategory().dtoToModel()
            );
            return articleDAO.updateArticle(article);
        }
    }

    @Override
    public void deleteArticle(int id) throws SQLException {
        if (articleDAO.getArticleById(id) == null) {
            throw new SQLException("Article not found with ID: " + id);
        }
        articleDAO.deleteArticle(id);
    }

    @Override
    public List<ArticleDTO> getAllArticles() throws SQLException {
        List<Article> allArticles = articleDAO.getAllArticles();

        return allArticles.stream()
                .map(ArticleDTO::modelToDTO)
                .collect(Collectors.toList());    }

    @Override
    public Article getArticleById(int id) throws SQLException {
        Article article = articleDAO.getArticleById(id);
        return (article != null) ? article : null;    }

}
