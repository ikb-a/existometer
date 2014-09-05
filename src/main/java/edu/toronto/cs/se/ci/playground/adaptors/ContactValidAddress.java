package edu.toronto.cs.se.ci.playground.adaptors;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.playground.contracts.AddressValidate;
import edu.toronto.cs.se.ci.playground.contracts.ContactValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.data.Contact;
import edu.toronto.cs.se.ci.playground.data.ContactTrust;
import edu.toronto.cs.se.ci.utils.BasicAdaptor;

public class ContactValidAddress extends BasicAdaptor<Contact, Boolean, ContactTrust, Address, Boolean, Double> implements ContactValidate {

	public ContactValidAddress() {
		super(AddressValidate.class);
	}

	@Override
	public Address transformArgs(Contact args) {
		return args.getAddress();
	}

	@Override
	public Boolean transformResult(Boolean result) {
		return result;
	}

	@Override
	public ContactTrust transformTrust(Double trust, Optional<Boolean> result,
			Optional<Boolean> originalResult) {
		// double conf = result.or(true) ? trust * 0.5 : trust;
		return new ContactTrust(
			0,     // name
			0,     // email
			trust, // address
			0,     // name-email
			0,     // email-address
			0      // name-address
		);
	}

}
