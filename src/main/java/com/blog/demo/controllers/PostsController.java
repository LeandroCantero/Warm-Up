package com.blog.demo.controllers;

import com.blog.demo.models.Post;
import com.blog.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    private Post post;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity <List<Object>> getPosts (){
        var posts = postService.getPosts();
        return new ResponseEntity(posts, HttpStatus.OK);

    }




}
