package com.javadevmz.knowledgehub.service;

import com.javadevmz.knowledgehub.config.JwtService;
import com.javadevmz.knowledgehub.dto.LoginDto;
import com.javadevmz.knowledgehub.dto.RegisterDto;
import com.javadevmz.knowledgehub.model.Token;
import com.javadevmz.knowledgehub.model.User;
import com.javadevmz.knowledgehub.repository.TokenRepository;
import com.javadevmz.knowledgehub.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 3 * * ?") // Every day at 3AM
    public void deleteOldTokens() {
        tokenRepository.deleteAllByRevokedTrueOrExpiredTrue();
    }

    public String register(RegisterDto dto) {
        User user = new User(dto.username(),  dto.email(), dto.firstName(), dto.lastName(), encoder.encode(dto.password()));
        user.setRoles(List.of(User.Role.READER));
        userRepository.save(user);
        String jwt = jwtService.generateJwt(user);
        Token token = new Token(jwt, user);
        tokenRepository.save(token);
        return jwt;
    }

    public String login(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByUsername(dto.username());
        String jwt = jwtService.generateJwt(user);
        Token token = new Token(jwt, user);
        tokenRepository.save(token);
        return jwt;
    }

    public void logout(HttpServletRequest req) {
       String jwt = jwtService.extractJwt(req);
       Token token = tokenRepository.findByValue(jwt);
       token.setRevoked(true);
    }
}
