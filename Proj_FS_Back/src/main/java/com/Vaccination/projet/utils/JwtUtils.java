/*package com.Vaccination.projet.utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;

public class JwtUtils {

    // Method to generate a JWT with a SecretKey
    public String generateJwt(String email) {
        try {
            // Generate a secret key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); // Specify the key size
            SecretKey secretKey = keyGenerator.generateKey();

            // Create the JWT token with the SecretKey
            return Jwts.builder()
                    .setSubject(email)
                    .signWith(secretKey, SignatureAlgorithm.HS256) // Use the SecretKey instead of a string
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Error while generating JWT", e);
        }
    }
}*/
