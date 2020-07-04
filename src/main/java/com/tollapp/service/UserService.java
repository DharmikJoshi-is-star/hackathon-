package com.tollapp.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.Recharge;
import com.tollapp.entity.TollHistory;
import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.RechargeRepository;
import com.tollapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;
	
	@Autowired
	VehicleService vehicleService;
	
	
	@Autowired
	private RechargeRepository rechargeRepository;
	
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

	public boolean addBalance(Long userId, String tokenId) {
		
		User user = userRepository.getOne(userId);
		Recharge recharge = new Recharge();
		
		recharge.setTokenId(tokenId);
		recharge.setUser(user);
		recharge.setAmount(150.00);
		Long dateTime = System.currentTimeMillis();
		recharge.setDate(new Date(dateTime));
		recharge.setTime(new Time(dateTime));
		
	
		recharge = rechargeRepository.save(recharge);
		
		if(user.getRecharges()==null)
			user.setRecharges(new ArrayList<Recharge>());
		
		user.getRecharges().add(recharge);
		
		userRepository.save(user);
		
		return true;
		
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
