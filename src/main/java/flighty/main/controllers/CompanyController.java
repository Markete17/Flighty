package flighty.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flighty.main.database.Company;
import flighty.main.service.CompanyService;


@RestController
public class CompanyController {
		
	@Autowired
	private CompanyService companyService;
	
	/**
	 * @param code (code of the company)
	 * @return company
	 */
	@GetMapping("/get_company")
	public ResponseEntity<Company> getCompany(@RequestParam String code) {
		Company company = companyService.findCompany(code);
		return new ResponseEntity<Company>(company,HttpStatus.OK);
	}
	
}
