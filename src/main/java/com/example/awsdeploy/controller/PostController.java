package com.example.awsdeploy.controller;

import com.example.awsdeploy.dto.PostResponse;
import com.example.awsdeploy.entity.Post;
import com.example.awsdeploy.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePostRequest request) {
        Post post = postService.createPost(request.title(), request.content());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(post));
    }

    // 글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {
        List<PostResponse> responses = postService.getPosts()
                .stream()
                .map(PostResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // 글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(PostResponse.from(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @RequestBody CreatePostRequest request
    ) {
        Post post = postService.updatePost(id, request.title(), request.content());
        return ResponseEntity.ok(PostResponse.from(post));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
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
