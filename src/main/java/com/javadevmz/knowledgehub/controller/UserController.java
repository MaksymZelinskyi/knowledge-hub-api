package com.javadevmz.knowledgehub.controller;

import com.javadevmz.knowledgehub.model.User;
import com.javadevmz.knowledgehub.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @PostMapping("/users/{id}/roles")
    public void addRole(@PathVariable Long id, @NotBlank @RequestBody String role) {
        User user = repository.findById(id).orElseThrow();
        user.getRoles().add(User.Role.valueOf(role));
    }

    @DeleteMapping("/users/{id}/roles")
    public void removeRole(@PathVariable Long id, @NotBlank @RequestBody String role) {
        User user = repository.findById(id).orElseThrow();
        user.getRoles().remove(User.Role.valueOf(role));
    }

    @GetMapping("/users/")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}
