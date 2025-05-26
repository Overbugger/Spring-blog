package com.backend.blog.controllers;

import com.backend.blog.dtos.CreateTagRequest;
import com.backend.blog.dtos.TagResponse;
import com.backend.blog.mappers.TagMapper;
import com.backend.blog.models.Tag;
import com.backend.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<TagResponse> tagResponses = tagService.listTags().stream()
                .map(tagMapper::toTagResponds)
                .toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTag(
           @Valid @RequestBody CreateTagRequest createTagRequest
    ) {
        List<Tag> savedTags = tagService.createTag(createTagRequest.getNames());

        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponds)
                .toList();

        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
