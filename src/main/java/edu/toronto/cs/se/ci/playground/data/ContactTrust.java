package edu.toronto.cs.se.ci.playground.data;

public class ContactTrust {
	
	private final double name;
	private final double email;
	private final double address;

	private final double nameEmail;
	private final double emailAddress;
	private final double nameAddress;

	public ContactTrust(double name, double email, double address, double nameEmail, double emailAddress, double nameAddress) {
		this.name = name;
		this.email = email;
		this.address = address;
		
		this.nameEmail = nameEmail;
		this.emailAddress = emailAddress;
		this.nameAddress = nameAddress;
	}
	
	public double getName() {
		return name;
	}

	public double getEmail() {
		return email;
	}

	public double getAddress() {
		return address;
	}

	public double getNameEmail() {
		return nameEmail;
	}

	public double getEmailAddress() {
		return emailAddress;
	}

	public double getNameAddress() {
		return nameAddress;
	}

}
