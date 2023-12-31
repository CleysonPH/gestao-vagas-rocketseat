package dev.cleysonph.gestaovagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.gestaovagas.modules.company.entities.JobEntity;
import dev.cleysonph.gestaovagas.modules.company.usecases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@RequestBody @Valid JobEntity jobEntity, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        return createJobUseCase.execute(jobEntity);
    }
    
}
