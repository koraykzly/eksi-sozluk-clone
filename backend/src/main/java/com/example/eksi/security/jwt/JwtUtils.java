package com.example.eksi.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.eksi.payload.response.JwtPair;

import com.example.eksi.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String TOKEN_TYPE_CLAIM = "tokenType";
    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access.expirationMin}")
    private int jwtAccessExpirationMin;

    @Value("${jwt.refresh.expirationMin}")
    private int jwtRefreshExpirationMin;

    public JwtBuilder generateJwtBase(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return generateJwtBase(userPrincipal.getUsername());
    }

    public JwtBuilder generateJwtBase(UserDetails userDetails) {
        return generateJwtBase(userDetails.getUsername());
    }

    private JwtBuilder generateJwtBase(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .signWith(key());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateJwtBase(authentication)
                .claim(TOKEN_TYPE_CLAIM, ACCESS_TOKEN_TYPE)
                .expiration(new Date((new Date()).getTime() + getMillisecond(jwtAccessExpirationMin)))
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateJwtBase(authentication)
                .claim(TOKEN_TYPE_CLAIM, REFRESH_TOKEN_TYPE)
                .expiration(new Date((new Date()).getTime() + getMillisecond(jwtRefreshExpirationMin)))
                .compact();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateJwtBase(userDetails)
                .claim(TOKEN_TYPE_CLAIM, ACCESS_TOKEN_TYPE)
                .expiration(new Date((new Date()).getTime() + getMillisecond(jwtAccessExpirationMin)))
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateJwtBase(userDetails)
                .claim(TOKEN_TYPE_CLAIM, REFRESH_TOKEN_TYPE)
                .expiration(new Date((new Date()).getTime() + getMillisecond(jwtRefreshExpirationMin)))
                .compact();
    }

    public JwtPair generateJwtPair(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtPair(
                generateAccessToken(authentication),
                generateRefreshToken(authentication),
                userPrincipal.getUsername());
    }

    public JwtPair generateJwtPair(UserDetails userDetails) {
        return new JwtPair(
                generateAccessToken(userDetails),
                generateRefreshToken(userDetails),
                userDetails.getUsername());
    }

    private long getMillisecond(int minute) {
        return minute * 60 * 1000L;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isAccessToken(String token) {
        return ACCESS_TOKEN_TYPE.equals(getClaims(token).get(TOKEN_TYPE_CLAIM, String.class));
    }

    public boolean isRefreshToken(String token) {
        return REFRESH_TOKEN_TYPE.equals(getClaims(token).get(TOKEN_TYPE_CLAIM, String.class));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key())
                .build()
                .parseSignedClaims(authToken);
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (JwtException e) {
            logger.error("JWT exception: {}", e.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) key())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
