package services.Interfaces;

import dto.UserDTO;
import models.User;

public interface UserService {
    boolean register(UserDTO userDTO);
    User login(String username, String password);
    boolean requestPasswordReset(String email);
    boolean resetPassword(String token, String newPassword);
    boolean verifyUser(String token);
}
