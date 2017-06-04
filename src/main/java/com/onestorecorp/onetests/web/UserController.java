package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.User;
import com.onestorecorp.onetests.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;

	@GetMapping("/api/principal")
	public User principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = repository.findOneByEmail(email);
        logger.debug("#user: {}", user);
        return user;
    }

    @GetMapping("/api/checkDup")
    public boolean checkDup(@RequestParam String email) {
	    User user = repository.findOneByEmail(email);
        return user != null;
    }

    @PostMapping("/api/signup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signup(@RequestBody User user) {
	    user.setRoles(Arrays.asList("USER"));
        repository.save(user);
    }

}