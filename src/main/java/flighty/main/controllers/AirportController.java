package flighty.main.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.database.Airport;
import flighty.main.database.AirportRepository;
import flighty.main.service.airportService;

@RestController
public class AirportController {
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private airportService airportService;

	@PostConstruct
	public void init() {
		/*
		 * Airport:
		 * 	-Code
		 * 	-Name
		 */
		
		Airport a1 = new Airport("BARJ", "Madrid (Barajas-Adolfo Suarez)");
		Airport a2 = new Airport("PRAT", "Barcelona");
		Airport a3 = new Airport("GAUL", "París (Charles de Gaulle)");
		Airport a4 = new Airport("TEGE", "Berlín (Tegel)");
		Airport a5 = new Airport("PRTL", "Lisboa");		
		Airport a6 = new Airport("LECU", "Madrid (Cuatro Vientos)");
		Airport a7 = new Airport("ORLY", "París (Orly)");
		Airport a8 = new Airport("SCHO", "Berlín (Schönefeld)");

		airportRepository.save(a1);
		airportRepository.save(a2);
		airportRepository.save(a3);
		airportRepository.save(a4);
		airportRepository.save(a5);
		airportRepository.save(a6);
		airportRepository.save(a7);
		airportRepository.save(a8);
	}
	
	@GetMapping("/airports")
	public List<String> airportList(){
		return this.airportService.airportList();
		
	}
	
}
