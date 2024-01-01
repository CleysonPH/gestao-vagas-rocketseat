package dev.cleysonph.gestaovagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.cleysonph.gestaovagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JWTCandidateProvider jwtProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isTokenPresent(authorizationHeader)) {
            var token = authorizationHeader.replace(TOKEN_PREFIX, "");
            var decodedJWT = jwtProvider.validateToken(token);

            if (decodedJWT == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                request.setAttribute("candidate_id", decodedJWT.getSubject());
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isTokenPresent(String authorizationHeader) {
        return authorizationHeader != null
            && authorizationHeader.startsWith(TOKEN_PREFIX);
    }
    
}
