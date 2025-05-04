package org.example.Entity;


import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    private boolean isEnabled;


//    @OneToOne(mappedBy = "user")
//    private VerificationTokenRepository _verificationToken;


    public User() {
        return;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public User(Long Id, String username, String password, String role) {
        this.id = Id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

//    public User() {
//    }


}


