package net.sprinBackend.springbootBackend.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private final static Key secreteKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final static long expirationDate = 86400000;

    public static String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .signWith(secreteKey)
                .compact();
    }

    public static String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secreteKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
