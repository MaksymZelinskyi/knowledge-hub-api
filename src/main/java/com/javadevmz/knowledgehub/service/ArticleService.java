package com.javadevmz.knowledgehub.service;

import com.javadevmz.knowledgehub.dto.CreateArticleDto;
import com.javadevmz.knowledgehub.dto.UpdateArticleDto;
import com.javadevmz.knowledgehub.model.Article;
import com.javadevmz.knowledgehub.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getArticle(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void addArticle(CreateArticleDto dto) {
        Article article = new Article(dto.title(), dto.content());
        repository.save(article);
    }

    public void updateArticle(Long id, UpdateArticleDto dto) {
        Article article = new Article(id, dto.title(), dto.content());
        repository.save(article);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
