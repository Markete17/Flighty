package flighty.main.controllers;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.database.Company;
import flighty.main.database.CompanyRepository;
import flighty.main.database.Flight;
import flighty.main.database.FlightRepository;
import flighty.main.service.flightService;

@RestController
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private flightService flightService;

	@Autowired
	private CompanyRepository companyRepository;
	
	@PostConstruct
	public void init() {
		/*
		 * Company:
		 * 	-Code
		 * 	-Name
		 * 	-Url
		 * 	-Phone
		 * 	-Global rating
		 */
		
		Company c1 = new Company("SA", "Singapore Airlines", "https://www.singaporeair.com", "905 445 444", 4.3);
		Company c2 = new Company("QA", "Qatar Airways", "https://www.qatarairways.com", "00 974 4022 6000", 3.7);
		Company c3 = new Company("NA", "ANA All Nippon Airways", "https://www.nipponairways.com", "904 488 747", 5);
		Company c4 = new Company("EA", "Emirates", "https://www.emirates.com", "912 757 792", 2.6);
		Company c5 = new Company("IB", "Iberia", "https://www.iberia.com", "901 11 15 00", 4.1);
		Company c6 = new Company("LU", "Lufthansa", "https://www.lufthansa.com", "902 883 882", 3.5);
		Company c7 = new Company("CP", "Cathay Pacific Airways", "https://www.cathay.com", "900 814 735", 4.8);
		Company c8 = new Company("UX", "Air Europa", "https://www.aireuropa.com/","911 401 501",3.3);
		Company c9 = new Company("AF","Air France","https://www.airfrance.es","00 33 9 69 39 36 54",1.7);

		companyRepository.save(c1);
		companyRepository.save(c2);
		companyRepository.save(c3);
		companyRepository.save(c4);
		companyRepository.save(c5);
		companyRepository.save(c6);
		companyRepository.save(c7);
		companyRepository.save(c8);
		companyRepository.save(c9);

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
		
		//From Madrid (Barajas-Adolfo Suarez) to Barcelona
		Flight f1_1 = new Flight("IB4545", c5, "BARJ", "PRAT", d0, 8, 30, 90, 140);
		Flight f1_2 = new Flight("IB4546", c5, "BARJ", "PRAT", d0, 10, 50, 100, 120);
		Flight f1_3 = new Flight("IB4547", c5, "BARJ", "PRAT", d0, 11, 10, 85, 130);
		Flight f1_4 = new Flight("IB4548", c5, "BARJ", "PRAT", d1, 15, 20, 95, 175);
		Flight f1_5 = new Flight("LU3230", c6, "BARJ", "PRAT", d1, 9, 00, 100, 135);
		Flight f1_6 = new Flight("IB4549", c5, "BARJ", "PRAT", d2, 20, 10, 90, 110);
		Flight f1_7 = new Flight("UX1218", c8, "BARJ", "PRAT", d1, 23, 15, 100, 125);
		
		//From Barcelona to Madrid (Barajas-Adolfo Suarez)
		Flight f1_8 = new Flight("IB4550", c5, "PRAT", "BARJ", d1, 14, 50, 120, 100);
		Flight f1_9 = new Flight("LU3231", c6, "PRAT", "BARJ", d1, 19, 05, 123, 115);
		Flight f1_10 = new Flight("IB4551", c6, "PRAT", "BARJ", d2, 3, 15, 115, 105);
		Flight f1_11 = new Flight("LU3232", c6, "PRAT", "BARJ", d2, 20, 35, 127, 110);
		Flight f1_12 = new Flight("IB4552", c5, "PRAT", "BARJ", d2, 10, 10, 120, 130);
		
		//From Madrid (Cuatro Vientos) to París (Charles de Gaulle)
		Flight f2_1 = new Flight("NA4589", c3, "LECU", "GAUL", d2, 12, 30, 160, 75);
		Flight f2_2 = new Flight("CP2390", c7, "LECU", "GAUL", d2, 15, 10, 140, 90);
		Flight f2_3 = new Flight("UX9464", c8, "LECU", "GAUL", d3, 17, 05, 125, 105);
		Flight f2_4 = new Flight("SA9464", c1, "LECU", "GAUL", d3, 4, 15, 115, 120);
		
		//From París (Charles de Gaulle) to Madrid (Cuatro Vientos)
		Flight f2_5 = new Flight("AF1278", c9, "GAUL", "LECU", d4, 19, 35, 110, 100);
		Flight f2_6 = new Flight("QA3745", c2, "GAUL", "LECU", d7, 21, 00, 130, 95);
		
		//From Barcelona to París (Charles de Gaulle)
		Flight f3 = new Flight("CP7597", c7, "PRAT", "GAUL", d5, 23, 45, 67, 93);
		
		//From París (Orly) to Lisboa
		Flight f4_1 = new Flight("AF2639", c9, "ORLY", "PRTL", d3, 12, 35, 105, 124);	
		Flight f4_2 = new Flight("LU3946", c6, "ORLY", "PRTL", d3, 7, 00, 110, 112);		
		Flight f4_3 = new Flight("QA3843", c2, "ORLY", "PRTL", d5, 11, 05, 95, 123);
		
		//From Lisboa to París (Orly)
		Flight f4_4 = new Flight("AF9563", c9, "PRTL", "ORLY", d6, 12, 35, 105, 124);
		Flight f4_5 = new Flight("AF9372", c9, "PRTL", "ORLY", d6, 15, 50, 102, 125);
		
		//From Lisboa to Berlín (Tegel)
		Flight f5_1 = new Flight("EA2649", c4, "PRTL", "TEGE", d0, 16, 25, 41, 67);
		Flight f5_2 = new Flight("CP8923", c7, "PRTL", "TEGE", d2, 13, 10, 35, 59);		
		Flight f5_3 = new Flight("NA9293", c3, "PRTL", "TEGE", d2, 12, 50, 27, 64);
		
		//From Berlín (Tegel) to Lisboa
		Flight f5_4 = new Flight("EA2649", c4, "TEGE", "PRTL", d4, 4, 05, 47, 37);
		Flight f5_5 = new Flight("CP8923", c7, "TEGE", "PRTL", d4, 12, 35, 51, 36);	
		Flight f5_6 = new Flight("NA9293", c3, "TEGE", "PRTL", d3, 17, 30, 58, 89);
		Flight f5_7 = new Flight("LU8374", c6, "TEGE", "PRTL", d7, 22, 45, 60, 57);
		
		//From "Berlín (Schönefeld)" to París (Charles de Gaulle)
		Flight f6_1 = new Flight("UX7353", c8, "SCHO", "GAUL", d3, 13, 15, 35, 39);
		
		//From París (Charles de Gaulle) to Berlín (Schönefeld) 
		Flight f6_2 = new Flight("UX7353", c8, "GAUL", "SCHO", d4, 11, 30, 40, 25);
		
		//From "Berlín (Schönefeld)" to Madrid (Barajas-Adolfo Suarez)
		Flight f7 = new Flight("QA8392", c2, "SCHO", "BARJ", d7, 9, 00, 175, 150);
		
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
	
	@RequestMapping("/search")
	public List<Flight> search(@RequestParam String origin,@RequestParam String dest,@RequestParam String method,@RequestParam Date date) {
		if(method.equals("One Way")) {
			String orig = flightService.switchName(origin);
			String des = flightService.switchName(dest);
			List<Flight> output = flightRepository.findByOriginAndDestAndDate(orig, des, date);
			return output;
		}
		else {
			return null; //ida y vuelta code
		}
		
		
	}
		
	}
