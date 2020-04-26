package flighty.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.service.airportService;

@RestController
public class AirportController {
	
	@Autowired
	private airportService airportService;
	
	@GetMapping("/airports")
	public List<String> airportList(){
		return this.airportService.airportList();
	}
	
}
