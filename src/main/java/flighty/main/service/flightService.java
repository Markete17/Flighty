package flighty.main.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flighty.main.database.Flight;
import flighty.main.database.FlightRepository;

@Service
public class flightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	public List<Flight> getReturnFlight(Flight origin){
		List<Flight> output=new LinkedList<Flight>();
		List<Flight> list = flightRepository.findAll();
		for(Flight f:list) {
			if(f.getOrigin().equals(origin.getDest()) && f.getDate().after(origin.getDate())) {
				output.add(f);
			}
		}
		return output;
	}

}
