package dev.cleysonph.gestaovagas.modules.candidate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    
}
