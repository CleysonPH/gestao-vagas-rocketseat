package dev.cleysonph.gestaovagas.modules.candidate.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.cleysonph.gestaovagas.modules.candidate.CandidateRepository;
import dev.cleysonph.gestaovagas.modules.candidate.dtos.ProfileCandidateResponseDTO;

public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var canidate = candidateRepository.findById(candidateId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ProfileCandidateResponseDTO.builder()
            .id(canidate.getId())
            .name(canidate.getName())
            .username(canidate.getUsername())
            .email(canidate.getEmail())
            .description(canidate.getDescription())
            .build();
    }
    
}
