package services;

import dto.UserDTO;
import models.User;
import models.enums.UserRole;
import org.mindrot.jbcrypt.BCrypt;
import repositories.Interfaces.UserRepository;
import services.Interfaces.UserService;
import utils.EmailService;

import java.util.Date;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public boolean register(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null ||
                userRepository.findByEmail(userDTO.getEmail()) != null) {
            return false; // User already exists
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
        user.setRole(UserRole.valueOf(userDTO.getRole()));
        user.setActive(true);
        user.setVerified(false);
        user.setDateJoined(new Date());
        user.setVerificationToken(UUID.randomUUID().toString());

        userRepository.save(user);

        // Send verification email
        String verificationLink = "http://localhost:8080/verify?token=" + user.getVerificationToken();
        emailService.sendVerificationEmail(user.getEmail(), verificationLink);

        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword()) && user.isVerified()) {
            return user;
        }
        return null;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            user.setResetTokenExpiry(new Date(System.currentTimeMillis() + 3600000)); // Token valid for 1 hour
            userRepository.update(user);

            // Send password reset email
            String resetLink = "http://localhost/reset-password?token=" + resetToken;
            emailService.sendPasswordResetEmail(user.getEmail(), resetLink);

            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        return userRepository.resetPassword(token, BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    }

    @Override
    public boolean verifyUser(String token) {
        return userRepository.verifyUser(token);
    }
}