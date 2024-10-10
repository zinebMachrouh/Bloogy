package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import dao.UserDAO;
import repositories.Interfaces.UserRepository;
import repositories.UserRepositoryImpl;
import services.EmailService;
import services.UserService;
import dto.UserDTO;
import models.User;
import utils.PasswordHasher;


public class AuthController extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException {
        // Create the required dependencies manually
        UserRepository userRepository = new UserRepositoryImpl(new UserDAO());
        PasswordHasher passwordHasher = new PasswordHasher();
        EmailService emailService = new EmailService();
        userService = new UserService(userRepository, passwordHasher, emailService);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/register":
                    registerUser(request, response);
                    break;
                case "/login":
                    loginUser(request, response);
                    break;
                case "/reset-password-request":
                    resetPasswordRequest(request, response);
                    break;
                case "/reset-password":
                    resetPassword(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/common/error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/verify-email":
                    verifyEmail(request, response);
                    break;
                case "/logout":
                    logoutUser(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/common/error.jsp").forward(request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDTO userDTO = new UserDTO(username, email, password);
        userService.register(userDTO);
        request.setAttribute("message", "Registration successful. Please check your email to verify your account.");
        request.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }

    private void verifyEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getParameter("token");
        userService.verifyEmail(token);
        request.setAttribute("message", "Email verified successfully. You can now log in.");
        request.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp").forward(request, response);
    }

    private void resetPasswordRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        userService.initiatePasswordReset(email);
        request.setAttribute("message", "Password reset link sent to your email.");
        request.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp").forward(request, response);
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        userService.resetPassword(token, newPassword);
        request.setAttribute("message", "Password reset successful. You can now log in with your new password.");
        request.getRequestDispatcher("/WEB-INF/jsp/common/message.jsp").forward(request, response);
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/auth/login");
    }
}