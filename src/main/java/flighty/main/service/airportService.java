package flighty.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flighty.main.database.Airport;
import flighty.main.database.AirportRepository;

@Service
public class airportService {
	
	@Autowired
	private AirportRepository airportRepository;
	
	public List<String> airportList(){
		return airportRepository.airportList();
		
	}
	
	public String getAirportCode(String name) {
		
		switch (name) {
		case "Madrid (Barajas-Adolfo Suarez)": return "BARJ";
		case "Barcelona" : return "PRAT";
		case "París (Charles de Gaulle)": return "GAUL";
		case "Berlín (Tegel)": return "TEGE";
		case "Lisboa":return "PRTL";
		case "Madrid (Cuatro Vientos)":return "LECU";
		case "Paris (Orly)": return "ORLY";
		case "Berlín (Schönefeld)": return "SCHO";
		default: return "nothing";
		}
	}

}
