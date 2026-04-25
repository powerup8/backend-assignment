package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Content should not be null")

    private String content;

    private LocalDateTime createdAt;

    private String authorType; // USER or BOT
    private Long authorId;

    // Constructors
    public Post() {}

    public Post(String content, String authorType, Long authorId) {
        this.content = content;
        this.authorType = authorType;
        this.authorId = authorId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}