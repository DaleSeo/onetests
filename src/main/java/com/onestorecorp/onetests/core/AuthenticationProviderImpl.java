package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.User;
import com.onestorecorp.onetests.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	private UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

	    User user = repository.findOneByEmail(email);

	    if (user == null) {
		    throw new UsernameNotFoundException("No email found");
	    }

	    if (!password.equals(user.getPassword())) {
		    throw new BadCredentialsException("Incorrect password.");
	    }

	    logger.info("The user has been authenticated successfully.");
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
