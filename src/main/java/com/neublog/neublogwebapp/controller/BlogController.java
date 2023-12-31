package com.neublog.neublogwebapp.controller;

import com.neublog.neublogwebapp.dto.CommentDto;
import com.neublog.neublogwebapp.dto.PostDto;
import com.neublog.neublogwebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private PostService postService;


    //All client Side Handlers in this controller
    //Handler to Handle localhost:8080/

    @GetMapping("/")
    public String viewBlogPosts(Model model){

        List<PostDto> postResponse = postService.findAllPosts();

        model.addAttribute("postResponse", postResponse);

        return "/blog/view_Post";

    }

    //Handler to handle view post request

    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl,
                            Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        CommentDto commentDto = new CommentDto();

        model.addAttribute("post",post);
        model.addAttribute("comment", commentDto);
        return "/blog/blogPost";
    }

    //handler method to handle the Blog Search Request
    //this is for client
    //localhost:8080/page/search?query=Keyword
    @GetMapping("/page/search")
    public String searchBlog(@RequestParam(value = "query")String query,
                             Model model){

        List<PostDto> postResponse = postService.searchPosts(query);
        model.addAttribute("postResponse",postResponse);

        return "/blog/view_Post";

    }

}
