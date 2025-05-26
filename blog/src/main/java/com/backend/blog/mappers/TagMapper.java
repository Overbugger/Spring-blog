package com.backend.blog.mappers;

import com.backend.blog.dtos.CreateCategoryRequest;
import com.backend.blog.dtos.CreateTagRequest;
import com.backend.blog.dtos.TagResponse;
import com.backend.blog.models.Category;
import com.backend.blog.models.Post;
import com.backend.blog.models.PostStatus;
import com.backend.blog.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponds(Tag tag);

    @Named("calculatePostCount")
    default long calculatePostCount(Set<Post> posts) {
        if (posts == null) {
            return 0;
        }

        return (int) posts.stream()
                .filter(post -> post.getStatus().equals(PostStatus.PUBLISHED))
                .count();
    }
}
