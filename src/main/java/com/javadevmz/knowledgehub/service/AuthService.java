package com.javadevmz.knowledgehub.service;

import com.javadevmz.knowledgehub.config.JwtService;
import com.javadevmz.knowledgehub.dto.LoginDto;
import com.javadevmz.knowledgehub.dto.RegisterDto;
import com.javadevmz.knowledgehub.model.User;
import com.javadevmz.knowledgehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterDto dto) {
        User user = new User(dto.username(),  dto.email(), dto.firstName(), dto.lastName(), encoder.encode(dto.password()));
        userRepository.save(user);
        return jwtService.generateJwt(user);
    }

    public String login(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByUsername(dto.username());
        return jwtService.generateJwt(user);
    }
}
