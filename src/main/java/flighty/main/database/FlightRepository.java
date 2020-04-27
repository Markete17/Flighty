package flighty.main.database;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	// Get flights with the given airports name and the date
	List<Flight> findByOriginAndDestAndDate(String origin,String dest,Date date);

}
