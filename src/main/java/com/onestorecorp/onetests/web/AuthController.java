package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.User;
import com.onestorecorp.onetests.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository repository;

    @GetMapping("/api/auth/isAuthenticated")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && authentication.getPrincipal() instanceof User;
    }

	@GetMapping("/api/auth/principal")
	public User principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        logger.debug("#user: {}", user);
        return user;
    }

    @GetMapping("/api/auth/checkDup")
    public boolean checkDup(@RequestParam String email) {
	    User user = repository.findOneByEmail(email);
	    logger.debug("#user: {}", user);
        return user != null;
    }

    @PostMapping("/api/auth/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signup(@RequestBody User user) {
	    user.setRoles(Arrays.asList("USER"));
        repository.save(user);
    }

}