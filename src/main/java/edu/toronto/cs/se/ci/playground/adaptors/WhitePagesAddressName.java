package edu.toronto.cs.se.ci.playground.adaptors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.Adaptor;
import edu.toronto.cs.se.ci.Source;
import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.playground.sources.WhitePagesReverseAddress;

public class WhitePagesAddressName extends Adaptor<Contact, Boolean, ContactTrust, Address, List<String>, Void> implements ContactValidate {

	public WhitePagesAddressName() {
		super(new WhitePagesReverseAddress());
	}

	private double similarity(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if (s1.equals(s2)) return 1;
		
		Set<String> pairs1 = new HashSet<>();
		Set<String> pairs2 = new HashSet<>();
		
		for (int i=1; i<s1.length(); i++)
			pairs1.add(s1.substring(i-1, i+1));

		for (int i=1; i<s2.length(); i++)
			pairs2.add(s2.substring(i-1, i+1));
		
		Set<String> intersection = new HashSet<>(pairs1);
		intersection.retainAll(pairs2);
		
		return (2.0 * intersection.size()) / (pairs1.size() + pairs2.size());
	}

	@Override
	public Expenditure[] getCost(Contact args,
			Source<Address, List<String>, Void> around) throws Exception {
		return around.getCost(args.getAddress());
	}

	@Override
	public Opinion<Boolean, ContactTrust> getOpinion(Contact args,
			Source<Address, List<String>, Void> around) throws UnknownException {
		List<String> names = around.getOpinion(args.getAddress()).getValue();
		double best = 0;
		
		for (String name : names) {
			double sim = similarity(name, args.getName());
			if (sim > best) best = sim;
		}
		
		boolean result = best > 0.5;
		double conf = result ? best : (1 - best);
		
		return new Opinion<Boolean, ContactTrust>(result, new ContactTrust(
			result ? conf : 0,
			0,
			result ? conf : 0,
			0,
			0,
			conf
		), around.getName());
	}

	@Override
	public ContactTrust getTrust(Contact args, Optional<Boolean> value,
			Source<Address, List<String>, Void> around) {
		return new ContactTrust(0, 0, 0, 0, 0, 0.5); // Woo!
	}

}
