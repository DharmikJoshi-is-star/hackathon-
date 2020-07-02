package com.tollapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.service.LoginService;

@RestController
public class LoginRestController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public Long checkCredentials(@RequestBody String[] usernamePassword) {
		Long userId = loginService.checkCredentials(usernamePassword[0], usernamePassword[1]);
		return userId;
	}
	
	
}
