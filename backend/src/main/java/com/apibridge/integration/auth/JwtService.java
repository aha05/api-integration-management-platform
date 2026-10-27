package com.apibridge.integration.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.apibridge.integration.user.entity.User;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;

    public Jwt generateAccessToken(User user){
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user){
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long tokenExpiration) {
        var roles = user.getRoles()
                .stream()
                .map(userRole -> userRole.getRole().getName())
                .toList();

        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        return new Jwt(claims, jwtConfig.getSecretKey()) ;
    }

    public Jwt parseToken(String token){
        try{
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (JwtException e){
            return null;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
