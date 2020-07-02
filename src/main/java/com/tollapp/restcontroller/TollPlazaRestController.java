package com.tollapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.entity.Vehicle;
import com.tollapp.service.TollPlazaService;

@RestController
public class TollPlazaRestController {

	@Autowired
	TollPlazaService tollPlazaService;
	
	@PostMapping("/proceedToll/{vehicleId}/{tollPlazaId}")
	public void proceedToll(@PathVariable("vehicleId") Long vehicleId, 
								@PathVariable("tollPlazaId") Long tollPlazaId ) {
		
		tollPlazaService.proceedToll(vehicleId,tollPlazaId);
		
	}
	
	
}
