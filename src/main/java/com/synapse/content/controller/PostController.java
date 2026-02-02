package com.synapse.content.controller;

import com.synapse.auth.security.SecurityConfig;
import com.synapse.content.dto.CreatePostRequest;
import com.synapse.content.entity.Post;
import com.synapse.content.repository.PostRepository;
import com.synapse.user.entity.User;
import com.synapse.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public String createPost(@RequestBody CreatePostRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User creator = userRepository.findByUsername(username).orElseThrow();

        Post post = new Post(
                creator, request.type(), request.content()
        );

        postRepository.save(post);

        return "Post Created";
    }
}
