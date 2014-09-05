package edu.toronto.cs.se.ci.playground.adaptors;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.contracts.Cooccur;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.utils.BasicAdaptor;

public class NameCityCooccur extends BasicAdaptor<Contact, Boolean, ContactTrust, List<String>, Boolean, Double> implements ContactValidate {

	public NameCityCooccur() {
		super(Cooccur.class);
	}

	@Override
	public List<String> transformArgs(Contact args) {
		return ImmutableList.of(args.getName(), args.getAddress().getCity());
	}

	@Override
	public Boolean transformResult(Boolean result) {
		return result;
	}

	@Override
	public ContactTrust transformTrust(Double trust, Optional<Boolean> result,
			Optional<Boolean> originalResult) {
		return new ContactTrust(
			0,    // name
			0,    // email
			0,    // address
			0,    // name-email
			0,    // email-address
			trust // name-address
		);
	}

}
