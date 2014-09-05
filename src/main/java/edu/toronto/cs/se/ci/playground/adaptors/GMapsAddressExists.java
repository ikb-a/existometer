package edu.toronto.cs.se.ci.playground.adaptors;

import org.json.JSONObject;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.playground.contracts.AddressValidate;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.playground.sources.GMapsGeocode;
import edu.toronto.cs.se.ci.utils.BasicAdaptor;

public class GMapsAddressExists extends BasicAdaptor<Address, Boolean, Double, Address, JSONObject, Void> implements AddressValidate {

	public GMapsAddressExists() {
		super(new GMapsGeocode());
	}

	@Override
	public Address transformArgs(Address args) {
		return args;
	}

	@Override
	public Boolean transformResult(JSONObject result) {
		return result.getString("status").equals("OK");
	}

	@Override
	public Double transformTrust(Void trust, Optional<Boolean> result,
			Optional<JSONObject> originalResult) {
		return result.or(true) ? 0.7 : 0.9;
	}

}
