package dev.cleysonph.gestaovagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleysonph.gestaovagas.modules.company.entities.CompanyEntity;
import dev.cleysonph.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public void execute(CompanyEntity companyEntity) {
        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(company -> {
                throw new RuntimeException("Username or email already exists");
            });
        companyRepository.save(companyEntity);
    }
    
}
