package com.backend.blog.repo;

import com.backend.blog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query("SELECT c From Category c LEFT JOIN FETCH c.posts")
    List<Category> findAllWithPostsCount();

    boolean existsByNameIgnoreCase(String name);
}
