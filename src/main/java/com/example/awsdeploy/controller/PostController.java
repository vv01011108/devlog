package com.example.awsdeploy.controller;

import com.example.awsdeploy.entity.Post;
import com.example.awsdeploy.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request.title(), request.content());
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    public record CreatePostRequest(
            String title,
            String content
    ) {
    }
}
