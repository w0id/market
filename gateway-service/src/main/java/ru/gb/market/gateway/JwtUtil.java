package ru.gb.market.gateway;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Autowired
    private JwtProperties properties;

    public JWT getAllClaimsFromToken(String token) {
            try {
                return JWTParser.parse(token);
            } catch (Exception ex) {
                throw new JwtException("An error occurred while attempting to decode the Jwt: " + ex.getMessage(), ex);
            }
//        return Jwts.parser()
//                .setSigningKey(properties.getSecret())
//                .parseClaimsJws(token)
//                .getBody();
    }

    private boolean isTokenExpired(String token){
        JWT jwt = null;
        try {
            jwt = JWTParser.parse(token);
            Map<String, Object> claims = jwt.getJWTClaimsSet().getClaims();
            String username = claims.get("preferred_username").toString();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            return this.getAllClaimsFromToken(token).getJWTClaimsSet().getExpirationTime().before(new Date());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }
}
