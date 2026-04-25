package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.example.demo.entity.Post; 
import com.example.demo.entity.Comment; 
import com.example.demo.repository.PostRepository; 
import com.example.demo.repository.CommentRepository; 
import java.time.LocalDateTime; 





@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RedisService redisService;   // ✅ FIX ADDED

    @Autowired
    private AtomicLockService atomicLockService;

    // ✅ CREATE POST
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        redisService.updateVirality(savedPost.getId(), "BOT_REPLY");

        return savedPost;
    }

    // ✅ ADD COMMENT
    public Comment addComment(Long postId, Comment comment) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepository.save(comment);

        redisService.updateVirality(postId, "COMMENT");

        return saved;
    }

    // ✅ LIKE POST
    public String likePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        redisService.updateVirality(postId, "LIKE");

        return "Post " + postId + " liked!";
    }
}