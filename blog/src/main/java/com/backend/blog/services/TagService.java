package com.backend.blog.services;

import com.backend.blog.dtos.TagResponse;
import com.backend.blog.models.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> listTags();
    List<Tag> createTag(Set<String> tagNames);
    void deleteTag(Integer id);
}
