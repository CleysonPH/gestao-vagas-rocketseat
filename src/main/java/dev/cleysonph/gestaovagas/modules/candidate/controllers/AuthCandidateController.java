package dev.cleysonph.gestaovagas.modules.candidate.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import dev.cleysonph.gestaovagas.modules.candidate.usecases.AuthCandidateUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidates")
    public ResponseEntity<?> auth(@RequestBody AuthCandidateRequestDTO authCandidateDTO) {
        try {
            return ResponseEntity.ok(authCandidateUseCase.execute(authCandidateDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }
    
}
