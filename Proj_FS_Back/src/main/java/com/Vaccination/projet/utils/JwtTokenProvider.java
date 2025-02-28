package com.Vaccination.projet.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import java.security.Key;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "UyQGfFZf3ZRA6pDi4/U7Dh03jtWz5EzJmtFZJXlXjY8="; // Replace with a secure key

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

  public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()  // Utilisez parserBuilder au lieu de parser
                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))  // Utilisation de la clé sous forme de tableau de bytes
                .build()  // Construisez le parser
                .parseClaimsJws(token)  // Parse le JWT
                .getBody();  // Récupère le corps des claims
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

  public String generateToken(UserDetails userDetails) {
        // Créez une clé de signature à partir de votre clé secrète
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures
                .signWith(key, SignatureAlgorithm.HS256)  // Utilisation de la clé au lieu de la chaîne de caractères
                .compact();
    }
}
