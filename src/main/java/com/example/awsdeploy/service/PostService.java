package com.example.awsdeploy.service;

import com.example.awsdeploy.entity.AppUser;
import com.example.awsdeploy.entity.Post;
import com.example.awsdeploy.exception.PostNotFoundException;
import com.example.awsdeploy.exception.UnauthorizedPostAccessException;
import com.example.awsdeploy.repository.PostRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(String title, String content, AppUser author) {
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllWithAuthor();
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    // 글 수정
    @Transactional
    public Post updatePost(Long id, String title, String content, AppUser currentUser) {
        Post post = postRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new UnauthorizedPostAccessException();
        }

        post.update(title, content);
        return post;
    }

    @Transactional
    public void deletePost(Long id, AppUser currentUser) {
        Post post = postRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new UnauthorizedPostAccessException();
        }

        postRepository.delete(post);
    }
}