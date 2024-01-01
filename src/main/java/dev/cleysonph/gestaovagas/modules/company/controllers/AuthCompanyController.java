package dev.cleysonph.gestaovagas.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.company.dtos.AuthCompanyDTO;
import dev.cleysonph.gestaovagas.modules.company.usecases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;
    
    @PostMapping("/companies")
    public ResponseEntity<?> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            return ResponseEntity.ok(authCompanyUseCase.execute(authCompanyDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }

}
