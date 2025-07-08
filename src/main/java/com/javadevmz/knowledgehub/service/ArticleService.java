package com.javadevmz.knowledgehub.service;

import com.javadevmz.knowledgehub.dto.CreateArticleDto;
import com.javadevmz.knowledgehub.dto.UpdateArticleDto;
import com.javadevmz.knowledgehub.model.Article;
import com.javadevmz.knowledgehub.repository.ArticleRepository;
import com.javadevmz.knowledgehub.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    public void addArticle(CreateArticleDto dto) {
        Article article = new Article(dto.title(), dto.content());
        articleRepository.save(article);
    }

    public void updateArticle(Long id, UpdateArticleDto dto) {
        Article article = new Article(id, dto.title(), dto.content());
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> searchArticlesByTag(String tag) {
        return articleRepository.findAllByTagsContains(tagRepository.findByValue(tag));
    }
}
