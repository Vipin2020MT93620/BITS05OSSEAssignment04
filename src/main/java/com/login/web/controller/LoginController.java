package com.login.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.login.web.service.LoginService;

@Controller
@SessionAttributes("uname")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String uname, @RequestParam String password){
		
		boolean isValid = service.validateUser(uname, password);
		
		if (!isValid) {
			model.put("errorMessage", "Login failed due to incorrect \n"
					+ "username and password");
			return "login";
			
		}
		
		model.put("name", uname);
		model.put("password", password);
		
		return "welcome to my home page!!";
	}
		public String showLoginPageError(ModelMap model){
		return "Error";
	}
}
