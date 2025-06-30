package com.javadevmz.knowledgehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String value;

}
