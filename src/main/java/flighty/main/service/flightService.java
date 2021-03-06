package flighty.main.service;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flighty.main.database.Flight;
import flighty.main.database.FlightRepository;

@Service
public class flightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@PostConstruct
	public void init() {

		Date d0 = Date.valueOf("2020-05-31");
		Date d1 = Date.valueOf("2020-06-05");
		Date d2 = Date.valueOf("2020-06-10");
		Date d3 = Date.valueOf("2020-06-15");
		Date d4 = Date.valueOf("2020-07-25");
		Date d5 = Date.valueOf("2020-07-30");
		Date d6 = Date.valueOf("2020-08-12");
		Date d7 = Date.valueOf("2020-08-17");

		/*
		 * Flight: 
		 * 	-Code 
		 * 	-Airport origin 
		 * 	-Airport Destination 
		 * 	-Flight departure date
		 * 	-Flight departure time (hour) 
		 * 	-Flight departure time (minutes) 
		 * 	-Cost
		 */

		// From Madrid (Barajas-Adolfo Suarez) to Barcelona
		Flight f1_1 = new Flight("IB4545", "IB", "BARJ", "PRAT", d0, 8, 30, 90, 140);
		Flight f1_2 = new Flight("IB4546", "IB", "BARJ", "PRAT", d0, 10, 50, 100, 120);
		Flight f1_3 = new Flight("IB4547", "IB", "BARJ", "PRAT", d0, 11, 10, 85, 130);
		Flight f1_4 = new Flight("IB4548", "IB", "BARJ", "PRAT", d1, 15, 20, 95, 175);
		Flight f1_5 = new Flight("LU3230", "LU", "BARJ", "PRAT", d1, 9, 00, 100, 135);
		Flight f1_6 = new Flight("IB4549", "IB", "BARJ", "PRAT", d2, 20, 10, 90, 110);
		Flight f1_7 = new Flight("UX1218", "UX", "BARJ", "PRAT", d1, 23, 15, 100, 125);

		// From Barcelona to Madrid (Barajas-Adolfo Suarez)
		Flight f1_8 = new Flight("IB4550", "IB", "PRAT", "BARJ", d1, 14, 50, 120, 100);
		Flight f1_9 = new Flight("LU3231", "LU", "PRAT", "BARJ", d1, 19, 05, 123, 115);
		Flight f1_10 = new Flight("IB4551", "LU", "PRAT", "BARJ", d2, 3, 15, 115, 105);
		Flight f1_11 = new Flight("LU3232", "LU", "PRAT", "BARJ", d2, 20, 35, 127, 110);
		Flight f1_12 = new Flight("IB4552", "IB", "PRAT", "BARJ", d2, 10, 10, 120, 130);

		// From Madrid (Cuatro Vientos) to París (Charles de Gaulle)
		Flight f2_1 = new Flight("NA4589", "NA", "LECU", "GAUL", d2, 12, 30, 160, 75);
		Flight f2_2 = new Flight("CP2390", "CP", "LECU", "GAUL", d2, 15, 10, 140, 90);
		Flight f2_3 = new Flight("UX9464", "UX", "LECU", "GAUL", d3, 17, 05, 125, 105);
		Flight f2_4 = new Flight("SA9464", "SA", "LECU", "GAUL", d3, 4, 15, 115, 120);

		// From París (Charles de Gaulle) to Madrid (Cuatro Vientos)
		Flight f2_5 = new Flight("AF1278", "AF", "GAUL", "LECU", d4, 19, 35, 110, 100);
		Flight f2_6 = new Flight("QA3745", "QA", "GAUL", "LECU", d7, 21, 00, 130, 95);

		// From Barcelona to París (Charles de Gaulle)
		Flight f3 = new Flight("CP7597", "CP", "PRAT", "GAUL", d5, 23, 45, 67, 93);

		// From París (Orly) to Lisboa
		Flight f4_1 = new Flight("AF2639", "AF", "ORLY", "PRTL", d3, 12, 35, 105, 124);
		Flight f4_2 = new Flight("LU3946", "LU", "ORLY", "PRTL", d3, 7, 00, 110, 112);
		Flight f4_3 = new Flight("QA3843", "QA", "ORLY", "PRTL", d5, 11, 05, 95, 123);

		// From Lisboa to París (Orly)
		Flight f4_4 = new Flight("AF9563", "AF", "PRTL", "ORLY", d6, 12, 35, 105, 124);
		Flight f4_5 = new Flight("AF9372", "AF", "PRTL", "ORLY", d6, 15, 50, 102, 125);

		// From Lisboa to Berlín (Tegel)
		Flight f5_1 = new Flight("EA2649", "EA", "PRTL", "TEGE", d0, 16, 25, 41, 67);
		Flight f5_2 = new Flight("CP8923", "CP", "PRTL", "TEGE", d2, 13, 10, 35, 59);
		Flight f5_3 = new Flight("NA9293", "NA", "PRTL", "TEGE", d2, 12, 50, 27, 64);

		// From Berlín (Tegel) to Lisboa
		Flight f5_4 = new Flight("EA2649", "EA", "TEGE", "PRTL", d4, 4, 05, 47, 37);
		Flight f5_5 = new Flight("CP8923", "CP", "TEGE", "PRTL", d4, 12, 35, 51, 36);
		Flight f5_6 = new Flight("NA9293", "NA", "TEGE", "PRTL", d3, 17, 30, 58, 89);
		Flight f5_7 = new Flight("LU8374", "LU", "TEGE", "PRTL", d7, 22, 45, 60, 57);

		// From "Berlín (Schönefeld)" to París (Charles de Gaulle)
		Flight f6_1 = new Flight("UX7353", "UX", "SCHO", "GAUL", d3, 13, 15, 35, 39);

		// From París (Charles de Gaulle) to Berlín (Schönefeld)
		Flight f6_2 = new Flight("UX7353", "UX", "GAUL", "SCHO", d4, 11, 30, 40, 25);

		// From "Berlín (Schönefeld)" to Madrid (Barajas-Adolfo Suarez)
		Flight f7 = new Flight("QA8392", "QA", "SCHO", "BARJ", d7, 9, 00, 175, 150);

		flightRepository.save(f1_1);
		flightRepository.save(f1_2);
		flightRepository.save(f1_3);
		flightRepository.save(f1_4);
		flightRepository.save(f1_5);
		flightRepository.save(f1_6);
		flightRepository.save(f1_7);
		flightRepository.save(f1_8);
		flightRepository.save(f1_9);
		flightRepository.save(f1_10);
		flightRepository.save(f1_11);
		flightRepository.save(f1_12);

		flightRepository.save(f2_1);
		flightRepository.save(f2_2);
		flightRepository.save(f2_3);
		flightRepository.save(f2_4);
		flightRepository.save(f2_5);
		flightRepository.save(f2_6);

		flightRepository.save(f3);

		flightRepository.save(f4_1);
		flightRepository.save(f4_2);
		flightRepository.save(f4_3);
		flightRepository.save(f4_4);
		flightRepository.save(f4_5);

		flightRepository.save(f5_1);
		flightRepository.save(f5_2);
		flightRepository.save(f5_3);
		flightRepository.save(f5_4);
		flightRepository.save(f5_5);
		flightRepository.save(f5_6);
		flightRepository.save(f5_7);

		flightRepository.save(f6_1);
		flightRepository.save(f6_2);

		flightRepository.save(f7);
	}
	
	/**
	 * @param origin (origin airport of the flight)
	 * @param dest (destination airport of the flight)
	 * @param date (date of the flight)
	 * @return list of flights 
	 */
	public List<Flight> findFlightsByAirportsAndDate(String origin, String dest, Date date) {
		return flightRepository.findByOriginAndDestAndDate(origin, dest, date);
	}

}
