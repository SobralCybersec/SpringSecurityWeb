package dev.math.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.math.security.entity.UserEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Getter
@Component
public class JwtConfig {

    private String secretKey = "secret";

    public String generateSecretKey(UserEntity user) {

        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        return JWT.create()
                .withClaim("key", "secret")
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(91239012))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            DecodedJWT jwtDecoder = JWT.require(algorithm).build().verify(token);
            return Optional.of(JWTUserData.builder()
                    .userId(jwtDecoder.getClaim("userId").asLong())
                    .email(jwtDecoder.getSubject())
                    .build());

        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
