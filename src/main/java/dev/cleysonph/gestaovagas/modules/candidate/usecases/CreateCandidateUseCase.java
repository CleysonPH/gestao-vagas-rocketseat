package dev.cleysonph.gestaovagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleysonph.gestaovagas.exceptions.UserFoundException;
import dev.cleysonph.gestaovagas.modules.candidate.CandidateEntity;
import dev.cleysonph.gestaovagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent(candidate -> {
                throw new UserFoundException();
            });
        return candidateRepository.save(candidateEntity);
    }
    
}
