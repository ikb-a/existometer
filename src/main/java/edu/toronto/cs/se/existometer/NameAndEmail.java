package edu.toronto.cs.se.existometer;

public final class NameAndEmail {

	private final String name;
	private final String email;
	
	public NameAndEmail(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

}