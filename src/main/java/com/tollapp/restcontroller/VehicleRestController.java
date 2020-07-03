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
	public Vehicle addVehicle(@RequestBody Vehicle vehicle, @PathVariable("userId") Long userId) {

		/*
		* 
		* {
			"registrationNo": "MH02CL0555",
			"registrationDate": "20-Jan-2012",
			"chasiNo": "WBAKB42080CY83879",
			"engineNo":"16257849",
			"ownerName": "Dharmik Joshi",
			"vehicleClass": "LMVIMP",
			"fuelType":"PETROL",
			"makerModel":"BMW INDIA PVT. LTD., BMW 740 L I PETROL"
		 }
		 */
		
		return vehicleService.addVehicle(userId, vehicle);
	}
	
	@PostMapping("/findVehicle")
	public Vehicle findVehicle(@RequestBody String registrationNo) {
		//MH02CL0555
		System.out.println(registrationNo);
		return vehicleService.findVehicle(registrationNo);
		
	}
}
