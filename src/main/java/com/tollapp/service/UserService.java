package com.tollapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.TollHistory;
import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;
	
	@Autowired
	VehicleService vehicleService;
	
	//copy this
	public Long registerUser(User user) {
			
		User u = userRepository.save(user);
		return u.getId();
		
	}

	public User getUserWithId(Long userId) {
		User user =  userRepository.getOne(userId);
		
		user.getVehicles().forEach(vehicle->{
			vehicle.setUser(null);
			vehicle.setTollHistories(null);
		});
		
		return user;
	}
	
	public User getUser(Long userId) {
		User user =  userRepository.getOne(userId);
		
		
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

	public boolean isContactExists(String contact) {
		
		Long id = userRepository.checkIfContactExist(contact);
		
		if(id==null)
			return false;
		else 
			return true;
		
	}

	public boolean editContact(Long userId, String contact) {
		
		User user = userRepository.getOne(userId);
		
		if(user!=null && contact!=null) {
			user.setContact(contact);
			return true;
		}
		return false;
		
	}

	//copy this
	public void deleteUser(Long userId) {
	
		vehicleService.removeVehicleWithUserId(userId);
		userRepository.deleteById(userId);
		
	}

	public Long checkCredentials(String credential, String password) {
		
		Long id = userRepository.checkCredentials(credential, password);
		
		if(id==null)
			return new Long("0");
		
		return id;
		
	}
	
	
}
