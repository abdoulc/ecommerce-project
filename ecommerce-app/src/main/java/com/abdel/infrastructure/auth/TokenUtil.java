package com.abdel.infrastructure.auth;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.command.port.out.TokenPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil implements TokenPort {
    private final String SECRET_KEY = "D7fR9x2vL6qZ8mY1nP5sB3wV0kC4eT9H";
    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public String generateToken(UserView user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", user.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername().value())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token, UserView user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername().value()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}

