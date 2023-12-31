package com.neublog.neublogwebapp.mapper;

import com.neublog.neublogwebapp.dto.CommentDto;
import com.neublog.neublogwebapp.entity.Comment;

public class CommentMapper {

    // Convert Comment Entity to Comment DTO
    public static CommentDto maptoCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }


    //Convert Comment DTO to Comment Entity

    public static Comment maptoComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }


}
