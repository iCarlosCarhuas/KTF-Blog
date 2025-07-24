package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.PostEntity;
import com.app.ktf.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * List all posts
     */
    @GetMapping
    public String listPosts(Model model) {
        List<PostEntity> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list"; // templates/posts/list.html
    }

    /**
     * Show form to create a new post
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new PostEntity());
        return "posts/create"; // templates/posts/create.html
    }

    /**
     * Process form to create a new post
     */
    @PostMapping("/create")
    public String createPost(@ModelAttribute PostEntity post, Model model) {
        postService.save(post);
        model.addAttribute("message", "Post created successfully!");
        return "redirect:/posts";
    }

    /**
     * Show form to edit an existing post
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        PostEntity post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        model.addAttribute("post", post);
        return "posts/edit"; // templates/posts/edit.html
    }

    /**
     * Process form to update an existing post
     */
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostEntity post, Model model) {
        post.setId(id);
        postService.save(post);
        model.addAttribute("message", "Post updated successfully!");
        return "redirect:/posts";
    }

    /**
     * View details of a post
     */
    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        PostEntity post = postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        model.addAttribute("post", post);
        return "posts/view"; // templates/posts/view.html
    }

    /**
     * Delete a post
     */
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, Model model) {
        postService.deleteById(id);
        model.addAttribute("message", "Post deleted successfully!");
        return "redirect:/posts";
    }

    /**
     * Handle IllegalArgumentException globally within this controller
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // templates/error.html
    }
}
