package edu.toronto.cs.se.ci.playground.sources;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.playground.contracts.AddressValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class PostalCodeFormatCheck extends BasicSource<Address, Boolean, Double> implements AddressValidate {

	@Override
	public Boolean getResponse(Address input) throws UnknownException {
		return input.getPostalCode().matches("^[a-zA-Z][0-9][a-zA-Z] ?[0-9][a-zA-Z][0-9]$");
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
