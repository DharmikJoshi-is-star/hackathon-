package com.tollapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;
	
	public Long registerUser(User user) {
		User u = userRepository.save(user);
		if(u!=null) {
			loginService.saveUser(u);
			return u.getId();
		}
			
		
		return new Long("0");
	}

	public User getUserWithId(Long userId) {
		User user =  userRepository.getOne(userId);
		
		user.getVehicles().forEach(vehicle->{
			vehicle.setUser(null);
			vehicle.setTollHistories(null);
		});
		
		return user;
	}

	public void saveUser(User user) {
		if(user!=null)
			userRepository.save(user);
	}

	public Double addBalance(Long userId, Double balance) {
		User user = userRepository.getOne(userId);
		if(user!=null) {
			Double newBal = user.getBalance() + balance;
			user.setBalance(newBal);
			userRepository.save(user);
			return newBal;
		}
		return null;
	}

	public List<Vehicle> getUserVehicles(Long userId) {
		return userRepository.getUserVehicles(userId);
	}

	public boolean isEmailRegistered(String email) {
		
		Long id = userRepository.getUserIdEmail(email);
		
		if(id==null)
			return false;
		else
			return true;
	}
}
