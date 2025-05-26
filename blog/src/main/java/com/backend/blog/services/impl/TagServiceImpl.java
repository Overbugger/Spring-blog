package com.backend.blog.services.impl;

import com.backend.blog.models.Tag;
import com.backend.blog.repo.TagRepo;
import com.backend.blog.repo.UserRepo;
import com.backend.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;
    private final UserRepo userRepo;

    @Override
    public List<Tag> listTags() {
        return tagRepo.findAllWithPostsCount();
    }

    @Transactional
    @Override
    public List<Tag> createTag(Set<String> tagNames) {
        log.info(tagNames.toString());
       List<Tag> existingTags = tagRepo.findByNameIn(tagNames);

       Set<String> existingTagNames = existingTags.stream()
               .map(Tag::getName)
               .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()) {
            savedTags = tagRepo.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(Integer id) {
        tagRepo.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()) {
                throw new IllegalStateException("Cannot delete tag with posts");
            }
            tagRepo.deleteById(id);
        });
    }

}
