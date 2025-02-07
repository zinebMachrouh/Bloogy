package repositories.Interfaces;

import models.User;
import java.util.Optional;

public interface UserRepository {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    boolean verifyUser(String token);
    boolean resetPassword(String token, String newPassword);
}