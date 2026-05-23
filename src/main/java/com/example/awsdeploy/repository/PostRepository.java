package com.example.awsdeploy.repository;

import com.example.awsdeploy.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.author order by p.id desc")
    List<Post> findAllWithAuthor();

    @Query("select p from Post p join fetch p.author where p.id = :id")
    Optional<Post> findByIdWithAuthor(@Param("id") Long id);

}
