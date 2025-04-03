package com.angels.module.hofmain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HofMainController {
	
	@RequestMapping(value = "/mainHof")
	public String mainHof() {
		return "hof/main/baseball-main"; 
	}


}
