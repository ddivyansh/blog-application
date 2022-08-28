package com.example.blogApplication.repository;

import com.example.blogApplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * A repository for Post JPA entity.
 * Extending JpaRepository allows us to access crud functions & also pagination & sorting related methods.
 * There's no need to annotate with @Repository as internally its annotated with @Repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE posts AUTO_INCREMENT=1", nativeQuery = true)
    void resetAutoIncrement(String tableName);
}
