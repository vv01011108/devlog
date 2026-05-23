package com.example.awsdeploy.controller;

import com.example.awsdeploy.dto.PostResponse;
import com.example.awsdeploy.entity.AppUser;
import com.example.awsdeploy.entity.Post;
import com.example.awsdeploy.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal AppUser currentUser,
            @Valid @RequestBody CreatePostRequest request) {
        Post post = postService.createPost(
                request.title(), request.content(), currentUser);

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
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(PostResponse.from(post));
    }

    // 글 수정
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @AuthenticationPrincipal AppUser currentUser,
            @PathVariable Long id,
            @Valid @RequestBody CreatePostRequest request
    ) {
        Post post = postService.updatePost(
                id, request.title(), request.content(), currentUser);
        return ResponseEntity.ok(PostResponse.from(post));

    }

    // 글 삭제
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal AppUser currentUser,
            @PathVariable Long id) {
        postService.deletePost(id, currentUser);

        return ResponseEntity.noContent().build();
    }

    public record CreatePostRequest(
            @NotBlank(message = "제목은 필수입니다.")
            String title,

            @NotBlank(message = "내용은 필수입니다.")
            String content
    ) {}

    public record UpdatePostRequest(
            @NotBlank(message = "제목은 필수입니다.")
            String title,

            @NotBlank(message = "내용은 필수입니다.")
            String content
    ) {}
}
