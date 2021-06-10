package com.mjroh.boot.config.jpa;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mjroh.boot.user.model.dto.MUserDto;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }
    	MUserDto user = (MUserDto) authentication.getPrincipal();
        return Optional.of(user.getEmail());
    }
}
