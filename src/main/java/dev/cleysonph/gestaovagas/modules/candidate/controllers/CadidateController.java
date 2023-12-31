package dev.cleysonph.gestaovagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CadidateController {

    @PostMapping
    public void create(@RequestBody @Valid CandidateEntity candidateEntity) {
        System.out.println(candidateEntity);
    }
    
}
