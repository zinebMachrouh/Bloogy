package repositories;

import dao.Interfaces.UserDAO;
import models.User;
import repositories.Interfaces.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDAO;

    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public boolean verifyUser(String token) {
        return userDAO.verifyUser(token);
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        return userDAO.resetPassword(token, newPassword);
    }
}