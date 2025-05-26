package com.backend.blog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest {

    @NotEmpty(message = "At least one tag is required")
    @Size(max = 10, message = "Maximum of {max} tags allowed")
    private Set<
            @NotBlank(message = "Tag name is required")
            @Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} character")
            @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters numbers, spaces, and hyphen")
            String
            > names;
}
