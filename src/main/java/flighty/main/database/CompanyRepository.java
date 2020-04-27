package flighty.main.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	// Get the company with the given code
	Company findByCode(String code);
	
}
