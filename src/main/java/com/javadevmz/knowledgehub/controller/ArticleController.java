package com.javadevmz.knowledgehub.controller;

import com.javadevmz.knowledgehub.dto.CreateArticleDto;
import com.javadevmz.knowledgehub.dto.UpdateArticleDto;
import com.javadevmz.knowledgehub.model.Article;
import com.javadevmz.knowledgehub.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/")
    public List<Article> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable Long id) {
        return service.getArticle(id);
    }

    @PostMapping("/")
    public void addArticle(@RequestBody CreateArticleDto dto) {
        service.addArticle(dto);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody UpdateArticleDto dto) {
        service.updateArticle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        service.delete(id);
    }
}
