package dev.cleysonph.gestaovagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private String benefits;

    private String level;

    @Column(name = "company_id")
    private UUID companyId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
