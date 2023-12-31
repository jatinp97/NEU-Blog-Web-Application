package com.neublog.neublogwebapp.service;

import com.neublog.neublogwebapp.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Integer commentId);

    List<CommentDto> findCommentsByPost();

}
