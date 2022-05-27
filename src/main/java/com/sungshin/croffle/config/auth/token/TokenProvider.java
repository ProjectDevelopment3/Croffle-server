package com.sungshin.croffle.config.auth.token;

import com.sungshin.croffle.config.auth.AppProperties;
import com.sungshin.croffle.config.auth.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        System.out.print("token provider: ");
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        System.out.println("user principal: " + userPrincipal);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +
                appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        System.out.println("getUserIdFromToken");
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        System.out.println("validate Token");
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("유효하지 않은 JWT 서명");
        } catch (MalformedJwtException e) {
            log.error("유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰");
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 JWT 토큰");
        } catch (IllegalArgumentException e) {
            log.error("비어있는 JWT");
        }
        return false;
    }
}
