package com.example.awsdeploy.repository;

import com.example.awsdeploy.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
