package dev.digital_wind_gays.neo_story.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import dev.digital_wind_gays.neo_story.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class DefaultJwtService implements JwtService {

    @Value("${app.jwt.issuer}")
    private String issuer;

    @Value("${app.jwt.subject}")
    private String subject;

    @Value("${app.jwt.duration}")
    private Long duration;

    @Value("${app.jwt.secret}")
    private String secret;

    @Override
    public String generate(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(ZonedDateTime.now().plusDays(duration).toInstant())
                .withClaim("userId", user.getId())
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public DecodedJWT verify(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(issuer)
                .withClaimPresence("userId")
                .build();

        return verifier.verify(token);
    }
}
