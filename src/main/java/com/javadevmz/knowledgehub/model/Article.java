package com.javadevmz.knowledgehub.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;


    private String title;
    private String content;

    @ManyToOne
    private User author;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime lastModification;
    @ManyToMany
    private Set<Tag> tags;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article(Long id, String title, String content) {
        this(title, content);
        this.id = id;
    }
}
