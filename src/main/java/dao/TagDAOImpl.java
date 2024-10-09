package dao;

import dao.Interfaces.TagDAO;
import models.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private final SessionFactory sessionFactory;

    public TagDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tag addTag(Tag tag) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tag);
            transaction.commit();
            return tag;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to add tag", e);
        }
    }

    @Override
    public Tag updateTag(Tag tag) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(tag);
            transaction.commit();
            return tag;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to update tag", e);
        }
    }

    @Override
    public void deleteTag(int id) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Tag tag = session.get(Tag.class, id);
            if (tag != null) {
                session.delete(tag);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to delete tag", e);
        }
    }

    @Override
    public List<Tag> getAllTags() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Tag", Tag.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get all tags", e);
        }
    }

    @Override
    public Tag getTagById(int id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tag.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get tag by id", e);
        }
    }

    @Override
    public int getTotalCount() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Tag", Tag.class).list().size();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get total count of tags", e);
        }
    }
}
