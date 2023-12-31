package dev.cleysonph.gestaovagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.candidate.CandidateEntity;
import dev.cleysonph.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CadidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CandidateEntity candidateEntity) {
        try {
            return ResponseEntity.ok(createCandidateUseCase.execute(candidateEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
