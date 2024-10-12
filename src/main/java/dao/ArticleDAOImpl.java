package dao;

import dao.Interfaces.ArticleDAO;
import models.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

public class ArticleDAOImpl implements ArticleDAO {
    private final SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);

    public ArticleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Article addArticle(Article article) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
            return article;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to add article", e);
        }
    }

    public Article updateArticle(Article article) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Use merge instead of update
            Article updatedArticle = (Article) session.merge(article);

            transaction.commit();
            return updatedArticle;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // Log the exception
            logger.error("Failed to update article", e); // Assuming logger is set up
            throw new SQLException("Failed to update article", e);
        }
    }



    public void deleteArticle(int id) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Article article = session.get(Article.class, id);
            if (article != null) {
                session.delete(article);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to delete article", e);
        }
    }

    public List<Article> getAllArticles() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Article", Article.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get all articles", e);
        }
    }

    public Article getArticleById(int id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Article.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get article by id", e);
        }
    }

    public int getTotalCount() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(*) from Article", Long.class).uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get total count", e);
        }
    }




}
