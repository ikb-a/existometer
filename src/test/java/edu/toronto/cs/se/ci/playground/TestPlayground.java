package edu.toronto.cs.se.ci.playground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.math.DoubleMath;

import edu.toronto.cs.se.ci.CI;
import edu.toronto.cs.se.ci.Estimate;
import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Allowance;
import edu.toronto.cs.se.ci.data.Result;
import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.selectors.AllSelector;
import junit.framework.TestCase;

public class TestPlayground extends TestCase {
	
	private CI<Contact, Boolean, ContactTrust, Double> ci;
	private Allowance[] budget = new Allowance[] {
		
	};
	
	protected void setUp() {
		App.registerSources();
    	ci = new CI<>(ContactValidate.class, new ContactValidAggregator(), new AllSelector<Contact, Boolean, ContactTrust>());
	}
	
	private Contact[] validContacts = {
		new Contact("Michael Layzell", "michael@thelayzells.com",
				new Address("2603", "7th ave NW", "Calgary", "AB", "Canada", "T2N 1A6")),
		new Contact("Rebecca Todesco", "rebeccatod@gmail.com",
				new Address("1915", "10th ave NW", "Calgary", "AB", "Canada", "T2N 1G4"))
	};

	public void testValid() throws InterruptedException, ExecutionException {
		List<Double> good = new ArrayList<>();
		List<Double> bad = new ArrayList<>();
		List<Estimate<Boolean, Double>> estimates = new ArrayList<>();
		
		for (Contact contact : validContacts) {
			estimates.add(ci.apply(contact, budget));
		}
		
		for (Estimate<Boolean, Double> estimate : estimates) {
			try {
				Result<Boolean, Double> res = estimate.get();
				if (res.getValue())
					good.add(res.getQuality());
				else
					bad.add(res.getQuality());
			} catch (ExecutionException e) {
				if (e.getCause() instanceof UnknownException)
					bad.add(0.0);
				else
					throw e;
			}
		}
		
		System.out.println("True Positives: " + good.size());
		if (good.size() > 0) {
			System.out.println("  Mean Quality: " + DoubleMath.mean(good));
			System.out.println("  Min Quality: " + Collections.min(good));
			System.out.println("  Max Quality: " + Collections.max(good));
		}

		System.out.println("False Negatives: " + bad.size());
		if (bad.size() > 0) {
			System.out.println("  Mean Quality: " + DoubleMath.mean(bad));
			System.out.println("  Min Quality: " + Collections.min(bad));
			System.out.println("  Max Quality: " + Collections.max(bad));
		}
	}
	
	private Contact[] invalidContacts = {
		new Contact("Santa Claus", "santa@thenorthpole.com",
				new Address("0", "Reindeer Road", "North Pole", "NWT", "Canada", "H0H 0H0")),
		new Contact("Michael Layzell", "bogus@bogusland.com",
				new Address("2603", "7th ave NW", "Calgary", "AB", "Canada", "T2N 1A6"))
	};

	public void testInvalid() throws InterruptedException, ExecutionException {
		List<Double> good = new ArrayList<>();
		List<Double> bad = new ArrayList<>();
		List<Estimate<Boolean, Double>> estimates = new ArrayList<>();
		
		for (Contact contact : invalidContacts) {
			estimates.add(ci.apply(contact, budget));
		}
		
		for (Estimate<Boolean, Double> estimate : estimates) {
			try {
				Result<Boolean, Double> res = estimate.get();
				if (! res.getValue())
					good.add(res.getQuality());
				else
					bad.add(res.getQuality());
			} catch (ExecutionException e) {
				if (e.getCause() instanceof UnknownException)
					bad.add(0.0);
				else
					throw e;
			}
		}
		
		System.out.println("True Negatives: " + good.size());
		if (good.size() > 0) {
			System.out.println("  Mean Quality: " + DoubleMath.mean(good));
			System.out.println("  Min Quality: " + Collections.min(good));
			System.out.println("  Max Quality: " + Collections.max(good));
		}

		System.out.println("False Positives: " + bad.size());
		if (bad.size() > 0) {
			System.out.println("  Mean Quality: " + DoubleMath.mean(bad));
			System.out.println("  Min Quality: " + Collections.min(bad));
			System.out.println("  Max Quality: " + Collections.max(bad));
		}
		
	}
}
