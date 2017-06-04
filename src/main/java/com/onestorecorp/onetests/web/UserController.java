package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/api/principal")
	public User principal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);

        User user = new User();
        user.setEmail("a@b.c");
        user.setName("테스트");
        return user;
    }

}