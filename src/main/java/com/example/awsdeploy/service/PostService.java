package com.example.awsdeploy.service;

import com.example.awsdeploy.entity.Post;
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
    public Post createPost(String title, String content) {
        Post post = new Post(title, content);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

}
