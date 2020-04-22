package flighty.main.controllers;

import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import flighty.main.database.Airport;
import flighty.main.database.AirportRepository;
import flighty.main.database.Company;
import flighty.main.database.CompanyRepository;
import flighty.main.database.Flight;
import flighty.main.database.FlightRepository;

public class indexController {
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired 
	private FlightRepository flightRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	
	 @PostConstruct
	 public void init() {
		 
		 Company c1=new Company("SA", "Singapore Airlines", "https://www.singaporeair.com", "905445444", 4);
		 Company c2=new Company("QA", "Qatar Airways", "https://www.qatarairways.com", "9047545544", 3);
		 Company c3=new Company("NA", "ANA All Nippon Airways", "https://www.nipponairways.com", "9044887476", 5);
		 Company c4=new Company("EA", "Emirates", "https://www.emirates.com", "9024145457", 4);
		 Company c5=new Company("IB", "Iberia", "https://www.iberia.com", "908210714", 4);
		 Company c6=new Company("LU", "Lufthansa", "https://www.lufthansa.com", "900896352154", 4);
		 Company c7=new Company("CP", "Cathay Pacific Airways", "https://www.cathay.com", "905445444", 4);
		 
		 Airport a1=new Airport("BARJ", "Madrid (Barajas-Adolfo Suarez)");
		 Airport a2=new Airport("PRAT", "Barcelona (El Prat)");
		 Airport a3=new Airport("GAUL", "Paris (París-Charles de Gaulle)");
		 Airport a4=new Airport("TEGE", "Berlin (Tegel)");
		 Airport a5=new Airport("LISB","Lisboa (Portela)");
		 
		 airportRepository.save(a1);airportRepository.save(a2);airportRepository.save(a3);airportRepository.save(a4);airportRepository.save(a5);
		 
		 companyRepository.save(c1);companyRepository.save(c3);companyRepository.save(c5);companyRepository.save(c7);
		 companyRepository.save(c2);companyRepository.save(c4);companyRepository.save(c6);
		 
		 Date d0 = Date.valueOf("2020-05-31");
		 Date d1 = Date.valueOf("2020-06-10");
		 Date d2 = Date.valueOf("2020-06-05");
		 Date d3 = Date.valueOf("2020-06-15");
		 Date d4 = Date.valueOf("2020-07-25");
		 Date d5 = Date.valueOf("2020-07-30");
		 Flight f1=new Flight("IB4545", a1, a2, d0, 8, 30, 60, 200,false);
		 
		 Flight f2=new Flight("IB4546", a1,a2, d0, 10, 50, 120, 120,false);
		 Flight f3=new Flight("IB4547", a1,a2, d0, 11, 10, 100, 100,true);
		 Flight f4=new Flight("IB4548", a2,a1, d1, 11, 10, 120, 100,true);
		 f4.setFlight(f3);
		 f3.setFlight(f4);
		 Flight f5=new Flight("IB4549", a1,a2, d0, 20, 10, 60, 110,true);
		 Flight f6=new Flight("IB4550", a2,a1, d2, 10, 10, 60, 90,true);
		 f5.setFlight(f6);
		 f6.setFlight(f5);
		 
		 Flight f7=new Flight("SA3230", a3,a4, d3, 10, 30, 180, 145,false);
		 Flight f8=new Flight("QA5604", a4,a1, d4, 11, 15, 170, 150,false);
		 Flight f9=new Flight("NA3230", a1,a3, d4, 12, 10, 195, 175,false);
		 Flight f10=new Flight("EA3230", a3,a2, d4, 10, 30,35, 25,false);
		 
		 Flight f11=new Flight("LU3230", a3,a2, d4, 10, 30,35, 35,true);
		 Flight f12=new Flight("LU3231", a2,a3, d5, 10, 20,40, 35,true);
		 
		 f11.setFlight(f12);
		 f12.setFlight(f11);
		 
		 Flight f13=new Flight("CP3230", a1,a5, d5, 20, 30,50, 100,false);
		 
		 f1.setCompany(c5);
		 f2.setCompany(c5);
		 f3.setCompany(c5);
		 f4.setCompany(c5);
		 f5.setCompany(c5);
		 f6.setCompany(c5);
		 f7.setCompany(c1);
		 f8.setCompany(c1);
		 f9.setCompany(c1);
		 f10.setCompany(c1);
		 f11.setCompany(c6);
		 f12.setCompany(c6);
		 f13.setCompany(c7);
		 
		 flightRepository.save(f1); flightRepository.save(f2); flightRepository.save(f3); flightRepository.save(f4); flightRepository.save(f5); flightRepository.save(f6);
		 flightRepository.save(f7); flightRepository.save(f8); flightRepository.save(f9); flightRepository.save(f10); flightRepository.save(f11); flightRepository.save(f12);
		 flightRepository.save(f13);
	 }

}
