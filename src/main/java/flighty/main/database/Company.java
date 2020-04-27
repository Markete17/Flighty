package flighty.main.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private String code; // Code of the company (IATA code)
	private String name; // Name of the company
	private String linkPage; // Link to the official web site of the company
	private String phone; // Customer support Phone
	private double rating; // Global rating of the users (value: 1-5)
	
	public Company() {}
	
	public Company(String code, String name, String linkPage, String phone, double rating) {
		super();
		this.code = code;
		this.name = name;
		this.linkPage = linkPage;
		this.phone = phone;
		this.rating = rating;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkPage() {
		return linkPage;
	}

	public void setLinkPage(String linkPage) {
		this.linkPage = linkPage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
