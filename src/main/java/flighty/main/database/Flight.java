package flighty.main.database;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Flight {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private String code; // par de letras de la compañia aerea + 4 digitos
	private Airport origin;
	private Airport dest;
	private Date date;
	private int hour;
	private int minutes;
	private int flightTime;
	private int price;
	private boolean complete; //ida y vuelta
	
	@OneToOne
	private Flight flight;
	
	@OneToOne
	private Company company;
	
	public Flight(String code,Airport origin,Airport dest, Date date, int hour, int minutes, int flightTime, int price,boolean complete) {
		super();
		this.code = code;
		this.origin=origin;
		this.dest=dest;
		this.date = date;
		this.complete = complete;
		this.hour = hour;
		this.minutes = minutes;
		this.flightTime = flightTime;
		this.price = price;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Flight getFlight() {
		return flight;
	}



	public void setFlight(Flight flight) {
		this.flight = flight;
	}



	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
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
