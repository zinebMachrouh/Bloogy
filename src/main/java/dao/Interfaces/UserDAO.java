package dao.Interfaces;

import models.User;

public interface UserDAO {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    boolean verifyUser(String token);
    boolean resetPassword(String token, String newPassword);
}