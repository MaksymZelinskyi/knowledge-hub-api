package com.javadevmz.knowledgehub.repository;

import com.javadevmz.knowledgehub.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {


}
