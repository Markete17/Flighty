package flighty.main.database;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	private String code; // Code of the flight: code of the company + 4 digits
	private String company; // Code of the company: IATA code
	private String origin; // Origin airport
	private String dest; // Destination airport
	private Date date; // Flight departure date
	private int hour; // Flight departure time (hour)
	private int minutes; // Flight departure time (minutes)
	private int flightTime; // Duration of the flight
	private int price; // Cost of the flight
	
	public Flight() {}

	public Flight(String code, String company, String origin, String dest, Date date, int hour, int minutes, int flightTime, int price) {
		super();
		this.code = code;
		this.company = company;
		this.origin = origin;
		this.dest = dest;
		this.date = date;
		this.hour = hour;
		this.minutes = minutes;
		this.flightTime = flightTime;
		this.price = price;
	}

	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDest() {
		return dest;
	}
	
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
