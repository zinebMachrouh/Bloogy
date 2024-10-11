package dto;

import models.User;
import models.enums.UserRole;

public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserRole role;

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {
    }

    public UserDTO(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO(Integer id, String username, String email, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public User dtoToModel() {
        return new User(
                this.id,
                this.username,
                this.email,
                this.password);
    }

    public static UserDTO modelToDTO(User user) {
        return new UserDTO(user.getId() ,user.getUsername(), user.getEmail(), user.getPassword());
    }
}