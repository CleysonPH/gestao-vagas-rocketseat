package dev.cleysonph.gestaovagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cleysonph.gestaovagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    
    Optional<CompanyEntity> findByUsername(String username);
    
}
