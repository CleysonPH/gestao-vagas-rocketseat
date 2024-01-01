package dev.cleysonph.gestaovagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.exceptions.UserFoundException;
import dev.cleysonph.gestaovagas.modules.candidate.CandidateEntity;
import dev.cleysonph.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import dev.cleysonph.gestaovagas.modules.candidate.usecases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CadidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CandidateEntity candidateEntity) {
        try {
            return ResponseEntity.ok(createCandidateUseCase.execute(candidateEntity));
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity<?> get(HttpServletRequest request) {
        var candidateId = UUID.fromString(request.getAttribute("candidate_id").toString());
        try {
            return ResponseEntity.ok(profileCandidateUseCase.execute(candidateId));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
