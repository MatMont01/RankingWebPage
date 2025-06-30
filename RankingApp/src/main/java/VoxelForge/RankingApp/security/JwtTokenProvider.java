// Archivo: src/main/java/VoxelForge/RankingApp/security/JwtTokenProvider.java
package VoxelForge.RankingApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    // Método para generar la clave de firma a partir del secreto
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Genera un token a partir de un objeto de autenticación
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // Obtiene el nombre de usuario desde el token JWT
    public String getUsernameFromJWT(String token) {
        // --- ¡AQUÍ ESTÁ LA SINTAXIS CORREGIDA! ---
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Valida la integridad y expiración del token
    public boolean validateToken(String authToken) {
        try {
            // --- ¡Y AQUÍ TAMBIÉN! ---
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception ex) {
            // Aquí puedes registrar el tipo de error si quieres más detalle
            // ej. MalformedJwtException, ExpiredJwtException, etc.
        }
        return false;
    }
}