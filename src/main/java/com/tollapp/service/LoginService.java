package com.tollapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.Login;
import com.tollapp.entity.User;
import com.tollapp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public Long checkCredentials(String username, String password) {
		Long userId = loginRepository.checkCredentials(username, password);
		if(userId==null)
			return new Long("0");     
		else 
			return userId;
	}

	public void saveUser(User u) {
		
		Login login = new Login();
		login.setUserId(u.getId());
		login.setUsername(u.getUsername());
		login.setPassword(u.getPassword());
		
		loginRepository.save(login);
		
	}
	
	
	
}
