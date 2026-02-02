package com.synapse.content.controller;

import com.synapse.content.entity.Post;
import com.synapse.content.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedController {
    private final PostRepository postRepository;

    public FeedController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/feed")
    public List<Post> getFeed() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
