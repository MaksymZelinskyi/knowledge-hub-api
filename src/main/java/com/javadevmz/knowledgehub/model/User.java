package com.javadevmz.knowledgehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated
    private Role role;

    public enum Role {
        ADMIN, CONTRIBUTOR, READER
    }
}
