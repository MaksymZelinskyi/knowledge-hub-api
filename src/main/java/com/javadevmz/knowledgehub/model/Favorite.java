package com.javadevmz.knowledgehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorite {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;
    @ManyToOne
    private Article article;

}
