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

    // 글 작성
    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request.title(), request.content());
    }

    // 글 목록 조회
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 글 상세 조회
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody CreatePostRequest request
    ) {
        return postService.updatePost(id, request.title(), request.content());
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    public record CreatePostRequest(
            String title,
            String content
    ) {}

    public record UpdatePostRequest(
            String title,
            String content
    ) {}
}
