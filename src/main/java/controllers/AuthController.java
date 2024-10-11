package controllers;

import dao.UsersDAOImpl;
import dto.UserDTO;
import models.User;
import repositories.UserRepositoryImpl;
import services.Interfaces.UserService;
import services.UserServiceImpl;
import utils.EmailService;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize Hibernate SessionFactory
        HibernateUtil.getSessionFactory();

        // Initialize UserDAO
        UsersDAOImpl userDAO = new UsersDAOImpl(HibernateUtil.getSessionFactory());

        // Initialize UserRepository
        UserRepositoryImpl userRepository = new UserRepositoryImpl(userDAO);

        // Initialize EmailService (you might want to pass configuration parameters here)
        EmailService emailService = new EmailService();

        // Initialize UserService
        this.userService = new UserServiceImpl(userRepository, emailService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/verify":
                verifyUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/register":
                register(request, response);
                break;
            case "/login":
                login(request, response);
                break;
            case "/forgot-password":
                forgotPassword(request, response);
                break;
            case "/reset-password":
                resetPassword(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getParameter("username"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setRole(request.getParameter("role"));

        if (userService.register(userDTO)) {
            request.setAttribute("message", "Registration successful. Please check your email to verify your account.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Username or email already exists.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (userService.requestPasswordReset(email)) {
            request.setAttribute("message", "Password reset instructions have been sent to your email.");
        } else {
            request.setAttribute("error", "Email not found.");
        }
        request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp").forward(request, response);
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");

        if (userService.resetPassword(token, newPassword)) {
            request.setAttribute("message", "Password has been reset successfully. You can now login with your new password.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Password reset failed. The link may have expired.");
            request.getRequestDispatcher("/WEB-INF/views/reset-password.jsp").forward(request, response);
        }
    }



    private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");

        if (userService.verifyUser(token)) {
            request.setAttribute("message", "Your account has been successfully verified. You can now login.");
        } else {
            request.setAttribute("error", "Account verification failed. The link may be invalid or expired.");
        }
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        // Shutdown Hibernate SessionFactory
        HibernateUtil.shutdown();
    }
}