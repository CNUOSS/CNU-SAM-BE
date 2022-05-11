package gp.cnusambe.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    public final static long TOKEN_EXPIRATION_SECONDS = 1000L * 60 * 30;
    public final static long REFRESH_EXPIRATION_SECONDS = 1000L * 60 * 120;

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(UserDetailsImpl userDetailsImpl) {
        return generateJwtToken(userDetailsImpl, TOKEN_EXPIRATION_SECONDS);
    }

    public String generateRefreshToken(UserDetailsImpl userDetailsImpl) {
        return generateJwtToken(userDetailsImpl, REFRESH_EXPIRATION_SECONDS);
    }

    public String generateJwtToken(UserDetailsImpl userDetailsImpl, long expirationMs) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationMs);

        if (expirationMs == TOKEN_EXPIRATION_SECONDS) {
            log.debug("AccessToken : " + now + "\n" + validity);
        } else if (expirationMs == REFRESH_EXPIRATION_SECONDS) {
            log.debug("RefreshToken : " + now + "\n" + validity);
        }
        return Jwts.builder()
                .setSubject(userDetailsImpl.getUserId())
                .claim(AUTHORITIES_KEY, userDetailsImpl.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserIdFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
