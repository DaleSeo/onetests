package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
        	return null;
        }

        if (!(authentication.getPrincipal() instanceof User)) {
	        return null;
        }

    	return (User) authentication.getPrincipal();
    }

}
