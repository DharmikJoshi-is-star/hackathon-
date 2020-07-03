package com.tollapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.TollPlaza;
import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	TollPlazaService tollPlazaService;
	
	public Vehicle addVehicle(Long userId, Vehicle vehicle) {
		
		User user = userService.getUserWithId(userId);
		
		if(user!=null) {
			if(user.getVehicles()==null)
				user.setVehicles(new ArrayList<Vehicle>());
			
			vehicle.setUser(user);
			
			vehicle = vehicleRepository.save(vehicle);
			
			user.getVehicles().add(vehicle);
			
			userService.saveUser(user);
		}      
		vehicle.getUser().setVehicles(null);
		return vehicle;
	}

	public Vehicle getVehicleWithId(Long vehicleId) {
		
		return vehicleRepository.getOne(vehicleId);
	}

	public void saveVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public Vehicle findVehicle(String registrationNo, Long tollPlazaId) {
		
		TollPlaza tollPlaza = tollPlazaService.getTollPlazaWithId(tollPlazaId);
		
		Vehicle vehicle = vehicleRepository.findVehicle(registrationNo);
		
		System.out.println(vehicle);
		
		if(vehicle!=null) {
			vehicle.setTollHistories(null);
			vehicle.setChasiNo(null);
			vehicle.setEngineNo(null);
			vehicle.setRegistrationDate(null);
			
			//canProceed
			if(vehicle.getUser().getBalance()>=tollPlaza.getTollPrice())
				vehicle.setCanProceed(true);
			
			vehicle.setUser(null);
			
			//vehicle.getUser().setVehicles(null);
		}
		
		return vehicle;
	}
	
}
