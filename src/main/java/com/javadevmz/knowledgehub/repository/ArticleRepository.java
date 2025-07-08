package com.javadevmz.knowledgehub.repository;

import com.javadevmz.knowledgehub.model.Article;
import com.javadevmz.knowledgehub.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByTagsContains(Tag tag);
}
