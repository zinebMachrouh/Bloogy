package services.Interfaces;

import dto.ArticleDTO;
import models.Article;

import java.sql.SQLException;
import java.util.List;

interface ArticleService {
    public Article addArticle(ArticleDTO article) throws SQLException;
    public Article updateArticle(ArticleDTO article) throws SQLException;
    void deleteArticle(int id) throws SQLException;
    List<Article> getAllArticles() throws SQLException;
    Article getArticleById(int id) throws SQLException;
}
