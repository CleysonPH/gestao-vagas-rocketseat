package dev.cleysonph.gestaovagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.exceptions.UserFoundException;
import dev.cleysonph.gestaovagas.modules.company.entities.CompanyEntity;
import dev.cleysonph.gestaovagas.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CompanyEntity companyEntity) {
        try {
            return ResponseEntity.ok(createCompanyUseCase.execute(companyEntity));
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
