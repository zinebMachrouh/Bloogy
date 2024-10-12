package dao;

import dao.Interfaces.CommentDAO;
import models.Comment;
import models.enums.CommentStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private final SessionFactory sessionFactory;

    public CommentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment addComment(Comment comment) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            if (comment.getCreationDate() == null){
                comment.setCreationDate(new java.util.Date());
            }

            if (comment.getStatus() == null){
                comment.setStatus(CommentStatus.approved);
            }

            transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
            return comment;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to add comment", e);
        }
    }

    @Override
    public Comment updateComment(Comment comment) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(comment);
            transaction.commit();
            return comment;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to update comment", e);
        }
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Comment comment = session.get(Comment.class, id);
            if (comment != null) {
                session.delete(comment);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new SQLException("Failed to delete comment", e);
        }
    }

    @Override
    public Comment getCommentById(int id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Comment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get comment", e);
        }
    }

    @Override
    public List<Comment> getAllComments(int articleId) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            List<Comment> comments = session.createQuery("from Comment c where c.article.id = :articleId and status = :status order by c.creationDate desc", Comment.class)
                    .setParameter("articleId", articleId)
                    .setParameter("status", CommentStatus.approved)
                    .list();
            System.out.println("Comments found: " + comments.size());
            System.out.println("Getting comments for article: " + articleId);
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get comments for the specified article", e);
        }
    }

}
