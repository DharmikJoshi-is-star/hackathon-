package com.tollapp.service;

import java.util.ArrayList;
import java.util.List;

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

	public boolean proceedToll(Long vehicleId, Long tollPlazaId) {
		
		Vehicle vehicle = vehicleService.getVehicleWithId(vehicleId);
		
		TollPlaza tollPlaza = tollPlazaRepository.getOne(tollPlazaId);
		
		User user = vehicle.getUser();
		
		System.out.println("vehicle: "+vehicle.getId());
		System.out.println("User: "+user.getId());
		System.out.println("tollPlaza: "+tollPlaza.getId());
		
		
		if(user.getBalance()>=tollPlaza.getTollPrice()) {

			user.setBalance( user.getBalance() - tollPlaza.getTollPrice() );
			
			if(tollPlaza.getTotalAmount()==null)
				tollPlaza.setTotalAmount(0.0);
			
			tollPlaza.setTotalAmount( tollPlaza.getTotalAmount() + tollPlaza.getTollPrice() );
			
			TollHistory tollHistory = tollHistoryService.saveToll(vehicle, tollPlaza, tollPlaza.getTollPrice());
			
			if(vehicle.getTollHistories()==null)
				vehicle.setTollHistories(new ArrayList<TollHistory>());
			
			if(tollPlaza.getTollHistories()==null)
				tollPlaza.setTollHistories(new ArrayList<TollHistory>());
			
			vehicle.getTollHistories().add(tollHistory);
			
			vehicleService.saveVehicle(vehicle);
			
			tollPlaza.getTollHistories().add(tollHistory);
			
			tollPlazaRepository.save(tollPlaza);
			
			return true;
		}
		else 
			return false;
		
	}

	public TollPlaza checkCredentials(String username, String password) {
		
		TollPlaza tollPlaza = tollPlazaRepository.checkCredentials(username, password);
		
		if(tollPlaza!=null) {
			tollPlaza.setTollHistories(null);
			
		}
		
		return tollPlaza;
	}

	public Long saveTollPlaza(TollPlaza tollPlaza) {
		
		if(tollPlaza!=null) {
			tollPlaza = tollPlazaRepository.save(tollPlaza);
			return tollPlaza.getId();
		}
		
		return new Long("0");
	}

	public TollPlaza getTollPlazaWithId(Long tollPlazaId) {
		
		if(tollPlazaId!=null) {
			TollPlaza tollPlaza = tollPlazaRepository.getOne(tollPlazaId);
			if(tollPlaza!=null) {
				return tollPlaza;
			}
		}
		
		
		return null;
	}

	public List<TollPlaza> fetchAllTollPlaza() {
		
		List<TollPlaza> tollPlazaList = tollPlazaRepository.findAll();
		
		tollPlazaList.forEach(toll->{
			toll.setTollHistories(null);
		});
		
		return tollPlazaList;
	}
	
	
	
}
