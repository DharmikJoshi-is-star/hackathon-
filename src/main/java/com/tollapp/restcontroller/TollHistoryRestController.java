package com.tollapp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.entity.TollHistory;
import com.tollapp.service.TollHistoryService;

@RestController
public class TollHistoryRestController {

	@Autowired
	TollHistoryService tollHistoryService;
	
	@GetMapping("/fetchHistoryFromToll/{tollPlazaId}")
	public List<TollHistory> fetchHistoryFromToll(@PathVariable("tollPlazaId") Long tollPlazaId){
		
		return tollHistoryService.fetchHistoryFromToll(tollPlazaId);
	}
	
	@GetMapping("/fetchHistoryFromVehicle/{vehicleId}")
	public List<TollHistory> fetchHistoryFromVehicle(@PathVariable("vehicleId") Long vehicleId){
		
		return tollHistoryService.fetchHistoryFromVehicle(vehicleId);
	}
	
	
	
	
}
