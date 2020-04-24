package flighty.main.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.database.Company;
import flighty.main.database.CompanyRepository;

@RestController
public class CompanyController {
	
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
	}
	
}
