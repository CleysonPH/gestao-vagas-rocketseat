package dev.cleysonph.gestaovagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleysonph.gestaovagas.modules.company.entities.JobEntity;
import dev.cleysonph.gestaovagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired JobRepository jobRepository;

    public void execute(JobEntity jobEntity) {
        jobRepository.save(jobEntity);
    }
    
}
