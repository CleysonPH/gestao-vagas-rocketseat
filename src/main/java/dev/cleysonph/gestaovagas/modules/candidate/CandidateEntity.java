package dev.cleysonph.gestaovagas.modules.candidate;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;

    private String name;

    @Pattern(regexp = "\\S+", message = "cannot contain blank spaces")
    private String username;

    @Email
    private String email;

    @Size(min = 10, max = 100)
    private String password;

    private String description;

    private String curriculum;
    
}
