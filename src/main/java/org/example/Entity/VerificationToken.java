package org.example.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class VerificationToken {

    private String token;


    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private Date expiryDate;


    public VerificationToken(Long Id, String token, User user,Date expiryDate) {
        this.id = Id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }


    public VerificationToken() {
        return;
    }

}
