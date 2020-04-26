package flighty.main.controllers;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.database.Flight;
import flighty.main.service.airportService;
import flighty.main.service.flightService;

@RestController
public class FlightController {

	@Autowired
	private flightService flightService;

	@Autowired
	private airportService airportService;
	
	/**
	 * @param origin (origin airport)
	 * @param dest (destination airport)
	 * @param method (outbound only or round flight)
	 * @param date1 (outbound date)
	 * @param date2 (return date)
	 * @return ResponseEntity<List<Flight>> (list of flights with status code)
	 */
	@GetMapping("/search")
	public ResponseEntity<List<Flight>> search(@RequestParam String origin, @RequestParam String dest, @RequestParam String method,
			@RequestParam Date date1, @RequestParam(required = false) Date date2) {
		String orig = airportService.getAirportCode(origin);
		String des = airportService.getAirportCode(dest);
		List<Flight> flightList = flightService.findFlightsByAirportsAndDate(orig, des, date1);
		
		if (flightList.isEmpty() || flightList == null) {
			return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
		}
		
		if (method.equals("One Way")) {
			return new ResponseEntity<List<Flight>>(flightList,HttpStatus.OK);
		} else {
			List<Flight> output = new LinkedList<>();
			List<Flight> destList = flightService.findFlightsByAirportsAndDate(des, orig, date2);
			
			if(destList.isEmpty() || destList==null) {
				return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
			}
			
			for (Flight f1 : flightList) {
				for (Flight f2 : destList) {
					output.add(f1);
					output.add(f2);
				}
			}
			return new ResponseEntity<List<Flight>>(output,HttpStatus.OK);
		}
	}

}
