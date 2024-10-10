package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class UserDAO {

    public User findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public User findByVerificationToken(String verificationToken) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE verificationToken = :verificationToken", User.class)
                    .setParameter("verificationToken", verificationToken)
                    .uniqueResult();
        }
    }

    public User findByResetToken(String resetToken) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE resetToken = :resetToken", User.class)
                    .setParameter("resetToken", resetToken)
                    .uniqueResult();
        }
    }

    public void create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}