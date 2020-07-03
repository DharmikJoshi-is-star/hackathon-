package com.tollapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tollapp.entity.TollPlaza;
import com.tollapp.entity.Vehicle;
import com.tollapp.service.TollPlazaService;
//C:\Users\Dharmik joshi\AppData\Local\Tesseract-OCR
@CrossOrigin(origins = {"*"}, allowedHeaders = {"Accept"})
@RestController
public class TollPlazaRestController {

	@Autowired
	TollPlazaService tollPlazaService;
	
	@CrossOrigin(origins = {"*"}, allowedHeaders = {"Accept"})
	@GetMapping("/proceedToll/{vehicleId}/{tollPlazaId}")
	public boolean proceedToll(@PathVariable("vehicleId") Long vehicleId, 
								@PathVariable("tollPlazaId") Long tollPlazaId ) {
		
		return tollPlazaService.proceedToll(vehicleId,tollPlazaId);
	}
	
	@CrossOrigin(origins = {"*"}, allowedHeaders = {"Accept"})
	@GetMapping("/loginToll/{username}/{password}")
	public TollPlaza loginToll(@PathVariable("username") String username, @PathVariable("password") String password) {
		//["d-toll", "12345"]
		return tollPlazaService.checkCredentials(username, password);
	   
	}
	
	@CrossOrigin(origins = {"*"}, allowedHeaders = {"Accept"})
	@PostMapping("/registerTollPlaza")
	public Long registerTollPlaza(@RequestBody TollPlaza tollPlaza) {
		/*
		 * {
		    "name": "Dahisar Toll",
		    "username": "d-toll",
		    "password":"12345",
		    "tollPrice": 30.00
			}
		 */
		return tollPlazaService.saveTollPlaza(tollPlaza);
	}
	
}
