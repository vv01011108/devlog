package com.example.awsdeploy.controller;

import com.example.awsdeploy.entity.Post;
import com.example.awsdeploy.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest request) {
        Post post = new Post(request.title(), request.content());
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public record CreatePostRequest(
            String title,
            String content
    ) {
    }
}
