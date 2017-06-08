package com.onestorecorp.onetests.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {
			"/",
            "/login",
			"/signup",
            "/profile",
			"/tests/**",
			"/services/**",
			"/console/**",
			"/suites/**",
			"/cases/**",
			"/calls/**",
			"/dashboard/**",
			"/profile/**",
			"/settings/**",
			"/admin/**"
	})
	public String index() {
		return "forward:/index.html";
	}

}
