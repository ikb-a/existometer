package edu.toronto.cs.se.ci.playground.sources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.playground.contracts.AddressValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class NonEmptyAddressCheck extends BasicSource<Address, Boolean, Double> implements AddressValidate {

	@Override
	public Boolean getResponse(Address input) throws UnknownException {
		return input.getCity().trim().length() > 0
			&& input.getCountry().trim().length() > 0
			&& input.getPostalCode().trim().length() > 0
			&& input.getProvence().trim().length() > 0
			&& input.getRoute().trim().length() > 0
			&& input.getStreetNumber().trim().length() > 0;
	}

	@Override
	public Expenditure[] getCost(Address args) throws Exception {
		return new Expenditure[] {};
	}

	@Override
	public Double getTrust(Address args, Optional<Boolean> value) {
		return value.or(true) ? 0.3 : 1;
	}
	
}
