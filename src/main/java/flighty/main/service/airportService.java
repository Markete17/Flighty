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

}
