package dev.cleysonph.gestaovagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.candidate.CandidateEntity;
import dev.cleysonph.gestaovagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CadidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public CandidateEntity create(@RequestBody @Valid CandidateEntity candidateEntity) {
        return candidateRepository.save(candidateEntity);
    }
    
}
