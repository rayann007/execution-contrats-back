package com.leoni.execution.contrats.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    // ✅ Clé secrète sécurisée pour HS512 (512 bits minimum)
    private final SecretKey jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private final long jwtExpirationMs = 86400000; // 1 jour

    // ✅ Génère un token signé avec la clé
    public String generateJwt(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role) // Ajout de l'autorité
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

    // ✅ Extrait l’email à partir du token JWT
    public String getEmailFromJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Vérifie que le token est valide
    public boolean validateJwt(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getRoleFromJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

}
