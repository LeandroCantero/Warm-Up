package com.blog.demo.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PostsRequest {
    private String title;
    private String content;
    private String image;
    private String category;
}
