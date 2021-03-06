package flighty.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AirportRepository extends JpaRepository<Airport, Long> {
	
	// Select the name of the airports
	@Query("select name from Airport")
	public List<String> airportList();

}
