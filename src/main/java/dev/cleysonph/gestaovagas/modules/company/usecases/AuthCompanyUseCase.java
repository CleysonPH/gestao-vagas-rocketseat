package dev.cleysonph.gestaovagas.modules.company.usecases;

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

import dev.cleysonph.gestaovagas.modules.company.dtos.AuthCompanyDTO;
import dev.cleysonph.gestaovagas.modules.company.dtos.AuthCompanyResponseDTO;
import dev.cleysonph.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.company}")
    private String secretKey;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new AuthenticationException());
        var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var accessToken = JWT.create()
            .withIssuer("javagas")
            .withSubject(company.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", List.of("ROLE_COMPANY"))
            .sign(Algorithm.HMAC256(secretKey));
        return new AuthCompanyResponseDTO(accessToken, expiresIn.toEpochMilli());
    }
    
}
