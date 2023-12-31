package com.neublog.neublogwebapp.service.impl;

import com.neublog.neublogwebapp.dto.PostDto;
import com.neublog.neublogwebapp.entity.Post;
import com.neublog.neublogwebapp.entity.User;
import com.neublog.neublogwebapp.mapper.PostMapper;
import com.neublog.neublogwebapp.repository.PostRepository;
import com.neublog.neublogwebapp.repository.UserRepository;
import com.neublog.neublogwebapp.service.PostService;
import com.neublog.neublogwebapp.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Service class and registered as spring bean to application
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostByUser() {
        String email = SecurityUtil.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Integer userId = createdBy.getId();
        List<Post> posts = postRepository.findPostByUser(userId);
        return posts.stream()
                .map((post -> PostMapper.mapToPostDto(post)))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtil.getCurrentUser().getUsername();
        User user =userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);

        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Integer postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtil.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

}
