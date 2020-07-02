package com.tollapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.entity.Vehicle;
import com.tollapp.service.VehicleService;

@RestController
public class VehicleRestController {

	@Autowired
	VehicleService vehicleService;
	
	@PostMapping("/addVehicle/{userId}")
	public void addVehicle(@RequestBody Vehicle vehicle, @PathVariable("userId") Long userId) {
		vehicleService.addVehicle(userId, vehicle);
	}
	
	@PostMapping("/findVehicle")
	public Vehicle findVehicle(@RequestBody String registrationNo) {
		
		return vehicleService.findVehicle(registrationNo);
		
	}
}
