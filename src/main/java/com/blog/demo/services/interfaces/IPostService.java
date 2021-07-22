package com.blog.demo.services.interfaces;

import com.blog.demo.dtos.PostDTO;
import com.blog.demo.dtos.requests.PostsRequest;
import com.blog.demo.models.Post;

import java.util.List;

public interface IPostService {
    List<PostDTO> getPosts();

    Object findById(Long id);

    Post createPost(PostsRequest postsRequest);

    String deletePost(Long id);

    Post updatePost(Long id, Post post);
}
