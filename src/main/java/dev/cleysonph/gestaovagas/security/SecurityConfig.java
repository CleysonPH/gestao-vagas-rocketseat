package dev.cleysonph.gestaovagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(customizer -> customizer
                .ignoringRequestMatchers("/**")
                .disable()
            )
            .authorizeHttpRequests(customizer -> customizer
                .requestMatchers("/candidates", "/companies").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
    
}
