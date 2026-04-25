package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private int depthLevel;

    private LocalDateTime createdAt;

    private String authorType; // USER or BOT
    private Long authorId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Constructors
    public Comment() {}

    public Comment(String content, int depthLevel, String authorType, Long authorId, Post post) {
        this.content = content;
        this.depthLevel = depthLevel;
        this.authorType = authorType;
        this.authorId = authorId;
        this.post = post;
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

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
