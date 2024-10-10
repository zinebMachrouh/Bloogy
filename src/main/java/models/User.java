package models;

import models.enums.UserRole;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "date_joined", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateJoined;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetTokenExpiry;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public Date getDateJoined() { return dateJoined; }
    public void setDateJoined(Date dateJoined) { this.dateJoined = dateJoined; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
    public String getVerificationToken() { return verificationToken; }
    public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }
    public Date getResetTokenExpiry() { return resetTokenExpiry; }
    public void setResetTokenExpiry(Date resetTokenExpiry) { this.resetTokenExpiry = resetTokenExpiry; }
}
