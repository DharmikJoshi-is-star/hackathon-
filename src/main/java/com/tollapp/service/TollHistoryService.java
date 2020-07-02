package com.tollapp.service;

import java.sql.Date;
import java.sql.Time;

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

}
