package dev.cleysonph.gestaovagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {

    @Value("${security.token.secret.company}")
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }
    
}
