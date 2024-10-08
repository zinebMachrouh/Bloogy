package dao;

import dao.Interfaces.CategoryDAO;
import models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private final SessionFactory sessionFactory;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Category addCategory(Category category) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            return category;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to add category", e);
        }
    }

    @Override
    public Category updateCategory(Category category) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
            return category;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to update category", e);
        }
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.delete(category);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to delete category", e);
        }
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Query<Category> query = session.createQuery("FROM Category", Category.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to retrieve categories", e);
        }
    }

    @Override
    public Category getCategoryById(int id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to retrieve category by id", e);
        }
    }

    @Override
    public int getTotalCount() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(c) FROM Category c", Long.class);
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get category count", e);
        }
    }
}
