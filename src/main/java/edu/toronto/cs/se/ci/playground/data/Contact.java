package edu.toronto.cs.se.ci.playground.data;

public class Contact {
	
	private final String name;
	private final String email;
	private final Address address;

	public Contact(String name, String email, Address address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

}
