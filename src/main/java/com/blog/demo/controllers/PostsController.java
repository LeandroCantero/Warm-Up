package com.blog.demo.controllers;

import com.blog.demo.dtos.requests.PostsRequest;
import com.blog.demo.models.Post;
import com.blog.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPostsById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PostsRequest postsRequest){
        postService.createPost(postsRequest);
        return ResponseEntity.ok("Character created");
    }

    @DeleteMapping(value = "/{id}")
    public String deletePost(@PathVariable ("id") Long id){
        return postService.deletePost(id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable ("id") Long id, @RequestBody Post post){
        return ResponseEntity.ok(postService.updatePost(id, post));
    }




}
