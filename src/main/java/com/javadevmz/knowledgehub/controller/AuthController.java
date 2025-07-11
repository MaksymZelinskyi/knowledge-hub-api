package com.javadevmz.knowledgehub.controller;

import com.javadevmz.knowledgehub.dto.AuthResponse;
import com.javadevmz.knowledgehub.dto.LoginDto;
import com.javadevmz.knowledgehub.dto.RegisterDto;
import com.javadevmz.knowledgehub.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterDto dto) {
        return new AuthResponse(service.register(dto));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginDto dto) {
        return new AuthResponse(service.login(dto));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest req) {
        service.logout(req);
    }
}
