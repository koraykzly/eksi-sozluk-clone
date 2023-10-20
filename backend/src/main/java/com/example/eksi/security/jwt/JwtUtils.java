package com.example.eksi.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.eksi.payload.response.JwtPair;

import com.example.eksi.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access.expirationMin}")
    private int jwtAccessExpirationMin;

    @Value("${jwt.refresh.expirationMin}")
    private int jwtRefreshExpirationMin;

    public JwtBuilder generateJwtBase(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .signWith(key(), SignatureAlgorithm.HS256);
    }

    public String generateAccessToken(Authentication authentication) {
        return generateJwtBase(authentication)
                .setExpiration(new Date((new Date()).getTime() + getMilisecond(jwtAccessExpirationMin)))
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateJwtBase(authentication)
                .setExpiration(new Date((new Date()).getTime() + getMilisecond(jwtRefreshExpirationMin)))
                .compact();
    }

    public JwtPair generateJwtPair(Authentication authentication) {
        return new JwtPair(
                generateAccessToken(authentication),
                generateRefreshToken(authentication));
    }

    private long getMilisecond(int minute) {
        return minute * 60 * 1000L;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
