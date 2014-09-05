package edu.toronto.cs.se.ci.playground.data;

public class Address {
	
	private final String streetNumber;
	private final String route;
	private final String city;
	private final String provence;
	private final String country;
	private final String postalCode;
	
	public Address(String streetNumber, String route, String city,
			String provence, String country, String postalCode) {
		this.streetNumber = streetNumber;
		this.route = route;
		this.city = city;
		this.provence = provence;
		this.country = country;
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getRoute() {
		return route;
	}

	public String getCity() {
		return city;
	}

	public String getProvence() {
		return provence;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

}
