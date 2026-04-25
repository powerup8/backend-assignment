package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private boolean isPremium;

    // Constructors
    public User() {}

    public User(String username, boolean isPremium) {
        this.username = username;
        this.isPremium = isPremium;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
