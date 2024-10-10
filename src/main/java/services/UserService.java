package services;

import dto.UserDTO;
import models.User;
import models.enums.UserRole;
import repositories.Interfaces.UserRepository;
import utils.Config;
import utils.PasswordHasher;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final EmailService emailService;

    // Constructor for injecting dependencies
    public UserService(UserRepository userRepository, PasswordHasher passwordHasher, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.emailService = emailService;
    }

    public void register(UserDTO userDTO) throws Exception {
        // Use UserRepository to find the user
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new Exception("Username already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordHasher.hash(userDTO.getPassword()));
        user.setActive(true);
        user.setDateJoined(new Date());
        user.setRole(UserRole.CONTRIBUTOR);
        user.setVerified(false);
        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);

        try {
            userRepository.save(user);

            String verificationLink = Config.get("BASE_URL") + "/auth/verify-email?token=" + verificationToken;
            String emailBody = "Click the following link to verify your email: " + verificationLink;
            emailService.sendEmail(user.getEmail(), "Email Verification", emailBody);
        } catch (Exception e) {
            throw new Exception("Error creating user: " + e.getMessage());
        }
    }

    public void verifyEmail(String token) throws Exception {
        Optional<User> optionalUser = userRepository.findByVerificationToken(token);
        User user = optionalUser.orElseThrow(() -> new Exception("Invalid verification token"));

        user.setVerified(true);
        user.setRole(UserRole.EDITOR);
        user.setVerificationToken(null);
        userRepository.update(user);
    }

    public User login(String username, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new Exception("User not found"));

        if (!user.isActive()) {
            throw new Exception("User is inactive");
        }

        if (!passwordHasher.verify(password, user.getPassword())) {
            throw new Exception("Incorrect password");
        }

        if (!user.isVerified()) {
            throw new Exception("Email not verified");
        }

        return user;
    }

    public void initiatePasswordReset(String email) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new Exception("User not found"));

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setResetTokenExpiry(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry

        userRepository.update(user);

        String baseUrl = Config.get("BASE_URL");
        String resetLink = baseUrl + "/auth/reset-password?token=" + resetToken;
        String emailBody = "Click the following link to reset your password: " + resetLink;
        emailService.sendEmail(user.getEmail(), "Password Reset Request", emailBody);
    }

    public void resetPassword(String token, String newPassword) throws Exception {
        Optional<User> optionalUser = userRepository.findByResetToken(token);
        User user = optionalUser.orElseThrow(() -> new Exception("Invalid reset token"));

        if (user.getResetTokenExpiry().before(new Date())) {
            throw new Exception("Expired reset token");
        }

        user.setPassword(passwordHasher.hash(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);

        userRepository.update(user);
    }
}
