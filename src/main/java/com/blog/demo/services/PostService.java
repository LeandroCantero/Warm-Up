package com.blog.demo.services;

import com.blog.demo.dtos.PostDTO;
import com.blog.demo.dtos.requests.PostsRequest;
import com.blog.demo.models.Post;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDTO> getPosts() {
        var posts = postRepository.findAll();
        if (posts == null) return null;

        var mappedPosts = posts
                .stream()
                .map(p -> new PostDTO(p.getId(), p.getTitle(), p.getImage(), p.getCategory(), p.getCreation_date()))
                .collect(Collectors.toList());
        return mappedPosts;
    }

    @Override
    public Object findById(Long id){
        if (postRepository.findById(id) == null) {
            return "This post doesn't exist";
        }
        else {
            return postRepository.findById(id);
        }
    }

    @Override
    public Post createPost(PostsRequest postsRequest) {
        var post = new Post();
        post.setTitle(postsRequest.getTitle());
        post.setCategory(postsRequest.getCategory());
        post.setContent(postsRequest.getContent());
        post.setImage(postsRequest.getImage());
        return postRepository.save(post);
    }

    @Override
    public String deletePost(Long id) {
        if (postRepository.findById(id).isPresent()){
            postRepository.deleteById(id);
            return "Post deleted";
        }
        else {
            return "This id doesn't exist";
        }
    }

    @Override
    public Post updatePost(Long id, Post post) {
        var newPost = postRepository.findById(id);
        var pst = newPost.get();
        if (newPost.isPresent()) {
            String title = post.getTitle() == null ? pst.getTitle() : post.getTitle();
            String content = post.getContent() == null ? pst.getContent() : post.getContent();
            String category = post.getCategory() == null ? pst.getCategory() : post.getCategory();
            String image = post.getImage() == null ? pst.getImage() : post.getImage();
            pst.setTitle(title);
            pst.setContent(content);
            pst.setCategory(category);
            pst.setImage(image);
        }
        return postRepository.save(pst);
    }
}
