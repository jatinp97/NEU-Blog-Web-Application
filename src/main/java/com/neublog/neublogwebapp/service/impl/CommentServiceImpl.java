package com.neublog.neublogwebapp.service.impl;

import com.neublog.neublogwebapp.dto.CommentDto;
import com.neublog.neublogwebapp.entity.Comment;
import com.neublog.neublogwebapp.entity.Post;
import com.neublog.neublogwebapp.entity.User;
import com.neublog.neublogwebapp.mapper.CommentMapper;
import com.neublog.neublogwebapp.repository.CommentRepository;
import com.neublog.neublogwebapp.repository.PostRepository;
import com.neublog.neublogwebapp.repository.UserRepository;
import com.neublog.neublogwebapp.service.CommentService;
import com.neublog.neublogwebapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

@Autowired
    private CommentRepository commentRepository;

@Autowired
private UserRepository userRepository;
@Autowired
private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.maptoComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper :: maptoCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtil.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Integer userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);

        return comments.stream()
                .map(comment -> CommentMapper.maptoCommentDto(comment))
                .collect(Collectors.toList());
    }
}
