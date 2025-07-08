package com.javadevmz.knowledgehub.controller;

import com.javadevmz.knowledgehub.dto.CreateArticleDto;
import com.javadevmz.knowledgehub.dto.UpdateArticleDto;
import com.javadevmz.knowledgehub.model.Article;
import com.javadevmz.knowledgehub.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public void addArticle(@Valid @RequestBody CreateArticleDto dto) {
        service.addArticle(dto);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @Valid @RequestBody UpdateArticleDto dto) {
        service.updateArticle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Article> getArticlesByTag(@NotBlank @RequestParam String tag) {
        return service.searchArticlesByTag(tag);
    }
}
