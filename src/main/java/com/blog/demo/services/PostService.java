package com.blog.demo.services;

import com.blog.demo.dtos.PostDTO;
import com.blog.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostDTO> getPosts() {
        var posts = postRepository.findAll();
        if (posts == null) return null;

        var mappedPosts = posts
                .stream()
                .map(p -> new PostDTO(p.getId(), p.getTitle(), p.getImage(), p.getCategory(), p.getCreation_date()))
                .collect(Collectors.toList());
        return mappedPosts;
    }
}
