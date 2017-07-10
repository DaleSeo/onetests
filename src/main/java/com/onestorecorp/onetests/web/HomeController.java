package com.onestorecorp.onetests.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {
			"/",
			"/test",
            "/login",
			"/signup",
            "/profile",
			"/tests/**",
			"/services/**",
			"/console/**",
			"/suites/**",
			"/cases/**",
			"/calls/**",
			"/stats/**",
			"/profile/**",
			"/settings/**",
			"/admin/**"
	})
	public String index() {
		return "forward:/index.html";
	}

}
