package repositories;

import dao.UserDAO;
import models.User;
import repositories.Interfaces.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserDAO userDAO;

    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = userDAO.findByEmail(email);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByVerificationToken(String token) {
        User user = userDAO.findByVerificationToken(token);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByResetToken(String token) {
        User user = userDAO.findByResetToken(token);
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        userDAO.create(user);
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}
