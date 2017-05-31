package com.onestorecorp.onetests.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = {
			"/",
			"tests/**",
			"/services/**",
			"/console/**",
			"/suites/**",
			"/cases/**",
			"/calls/**",
			"/dashboard/**",
			"/profile/**",
			"/settings/**"
	})
	public String index() {
		return "forward:/index.html";
	}

}
