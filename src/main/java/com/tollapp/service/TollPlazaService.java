package com.tollapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.TollHistory;
import com.tollapp.entity.TollPlaza;
import com.tollapp.entity.User;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.TollPlazaRepository;

@Service
public class TollPlazaService {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	TollPlazaRepository tollPlazaRepository;
	
	@Autowired
	TollHistoryService tollHistoryService;

	public void proceedToll(Long vehicleId, Long tollPlazaId) {
		
		Vehicle vehicle = vehicleService.getVehicleWithId(vehicleId);
		
		TollPlaza tollPlaza = tollPlazaRepository.getOne(tollPlazaId);
		
		User user = vehicle.getUser();
		
		user.setBalance( user.getBalance() - tollPlaza.getTollPrice() );
		
		TollHistory tollHistory = tollHistoryService.saveToll(vehicle, tollPlaza, tollPlaza.getTollPrice());
		
		if(vehicle.getTollHistories()==null)
			vehicle.setTollHistories(new ArrayList<TollHistory>());
		
		if(tollPlaza.getTollHistories()==null)
			tollPlaza.setTollHistories(new ArrayList<TollHistory>());
		
		vehicle.getTollHistories().add(tollHistory);
		
		vehicleService.saveVehicle(vehicle);
		
		tollPlaza.getTollHistories().add(tollHistory);
		
		tollPlazaRepository.save(tollPlaza);
		
	}
	
	
	
}
