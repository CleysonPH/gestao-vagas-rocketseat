package dev.cleysonph.gestaovagas.modules.candidate.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import dev.cleysonph.gestaovagas.modules.candidate.CandidateRepository;
import dev.cleysonph.gestaovagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import dev.cleysonph.gestaovagas.modules.candidate.dtos.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateDTO.username())
            .orElseThrow(AuthenticationException::new);
        var passwordMatches = passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
            .withIssuer("javagas")
            .withSubject(candidate.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", List.of("candidate"))
            .sign(Algorithm.HMAC256(secretKey));
        return new AuthCandidateResponseDTO(token, expiresIn.toEpochMilli());
    }
    
}
