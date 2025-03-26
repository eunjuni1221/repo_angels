package com.angels.module.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	@RequestMapping(value = "login/Login")
	public String login() {
		
		return "/xdm/login/Login"; 
	}

}
