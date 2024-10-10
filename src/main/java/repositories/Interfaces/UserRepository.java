package repositories.Interfaces;

import models.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationToken(String token);
    Optional<User> findByResetToken(String token);
    void update(User user);
}