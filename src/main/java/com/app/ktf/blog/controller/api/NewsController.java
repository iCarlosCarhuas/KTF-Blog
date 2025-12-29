package com.app.ktf.blog.controller.api;

import com.app.ktf.blog.entity.NewsEntity;
import com.app.ktf.blog.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    @PostMapping
    public NewsEntity createNews(@RequestBody NewsEntity news) {
        return newsRepository.save(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsEntity> getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
