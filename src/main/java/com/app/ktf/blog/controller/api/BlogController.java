package com.app.ktf.blog.controller.api;

import com.app.ktf.blog.entity.PostEntity;
import com.app.ktf.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PostEntity createPost(@RequestBody PostEntity post) {
        return postRepository.save(post);
    }
}
