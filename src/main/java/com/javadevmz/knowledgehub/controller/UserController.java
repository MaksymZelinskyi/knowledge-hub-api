package com.javadevmz.knowledgehub.controller;

import com.javadevmz.knowledgehub.model.User;
import com.javadevmz.knowledgehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @PutMapping("/{id}/role")
    public void setRole(@PathVariable Long id, @RequestBody String role) {
        User user = repository.findById(id).orElseThrow();
        user.setRole(User.Role.valueOf(role));
    }

}
