package flighty.main.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flighty.main.database.Airport;
import flighty.main.database.AirportRepository;

@Service
public class airportService {

	@Autowired
	private AirportRepository airportRepository;

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

	/**
	 * @return name of the airports
	 */
	public List<String> airportList() {
		return airportRepository.airportList();
	}

	/**
	 * @param name of the airport
	 * @return code of the airport
	 */
	public String getAirportCode(String name) {
		switch (name) {
		case "Madrid (Barajas-Adolfo Suarez)":
			return "BARJ";
		case "Barcelona":
			return "PRAT";
		case "París (Charles de Gaulle)":
			return "GAUL";
		case "Berlín (Tegel)":
			return "TEGE";
		case "Lisboa":
			return "PRTL";
		case "Madrid (Cuatro Vientos)":
			return "LECU";
		case "Paris (Orly)":
			return "ORLY";
		case "Berlín (Schönefeld)":
			return "SCHO";
		default:
			return "nothing";
		}
	}

}
