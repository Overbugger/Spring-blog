package com.backend.blog.repo;

import com.backend.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {

    @Query("SELECT t From Tag t LEFT JOIN FETCH t.posts")
    List<Tag> findAllWithPostsCount();

    List<Tag> findByNameIn(Set<String> names);
}
