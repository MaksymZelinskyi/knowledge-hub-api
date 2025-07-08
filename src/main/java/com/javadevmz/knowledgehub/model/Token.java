package com.javadevmz.knowledgehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    private String value;
    private boolean revoked;
    private boolean expired;

    @ManyToOne
    private User user;

    public Token(String value, User user) {
        this.value = value;
        this.user = user;
    }
}
