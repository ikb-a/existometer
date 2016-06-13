package edu.toronto.cs.se.ci.playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import edu.toronto.cs.se.ci.CI;
import edu.toronto.cs.se.ci.Contracts;
import edu.toronto.cs.se.ci.Estimate;
import edu.toronto.cs.se.ci.budget.Allowance;
import edu.toronto.cs.se.ci.budget.basic.Dollars;
import edu.toronto.cs.se.ci.budget.basic.Time;
import edu.toronto.cs.se.ci.data.Result;
import edu.toronto.cs.se.ci.playground.adaptors.ContactValidAddress;
import edu.toronto.cs.se.ci.playground.adaptors.GMapsAddressExists;
import edu.toronto.cs.se.ci.playground.adaptors.NameCityCooccur;
import edu.toronto.cs.se.ci.playground.adaptors.NameEmailCooccur;
import edu.toronto.cs.se.ci.playground.adaptors.SearchCoccur;
import edu.toronto.cs.se.ci.playground.adaptors.WhitePagesAddressName;
import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.playground.sources.BingHits;
import edu.toronto.cs.se.ci.playground.sources.GoogleHits;
import edu.toronto.cs.se.ci.playground.sources.NameEmailOnGithub;
import edu.toronto.cs.se.ci.playground.sources.NonEmptyAddressCheck;
import edu.toronto.cs.se.ci.playground.sources.PostalCodeFormatCheck;
import edu.toronto.cs.se.ci.selectors.AllSelector;

/**
 * Hello world!
 *
 */
public class App {

	public static void registerSources() {
		Contracts.register(new ContactValidAddress());
		Contracts.register(new NameCityCooccur());
		Contracts.register(new NameEmailCooccur());
		Contracts.register(new SearchCoccur());
		Contracts.register(new BingHits());
		Contracts.register(new GMapsAddressExists());
		Contracts.register(new GoogleHits());
		Contracts.register(new NonEmptyAddressCheck());
		Contracts.register(new PostalCodeFormatCheck());
		Contracts.register(new NameEmailOnGithub());
		Contracts.register(new WhitePagesAddressName());
	}

	public static Contact readContact() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your name: ");
		String name = br.readLine();
		System.out.print("Enter your email: ");
		String email = br.readLine();
		System.out.print("Enter your street number: ");
		String number = br.readLine();
		System.out.print("Enter your street name: ");
		String route = br.readLine();
		System.out.print("Enter your postal code: ");
		String postalCode = br.readLine();
		System.out.print("Enter your city: ");
		String city = br.readLine();
		System.out.print("Enter your province: ");
		String provence = br.readLine();

		Address address = new Address(number, route, city, provence, "Canada",
				postalCode);
		Contact contact = new Contact(name, email, address);

		return contact;
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException, IOException {
		registerSources();

		Allowance[] budget = new Allowance[] { new Dollars(BigDecimal.ONE),
				new Time(4, TimeUnit.SECONDS) };
		Contact contact = readContact();

		/*
		 * The ContactValidate class is used to identify sources which validate contacts.
		 */
		CI<Contact, Boolean, ContactTrust, Double> myCI = new CI<>(
				ContactValidate.class, new ContactValidAggregator(),
				new AllSelector<Contact, Boolean, ContactTrust>());

		Estimate<Boolean, Double> estimate = myCI.apply(contact, budget);

		Result<Boolean, Double> res = estimate.get();

		System.out.println(res.getValue());
		System.out.println(res.getQuality());
	}
}
