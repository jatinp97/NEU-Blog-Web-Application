package com.neublog.neublogwebapp.mapper;

import com.neublog.neublogwebapp.dto.PostDto;
import com.neublog.neublogwebapp.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    //convert post entity to postDTo
    //map Post entity to PostDto

    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream()
                        .map((comment) -> CommentMapper.maptoCommentDto(comment))
                        .collect(Collectors.toSet()))
                .build();
    }
        //Map PostDto to postEntity

    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .url(postDto.getUrl())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();


        }
    }

