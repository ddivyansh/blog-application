package com.example.blogapplication.repository;

import com.example.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository for Post JPA entity.
 * Extending JpaRepository allows us to access crud functions & also pagination & sorting related methods.
 * There's no need to annotate with @Repository as internally its annotated with @Repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
