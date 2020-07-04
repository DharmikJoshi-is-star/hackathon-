package com.tollapp.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tollapp.entity.TollHistory;
import com.tollapp.entity.TollPlaza;
import com.tollapp.entity.Vehicle;
import com.tollapp.repository.TollHistoryRepository;

@Service
public class TollHistoryService {

	@Autowired
	TollHistoryRepository tollHistoryRepository;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	TollPlazaService tollPlazaService;
	
	public TollHistory saveToll(Vehicle vehicle, TollPlaza tollPlaza, Double tollPrize) {
		
		TollHistory tollHistory = new TollHistory();
		
		tollHistory.setVehicle(vehicle);
		tollHistory.setTollPlaza(tollPlaza);
		tollHistory.setTollPrize(tollPrize);
		Long dateTime = System.currentTimeMillis();
		tollHistory.setDate(new Date(dateTime));
		tollHistory.setTime(new Time(dateTime));
		
		return tollHistoryRepository.save(tollHistory);
	}

	public List<TollHistory> fetchHistoryFromToll(Long tollPlazaId) {
		
		TollPlaza tollPlaza= tollPlazaService.getTollPlazaWithId(tollPlazaId);
		
		tollPlaza.getTollHistories().forEach(th->{
			th.setTollPlaza(null);
			th.getVehicle().setTollHistories(null);
			th.getVehicle().setUser(null);
		});
		
		return tollPlaza.getTollHistories();
	}

	public List<TollHistory> fetchHistoryFromVehicle(Long vehicleId) {
		
		Vehicle vehicle = vehicleService.getVehicleWithId(vehicleId);
		
		vehicle.getTollHistories().forEach(th->{
			th.getTollPlaza().setTollHistories(null);
			th.setVehicle(null);
		});
		
		return vehicle.getTollHistories();
	}

}
