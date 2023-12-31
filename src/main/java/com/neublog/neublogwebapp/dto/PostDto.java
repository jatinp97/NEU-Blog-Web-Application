package com.neublog.neublogwebapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

//transfer data between controller layer and view layer
@Data
@Builder //to generate builder pattern
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;
    @NotEmpty(message = "Title Should not be empty")
    private String title;
    private String url;
    @NotEmpty(message = "Content has to be entered")
    private String content;
    @NotEmpty(message = "Enter the Description")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Set<CommentDto> comments;

}
