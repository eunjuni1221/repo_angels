package com.angels.module.hofLogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HofLoginController {
	
	@RequestMapping(value = "/hof/hofLogin")
	public String login() {

		return "hof/login/baseball-login"; 
	}

}
