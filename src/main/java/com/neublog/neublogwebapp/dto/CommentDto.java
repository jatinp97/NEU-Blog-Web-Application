package com.neublog.neublogwebapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Enter Valid Email ID/Should not be Null")
    @Email
    private String email;
    @NotEmpty(message = "Cannot Be NULL/Empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
