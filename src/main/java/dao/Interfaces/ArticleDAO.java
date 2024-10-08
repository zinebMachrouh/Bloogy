package dao.Interfaces;

import models.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {
    public Article addArticle(Article article) throws SQLException;
    public Article updateArticle(Article article) throws SQLException;
    void deleteArticle(int id) throws SQLException;
    public List<Article> getAllArticles() throws SQLException;
    public Article getArticleById(int id) throws SQLException;
    public int getTotalCount() throws SQLException;
}
