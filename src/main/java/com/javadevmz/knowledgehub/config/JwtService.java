package com.javadevmz.knowledgehub.config;

import com.javadevmz.knowledgehub.model.Token;
import com.javadevmz.knowledgehub.model.User;
import com.javadevmz.knowledgehub.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET = "mYsupeR  SecrT";
    private static final int EXPIRATION = 1000 * 60 * 60;

    private final TokenRepository tokenRepository;

    public String generateJwt(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles().stream().map(User.Role::name))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        String extractedUsername = extractUsername(jwt);
        Token token = tokenRepository.findByValue(jwt);
        if(token==null) return false;
        if(isExpired(token.getValue())) {
            token.setExpired(true);
            return false;
        }
        return !token.isRevoked() && extractedUsername.equals(userDetails.getUsername());
    }

    public boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractJwt(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        String token = null;
        if(authorization!=null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);
        }
        return token;
    }
}
