package com.springapp.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class HelloController {

	@RequestMapping("/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/login")
	public String login(String userName,String password,ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);

		try {
			subject.login(token);
			model.addAttribute("message", "µÇÂ½³É¹¦!");
			return "sucess";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			model.addAttribute("message", "µÇÂ½Ê§°Ü!");
			return "login";
		}



	}
	
}