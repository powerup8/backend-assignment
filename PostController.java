package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // ✅ 1. Create Post
    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.createPost(post);
    }

    // ✅ 2. Add Comment
    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return postService.addComment(postId, comment);
    }

    // ✅ 3. Like Post
    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId) {
        return postService.likePost(postId);
    }
    
    
   
   
    

}